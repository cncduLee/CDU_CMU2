package edu.cmucdu.ecommerce.domain.product;

import edu.cmucdu.ecommerce.domain.user.Seller;
import edu.cmucdu.ecommerce.domain.util.Description;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class SellerProduct {

    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sellerProduct",fetch=FetchType.EAGER)
    private Set<ProductPic> images = new HashSet<ProductPic>();

    @ManyToOne
    private Seller seller;

    private double price;

    private double weight;

    @OneToOne
    private Promotion promotion;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date durationOfFruit;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date readyTime;

    @ManyToOne
    private Description brandName;
}
