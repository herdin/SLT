package service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.TestDataDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations = {
	    "classpath:spring/application-config.xml"
		,"classpath:spring/database-config.xml"
})
public class TestMariaDbDaoTest {
	
	private Logger logger = LoggerFactory.getLogger(TestMariaDbDaoTest.class);
	
	@Autowired
	private TestDataDao testDataDao;
	
	@Before
	public void setup() {
		logger.info("BEFORE SETUP COUNT : {}");
	}
	
	@Test
	public void insert() {
	}
	
	@Test
	public void select() {
	}
	
	@Test
	public void update() {
	}
	
	public void delete() {
		
	}
}
