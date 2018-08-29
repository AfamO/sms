package com.longbridge.sams.admin.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import com.longbridge.sams.ApplicationException;
import com.longbridge.sams.data.dto.CodeTypeDTO;
import com.longbridge.sams.model.Code;





public interface CodeService {
	Code getCode(long id);
	
	Iterable<Code> getAllCodes();
	Iterable<Code> getCodeByType(String type);
	Iterable<Code> getCodeByName(String name);
	Iterable<Code> findCode(String code,String type, String desc);
	public Iterable<Code> findCode(Code c) ;
	
	Page<Code> getAllCodes(Pageable page);
	Page<Code> getCodeByType(String type,Pageable page);
	Page<Code> findCode(Code c,Pageable page) ;
	Page<Code> findCode(String pattern,Pageable page);
	Page<CodeTypeDTO> getCodeTypes(Pageable pageDetails);
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	Code modify(Code code) throws ApplicationException;
	@PreAuthorize("hasAuthority('ROLE_STAFF')")
	Code add(Code code) throws ApplicationException;
	void remove(Code code) throws ApplicationException;
	
}
