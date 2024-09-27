package com.nqt.Thai.controller;

import com.nqt.Thai.domain.Customer;
import com.nqt.Thai.domain.Staff;
import com.nqt.Thai.service.CsvService;
import com.nqt.Thai.service.CustomerService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {
    private CsvService csvService;
    private CustomerService customerService;

    public CustomerController(CustomerService customerService, CsvService csvService) {
        this.customerService = customerService;
        this.csvService = csvService;
    }

    @GetMapping("/")
    public String showUploadPage(Model model) {
        return "index"; // Trả về tên file upload.html (view)
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model, @RequestParam("table") String tableName) {
        String message = "";
        if (csvService.hasCsvFormat(file)) {
            try {
                customerService.save(file);
                message = "The file is uploaded successfully: " + file.getOriginalFilename();
                model.addAttribute("message", message);
            } catch (Exception e) {
                message = "The file is not upload successfully: " + file.getOriginalFilename() + "!";
                model.addAttribute("message", message);
            }
        } else {
            message = "Please upload a CSV file!";
            model.addAttribute("message", message);
        }
        return "index";
    }

    @GetMapping("/download")
    public  ResponseEntity<String> exportCsv(HttpServletResponse response, @RequestParam("table") String tableName) {
        try {
            response.setContentType("text/csv; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=products.csv");
            PrintWriter writer = response.getWriter();
            if(tableName.equals("Customer")) {
                csvService.export(Customer.class, writer);
            }else if(tableName.equals("Staff")){
                csvService.export(Staff.class, writer);
            }else
                csvService.export(Customer.class, writer);
            return ResponseEntity.ok("Export file successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Export file failed: " + e.getMessage());
        }
    }
}
