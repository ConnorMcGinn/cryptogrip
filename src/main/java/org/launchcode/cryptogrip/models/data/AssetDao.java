package org.launchcode.cryptogrip.models.data;

import org.launchcode.cryptogrip.models.Asset;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AssetDao extends CrudRepository<Asset, Integer> {
}
