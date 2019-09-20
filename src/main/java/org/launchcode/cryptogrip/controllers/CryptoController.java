package org.launchcode.cryptogrip.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.launchcode.cryptogrip.models.Crypto;
import org.launchcode.cryptogrip.models.data.CryptoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CryptoController {

    private static final String apiKey = "aea6365d-04ca-4e27-9203-12cb64d0739b";

    @Autowired
    private CryptoDao cryptoDao;

    @RequestMapping(value = "")
    public String index(Model model) throws IOException, URISyntaxException {
        populateCryptoTable();
        model.addAttribute("cryptos", cryptoDao.findAll());
        return "index";
    }

    public void populateCryptoTable() throws IOException, URISyntaxException {

        // Generate API call to get 200 top cryptocurrencies
        String uri = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("limit","200"));

        // Retrieve JSON from cmcAPI
        String stringJsonResponse = makeAPICall(uri, parameters);

        // Parse JSON data into Crypto objects
        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree(stringJsonResponse);

        JsonNode dataNode = root.path("data");

        for (JsonNode node : dataNode) {
            // Get base level data
            int id = node.path("id").asInt();
            String name = node.path("name").asText();
            int cmcRank = node.path("cmc_rank").asInt();
            String symbol = node.path("symbol").asText();

            // Get data from the nested level: quote/USD/
            JsonNode usdNode = node.path("quote").path("USD");
            double price = usdNode.path("price").asDouble();
            double marketCap = usdNode.path("market_cap").asDouble();

            Crypto crypto = new Crypto(id,name,symbol,price,marketCap,cmcRank);

            cryptoDao.save(crypto);
        }

    }

    public String makeAPICall(String uri, List<NameValuePair> parameters)
            throws URISyntaxException, IOException {
        String response_content = "";

        URIBuilder query = new URIBuilder(uri);
        query.addParameters(parameters);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(query.build());

        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        request.addHeader("X-CMC_PRO_API_KEY", apiKey);

        CloseableHttpResponse response = client.execute(request);

        try {
            HttpEntity entity = response.getEntity();
            response_content = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }

        return response_content;
    }

}
