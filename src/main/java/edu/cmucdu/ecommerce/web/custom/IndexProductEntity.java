package edu.cmucdu.ecommerce.web.custom;

public class IndexProductEntity {
	
		String name;
		String description;
		public IndexProductEntity(String name, String description) {
			super();
			this.name = name;
			this.description = description;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		
	}

}
