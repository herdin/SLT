package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestErrorController {
	
	private final Logger logger = LoggerFactory.getLogger(TestErrorController.class);
	
	@RequestMapping(value="error404")
	public ModelAndView handleError() {
		return new ModelAndView("error/error");
	}//END OF FUNCTION
	
	
	
}//END OF CLASS
