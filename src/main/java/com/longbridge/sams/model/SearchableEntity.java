package com.longbridge.sams.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface SearchableEntity {
	default @JsonIgnore
	List<String> getDefaultSearchFields(){
		 return new ArrayList<String>();
	}
}
