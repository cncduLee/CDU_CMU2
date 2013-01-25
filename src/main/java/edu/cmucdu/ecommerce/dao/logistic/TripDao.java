package edu.cmucdu.ecommerce.dao.logistic;

import edu.cmucdu.ecommerce.domain.logistic.Trip;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Trip.class)
public interface TripDao {
}
