package org.launchcode.cryptogrip.controllers;

import org.launchcode.cryptogrip.models.data.PortfolioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PortfolioController {

    @Autowired
    private PortfolioDao portfolioDao;

    @RequestMapping(value = "portfolio")
    public String portfolio(Model model) {



        return "";
    }

}
