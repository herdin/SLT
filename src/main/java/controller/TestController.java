package controller;

import java.util.Map;

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

@Controller
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private TestServices testServices;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value="/test")
	public ModelAndView loginInit(@RequestParam Map<String,Object> map) {
		logger.debug("loginInit()");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("message", testServices.testService());
		mav.setViewName("showMessage");
		return mav;
	}//END OF FUNCTION

}//END OF CLASS
