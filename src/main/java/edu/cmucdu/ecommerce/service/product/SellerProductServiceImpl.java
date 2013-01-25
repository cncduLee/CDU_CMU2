package edu.cmucdu.ecommerce.service.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cmucdu.ecommerce.dao.product.SellerProductDao;
import edu.cmucdu.ecommerce.domain.product.SellerProduct;

@Service
public class SellerProductServiceImpl implements SellerProductService {

	@Autowired
	SellerProductDao sellerProductDao;
	
	@Override
	public List<SellerProduct> getSellerProductFromProductID(long id){
		
		List<SellerProduct> allsp = sellerProductDao.findAll();
		List<SellerProduct> sps = new ArrayList<SellerProduct>();
		
		for(SellerProduct sp:allsp)
		{
			if(sp.getProduct().getId() == id)
			{
				sps.add(sp);
			}
		}
		return sps;
	}
	
	@Override
	public List<SellerProduct> getSellerProductFromSeller(long id){
		
		List<SellerProduct> allsp = sellerProductDao.findAll();
		List<SellerProduct> sps = new ArrayList<SellerProduct>();
		
		for(SellerProduct sp:allsp)
		{
			if(sp.getSeller().getId() == id)
			{
				sps.add(sp);
			}
		}
		return sps;
	}
	
	@Override
	public SellerProduct getSellerProductFromSellerAndProductID(long productId, long sellerId){
		
		List<SellerProduct> allsp = sellerProductDao.findAll();
		
		for(SellerProduct sp:allsp)
		{
			if(sp.getSeller().getId() == sellerId && sp.getProduct().getId() == productId)
			{
				return sp;
			}
		}
		
		return null;
	}
	
}
