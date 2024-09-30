package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

@RequestMapping(value="/admin")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtService jwtService;

    @PostMapping(value="/addAdmin")
    public void addAdmin(@RequestBody Admin admin) {
        adminService.addAdmin(admin);
    }

    @GetMapping(value="/showAdmin")
    public List<Admin> showAdmin() {
        return adminService.showAdmin();
    }

    @GetMapping(value="/adminLogin/{user}/{password}")
    public int adminLogin(@PathVariable String user, @PathVariable String password) {
        return adminService.login(user, password);
    }

    @GetMapping(value="/searchByUserAdmin/{user}")
    public Admin searchByUserName(@PathVariable String user) {
        return adminService.searchByUserName(user);
    }

    @GetMapping(value="/searchAdmin/{id}")
    public Admin searchAdmin(@PathVariable int id) {
        return adminService.searchAdmin(id);
    }
    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
        	System.out.println("Success");
            return jwtService.generateToken(authRequest.getUsername());
       } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
    
    @GetMapping("/user/{username}")
    public ResponseEntity<UserDetails> getUserByUsername(@PathVariable String username) {
        try {
            UserDetails userDetails = adminService.loadUserByUsername(username);
            System.out.println("Success");
            return ResponseEntity.ok(userDetails);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
