package edu.cmucdu.ecommerce.dao.logistic;

import edu.cmucdu.ecommerce.domain.logistic.PortTrip;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = PortTrip.class)
public interface PortTripDao {
}
