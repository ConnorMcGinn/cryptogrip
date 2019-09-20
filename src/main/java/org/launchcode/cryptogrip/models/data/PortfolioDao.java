package org.launchcode.cryptogrip.models.data;

import org.launchcode.cryptogrip.models.Portfolio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PortfolioDao extends CrudRepository<Portfolio, Integer> {
}
