package com.nqt.Thai.service.imp;
import com.nqt.Thai.domain.Customer;
import com.nqt.Thai.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ManagerServiceImp {
    void saveManager(MultipartFile file);
    List<Manager> findAllManager();
}
