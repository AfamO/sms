package com.longbridge.sams.repository;

import com.longbridge.sams.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long> {
}
