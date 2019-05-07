package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.TestDao;
import vo.TestVO;

@Service
public class TestServices {
	
	private Logger logger = LoggerFactory.getLogger(TestServices.class);
	
	@Autowired
	private TestDao testDao;
	
	public String testService() {
		logger.debug("test()");
		TestVO testVo = testDao.testDao();
		String time = (testVo==null)? "null":testVo.getCurrentDatetime();
		return time;
	}//END OF FUNCTION
	
}//END OF CLASS
