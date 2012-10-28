package edu.cmucdu.ecommerce.domain.product;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

import edu.cmucdu.ecommerce.domain.util.Description;
import edu.cmucdu.ecommerce.domain.util.LocaleEnum;

@RooJavaBean
@RooToString
@RooJpaEntity
public class ProductPic {

  
	@NotNull
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;
    
    private String imageType;
    
    private String url;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Description description;
    
    @ManyToOne
    private Product product;
    
    @ManyToOne
    private SellerProduct sellerProduct;
    
    @Transient
	LocaleEnum locale = LocaleEnum.CHINESE;
    
    public String getLocalDescription() {
		return description.getLocalDescription();
	}
    
}
