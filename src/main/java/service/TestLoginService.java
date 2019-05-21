package service;

import java.util.ArrayList;
import java.util.Collection;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vo.TestUser;

@Service("testLoginService")
public class TestLoginService implements UserDetailsService {
	private Logger logger = LoggerFactory.getLogger(TestLoginService.class);
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("USER CHECK");
		TestUser param = new TestUser();
		param.setUsername(username);
		
		TestUser result = sqlSessionTemplate.selectOne("testUser.get", param);
		if(result == null) {
			throw new UsernameNotFoundException("TEMPLATE USERNAME NOT FOUND MESSAGE");
		}
		
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>(); 
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		UserDetails user = new User(result.getUsername(), result.getPassword(), authorities);
		return user;
	}
}//END OF CLASS