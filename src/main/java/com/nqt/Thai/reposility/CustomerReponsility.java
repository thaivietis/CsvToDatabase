package com.nqt.Thai.reposility;

import com.nqt.Thai.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerReponsility extends JpaRepository<Customer, Integer> {

}
