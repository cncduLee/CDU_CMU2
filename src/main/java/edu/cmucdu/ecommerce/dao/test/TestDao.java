package edu.cmucdu.ecommerce.dao.test;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.stereotype.Repository;

import edu.cmucdu.ecommerce.domain.product.Product;
import edu.cmucdu.ecommerce.domain.user.security.Authority;

@RooJpaRepository(domainType = Authority.class)
@Repository
public class TestDao {
	
	@PersistenceContext
    private EntityManager entityManager;
	
//	@SuppressWarnings("unchecked")
//	public Iterator<Object[]> getProductFromId(String productId)
//	{
//		StringBuilder queryString = new StringBuilder("SELECT * FROM product WHERE id = '"+productId+"'");
//		System.out.println(queryString);
//		Query query = entityManager.createNativeQuery(queryString.toString());
//		
//		List<Object[]> result = query.getResultList();
//		
//		return result.iterator();
//	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getProductFromId(String productId)
	{
		StringBuilder queryString = new StringBuilder("SELECT p FROM product p WHERE p.id = '"+productId+"'");
		System.out.println(queryString);
		Query query = entityManager.createQuery(queryString.toString());
		
		List<Product> result = query.getResultList();
		
		return result;
	}

}
