package pojo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pojo.AddPlace.Location;

public class SetPlaceDetails {

	public AddPlace getPlaceDetails(String name, String lang, String addr) {

		AddPlace place = new AddPlace();
		Location loc = place.new Location();
		// StringBuilder addrBuilder = new StringBuilder(addr);
		StringBuilder addrBuilder = addAddress(addr);

		loc.setLng(38.98763);
		loc.setLat(-38.5645);

		place.setAccuracy(50);
		place.setAddress(addrBuilder.toString());
		place.setLanguage(lang);
		place.setLocation(loc);
		place.setPhone_number("8383821926");

		List<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");

		place.setTypes(types);
		place.setWebsite("https://rahulshettyacademy.com");
		place.setName(name);

		return place;
	}


	public StringBuilder addAddress(String address){

		StringBuilder addrBuilder = null;

		if(!address.isEmpty()){
			addrBuilder = new StringBuilder(address);
		}

		return addrBuilder;
	}

	public DeletePlace getDeletePlaceDetails(String placeid) {

		DeletePlace delPlaceObj = new DeletePlace();
		delPlaceObj.setPlace_id(placeid);

		return delPlaceObj;
	}

	public static void main(String[] args) throws JsonProcessingException {

		SetPlaceDetails obj = new SetPlaceDetails();
		String name = "Polas House";
		String lang = "en-US";
		String addr = "16-262, 3rd B";
		AddPlace placeObj = obj.getPlaceDetails(name, lang, addr);
		DeletePlace delPlace = new DeletePlace();

		// Convert the object to JSONString
		ObjectMapper mapper = new ObjectMapper();

		// Convert the object to JSONString
		String jsonString = mapper.writeValueAsString(placeObj);
		System.out.println("AddPlace Json=" + jsonString);

		String delJsonString = mapper.writeValueAsString(delPlace.getDeletePlace("abc12343"));
		System.out.println("DeletePlace Json=" + delJsonString);
	}
}