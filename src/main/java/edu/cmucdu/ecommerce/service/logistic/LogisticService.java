package edu.cmucdu.ecommerce.service.logistic;

import java.util.List;
import java.util.Set;

import edu.cmucdu.ecommerce.domain.logistic.LocalToPort;
import edu.cmucdu.ecommerce.domain.logistic.Logistic;
import edu.cmucdu.ecommerce.domain.logistic.PortToLocal;
import edu.cmucdu.ecommerce.domain.logistic.PortTrip;
import edu.cmucdu.ecommerce.domain.product.SellerProduct;
import edu.cmucdu.ecommerce.domain.user.Buyer;

public interface LogisticService {
	
	public Set<List<PortTrip>> findPort(String sourcePort, String destinationPort);
	
	public Logistic createLogistic(SellerProduct sellerProduct, Buyer buyer, LocalToPort localToPort, PortToLocal portToLocal, List<PortTrip> portTrips);
	

}
