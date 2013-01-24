package edu.cmucdu.ecommerce.domain.logistic;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity
public class PortTrip {

    private String sourcePort;

    private String destinationPort;
}
