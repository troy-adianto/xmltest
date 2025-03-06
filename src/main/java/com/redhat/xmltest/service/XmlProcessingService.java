package com.redhat.xmltest.service;

import com.redhat.xmltest.model.Company;
import com.redhat.xmltest.model.Employee;
import com.redhat.xmltest.model.Companies;


import com.redhat.xmltest.repository.CompanyRepository;
import com.redhat.xmltest.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class XmlProcessingService {

    private static final Logger logger = LoggerFactory.getLogger(XmlProcessingService.class);


    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Company> processXml(String filePath) throws JAXBException {
        long startTime = System.currentTimeMillis();

        // Create JAXB context and unmarshaller
        JAXBContext context = JAXBContext.newInstance(Companies.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        // Unmarshal XML file to Java objects (List of Company)
        Companies companies = null;
        for (int i=0;i<500;i++){
        File xmlFile = new File(filePath);
        companies = (Companies) unmarshaller.unmarshal(xmlFile);
        }
        
        for (Company company : companies.getCompanies()) {
            companyRepository.save(company);
            for (Employee employee : company.getEmployees()) {
                employeeRepository.save(employee);
            }
        }
        // Save companies and employees to the database
        // for (Company company : companies.getCompanies()) {
        //     Company savedCompany = companyRepository.save(company);

        //     for (Employee employee : company.getEmployees()) {
        //         employee.setId(null); // Ensure employee's ID is null so it gets saved as a new record
        //         employeeRepository.save(employee);
        //     }
        // }
        // Calculate and log processing time
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        logger.info("XML processing completed in {} ms", duration);
        return companies.getCompanies();
    }
}


