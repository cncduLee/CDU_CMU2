package edu.cmucdu.ecommerce.dao.logistic;

import java.util.List;

import edu.cmucdu.ecommerce.domain.logistic.PortTrip;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = PortTrip.class)
public interface PortTripDao {
	List<PortTrip> findBySourcePort(String sourceport);
	List<PortTrip> findByDestinationPort(String destinationPort);
	
}
