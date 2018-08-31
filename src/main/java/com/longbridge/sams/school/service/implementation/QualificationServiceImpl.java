package com.longbridge.sams.school.service.implementation;

import com.longbridge.sams.ApplicationException;
import com.longbridge.sams.model.Qualification;
import com.longbridge.sams.repository.QualificationRepo;
import com.longbridge.sams.school.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QualificationServiceImpl implements QualificationService {


    @Autowired
    private QualificationRepo qualificationRepo;

    @Override
    public Qualification createQualif(Qualification qualification){
        return qualificationRepo.save(qualification);
    }

    @Override
    public Qualification updateQualification(Qualification qualification) {
        return qualificationRepo.save(qualification);
    }

    @Override
    public void deleteQualification(Qualification qualification)  {

    }

    @Override
    public Qualification getQualification(Long id) {
        return qualificationRepo.getOne(id);
    }

    @Override
    public Page<Qualification> getQualification(Pageable pageable) {
        return qualificationRepo.findAll(pageable);
    }
}
