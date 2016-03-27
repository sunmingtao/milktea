package com.milktea.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.milktea.dao.ShopDao;
import com.milktea.entity.Shop;

@Service
public class ShopUserServiceImpl implements UserDetailsService {

	@Autowired
	private ShopDao shopDao;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Shop shop = shopDao.findShopById(username);
		List<GrantedAuthority> authorities = getAuthorities();
		User user = new User(username, shop.getPassword(), authorities);
		return user;
	}
	
	private List<GrantedAuthority> getAuthorities() {
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(authority);
		return authorities;
	}
}
