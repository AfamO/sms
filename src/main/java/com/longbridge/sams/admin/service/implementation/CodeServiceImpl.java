package com.longbridge.sams.admin.service.implementation;




import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.longbridge.sams.ApplicationException;
import com.longbridge.sams.admin.service.CodeService;
import com.longbridge.sams.data.dto.CodeTypeDTO;
import com.longbridge.sams.model.Code;
import com.longbridge.sams.repository.CodeRepository;
import com.longbridge.sams.utils.Messages;


@Service
//@Transactional
public class CodeServiceImpl implements CodeService {

	@Autowired
	private CodeRepository repo;
	public void setRepo(CodeRepository repo) {
		this.repo = repo;
	}

	@Autowired
	Messages Messages;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Override
	public Code getCode(long id) {
		Code code = repo.findById(id).get();
		return code;
	}

	@Override
	public Iterable<Code> getAllCodes() {

	    return repo.findAll();
	}

	@Override
	public Iterable<Code> getCodeByType(String type) {
		
		return repo.findByType(type);
	}

	@Override
	public Iterable<Code> getCodeByName(String name) {

	    return  repo.findByName(name);
	}

	@Override
	@Transactional 
	public Code modify(Code code) throws ApplicationException {
		Code result = null;
		try {
			result = repo.save(code);
		} catch (Exception e) {
			log.error("Error saving code {}",code, e);
			throw new ApplicationException(e);
		}
		return result;
	}

	@Override
//	@Transactional(rollbackFor=Exception.class)
	public Code add(Code code) throws ApplicationException {
		Code result = null;
		try {

			result = repo.save(code);;
		}
		catch (Exception e) {
			log.error("Error adding code {}",code, e);
			throw new ApplicationException(e);
		}
		return result;
	}

//    @Override
//    @Transactional(rollbackFor=Exception.class)
//    public Code add(Code code) throws ApplicationException {
//        Code result = null;
//        try {
//            result = repo.save(code);
//        }
//        catch (Exception e) {
//            log.error("Error adding code {}",code, e);
//            throw new ApplicationException(e);
//        }
//        return result;
//    }



	@Override
	public void remove(Code code) throws ApplicationException {
		repo.delete(code);
	}

	@Override
	public Iterable<Code> findCode(String name, String type, String desc) {
		Code c = new Code();
		if(StringUtils.isNotEmpty(name)){
			c.setName(name);
		}
		
		if(StringUtils.isNotEmpty(type)){
			c.setType(type);
		}
		if(StringUtils.isNotEmpty(desc)){
			c.setDescription(desc);
		}
		ExampleMatcher matcher = ExampleMatcher.matchingAll().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase().withIgnoreNullValues();
		return repo.findAll(Example.of(c,matcher));
	}

	@Override
	public Iterable<Code> findCode(Code c) {
		return repo.findAll(Example.of(c));
	}

	@Override
	public Page<Code> getAllCodes(Pageable page) {
		return repo.findAll(page);
	}

	@Override
	public Page<Code> findCode(String pattern,Pageable page) {
//		return repo.findUsingPattern(pattern, page);
		return repo.findCodeByType(pattern, page);
	}
	
	@Override
	public Page<Code> getCodeByType(String type,Pageable page) {
		Code c = new Code();
		c.setType(type);
		return repo.findByType(type,page);
	}

	@Override
	public Page<Code> findCode(Code c, Pageable page) {
		if(StringUtils.isBlank(c.getName())){
			c.setName(null);
		}
		if(StringUtils.isBlank(c.getType())){
			c.setType(null);
		}
		if(StringUtils.isBlank(c.getDescription())){
			c.setDescription(null);
		}
		ExampleMatcher matcher = ExampleMatcher.matchingAll().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase().withIgnoreNullValues();
		return repo.findAll(Example.of(c,matcher),page);
	}

	@Override
	public Page<CodeTypeDTO> getCodeTypes(Pageable pageDetails) {
		Page<String> allTypes = repo.findAllTypes(pageDetails);
		long t = allTypes.getTotalElements();
		List<CodeTypeDTO> list = new ArrayList<CodeTypeDTO>();
		for(String s :allTypes){
			list.add(new CodeTypeDTO(s));
		}
		return new PageImpl<CodeTypeDTO>(list,pageDetails,t);
	}
}
