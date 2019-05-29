package com.codegym.cms.repository;

import com.codegym.cms.model.Customer;

import java.util.List;

public interface CustomerRepository extends Repository {
    List<Customer> findAll();

    Customer findById(Long id);

    void save(Customer model);

    void remove(Long id);
}
