package edu.cmucdu.ecommerce.service.logistic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import edu.cmucdu.ecommerce.dao.logistic.MockPortTripDaoForTestLogisticService;
import edu.cmucdu.ecommerce.domain.logistic.PortTrip;

public class LogisticServiceTest {

	
	LogisticService logisticService;
	
	@Before
	public void setup(){
		logisticService = new DefaultLogisticService();
		((DefaultLogisticService)logisticService).setTripDao(new MockPortTripDaoForTestLogisticService());
	}
	
	public String getTripList(List<PortTrip> trip){
		StringBuffer buffer = new StringBuffer();
		if (trip== null || trip.size()==0){
			return "no trip";
		}
		buffer.append(trip.get(0).getSourcePort());
		for (PortTrip portTrip : trip) {
			buffer.append("-" + portTrip.getDestinationPort());
		}	
		
		
		return buffer.toString();
	}
	@Test
	public void testFindPort() {
		Set<List<PortTrip>> output = logisticService.findPort("a", "e");
		assertEquals(3, output.size());
		List<PortTrip> expected = new ArrayList<PortTrip>();
		expected.add(new PortTrip("a","e"));
		assertTrue(output.contains(expected));
		expected = new ArrayList<PortTrip>();
		expected.add(new PortTrip("a","d"));
		expected.add(new PortTrip("d","c"));
		expected.add(new PortTrip("c","e"));
		assertTrue(output.contains(expected));
		expected = new ArrayList<PortTrip>();
		expected.add(new PortTrip("a","b"));
		expected.add(new PortTrip("b","c"));
		expected.add(new PortTrip("c","e"));
		assertTrue(output.contains(expected));
		
		output = logisticService.findPort("a", "d");
		assertEquals(1, output.size());
		expected = new ArrayList<PortTrip>();
		expected.add(new PortTrip("a","d"));
		assertTrue(output.contains(expected));
		
		output = logisticService.findPort("a", "d");
		assertEquals(1, output.size());
		expected = new ArrayList<PortTrip>();
		expected.add(new PortTrip("a","d"));
		assertTrue(output.contains(expected));
		output = logisticService.findPort("a", "z");
		assertEquals(0, output.size());
		
		output = logisticService.findPort("k", "a");
		assertEquals(0, output.size());
		
	}

}
