package com.nqt.Thai.service;
import java.util.ArrayList;
import java.util.List;

import com.nqt.Thai.reposility.CustomerReponsility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import com.nqt.Thai.domain.Customer;
import java.nio.charset.StandardCharsets;

@Service
public class CsvService {
    @Autowired
    private CustomerReponsility customerReponsility;
    public static String TYPE = "text/csv";
    static String[] HEADERs = {"STT", "Full Name", "Age"};

    public static boolean hasCsvFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public List<Customer> improt(InputStream is) {
        List<Customer> cusList = null;
        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String headerLine = bReader.readLine();
            String[] headers = headerLine.split(",");
            List<Object> records = new ArrayList<>();
            String line;
            cusList = new ArrayList<Customer>();
            while ((line = bReader.readLine()) != null) {
                try {
                    if (line != null) {
                        String[] array = line.split(";");
                        Customer cus = new Customer();
                        cus.setId(Integer.parseInt(array[0]));
                        cus.setFullName(array[1]);
                        cus.setAge(Integer.parseInt(array[2]));
                        cusList.add(cus);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cusList;
    }
}