package cz.zcu.kiv.pia.kivbook.controller;

import cz.zcu.kiv.pia.kivbook.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller of the login page.
 *
 * @author Jaroslav Klaus
 */
@RestController
@Slf4j
public class LoginController {

	@GetMapping({"/", "/login"})
	public ModelAndView showLogin() {
		log.debug("Entering showLogin method.");
		// TODO: Redirect somewhere else if logged in.
		return new ModelAndView("login");
	}

	@PostMapping("/authenticate")
	public ModelAndView authenticate(@RequestBody UserDto user) {
		log.debug("Entering authenticate method.");
		log.debug(user.toString());
		// TODO: Return correct view based on authentication success.
		return new ModelAndView("login");
	}

	@PostMapping("/register")
	public ModelAndView register(@RequestBody UserDto user) {
		log.debug("Entering register method.");
		log.debug(user.toString());
		//TODO: Return proper view.
		return new ModelAndView("login");
	}

}
