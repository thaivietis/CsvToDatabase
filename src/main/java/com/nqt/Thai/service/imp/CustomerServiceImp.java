package com.nqt.Thai.service.imp;

import java.util.List;
import com.nqt.Thai.domain.Customer;
import org.springframework.web.multipart.MultipartFile;

public interface CustomerServiceImp {
    void save(MultipartFile file);
    List <Customer> findAll();
}
