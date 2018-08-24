package com.longbridge.sams.school.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.longbridge.sams.model.Staff;
import com.longbridge.sams.repository.StaffRepository;
import com.longbridge.sams.school.service.StaffService;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffRepository repo;

	@Override
	public Staff createStaff(Staff staffDTO) {
		return repo.save(staffDTO);
	}

	@Override
	public Staff updateStaff(Staff staffDTO) {
		return repo.save(staffDTO);
	}

	@Override
	public void deleteStaff(Staff staff) {
		repo.delete(staff);
	}

	@Override
	public Staff getStaff(Long id) {
		return repo.getOne(id);
	}

	@Override
	public Page<Staff> getStaff(Pageable pageable) {
		return repo.findAll(pageable);
	}

}
