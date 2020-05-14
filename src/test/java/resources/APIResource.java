package resources;

public enum APIResource {
	addPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
private String resource;
	APIResource(String resource) {
		// TODO Auto-generated constructor stub
		this.resource =resource;
	}
	
	public String getAPIResource() {
		return resource;
		
	}

}
