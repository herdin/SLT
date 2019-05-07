package controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import service.TestServices;
import util.CommonUtils;

@Controller
@RequestMapping(value="/test")
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private TestServices testServices;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value="/")
	public ModelAndView test(@RequestParam Map<String,Object> map, HttpServletRequest request) {
		logger.debug(CommonUtils.getUrl(request));
		
		ModelAndView mav = new ModelAndView("showMessage");
		mav.addObject("message", testServices.testService());
		return mav;
	}//END OF FUNCTION
	
	@RequestMapping(value="/list")
	public ModelAndView testList(@RequestParam Map<String,Object> map, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView("showMessage");
		mav.addObject("message", testServices.testService());
		return mav;
	}//END OF FUNCTION
	
	@RequestMapping(value="/info")
	public ModelAndView info(@RequestParam Map<String,Object> map, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("showMessage");
		mav.addObject("message", testServices.testService());
		return mav;
	}//END OF FUNCTION
	
	@RequestMapping(value="/add")
	public ModelAndView add(@RequestParam Map<String,Object> map, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("showMessage");
		mav.addObject("message", testServices.testService());
		return mav;
	}//END OF FUNCTION
	
	@RequestMapping(value="/del")
	public ModelAndView del(@RequestParam Map<String,Object> map, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("showMessage");
		mav.addObject("message", testServices.testService());
		return mav;
	}//END OF FUNCTION
	
	@RequestMapping(value="/mod")
	public ModelAndView mod(@RequestParam Map<String,Object> map, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("showMessage");
		mav.addObject("message", testServices.testService());
		return mav;
	}//END OF FUNCTION
	
}//END OF CLASS
