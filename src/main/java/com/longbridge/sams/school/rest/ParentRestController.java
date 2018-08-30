package com.longbridge.sams.school.rest;

import com.longbridge.sams.model.Parent;
import com.longbridge.sams.school.service.ParentService;
import com.longbridge.sams.utils.DataTablesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller("/parent")
public class ParentRestController {

    @Autowired
    ParentService parentService;

    private static final Logger logger = LoggerFactory.getLogger(ParentRestController.class);



    @GetMapping
    public DataTablesOutput<Parent> getParents(DataTablesInput input) {
        Pageable pageable = DataTablesUtils.getPageable(input);

        Page<Parent> parents = null;
        parents = parentService.getParents(pageable);
        DataTablesOutput<Parent> out = new DataTablesOutput<Parent>();
        out.setDraw(input.getDraw());
        out.setData(parents.getContent());
        out.setRecordsFiltered(parents.getTotalElements());
        out.setRecordsTotal(parents.getTotalElements());
        return out;
    }
}
