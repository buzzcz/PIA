package cz.zcu.kiv.pia.kivbook.service.auth;

/**
 * Interface for service to provide currently logged in user and auto-login after registration.
 *
 * @author Jaroslav Klaus
 */
public interface SecurityService {

	String getLoggedInUsername();

	void autologin(String username, String password);

}
