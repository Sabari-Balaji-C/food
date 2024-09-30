package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
@Service
public class AdminService implements UserDetailsService  {

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Lazy
    @Autowired
	private PasswordEncoder encoder;

    public List<Admin> showAdmin() {
        return adminRepo.findAll();
    }

    public void addAdmin(Admin admin) {
    	admin.setPassword(encoder.encode(admin.getPassword()));
        adminRepo.save(admin);
    }

    public int login(String user, String pwd) {
        String cmd = "select count(*) cnt from Admin where USERNAME = ? AND PASSWORD = ?";
        List<Object> counts = jdbcTemplate.query(cmd, new Object[] {user, pwd}, new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                Object ob = rs.getInt("cnt");
                return ob;
            }
        });
        int i = (Integer) counts.get(0);
        return i;
    }

    public Admin searchByUserName(String userName) {
        String cmd = "select * from Admin where USERNAME = ?";
        List<Admin> adminList = jdbcTemplate.query(cmd, new Object[] {userName}, new RowMapper<Admin>() {
            @Override
            public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
                Admin admin = new Admin();
                admin.setAdminId(rs.getInt("ADMIN_ID"));
                admin.setAdminName(rs.getString("ADMIN_NAME"));
                admin.setAdminPhoneNo(rs.getString("ADMIN_PHN_NO"));
                admin.setUsername(rs.getString("USERNAME"));
                admin.setPassword(rs.getString("PASSWORD"));
                admin.setAdminEmail(rs.getString("ADMIN_EMAIL"));
                return admin;
            }
        });

        if (adminList.size() == 1) {
            return adminList.get(0);
        }
        return null;
    }

    public Admin searchAdmin(int id) {
        Optional<Admin> admin = adminRepo.findById(id);
        return admin.orElse(null);
    }
    
    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		  Optional<Admin> userDetail = adminRepo.findByAdminname(username);

	        // Converting userDetail to EmployDetails
	        return userDetail.map(AdminDetails::new)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
	}
}
