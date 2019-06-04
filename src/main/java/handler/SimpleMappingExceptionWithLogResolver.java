package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class SimpleMappingExceptionWithLogResolver extends SimpleMappingExceptionResolver {
	private Logger logger = LoggerFactory.getLogger(SimpleMappingExceptionWithLogResolver.class);
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		this.logger.error(ex.getMessage());
		return super.resolveException(request, response, handler, ex);
	}
}
