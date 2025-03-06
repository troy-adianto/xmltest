package com.redhat.xmltest.repository;

import com.redhat.xmltest.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    // You can define custom queries if needed, for example:
    // List<Company> findByName(String name);
}
