package com.longbridge.sams.admin.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.longbridge.sams.admin.service.CodeService;
import com.longbridge.sams.data.dto.CodeTypeDTO;
import com.longbridge.sams.model.Code;
import com.longbridge.sams.utils.DataTablesUtils;

@RestController()
@RequestMapping("/admin/v1/code")
public class AdmCodeController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	CodeService codeService;


	@GetMapping
	public DataTablesOutput<Code> getAllCodesOfType(@RequestParam(name = "type") String codeType,
			DataTablesInput input) {

		Pageable pageable = DataTablesUtils.getPageable(input);
		Page<Code> codes = codeService.getCodeByType(codeType, pageable);
		DataTablesOutput<Code> out = new DataTablesOutput<Code>();
		out.setDraw(input.getDraw());
		out.setData(codes.getContent());
		out.setRecordsFiltered(codes.getTotalElements());
		out.setRecordsTotal(codes.getTotalElements());
		return out;
	}

	@GetMapping(path = "/type")
	public DataTablesOutput<CodeTypeDTO> getAllCodeTypes(DataTablesInput input) {
		Pageable pageable = DataTablesUtils.getPageable(input);
		Page<CodeTypeDTO> codeTypes = codeService.getCodeTypes(pageable);
		DataTablesOutput<CodeTypeDTO> out = new DataTablesOutput<CodeTypeDTO>();
		out.setDraw(input.getDraw());
		out.setData(codeTypes.getContent());
		out.setRecordsFiltered(codeTypes.getTotalElements());
		out.setRecordsTotal(codeTypes.getTotalElements());
		return out;
	}

}
