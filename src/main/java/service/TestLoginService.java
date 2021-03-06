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

import config.SecurityConfig.ROLE;
import vo.TestUserVo;

@Service("testLoginService")
public class TestLoginService implements UserDetailsService {
	private Logger logger = LoggerFactory.getLogger(TestLoginService.class);
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("USER CHECK");
		TestUserVo param = new TestUserVo();
		param.setUsername(username);
		
		TestUserVo result = sqlSessionTemplate.selectOne("testUser.get", param);
		if(result == null) {
			throw new UsernameNotFoundException("TEMPLATE USERNAME NOT FOUND MESSAGE");
		}
		
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>(); 
		authorities.add(new SimpleGrantedAuthority(ROLE.USER.getAuthString()));
		
		UserDetails user = new User(result.getUsername(), result.getPassword(), authorities);
		return user;
	}
}//END OF CLASS