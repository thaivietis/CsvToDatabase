package com.nqt.Thai.reposility;

import com.nqt.Thai.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustemerReponsility extends JpaRepository<Long, Customer> {

}
