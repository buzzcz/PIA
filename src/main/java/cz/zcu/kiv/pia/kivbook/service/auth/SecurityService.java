package cz.zcu.kiv.pia.kivbook.service.auth;

import org.springframework.web.servlet.ModelAndView;

/**
 * Interface for service to provide currently logged in user and auto-login after registration.
 *
 * @author Jaroslav Klaus
 */
public interface SecurityService {

	String getLoggedInUsername();

	boolean authenticate(String username, String password);

	ModelAndView checkAuthenticated();

}
