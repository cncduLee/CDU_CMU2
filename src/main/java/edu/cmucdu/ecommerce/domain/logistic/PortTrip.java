package edu.cmucdu.ecommerce.domain.logistic;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity
public class PortTrip extends  Trip {

	
    public PortTrip() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((destinationPort == null) ? 0 : destinationPort.hashCode());
		result = prime * result
				+ ((sourcePort == null) ? 0 : sourcePort.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PortTrip other = (PortTrip) obj;
		if (destinationPort == null) {
			if (other.destinationPort != null)
				return false;
		} else if (!destinationPort.equals(other.destinationPort))
			return false;
		if (sourcePort == null) {
			if (other.sourcePort != null)
				return false;
		} else if (!sourcePort.equals(other.sourcePort))
			return false;
		return true;
	}

	public PortTrip(String sourcePort, String destinationPort) {
		super();
		this.sourcePort = sourcePort;
		this.destinationPort = destinationPort;
	}

	
	private String sourcePort;

    private String destinationPort;
}
