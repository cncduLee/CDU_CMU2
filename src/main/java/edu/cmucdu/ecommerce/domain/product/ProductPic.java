package edu.cmucdu.ecommerce.domain.product;

import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.classpath.operations.jsr303.RooUploadedFile;

import edu.cmucdu.ecommerce.domain.util.Description;

@RooJavaBean
@RooToString
@RooJpaEntity
public class ProductPic {

  
    @Lob
    private byte[] image;
    
    private String imageType;
    
    @OneToOne
    private Description description;
    
    @ManyToOne
    private Product product;
    
    @ManyToOne
    private SellerProduct sellerProduct;
    
}
