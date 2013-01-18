package edu.cmucdu.ecommerce.dao.product.comment;

import edu.cmucdu.ecommerce.domain.product.comment.Comment;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Comment.class)
public interface CommentDao {
}
