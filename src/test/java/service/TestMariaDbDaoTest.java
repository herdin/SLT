package service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.TestMariaDbDao;
import vo.TestVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations = {
    "classpath:spring/application-config-test.xml"
	,"classpath:spring/database-config-local-mariadb.xml"
//    ,"classpath:spring/mvc-config.xml"
})
public class TestMariaDbDaoTest {
	private Logger logger = LoggerFactory.getLogger(TestMariaDbDaoTest.class);
	@Autowired
	private TestMariaDbDao testMariaDbDao;
	private TestVo param;
	
	@Before
	public void setup() {
		logger.info("BEFORE SETUP COUNT : " + this.testMariaDbDao.count());
		TestVo tp = new TestVo();
		tp.setId("10001");
		List<TestVo> vos = this.testMariaDbDao.selectList(tp);
		for(TestVo vo : vos) {
			logger.info("SELECT : " + vo);
			this.testMariaDbDao.delete(vo);
		}
		logger.info("AFTER SETUP COUNT : " + this.testMariaDbDao.count());
		
		this.param = new TestVo();
		param.setId("TEST_ID_01");
		param.setDe("TEST_DE_01");
	}
	
	@Test
	public void insert() {

		int insertCount = this.testMariaDbDao.insert(this.param);
		logger.info("INSERT RESULT : {}", insertCount);
		logger.info("AFTER INSERT COUNT : " + this.testMariaDbDao.count());
		
		TestVo result = this.testMariaDbDao.selectOne(this.param);
		
		assertThat(this.param.getId(), is(result.getId()));
	}
	
	@Test
	public void select() {
		
		this.insert();
		
		TestVo result = this.testMariaDbDao.selectOne(this.param);
		assertThat(result.getId(), is(result.getId()));
		
		List<TestVo> resultList = this.testMariaDbDao.selectList(null);
		for(TestVo testVo : resultList) {
			logger.info("ID({}), DE({})", testVo.getId(), testVo.getDe());
		}
		
		int count = this.testMariaDbDao.count();
		assertThat(resultList.size(), is(count));
		
	}
	
	@Test
	public void update() {
		this.insert();
		this.param.setDe(this.param.getDe() + "_MOD");
		this.testMariaDbDao.update(param);
		TestVo result = this.testMariaDbDao.selectOne(param);
		
		assertThat(result.getDe(), is(this.param.getDe()));
		
	}
}
