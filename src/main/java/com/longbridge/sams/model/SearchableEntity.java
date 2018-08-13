package com.longbridge.sams.model;

import java.util.ArrayList;
import java.util.List;

public interface SearchableEntity {
	default
	List<String> getDefaultSearchFields(){
		 return new ArrayList<String>();
	}
}
