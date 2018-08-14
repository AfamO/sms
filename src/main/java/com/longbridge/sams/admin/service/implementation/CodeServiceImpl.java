package com.longbridge.sams.admin.service.implementation;

import com.longbridge.sams.admin.service.CodeService;
import com.longbridge.sams.model.Code;
import com.longbridge.sams.repository.CodeRepository;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.logging.log4j.message.MessageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    CodeRepository codeRepo;

    @Autowired
    MessageSource messageSource;


    @Override
    public String create(Code code) {
        String result;
        try {

            codeRepo.save(code);
            result = "Successfully Created";
        } catch (Exception ex) {
            result = "Failed";
        }

        return result;
    }


    @Override
    public String update(Code code) {

        String result = "";

        try{
            Code code1 = codeRepo.getCodeById(code.getId());
            if (code1 != null){
                BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
                //BeanUtils.copyProperties(Destination, Source)
                BeanUtils.copyProperties(code1,code);

                codeRepo.save(code1);

                result = "Successful";
            }


        }catch (Exception ex){
            result = "Failed";
        }

        return result;
    }

    @Override
    public String delete(Code code) {
        String result;
        try {
            code.setDelFlag("Y");
            codeRepo.save(code);
            result = "Successfully Deleted";

        } catch (Exception ex) {

            result = "Failed";
        }

        return result;

    }

    @Override
    public Code getCodeById(Long id) {
        Code code1 = null;

        Optional<Code> tempCode = codeRepo.findById(id);
        if (tempCode.isPresent()) {
            Code code = tempCode.get();
            code1 = code;

        }

        return code1;

    }
//
//    @Override
//    public List<Code> getCodeByType(String type) {
//
//        Iterable<Code> codeList = codeRepo.findByType(type);
//
//        return (List<Code>) codeList;
//    }

//    @Override
//    public Iterable<Code> getCodeByType(String type) {
//        return codeRepo.findAllTypes();
//    }

    @Override
    public Iterable<Code> getCodeType(String type) {
        return codeRepo.findByType(type);
    }


//    @Override
//    public Code getCodeByType(String type) {
//        Code result = null;
//        Iterable<Code> codeTypes = codeRepo.findByType(type);
//
//        for (Code code : codeTypes) {
//            result = code;
//        }
//
//        return result;
//    }


    @Override
    public Code getCodeByName(String name) {
        Code result = null;

        Iterable<Code> codeNames = codeRepo.findByName(name);

        for (Code codeName : codeNames){
            result = codeName;
        }

        return result;
    }



    @Override
    public List<Code> getAllCodes(String delFlag) {
        List<Code> codeList = codeRepo.findByDelFlag(delFlag);
        return codeList;
    }


    @Override
    public Page<Code> getCodes(Pageable pageable) {
        return codeRepo.findAll(pageable);
    }
}