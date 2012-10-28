package edu.cmucdu.ecommerce.domain.user;

import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import edu.cmucdu.ecommerce.domain.user.security.Principal;
import edu.cmucdu.ecommerce.domain.util.Description;
import edu.cmucdu.ecommerce.domain.util.LocaleEnum;
import edu.cmucdu.ecommerce.web.util.WebUtil;

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

}
