package com.longbridge.sams.school.service;

import com.longbridge.sams.ApplicationException;
import com.longbridge.sams.model.Qualification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


public interface QualificationService {


    Qualification createQualif(Qualification qualification) ;

    Qualification updateQualification(Qualification qualification);

    void deleteQualification(Qualification qualification);

    Qualification getQualification(Long id);

    Page<Qualification> getQualification (Pageable pageable );
}
