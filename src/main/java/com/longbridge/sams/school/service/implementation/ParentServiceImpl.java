package com.longbridge.sams.school.service.implementation;

import com.longbridge.sams.model.Parent;
import com.longbridge.sams.repository.ParentRepo;
import com.longbridge.sams.school.service.ParentService;
import com.longbridge.sams.utils.CustomBeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import com.longbridge.sams.utils.Messages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ParentServiceImpl implements ParentService {

    @Autowired
    ParentRepo repo;

    @Autowired
    Messages messageSource;


    @Override
    public String create(Parent parent) {
        String result = "";
        try{
            repo.save(parent);
            result = messageSource.get("parent.create.success");
        }catch (Exception ex){
            result= messageSource.get("parent.create.error");
        }

        return result;
    }


    @Override
    public String delete(Parent parent) {
        String result = "";
        try{
            repo.delete(parent);
            result = messageSource.get("parent.delete.success");
        }catch (Exception ex){
            result = messageSource.get("parent.delete.error");
        }

        return result;
    }

    @Override
    public String modify(Parent parent) {
        String result = "";
        Parent parent1 = repo.getOne(parent.getId());
        try{
            if(parent1 != null){

                CustomBeanUtilsBean.getInstance().copyProperties(parent1, parent);
                result = messageSource.get("parent.update.success");
            }
        }catch (Exception ex){
            result = messageSource.get("parent.update.error");
        }

        return result;
    }

    @Override
    public Optional<Parent> getParent(Long id) {

        return repo.findById(id);
    }

    @Override
    public Page<Parent> getParents(Pageable pageable) {
        return repo.findAll(pageable);
    }
}
