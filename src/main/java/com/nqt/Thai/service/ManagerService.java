package com.nqt.Thai.service;

import com.nqt.Thai.domain.Customer;
import com.nqt.Thai.reposility.CustomerReponsility;
import com.nqt.Thai.service.imp.ManagerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.nqt.Thai.domain.Manager;
import com.nqt.Thai.reposility.ManagerReponsility;

import java.io.IOException;
import java.util.List;

@Service
public class ManagerService implements ManagerServiceImp {
    @Autowired
    private ManagerReponsility manRepo;
    @Autowired
    private CsvService csvService;
    @Override
    public void saveManager(MultipartFile file) {
        try {
            List <Manager> manList = csvService.improt(file.getInputStream());
            manRepo.saveAll(cusList);
        } catch (IOException ex) {
            throw new RuntimeException("Data is not store successfully: " + ex.getMessage());
        }
    }

    @Override
    public List<Manager> findAllManager() {
        return List.of();
    }
}
