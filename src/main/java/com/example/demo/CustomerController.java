package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping(value="/customer")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	
	@PostMapping(value="/addCustomer")
	public void addCustomer(@RequestBody Customer customer) {
		customerService.addCustomer(customer);
	}
	@GetMapping(value="/showCustomer")
	public List<Customer> showCustomer() {
		return customerService.showCustomer();
	}
	
	@GetMapping(value="/customerLogin/{user}/{password}")
	public int customerLogin(@PathVariable String user, @PathVariable String password) {
		return customerService.login(user, password);
	}
	
	@GetMapping(value="/searchByUser/{user}")
	public Customer searchByUserName(@PathVariable String user) {
		return customerService.searchByUserName(user);
	}
	@GetMapping(value="/searchCustomer/{id}")
	public Customer searchCustomer(@PathVariable int id) {
		return customerService.searchCustomer(id);
	}
	@DeleteMapping(value="/deleteCustomer/{id}")
	public void deleteCustomer(@PathVariable int id) {
	    customerService.deleteCustomer(id);
	}
}
