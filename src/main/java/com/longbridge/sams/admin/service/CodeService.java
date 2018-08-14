package com.longbridge.sams.admin.service;


import com.longbridge.sams.model.Code;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CodeService {

    String create(Code code);
    String delete(Code code);
    String update(Code code);
    Code getCodeById(Long id);
//    Code  getCodeByType(String type);
//    List<Code> getCodeByType(String type);
    Code getCodeByName(String name);

    Iterable<Code> getCodeType(String type);
    List<Code> getAllCodes(String delFlag);

    Page<Code> getCodes(Pageable pageable);


}
