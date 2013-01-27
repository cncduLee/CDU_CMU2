package edu.cmucdu.ecommerce.domain.logistic;

import javax.persistence.ManyToOne;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity
public class Logistic {

    @ManyToOne
    private Trip trip;

    private Double distanceFromLastPortToDestination;

    private String timeFromLastPortToAddress;

    private Double costFromLastPortToAddress;

    private int unit;
}
