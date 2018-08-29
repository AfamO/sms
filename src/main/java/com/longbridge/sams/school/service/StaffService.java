package com.longbridge.sams.school.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.longbridge.sams.ApplicationException;
import com.longbridge.sams.model.Staff;


public interface StaffService {
	
	Staff createStaff(Staff staffDTO) throws ApplicationException;

	Staff updateStaff(Staff staffDTO) throws ApplicationException;

    void deleteStaff(Staff staff) throws ApplicationException;

    Staff getStaff(Long Id);

    Page<Staff> getStaff (Pageable pageable );
    
    


}
