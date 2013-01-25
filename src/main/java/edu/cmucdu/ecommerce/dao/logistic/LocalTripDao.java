package edu.cmucdu.ecommerce.dao.logistic;

import edu.cmucdu.ecommerce.domain.logistic.LocalTrip;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = LocalTrip.class)
public interface LocalTripDao {
}
