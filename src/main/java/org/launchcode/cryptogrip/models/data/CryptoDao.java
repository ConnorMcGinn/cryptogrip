package org.launchcode.cryptogrip.models.data;

import org.launchcode.cryptogrip.models.Crypto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CryptoDao extends CrudRepository<Crypto, Integer> {
}
