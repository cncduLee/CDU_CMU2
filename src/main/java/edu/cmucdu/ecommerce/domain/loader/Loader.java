package edu.cmucdu.ecommerce.domain.loader;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import edu.cmucdu.ecommerce.dao.product.ProductDao;
import edu.cmucdu.ecommerce.dao.product.ProductPicDao;
import edu.cmucdu.ecommerce.dao.security.AuthorityDao;
import edu.cmucdu.ecommerce.dao.security.AuthorityPrincipalAssignmentDao;
import edu.cmucdu.ecommerce.dao.user.BuyerDao;
import edu.cmucdu.ecommerce.dao.user.SellerDao;
import edu.cmucdu.ecommerce.domain.product.Product;
import edu.cmucdu.ecommerce.domain.product.ProductPic;
import edu.cmucdu.ecommerce.domain.product.SellerProduct;
import edu.cmucdu.ecommerce.domain.user.Buyer;
import edu.cmucdu.ecommerce.domain.user.Seller;
import edu.cmucdu.ecommerce.domain.user.security.Authority;
import edu.cmucdu.ecommerce.domain.user.security.AuthorityPrincipalAssignment;
import edu.cmucdu.ecommerce.domain.user.security.Principal;
import edu.cmucdu.ecommerce.domain.util.Description;

/**
 * Preload test data for in-mem db,<br>
 * <a href="http://forum.springsource.org/showthread.php?84312-How-to-store-sample-data/page2">acknowledgement: this technique</a>
 */
@Component
public class Loader implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	ProductDao productDao;
	@Autowired
	SellerDao sellerDao;
	@Autowired
	AuthorityDao authorityDao;
	@Autowired
	AuthorityPrincipalAssignmentDao authorityPrincipalAssignmentDao;
//	@Autowired
//	PromotionDao promotionDao;
	@Autowired
	BuyerDao buyerDao;
	@Autowired
	ProductPicDao productPicDao;
	
	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() != null)
			return;
		
		// ############## Create Seller ###############
		Seller s1 = new Seller();
		s1.setName(new Description(Messages.getString("Loader.0"), Messages.getString("Loader.1"), Messages.getString("Loader.2"))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		s1.setDescription(new Description(Messages.getString("Loader.3"), Messages.getString("Loader.4"), Messages.getString("Loader.5"))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			//Set principle
			Principal s1p = new Principal();
			s1p.setUser(s1);
			s1p.setEnabled(true);
			s1p.setUsername("seller1"); //$NON-NLS-1$
			s1p.setPassword(("1234")); //$NON-NLS-1$
		s1.setPrinciple(s1p);
		s1.setAddress(new Description(Messages.getString("Loader.40"), Messages.getString("Loader.41"), Messages.getString("Loader.42")));
		s1.setTelephoneNo("+6653123456");
		sellerDao.save(s1);

		Seller s2 = new Seller();
		s2.setName(new Description(Messages.getString("Loader.8"), Messages.getString("Loader.9"), Messages.getString("Loader.10"))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		s2.setDescription(new Description(Messages.getString("Loader.11"), Messages.getString("Loader.12"), Messages.getString("Loader.13"))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			//Set principle
			Principal s2p = new Principal();
			s2p.setUser(s2);
			s2p.setEnabled(false);
			s2p.setUsername("seller2"); //$NON-NLS-1$
			s2p.setPassword("1234"); //$NON-NLS-1$
		s2.setPrinciple(s2p);
		s2.setAddress(new Description(Messages.getString("Loader.40"), Messages.getString("Loader.41"), Messages.getString("Loader.42")));
		s2.setTelephoneNo("+6653666666");
		sellerDao.save(s2);
		
		// ############### Create Product ################
		Product p1 = new Product();
		p1.setName(new Description(Messages.getString("Loader.16"), Messages.getString("Loader.17"), Messages.getString("Loader.18"))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		p1.setDescription(new Description(Messages.getString("Loader.16"), Messages.getString("Loader.17"), Messages.getString("Loader.18"))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			//Add seller list
			Set<SellerProduct> sellerlist = new HashSet<SellerProduct>();
			p1.setSellerProducts(sellerlist);
				//Create Seller Product
				SellerProduct sp1 = new SellerProduct();
				sp1.setSeller(s1);
				sp1.setProduct(p1);
				sp1.setPrice(100);
				sp1.setWeight(100);
				SellerProduct sp2 = new SellerProduct();
				sp2.setSeller(s2);
				sp2.setProduct(p1);
				sp2.setPrice(200);
				sp2.setWeight(1000);
			sellerlist.add(sp1);
			sellerlist.add(sp2);
			//Add product picture
			ProductPic pp = new ProductPic();
			pp.loadFile("/images/fruits/longan.jpg");
			pp.setProduct(p1);
			pp.setSellerProduct(sp1);
			pp.setDescription(new Description(Messages.getString("Loader.31"), Messages.getString("Loader.32"), Messages.getString("Loader.33")));
			productPicDao.save(pp);
		p1.getImages().add(pp);
		productDao.save(p1);
		Product p2 = new Product();
		p2.setName(new Description(Messages.getString("Loader.22"), Messages.getString("Loader.23"), Messages.getString("Loader.24"))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		p2.setDescription(new Description(Messages.getString("Loader.22"), Messages.getString("Loader.23"), Messages.getString("Loader.24"))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		//Add seller list
			Set<SellerProduct> sellerlist2 = new HashSet<SellerProduct>();
		p2.setSellerProducts(sellerlist2);
			//Create Seller Product
			SellerProduct sp3 = new SellerProduct();
			sp3.setSeller(s2);
			sp3.setProduct(p2);
			sp3.setPrice(400);
			sp3.setWeight(100);
			sellerlist2.add(sp3);
			//Add product picture
			ProductPic pp2 = new ProductPic();
			pp2.loadFile("/images/fruits/durian.jpg");
			pp2.setProduct(p2);
			pp2.setSellerProduct(sp3);
			pp2.setDescription(new Description(Messages.getString("Loader.49"), Messages.getString("Loader.50"), Messages.getString("Loader.51")));
			productPicDao.save(pp2);
		p2.getImages().add(pp2);
		productDao.save(p2);
		Product p3 = new Product();
		p3.setName(new Description(Messages.getString("Loader.28"), Messages.getString("Loader.29"), Messages.getString("Loader.30"))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		p3.setDescription(new Description(Messages.getString("Loader.28"), Messages.getString("Loader.29"), Messages.getString("Loader.30"))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		Set<SellerProduct> sellerlist3 = new HashSet<SellerProduct>();
		p3.setSellerProducts(sellerlist3);
			//Create Seller Product
			SellerProduct sp4 = new SellerProduct();
			sp4.setSeller(s2);
			sp4.setProduct(p3);
			sp4.setPrice(10);
			sp4.setWeight(10);
			sellerlist3.add(sp4);
			//Add product picture
			ProductPic pp3 = new ProductPic();
			pp3.loadFile("/images/fruits/mangosteen.jpg");
			pp3.setProduct(p3);
			pp3.setSellerProduct(sp4);
			pp3.setDescription(new Description(Messages.getString("Loader.52"), Messages.getString("Loader.53"), Messages.getString("Loader.54")));
			productPicDao.save(pp3);
		p3.getImages().add(pp3);
		productDao.save(p3);
		
		// ########## Create Buyer ############
		Buyer b1 = new Buyer();
		b1.setName(new Description(Messages.getString("Loader.37"), Messages.getString("Loader.38"), Messages.getString("Loader.39")));
		b1.setAddress(new Description(Messages.getString("Loader.40"), Messages.getString("Loader.41"), Messages.getString("Loader.42")));
		b1.setTelephoneNo("+66876610103");
			Principal b1p = new Principal();
			b1p.setUser(b1);
			b1p.setUsername("buyer1");
			b1p.setPassword(("1234"));
			b1p.setEnabled(true);
		b1.setPrinciple(b1p);
		b1.setDescription(new Description(Messages.getString("Loader.37"), Messages.getString("Loader.38"), Messages.getString("Loader.39")));
		buyerDao.save(b1);
		Buyer b2 = new Buyer();
		b2.setName(new Description(Messages.getString("Loader.46"), Messages.getString("Loader.47"), Messages.getString("Loader.48")));
		b2.setAddress(new Description(Messages.getString("Loader.43"), Messages.getString("Loader.44"), Messages.getString("Loader.45")));
		b2.setTelephoneNo("18000000000");
			Principal b2p = new Principal();
			b2p.setUser(b2);
			b2p.setUsername("buyer2");
			b2p.setPassword(("1234"));
			b2p.setEnabled(true);
		b2.setPrinciple(b2p);
		b2.setDescription(new Description(Messages.getString("Loader.46"), Messages.getString("Loader.47"), Messages.getString("Loader.48")));
		buyerDao.save(b2);
		
		// ########## Create Authority and Authority Principle Assignment
		Authority a1 = new Authority();
		a1.setAuthority("ROLE_ADMIN");
		a1.setRoleId("00000000");
		authorityDao.save(a1);
		Authority a2 = new Authority();
		a2.setAuthority("ROLE_SELLER");
		a2.setRoleId("00000001");
		authorityDao.save(a2);
		Authority a3 = new Authority();
		a3.setAuthority("ROLE_BUYER");
		a3.setRoleId("00000002");
		authorityDao.save(a3);
		
		AuthorityPrincipalAssignment apa1 = new AuthorityPrincipalAssignment();
		apa1.setRoleId(a2);
		apa1.setUsername(s1p);
		authorityPrincipalAssignmentDao.save(apa1);
		AuthorityPrincipalAssignment apa2 = new AuthorityPrincipalAssignment();
		apa2.setRoleId(a2);
		apa2.setUsername(s2p);
		authorityPrincipalAssignmentDao.save(apa2);
		AuthorityPrincipalAssignment apa3 = new AuthorityPrincipalAssignment();
		apa3.setRoleId(a3);
		apa3.setUsername(b1p);
		authorityPrincipalAssignmentDao.save(apa3);
		
		Principal b2p1 = new Principal();

		b2p1.setUsername("admin");
		b2p1.setPassword(("admin"));
		b2p1.setEnabled(true);
		AuthorityPrincipalAssignment apa4 = new AuthorityPrincipalAssignment();
		apa4.setRoleId(a1);
		apa4.setUsername(b2p1);
		Seller s5 = new Seller();
		s5.setName(new Description(Messages.getString("Loader.0"), Messages.getString("Loader.1"), Messages.getString("Loader.2"))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		s5.setDescription(new Description(Messages.getString("Loader.3"), Messages.getString("Loader.4"), Messages.getString("Loader.5"))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		s5.setPrinciple(b2p1);
		b2p1.setUser(s5);
		authorityPrincipalAssignmentDao.save(apa4);
		sellerDao.save(s5);
//		// ########## Create Promotion ##########
//		Promotion pro1 = new Promotion();
//		pro1.setAbsoluteDiscount(10.0);
//		pro1.setDescription(new Description(Messages.getString("Loader.34"), Messages.getString("Loader.35"), Messages.getString("Loader.36")));
//		pro1.setPercentDiscount(10.0);
//		pro1.setProduct(sp1);
//		Date date = new Date();
//		date.setTime(0);
//		pro1.setStartDate(date);
//		date.setTime(1);
//		pro1.setStopDate(date);
//		promotionDao.save(pro1);
		
//		// Create test picture
//		ProductPic pp = new ProductPic();
//		pp.loadFile("/images/test/test.jpg");
//		productPicDao.save(pp);
		
		
	}
	
	private String getMD5Encode(String password){
//		byte[] bytesOfMessage;
//		try {
//			bytesOfMessage = password.getBytes("UTF-8");
//			MessageDigest md = MessageDigest.getInstance("MD5");
//			byte[] thedigest = md.digest(bytesOfMessage);
//			return new String(thedigest);
			return password;
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//		
//			e.printStackTrace();
//			return null;
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}

		
	}
}

