package mum.edu.mumscrum.interceptor;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import edu.mum.mumscrum.entity.Employee;
import edu.mum.mumscrum.service.EmployeeService;

public class SecurityInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = Logger.getLogger(SecurityInterceptor.class);
	@Autowired
	EmployeeService employeeService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {

			Principal principal = request.getUserPrincipal();
			if (principal != null) {
				int sessionLoginId = request.getSession().getAttribute("loginId") != null
						? (int) request.getSession().getAttribute("loginId") : 0;
				
				logger.info("---:::" + sessionLoginId + "::---");
				if (sessionLoginId == 0) {
					Employee employee = employeeService.getEmployeeDetail(principal.getName());
					request.getSession().setAttribute("loginId", employee.getId());
					request.getSession().setAttribute("emailId", employee.getEmail());

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("---:::" + e.getMessage() + ":::---");
		}
		return super.preHandle(request, response, handler);

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// System.out.println("Calling afterCompletion");
		super.afterCompletion(request, response, handler, ex);
	}

}
