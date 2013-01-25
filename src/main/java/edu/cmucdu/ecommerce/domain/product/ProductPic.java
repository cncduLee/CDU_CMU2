package edu.cmucdu.ecommerce.domain.product;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import javax.activation.MimetypesFileTypeMap;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.apache.commons.io.IOUtils;
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
    
    public void loadFile(String uri){
    	
    	
    	try {
    	
    		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(uri);
    	
			image = IOUtils.toByteArray(is);
			MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
			imageType = mimeTypesMap.getContentType(uri);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
