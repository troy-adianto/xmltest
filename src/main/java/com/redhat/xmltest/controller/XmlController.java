package com.redhat.xmltest.controller;

import com.redhat.xmltest.model.Company;
import com.redhat.xmltest.service.XmlProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
public class XmlController {

    @Autowired
    private XmlProcessingService xmlProcessingService;

    @Operation(summary = "Process XML File", description = "This endpoint processes the XML file and stores the data into the database.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully processed XML file"),
        @ApiResponse(responseCode = "500", description = "Error processing XML file")
    })
    @GetMapping("/process-xml/{fileName}")
    public List<Company> processXmlFile(@Parameter(description = "The XML file name to process") @PathVariable String fileName) {
        try {
            List<Company> companies = null;
            // Assuming the XML file is located in the resources directory
            for (int i=0;i<100;i++){
            String filePath = "src/main/resources/" + fileName + ".xml";
            companies = xmlProcessingService.processXml(filePath);
            }
            return companies;  // Return List<Company> instead of single Company
        } catch (Exception e) {
            throw new RuntimeException("Error processing XML file", e);
        }
    }
}
