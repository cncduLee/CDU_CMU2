package edu.cmucdu.ecommerce.domain.user;

import edu.cmucdu.ecommerce.domain.product.Product;
import java.util.Date;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity
public class BuyerRequest {

    private Double amount;

    @OneToOne
    private Buyer buyer;

    @ManyToOne
    private Product product;

    private Double money;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date buyingTime;
}
