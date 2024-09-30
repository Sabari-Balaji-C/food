package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value="/vendor")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class VendorController {
	
	@Autowired
	private VendorService vendorService;
	
	
	@PostMapping(value="/addVendor")
	public void addVendor(@RequestBody Vendor vendor) {
		vendorService.addVendor(vendor);
	}
	
	@GetMapping(value="/showVendor")
	public List<Vendor> showVendor() {
		return vendorService.showVendor();
	}
	
	@GetMapping(value="/vendorLogin/{user}/{password}")
	public int vendorLogin(@PathVariable String user, @PathVariable String password) {
		return vendorService.login(user, password);
	}
	
	@GetMapping(value="/searchByVendor/{user}")
	public Vendor searchByVendorName(@PathVariable String user) {
		return vendorService.searchByVendorName(user);
	}
	@GetMapping(value="/searchVendor/{id}")
	public Vendor searchVendor(@PathVariable int id) {
		return vendorService.searchVendor(id);
	}

}
