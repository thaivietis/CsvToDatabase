package com.nqt.Thai.service.imp;
import com.nqt.Thai.domain.Manager;
import com.nqt.Thai.domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StaffServiceImp{
    void saveStaff(MultipartFile file);
    List<Staff> findAllStaff();
}
