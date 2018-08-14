package com.longbridge.sams.admin.rest;

import com.longbridge.sams.admin.service.CodeService;
import com.longbridge.sams.model.Code;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parent")
public class CodeController {

    private static final Logger logger = LoggerFactory.getLogger(CodeController.class);

    @Autowired
    CodeService codeService;

    @PostMapping("/save")
    public String create(@RequestBody Code code){
        codeService.create(code);
        return "successfully created";
    }

    @PostMapping("/update")
    public String update(@RequestBody Code code){

        codeService.update(code);

        return "Successfully Updated";
    }

    @PostMapping("/delete")
    public String delete(@RequestBody Code code){

        codeService.delete(code);

        return "Successfully created";
    }

    @GetMapping("/{id}/get")
    public Code get(@PathVariable Long id){
        Code code = codeService.getCodeById(id);
        return code;
    }

    @GetMapping("/all")
    public List<Code> getAllCodes(){
        List<Code> codeList = codeService.getAllCodes("N");
        return codeList;
    }
    

    @RequestMapping(
            value = "/**",
            method = RequestMethod.OPTIONS
    )

    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }
	

}
