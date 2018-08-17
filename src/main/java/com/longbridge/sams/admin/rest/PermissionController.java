package com.longbridge.sams.admin.rest;

import com.longbridge.sams.ApplicationException;
import com.longbridge.sams.admin.service.PermissionService;
import com.longbridge.sams.model.Permission;
import com.longbridge.sams.utils.DataTablesUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @Autowired
    MessageSource messageSource;

    private Logger logger = LoggerFactory.getLogger(this.getClass());



    @PostMapping("/add")
    String CreatePermission(@RequestBody Permission permission, BindingResult result) {

        if (result.hasErrors()) {
            logger.warn("Error occurred creating permission{}", result.toString());
            return "Error Creating Page";
        }
        try {
            if (permission.getId() != null) {
                return permissionService.modify(permission);
            } else {
                return permissionService.create(permission);
            }

        } catch (ApplicationException ex) {
            result.reject(ex.getMessage());
            logger.error("Error occurred creating permission{}", ex);

        }
        return "/admin/permission/list";
    }


//        //	ResponseEntity represents an HTTP response, including headers, body, and status.
//       //	While @ResponseBody puts the return value into the body of the response,
//       //	ResponseEntity also allows us to add headers and status code.
//
//    ResponseEntity<Permission> createPermission(@ModelAttribute("permission") @Valid Permission permission,BindingResult result,Model model, Locale locale
//    ) {
//        String response = "";
//        System.out.println("I'm right here inside controller");
//
//
//        if (result.hasErrors()) {
//            logger.warn("Error occurred creating permission{}", result.toString());
//            return ResponseEntity.badRequest().body(permission);
//        }
//
//        try {
//            if(permission.getId() != null){
//                response = permissionService.modify(permission);
//                String msg = messageSource.getMessage("permission.update.success",null,locale);
//            }else{
//                response = permissionService.create(permission);
//                String msg = messageSource.getMessage("permission.create.success",null,locale);
//
//            }
//
//        } catch (ApplicationException ex) {
//            result.reject(ex.getMessage());
//            String msg = messageSource.getMessage("permission.create.error",null,locale);
//            logger.error("Error occurred creating permission{}", ex);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(permission);
//        }
//        return ResponseEntity.ok().body(permission);
//    }

    @GetMapping("/edit/{id}")
    public String editPermission(@RequestParam("id")Long id, Model model){
        Permission permission = permissionService.get(id);
        model.addAttribute(permission);
        return "admin/permission/edit";
    }

    @GetMapping("/view/{id}")
    String getPermissions(@PathVariable Long id, Model model) {
        Permission perm = permissionService.get(id);
        model.addAttribute("permission", perm);
        return "admin/permission/detail";
    }

    public String getPerformance(){
        return "admin/permission/list";

    }


    @GetMapping("/list")
    public @ResponseBody DataTablesOutput<Permission> getPermissions(DataTablesInput input){
        Pageable pageable = DataTablesUtils.getPageable(input);

        Page<Permission>  permissions = null;
        permissions = permissionService.getPermissions(pageable);

        DataTablesOutput<Permission> out = new DataTablesOutput<Permission>();
        out.setDraw(input.getDraw());
        out.setData(permissions.getContent());
        out.setRecordsFiltered(permissions.getTotalElements());
        out.setRecordsTotal(permissions.getTotalElements());
        return out;

    }


}
