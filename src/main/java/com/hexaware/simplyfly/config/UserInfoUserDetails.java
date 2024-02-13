package com.hexaware.simplyfly.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hexaware.simplyfly.entities.Admin;
import com.hexaware.simplyfly.entities.Customer;
import com.hexaware.simplyfly.entities.User;

public class UserInfoUserDetails implements UserDetails {
	
	
	
	
	private String name;
	private String password;
	private List<GrantedAuthority> authorities;
	
	public UserInfoUserDetails(Customer customer) {
		this.name=customer.getUsername();
		this.password=customer.getPassword();
		this.authorities=Arrays.stream(customer.getRoles().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	public UserInfoUserDetails(User user) {
		this.name=user.getUsername();
		this.password=user.getPassword();
		this.authorities=Arrays.stream(user.getRole().toString().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}
	
	public UserInfoUserDetails(Admin admin) {
		
		this.name=admin.getUsername();
		this.password=admin.getPassword();
		this.authorities=Arrays.stream(admin.getRole().toString().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
