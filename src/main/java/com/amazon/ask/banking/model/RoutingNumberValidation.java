package com.amazon.ask.banking.model;

/**
 * @author Karthick Rajendran
 *
 */
public class RoutingNumberValidation {

 private Long id; 
 private String routing; 
 private String valid;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getRouting() {
	return routing;
}

public void setRouting(String routing) {
	this.routing = routing;
}

public String getValid() {
	return valid;
}

public void setValid(String valid) {
	this.valid = valid;
}

}
