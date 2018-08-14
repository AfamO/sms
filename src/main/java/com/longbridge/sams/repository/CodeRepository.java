package com.longbridge.sams.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.longbridge.sams.model.Code;

import java.util.List;

@Repository
public interface CodeRepository extends CommonRepository<Code, Long> {

	Iterable<Code> findByName(String name);

	Iterable<Code> findByType(String type);

	Page<Code> findByType(String type, Pageable page);

	@Query("select distinct c.type from Code c")
	Iterable<String> findAllTypes();

	@Query("select distinct c.type from Code c")
	Page<String> findAllTypes(Pageable pageable);

	List<Code> findByDelFlag(String delFlag);

	Code getCodeById(Long id);
	
}
