package edu.cmucdu.ecommerce.domain.product;

import edu.cmucdu.ecommerce.domain.user.Seller;
import edu.cmucdu.ecommerce.domain.util.LocaleEnum;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sellerProduct")
    private List<ProductPic> images = new ArrayList<ProductPic>();


    @ManyToOne
    private Seller seller;

    private double price;

    private double weight;

    @OneToOne
    private Promotion promotion;
    
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date readyDate;
    
    @Transient
	LocaleEnum locale =LocaleEnum.CHINESE;

	public void setLocale(LocaleEnum locale){
		this.locale = locale;
	}

	public void setImages(List<ProductPic> images) {
        this.images = images;
        for (ProductPic productPic : images) {
			productPic.setSellerProduct(this);
		}
    }
}
