package com.monmar.springdemo.service;

import java.util.List;

import com.monmar.springdemo.entity.*;

public interface CustomerService {
	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);

	public Customer getCustomer(int id);

	public void deleteCustomer(int id);

}
