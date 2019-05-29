package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.TestDao;
import dao.TestDataDao;
import vo.TestDataVo;

@Service
public class TestCrudService {
	
	private Logger logger = LoggerFactory.getLogger(TestCrudService.class);
	
	@Autowired
	private TestDao testDao;
	@Autowired
	private TestDataDao testDataDao;
	
	public String testService() {
		this.logger.debug("test()");
		return testDao.getTime();
	}//END OF FUNCTION
	
	public int addTestData(TestDataVo testDataVo) {
		this.logger.debug("");
		return testDataDao.insert(testDataVo);
	}
	
	public TestDataVo selectOneTestData(TestDataVo testDataVo) {
		this.logger.debug("");
		return testDataDao.selectOne(testDataVo);
	}
	
	public List<TestDataVo> selectListTestData(TestDataVo testDataVo) {
		this.logger.debug("");
		return testDataDao.selectList(testDataVo);
	}
	
	public int updateTestData(TestDataVo testDataVo) {
		this.logger.debug("");
		return testDataDao.update(testDataVo);
	}
	
	public int deleteTestData(TestDataVo testDataVo) {
		this.logger.debug("");
		return testDataDao.delete(testDataVo);
	}
	
}//END OF CLASS
