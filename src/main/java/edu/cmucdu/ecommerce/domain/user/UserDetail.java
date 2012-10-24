package edu.cmucdu.ecommerce.domain.user;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

import edu.cmucdu.ecommerce.domain.user.security.Principal;
import edu.cmucdu.ecommerce.domain.util.Description;
import edu.cmucdu.ecommerce.domain.util.LocaleEnum;

@RooJavaBean
@RooToString
@RooJpaEntity(inheritanceType = "TABLE_PER_CLASS")
public abstract class UserDetail {
	/**
	 * the username and password of the user
	 */
	@OneToOne (cascade=CascadeType.ALL)
	private Principal principle; 
	

	@Transient
	LocaleEnum locale =LocaleEnum.CHINESE;

	@OneToOne(cascade=CascadeType.ALL)
	Description name;
	@OneToOne(cascade=CascadeType.ALL)
	Description description;
	
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
