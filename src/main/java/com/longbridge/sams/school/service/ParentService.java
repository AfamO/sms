package com.longbridge.sams.school.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.longbridge.sams.model.Parent;

import java.util.Optional;


public interface ParentService {

    String create(Parent parent);

    String delete(Parent parent);

    String modify(Parent parent);

    Optional<Parent> getParent(Long id);

    Page<Parent> getParents(Pageable pageable);

}