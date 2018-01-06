package cz.zcu.kiv.pia.kivbook.controller;

import cz.zcu.kiv.pia.kivbook.dto.UserDto;
import cz.zcu.kiv.pia.kivbook.persistence.service.UserPersistenceService;
import cz.zcu.kiv.pia.kivbook.service.auth.SecurityService;
import cz.zcu.kiv.pia.kivbook.service.auth.UserValidatorImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

	@Autowired
	private UserPersistenceService userPersistenceService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidatorImpl userValidator;

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
		return new ModelAndView("redirect:login");
	}

	@PostMapping("/register")
	public ModelAndView register(@RequestBody UserDto user, BindingResult bindingResult, Model model) {
		log.debug("Entering register method.");
		log.debug(user.toString());
		userValidator.validate(user, bindingResult);
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("login");
			modelAndView.getModel().putAll(model.asMap());

			return modelAndView;
		}

		userPersistenceService.save(user);
		securityService.autologin(user.getUsername(), user.getPassword());

		//TODO: Return proper view.
		return new ModelAndView("redirect:/profile");
	}

}
