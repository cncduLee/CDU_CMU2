package edu.cmucdu.ecommerce.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cmucdu.ecommerce.dao.product.ProductDao;
import edu.cmucdu.ecommerce.dao.product.ProductPicDao;
import edu.cmucdu.ecommerce.domain.product.Product;
import edu.cmucdu.ecommerce.domain.product.ProductPic;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	ProductPicDao productPicDao;
	
	@Override
	public List<Product> getProductRandomly(int amount) {
		
		List<Product> productList = productDao.findAll();
		List<Product> randomList = new ArrayList<Product>();
		
		Random rd = new Random();
		
		for(int i = 0; i < amount; i++)
		{
			int index = (int) (rd.nextDouble() * productList.size());
            randomList.add(productList.get(index));
            productList.remove(index);
		}
		
		return randomList;
	}
	
	@Override
	public List<ProductPic> getPictureFromID(long id) {
		List<ProductPic> pics = productPicDao.findAll();
		List<ProductPic> picsFromID = new ArrayList<ProductPic>();
		for(ProductPic pic:pics)
		{
			if(pic.getId() == id)
			{
				picsFromID.add(pic);
			}
		}
		return picsFromID;
	}
	
	@Override
	public Product getProductFromID(long id) {
		return productDao.findOne(id);
	}

	@Override
	public List<Product> getProductsByType(int type) {
		
		List<Product> all = new ArrayList<Product>();
		
		switch (type) {
		case 1:
			//get the all products
			all = productDao.findAll();
			break;
		case 2:
			//get the promotion(Hot sell) products
			break;
		case 3:
			//up coming(新品，即将上市)
			break;
		default:
			break;
		}
		return all;
	}

}
