package edu.cmucdu.ecommerce.dao.logistic;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Array;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import edu.cmucdu.ecommerce.domain.logistic.PortTrip;

public class MockPortTripDaoForTestLogisticService implements PortTripDao {

	List<PortTrip> portTrips;
	public MockPortTripDaoForTestLogisticService(){
		portTrips = new ArrayList<PortTrip>();
		portTrips.add(new PortTrip("a","b"));
		portTrips.add(new PortTrip("a","d"));
		portTrips.add(new PortTrip("b","c"));
		portTrips.add(new PortTrip("d","c"));
		
		portTrips.add(new PortTrip("c","e"));
		portTrips.add(new PortTrip("c","f"));
		portTrips.add(new PortTrip("a","e"));
		portTrips.add(new PortTrip("b","a"));
		
	}
	@Override
	public List<PortTrip> findBySourcePort(String sourceport) {
		List<PortTrip> output = new ArrayList<PortTrip>();
		for (PortTrip portTrip : portTrips) {
			if (portTrip.getSourcePort().equals(sourceport)){
				output.add(portTrip);
			}
		}
		return output;
	}

	@Override
	public List<PortTrip> findByDestinationPort(String destinationPort) {
		List<PortTrip> output = new ArrayList<PortTrip>();
		for (PortTrip portTrip : portTrips) {
			if (portTrip.getDestinationPort().equals(destinationPort)){
				output.add(portTrip);
			}
		}
		return output;
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteInBatch(Iterable<PortTrip> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PortTrip> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PortTrip> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends PortTrip> List<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PortTrip saveAndFlush(PortTrip arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PortTrip> findAll(Pageable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Long arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(PortTrip arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends PortTrip> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<PortTrip> findAll(Iterable<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PortTrip findOne(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends PortTrip> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<PortTrip> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PortTrip> findAll(Specification<PortTrip> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PortTrip> findAll(Specification<PortTrip> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PortTrip> findAll(Specification<PortTrip> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PortTrip findOne(Specification<PortTrip> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
