package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/auth")
public class TestAuthController {
	private static final Logger logger = LoggerFactory.getLogger(TestAuthController.class);
	public static String storedState;
	
	@RequestMapping(value="loginForm", method=RequestMethod.GET)
	public ModelAndView loginInit(
		@RequestParam(value="error", required=false) String error,
		@RequestParam(value="logout", required=false) String logout) {
		logger.debug("loginInit()");
		ModelAndView mav = new ModelAndView();
		if(error !=  null) {
			mav.addObject("loginError", "TEMPLATE ERROR MESSAGE");
		}
		if(logout != null) {
			mav.addObject("logout", "TEMPLATE LOGOUT MESSAGE");
		}
		mav.setViewName("loginForm");
		return mav;
	}//END OF FUNCTION
	
	@RequestMapping(value="main")
	public ModelAndView mainInit() {
		ModelAndView mav = new ModelAndView("main");
		return mav;
	}
}//END OF CLASS
