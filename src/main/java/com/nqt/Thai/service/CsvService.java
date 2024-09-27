package com.nqt.Thai.service;
import java.util.ArrayList;
import java.util.List;

import com.nqt.Thai.reposility.CustomerReponsility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import com.nqt.Thai.domain.Customer;
import java.lang.reflect.Field;
import java.io.PrintWriter;
import com.nqt.Thai.validate.CustomValidate;

import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class CsvService {
    @Autowired
    private CustomerReponsility customerReponsility;
    public static String TYPE = "text/csv";

    public static boolean hasCsvFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public <T> List<?> improt(InputStream is, Class<T> clazz) {
        List<T> data = new ArrayList<T>();
        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String headerLine = bReader.readLine();
            String[] headers = headerLine.split(",");
            List<Object> records = new ArrayList<>();
            String line;
            while ((line = bReader.readLine()) != null) {
                try {
                    if (line != null) {
                        String[] array = line.split(";");
                        Customer cus = new Customer();
                        cus.setId(Integer.parseInt(array[0]));
                        cus.setFullName(array[1]);
                        cus.setAge(Integer.parseInt(array[2]));
                        data.add(cus);
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

    public void export(Class<?> clazz, PrintWriter writer) throws IOException {
        StringBuilder headerLine = new StringBuilder();
        List<Customer> customers = customerReponsility.findAll();
        for (Field field : clazz.getDeclaredFields()) {
            CustomValidate annotation = field.getAnnotation(CustomValidate.class);
            if (annotation != null) {
                headerLine.append(annotation.column()).append(",");
            }
        }
        writer.print(headerLine.toString());
        List<Customer> records = customerReponsility.findAll();
        for (Object record : records) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(CustomValidate.class)) {
                    try {
                        writer.print(field.get(record) + ",");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            writer.println();
        }
        writer.flush();
        writer.close();
    }
}