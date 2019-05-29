package controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import service.TestCrudService;
import util.CommonUtils;
import vo.TestDataVo;

@Controller
@RequestMapping(value="/noauth/crud")
public class TestCrudController {
	
	private final Logger logger = LoggerFactory.getLogger(TestCrudController.class);
	
	@Autowired
	private TestCrudService testCrudService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value="/")
	public ModelAndView test(@RequestParam Map<String,Object> map, HttpServletRequest request) {
		logger.debug(CommonUtils.getUrl(request));
		logger.debug("url : {}" , new String(request.getRequestURL()));
		
		ModelAndView mav = new ModelAndView("crudTest");
		mav.addObject("message", testCrudService.testService());
		return mav;
	}//END OF FUNCTION
	
	@RequestMapping(value="/list")
	public @ResponseBody List<TestDataVo> testList(TestDataVo testDataVo) {
		this.logger.debug("recv vo : " + testDataVo);
		return this.testCrudService.selectListTestData(testDataVo);
	}//END OF FUNCTION
	
	@RequestMapping(value="/info")
	public ModelAndView info(@RequestParam Map<String,Object> map, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("showMessage");
		mav.addObject("message", testCrudService.testService());
		return mav;
	}//END OF FUNCTION
	
	@RequestMapping(value="/add")
	public @ResponseBody int add(TestDataVo testDataVo) {
		this.logger.debug("recv vo : " + testDataVo);
		return this.testCrudService.addTestData(testDataVo);
	}//END OF FUNCTION
	
	@RequestMapping(value="/del")
	public @ResponseBody int del(TestDataVo testDataVo) {
		this.logger.debug("recv vo : " + testDataVo);
		return this.testCrudService.deleteTestData(testDataVo);
	}//END OF FUNCTION
	
	@RequestMapping(value="/mod")
	public @ResponseBody int mod(TestDataVo testDataVo) {
		this.logger.debug("recv vo : " + testDataVo);
		return this.testCrudService.updateTestData(testDataVo);
	}//END OF FUNCTION
	
}//END OF CLASS
