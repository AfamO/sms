package com.longbridge.sams.school.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.longbridge.sams.model.Staff;


public interface StaffService {
    String createStaff(Staff staffDTO);

    String updateStaff(Staff staffDTO);

    String deleteStaff(Staff staff);

    Staff getStaff(Long Id);

    Page<Staff> getStaff (String delFlag, Pageable pageable );
    
    


}
