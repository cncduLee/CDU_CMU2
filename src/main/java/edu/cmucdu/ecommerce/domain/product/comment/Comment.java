package edu.cmucdu.ecommerce.domain.product.comment;

import edu.cmucdu.ecommerce.domain.user.UserDetail;
import edu.cmucdu.ecommerce.domain.util.LocaleEnum;

import java.util.Date;
import javax.persistence.ManyToOne;
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
public class Comment {

    @ManyToOne
    private UserDetail commenter;

    private String comment;

    @Transient
	LocaleEnum locale = LocaleEnum.CHINESE;
    
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date submitDate;
}
