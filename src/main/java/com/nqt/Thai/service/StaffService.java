package com.nqt.Thai.service;

import com.nqt.Thai.domain.Customer;
import com.nqt.Thai.domain.Staff;
import com.nqt.Thai.reposility.CustomerReponsility;
import com.nqt.Thai.service.imp.StaffServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import com.nqt.Thai.reposility.StaffReponsility;

import java.io.IOException;
import java.util.List;

public class StaffService implements StaffServiceImp {
    @Autowired
    private StaffReponsility staRepo;
    @Autowired
    private CsvService csvService;
    @Override
    public void saveStaff(MultipartFile file) {

    }

    @Override
    public List<Staff> findAllStaff() {
        return List.of();
    }
}
