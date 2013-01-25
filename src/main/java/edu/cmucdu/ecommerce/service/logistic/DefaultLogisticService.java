package edu.cmucdu.ecommerce.service.logistic;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cmucdu.ecommerce.dao.logistic.LogisticDao;
import edu.cmucdu.ecommerce.domain.logistic.LocalToPort;
import edu.cmucdu.ecommerce.domain.logistic.Logistic;
import edu.cmucdu.ecommerce.domain.logistic.PortToLocal;
import edu.cmucdu.ecommerce.domain.logistic.PortTrip;
import edu.cmucdu.ecommerce.domain.product.SellerProduct;
import edu.cmucdu.ecommerce.domain.user.Buyer;

@Service
public class DefaultLogisticService implements LogisticService {
	@Autowired
	LogisticDao logistic;
	
	@Override
	public Set<List<PortTrip>> findPort(String sourcePort,
			String destinationPort) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public Logistic createLogistic(SellerProduct sellerProduct, Buyer buyer, LocalToPort localToPort, PortToLocal portToLocal, List<PortTrip> portTrips) {
		// TODO Auto-generated method stub
		return null;
	}

}
