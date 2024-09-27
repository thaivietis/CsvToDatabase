package com.nqt.Thai.service;

import java.util.List;
import java.io.IOException;
import com.nqt.Thai.domain.Customer;
import com.nqt.Thai.reposility.CustomerReponsility;
import com.nqt.Thai.service.imp.CustomerServiceImp;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CustomerService implements CustomerServiceImp {
    private CustomerReponsility cusRepo;
    private CsvService csvService;
    public CustomerService(CustomerReponsility cusRepo, CsvService csvService) {
        this.cusRepo = cusRepo;
        this.csvService = csvService;
    }
    @Override
    public void save(MultipartFile file) {
        try {
            List <Customer> cusList = csvService.improt(file.getInputStream());
            cusRepo.saveAll(cusList);
        } catch (IOException ex) {
            throw new RuntimeException("Data is not store successfully: " + ex.getMessage());
        }
    }

    @Override
    public List<Customer> findAll() {
        return cusRepo.findAll();
    }
}
