package edu.cmucdu.ecommerce.domain.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

import edu.cmucdu.ecommerce.domain.product.ProductPic;
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
	@OneToOne(cascade = CascadeType.ALL)
	private Principal principle;

	@Transient
	LocaleEnum locale = LocaleEnum.CHINESE;
	 @OneToMany(cascade = CascadeType.ALL)
	    private List<ProductPic> images = new ArrayList<ProductPic>();
	@OneToOne(cascade = CascadeType.ALL)
	Description name;
	@OneToOne(cascade = CascadeType.ALL)
	Description description;

	public String getLocalName() {
		return name.getLocalDescription();
	}

	public String getLocalDescription() {
		return description.getLocalDescription();
	}
	
	public String telephoneNo;
	
	@OneToOne(cascade = CascadeType.ALL)
	Description address;
	
	public String getLocalAddress()
	{
		if(address==null)
			return "No address";
		else
			return address.getLocalDescription();
	}
	
	
}
