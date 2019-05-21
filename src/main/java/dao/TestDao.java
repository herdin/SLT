package dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import vo.TestVO;

@Component
public class TestDao {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public TestVO testDao() {
		return sqlSessionTemplate.selectOne("testUser.getTime");
	}//END OF FUNCTION
	
}//END OF CLASS
