package edu.cmucdu.ecommerce.dao.logistic;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import edu.cmucdu.ecommerce.domain.logistic.PortTrip;

@RooJpaRepository(domainType = edu.cmucdu.ecommerce.domain.logistic.Logistic.class)
public interface LogisticDao {

}
