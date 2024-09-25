package com.nqt.Thai.service;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import com.nqt.Thai.domain.Customer;

public class CsvService {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "STT", "Full Name", "Age" };
    public static boolean hasCsvFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
    public static List<Customer> csvToStuList(InputStream is) {
        try (BufferedReader bReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(bReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<Customer> cusList = new ArrayList<Customer>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                Customer cus = new Customer();
                cus.setId(Integer.parseInt(csvRecord.get("ID")));
                cus.setFullName(csvRecord.get("Full Name"));
                cus.setAge(Integer.parseInt(csvRecord.get("Age")));
                cusList.add(cus);
            }
            return cusList;
        } catch (IOException e) {
            throw new RuntimeException("CSV data is failed to parse: " + e.getMessage());
        }
    }
}
