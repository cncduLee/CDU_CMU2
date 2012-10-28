package edu.cmucdu.ecommerce.domain.product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

import edu.cmucdu.ecommerce.domain.util.Description;
import edu.cmucdu.ecommerce.domain.util.LocaleEnum;

@RooJavaBean
@RooToString
@RooJpaEntity
public class Product {

    @OneToOne(cascade= CascadeType.ALL)
    private Description name;

    @OneToOne(cascade = CascadeType.ALL)
    private Description description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="product")

    private Set<SellerProduct> sellerProducts = new HashSet<SellerProduct>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy="product")
    private List<ProductPic> images = new ArrayList<ProductPic>();
	@Transient
	LocaleEnum locale =LocaleEnum.CHINESE;

	public void setLocale(LocaleEnum locale){
		this.locale = locale;
	}


	
	public String getLocalName(){
		if (locale == LocaleEnum.CHINESE){
			return name.getChineseDesc();
		}else if(locale == LocaleEnum.THAI ){
			return name.getThaiDesc();
		}else{
			return name.getEnglishDesc();
		}
	}
	
	public String getLocalDescription(){
		if (locale == LocaleEnum.CHINESE){
			return description.getChineseDesc();
		}else if(locale == LocaleEnum.THAI ){
			return description.getThaiDesc();
		}else{
			return description.getEnglishDesc();
		}
	}
	

}
