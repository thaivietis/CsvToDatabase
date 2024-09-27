package com.nqt.Thai.controller;
import java.util.List;
import com.nqt.Thai.domain.Customer;
import com.nqt.Thai.service.CsvService;
import com.nqt.Thai.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import java.io.IOException;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
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
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
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

    @GetMapping("/list")
    public String getStudents(Model model) {
        List<Customer> cusList = customerService.findAll();
        if (!cusList.isEmpty()) {
            model.addAttribute("Customers", cusList);
        } else {
            model.addAttribute("message", "Data is not found");
        }
        return "index"; // Trả về trang student-list.html (view)
    }

    @GetMapping("/download")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users.csv";
        response.setHeader(headerKey, headerValue);

        List<Customer> listUsers = customerService.findAll();

        // Khởi tạo đối tượng ICsvBeanWriter
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

        // Mảng tiêu đề của CSV
        String[] csvHeader = {"STT", "Age", "Full Name"};
        String[] nameMapping = {"id", "age", "full_name"};

        // Ghi tiêu đề, mỗi phần tử của csvHeader sẽ nằm trong một cột riêng biệt
        csvWriter.writeHeader(csvHeader);

        // Ghi dữ liệu người dùng
        for (Customer user : listUsers) {
            csvWriter.write(user, nameMapping);
        }

        csvWriter.close();
    }

}
