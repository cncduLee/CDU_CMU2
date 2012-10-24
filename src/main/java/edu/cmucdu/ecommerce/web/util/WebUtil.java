package edu.cmucdu.ecommerce.web.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.support.RequestContextUtils;

import edu.cmucdu.ecommerce.domain.util.LocaleEnum;

/**


 * web xxxx123abc


 * @author Administrator
 *
 */
public class WebUtil {
	public static LocaleEnum getLocaleEnum(HttpServletRequest httpServletRequest){
		Locale locale = RequestContextUtils.getLocale(httpServletRequest);
		
		if(locale.getDisplayLanguage()=="Thai")
			return LocaleEnum.THAI;
		else if(locale.getDisplayLanguage()=="cn")
			return LocaleEnum.CHINESE;
		else
			return LocaleEnum.ENGLISH;
	}
}
