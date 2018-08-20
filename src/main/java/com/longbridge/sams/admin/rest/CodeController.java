package com.longbridge.sams.admin.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.longbridge.sams.admin.service.CodeService;
import com.longbridge.sams.data.dto.CodeTypeDTO;
import com.longbridge.sams.model.Code;
import com.longbridge.sams.utils.CustomBeanUtilsBean;
import com.longbridge.sams.utils.DataTablesUtils;

@RestController()
@RequestMapping("/admin/v1/code")
public class CodeController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	CodeService codeService;

	@GetMapping("{id}")
	ResponseEntity<?> getCode(@PathVariable Long id) {
		Code code = codeService.getCode(id);
		return ResponseEntity.ok(new ResponseData(code));
	}

	@PostMapping(consumes="application/json")
	public ResponseEntity<?> createCode(@RequestBody @Valid Code code, BindingResult result) {
		Code response = null;
		ResponseEntity<?> resp = null;
		ResponseData dt = new ResponseData();
		try {
			if(result.hasErrors()) {
				List<FieldError> errors = result.getFieldErrors().stream().map(f -> new FieldError(f.getField(),f.getDefaultMessage())).collect(Collectors.toList());
				dt.setError(errors);
				return ResponseEntity.badRequest().body(dt);
			}
			if (code.getId() != null) {
				Code code2 = codeService.getCode(code.getId());
				CustomBeanUtilsBean.getInstance().copyProperties(code2, code);
				code = code2;
				response = codeService.modify(code);
				resp = new ResponseEntity<>(new ResponseData(response),HttpStatus.OK);
			} else {
				response = codeService.add(code);
				resp = new ResponseEntity<>(new ResponseData(response),HttpStatus.OK);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.debug("Error Adding {}",code,ex);
			dt.setError(ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dt);
		}
		
		return resp;
	}
	


/*	@GetMapping("code")
	public DataTablesOutput<Code> getCodes(@RequestBody DataTablesInput input) {
		Pageable pageable = DataTablesUtils.getPageable(input);

		Page<Code> codes = null;
			codes = codeService.getAllCodes(pageable);
//		}
		DataTablesOutput<Code> out = new DataTablesOutput<Code>();
		out.setDraw(input.getDraw());
		out.setData(codes.getContent());
		out.setRecordsFiltered(codes.getTotalElements());
		out.setRecordsTotal(codes.getTotalElements());
		return out;
	}
*/


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
