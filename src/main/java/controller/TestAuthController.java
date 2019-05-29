package controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/auth")
public class TestAuthController {
	private final Logger logger = LoggerFactory.getLogger(TestAuthController.class);
	@Autowired
	private MessageSource messageSource;
	private final String DEFAULT_MESSAGE = "DEFAULT_MESSAGE";
	
	@RequestMapping(value="loginForm", method=RequestMethod.GET)
	public ModelAndView loginInit(
		Locale locale,
		@RequestParam(value="error", required=false) String error,
		@RequestParam(value="logout", required=false) String logout) {
		this.logger.info("current locale : {}", locale.getLanguage());
		ModelAndView mav = new ModelAndView();
		if(error !=  null) {
			mav.addObject("errorMessage", messageSource.getMessage("login.fail.default", null, DEFAULT_MESSAGE, locale));
		}
		if(logout != null) {
			mav.addObject("logoutMessage", messageSource.getMessage("logout.default", null, DEFAULT_MESSAGE, locale));
		}
		mav.setViewName("loginForm");
		return mav;
	}//END OF FUNCTION
	
	@RequestMapping(value="main")
	public ModelAndView mainInit(Locale locale) {
		this.logger.info("");
		ModelAndView mav = new ModelAndView("main");
		mav.addObject("loginSuccessMessage", messageSource.getMessage("login.success.default", null, DEFAULT_MESSAGE, locale));
		return mav;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String loout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/auth/loginForm?logout";
	}

}//END OF CLASS
