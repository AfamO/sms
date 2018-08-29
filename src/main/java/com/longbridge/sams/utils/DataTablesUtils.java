package com.longbridge.sams.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public class DataTablesUtils {

	public static Pageable getPageable(DataTablesInput input) {
		return PageRequest.of(input.getStart()/input.getLength(),input.getLength());
	}
}
