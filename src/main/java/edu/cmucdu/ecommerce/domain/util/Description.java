package edu.cmucdu.ecommerce.domain.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import edu.cmucdu.ecommerce.web.util.WebUtil;

@RooJavaBean
@RooToString
@RooJpaEntity
public class Description {
	String thaiDesc;
	String chineseDesc;
	String englishDesc;

	public Description() {

	}

	public Description(String thaiDesc, String chineseDesc, String englishDesc) {
		super();
		this.thaiDesc = thaiDesc;
		this.chineseDesc = chineseDesc;
		this.englishDesc = englishDesc;
	}

	public String getLocalDescription() {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
		.getRequestAttributes()).getRequest();
		if (request == null) {
			return toString();
		}
		LocaleEnum locale = WebUtil.getLocaleEnum(request);
		if (locale == LocaleEnum.CHINESE) {
			return chineseDesc;
		} else if (locale == LocaleEnum.THAI) {
			return thaiDesc;
		} else {
			return englishDesc;
		}
	}
}
