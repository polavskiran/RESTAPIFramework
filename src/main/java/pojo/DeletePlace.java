package pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DeletePlace {

	private String place_id;

	public String getPlace_id() {
		return place_id;
	}

	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}
	
	public DeletePlace getDeletePlace(String placeid) {
		
		DeletePlace delPlace = new DeletePlace();
		delPlace.setPlace_id(placeid);
		
		return delPlace;
	}
	
	public static void main(String[] args) {
		
		DeletePlace delPlace = new DeletePlace();
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(delPlace.getDeletePlace("1223456"));
			System.out.println("Json String= "+json);
		}catch(JsonProcessingException e) {
			
		}
	}
}