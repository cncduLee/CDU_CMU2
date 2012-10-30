package edu.cmucdu.ecommerce.domain.loader;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import edu.cmucdu.ecommerce.dao.product.ProductDao;
import edu.cmucdu.ecommerce.dao.product.ProductPicDao;
import edu.cmucdu.ecommerce.dao.product.PromotionDao;
import edu.cmucdu.ecommerce.dao.security.AuthorityDao;
import edu.cmucdu.ecommerce.dao.security.AuthorityPrincipalAssignmentDao;
import edu.cmucdu.ecommerce.dao.user.SellerDao;
import edu.cmucdu.ecommerce.domain.product.*;
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
			s1p.setUserName(Messages.getString("Loader.6")); //$NON-NLS-1$
			s1p.setPassword(Messages.getString("Loader.7")); //$NON-NLS-1$
		s1.setPrinciple(s1p);
		sellerDao.save(s1);

		Seller s2 = new Seller();
		s2.setName(new Description(Messages.getString("Loader.8"), Messages.getString("Loader.9"), Messages.getString("Loader.10"))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		s2.setDescription(new Description(Messages.getString("Loader.11"), Messages.getString("Loader.12"), Messages.getString("Loader.13"))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			//Set principle
			Principal s2p = new Principal();
			s2p.setUser(s2);
			s2p.setEnabled(false);
			s2p.setUserName(Messages.getString("Loader.14")); //$NON-NLS-1$
			s2p.setPassword(Messages.getString("Loader.15")); //$NON-NLS-1$
		s2.setPrinciple(s2p);
		sellerDao.save(s2);
		
		// ############### Create Product ################
		Product p1 = new Product();
		p1.setName(new Description(Messages.getString("Loader.16"), Messages.getString("Loader.17"), Messages.getString("Loader.18"))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		p1.setDescription(new Description("","","")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
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
		
		productDao.save(p1);
		Product p2 = new Product();
		p2.setName(new Description(Messages.getString("Loader.22"), Messages.getString("Loader.23"), Messages.getString("Loader.24"))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		p2.setDescription(new Description("","","")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		productDao.save(p2);
		Product p3 = new Product();
		p3.setName(new Description(Messages.getString("Loader.28"), Messages.getString("Loader.29"), Messages.getString("Loader.30"))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		p3.setDescription(new Description("","","")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		productDao.save(p3);
		
//		List<Seller> sellerList = sellerDao.findAll();
//		List<Principal> plist =  Principal.findAllPrincipals();
//		System.out.println("xx"); //$NON-NLS-1$
		
		// ############# Create Product Picture ###############
//		ProductPic pic1 = new ProductPic();
//		pic1.setDescription(new Description(Messages.getString("Loader.31"), Messages.getString("Loader.32"), Messages.getString("Loader.33")));
//		pic1.setProduct(p1);
//		pic1.setSellerProduct(sp1);
//			//Load image file
//		try{
//			File fnew=new File("/tmp/rose.jpg");
//			BufferedImage originalImage=ImageIO.read(fnew);
//			ByteArrayOutputStream baos=new ByteArrayOutputStream();
//			ImageIO.write(originalImage, "jpg", baos );
//			byte[] imageInByte=baos.toByteArray();
//			pic1.setImage(imageInByte);
//			pic1.setImageType("jpg");
//		}
//		catch(Exception E){
//		}
//		pic1.
		
		// ########## Create Authority and Authority Principle Assignment
		Authority a1 = new Authority();
		a1.setAuthority("ROLE_ADMIN");
		a1.setRoleId("12345678");
		AuthorityPrincipalAssignment apa1 = new AuthorityPrincipalAssignment();
		apa1.setRoleId(a1);
		apa1.setUsername(s1p);
		authorityDao.save(a1);
		authorityPrincipalAssignmentDao.save(apa1);
		
		Authority a2 = new Authority();
		a2.setAuthority("ROLE_USER");
		a2.setRoleId("12345679");
		AuthorityPrincipalAssignment apa2 = new AuthorityPrincipalAssignment();
		apa2.setRoleId(a2);
		apa2.setUsername(s2p);
		authorityDao.save(a2);
		authorityPrincipalAssignmentDao.save(apa2);
		
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
		// Create test picture
		ProductPic pp = new ProductPic();
		pp.loadFile("/images/test/test.jpg");
		productPicDao.save(pp);
		// ########## Create Buyer ############
	}
}

