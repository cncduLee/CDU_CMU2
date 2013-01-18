package edu.cmucdu.ecommerce.dao.product;

import edu.cmucdu.ecommerce.domain.product.SearchHistory;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = SearchHistory.class)
public interface SearchHistoryDao {
}
