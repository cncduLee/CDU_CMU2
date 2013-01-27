package edu.cmucdu.ecommerce.service.logistic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cmucdu.ecommerce.dao.logistic.LogisticDao;
import edu.cmucdu.ecommerce.dao.logistic.PortTripDao;
import edu.cmucdu.ecommerce.domain.logistic.LocalToPort;
import edu.cmucdu.ecommerce.domain.logistic.Logistic;
import edu.cmucdu.ecommerce.domain.logistic.PortToLocal;
import edu.cmucdu.ecommerce.domain.logistic.PortTrip;
import edu.cmucdu.ecommerce.domain.product.SellerProduct;
import edu.cmucdu.ecommerce.domain.user.Buyer;

@Service
public class DefaultLogisticService implements LogisticService {
	@Autowired
	PortTripDao tripDao;
	
	public void setTripDao(PortTripDao tripDao) {
		this.tripDao = tripDao;
	}

	@Override
	public Set<List<PortTrip>> findPort(String sourcePort,
			String destinationPort) {
		// Create the output
		Set<List<PortTrip>> output = new HashSet<List<PortTrip>>();
		// get the list of the first destination port
		List<PortTrip> sourcePorts = tripDao.findBySourcePort(sourcePort);
		// check for each source port
		for (PortTrip portTrip : sourcePorts) {
			// if it reach the destination end this
			if (portTrip.getDestinationPort().equals(destinationPort)){
				List<PortTrip> trip = new ArrayList<PortTrip>();
				trip.add(portTrip);
				output.add(trip);
			}else{
				// The trip should continue
				List<PortTrip> trip = new ArrayList<PortTrip>();
				trip.add(portTrip);
				output.addAll(findPort(trip,destinationPort));
			}
		}
		return output;
	}
	
	private Set<List<PortTrip>> findPort(List<PortTrip> trip, String destinationPort){
		// Result set
		Set<List<PortTrip>> output = new HashSet<List<PortTrip>>();
		// get current destination
		String currentDestPort = trip.get(trip.size()-1).getDestinationPort();
		List<PortTrip> nextPorts = tripDao.findBySourcePort(currentDestPort);
		//no more port
		if (nextPorts.size() == 0){
			return output;
		}
		// if there are some port
		// iterate over the list
		for (PortTrip portTrip : nextPorts) {
			// check whether it is a loop or not
			// If it is a loop ignore it
			// otherwise check whether it reach the destination or not
			if (!isLoopTrip(trip, portTrip)){
				// check whether it reach the destination or not
				if (portTrip.getDestinationPort().equals(destinationPort)){
					// if reach destination add to the output
					List<PortTrip> newTrip = new ArrayList<PortTrip>(trip);
					newTrip.add(portTrip);
					output.add(newTrip);
				}else{
					// create a new trip and make a recursive call to find a new trip
					List<PortTrip> newTrip = new ArrayList<PortTrip>(trip);
					newTrip.add(portTrip);
					output.addAll(findPort(newTrip, destinationPort));
				}				
			}
		}
		return output;
	}
	
	private boolean isLoopTrip(List<PortTrip> trip, PortTrip destination){
		for (PortTrip portTrip : trip) {
			if (portTrip.getSourcePort().equals(destination.getDestinationPort())){
				return true;
			}
		}		
		return false;
		
	}

	@Override
	public Logistic createLogistic(SellerProduct sellerProduct, Buyer buyer, LocalToPort localToPort, PortToLocal portToLocal, List<PortTrip> portTrips) {
		// TODO Auto-generated method stub
		return null;
	}

}
