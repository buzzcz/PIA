package cz.zcu.kiv.pia.kivbook.service.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Jaroslav Klaus
 */
@Service
@Slf4j
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public String getLoggedInUsername() {
		log.debug("Entering getLoggedInUsername method.");

		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
		if (userDetails instanceof UserDetails) {
			return ((UserDetails) userDetails).getUsername();
		}

		return null;
	}

	@Override
	public boolean authenticate(String username, String password) {
		log.debug("Entering authenticate method.");

		try {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password,
					userDetails.getAuthorities());

			if (authenticate(token)) {
				return true;
			}
		} catch (UsernameNotFoundException e) {
			log.error("UserDetalsService failure: {}.", e.getMessage());
		}
		log.debug("Authentication failed, user {} not authenticated.", username);

		return false;
	}

	public ModelAndView checkAuthenticated() {
		log.debug("Entering checkAuthenticated method.");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof
				AnonymousAuthenticationToken)) {
			return new ModelAndView("redirect:/feed");
		}

		return null;
	}

	private boolean authenticate(UsernamePasswordAuthenticationToken token) {
		try {
			authenticationManager.authenticate(token);

			if (token.isAuthenticated()) {
				log.debug("Authentication successful, user {} logged in.", token.getName());
				SecurityContextHolder.getContext().setAuthentication(token);

				return true;
			}
		} catch (AuthenticationException e) {
			log.error("Authentication failure: {}.", e.getMessage());
		}
		return false;
	}
}
