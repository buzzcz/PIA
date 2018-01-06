package cz.zcu.kiv.pia.kivbook.controller;

import cz.zcu.kiv.pia.kivbook.dto.UserDto;
import cz.zcu.kiv.pia.kivbook.persistence.service.UserPersistenceService;
import cz.zcu.kiv.pia.kivbook.service.auth.SecurityService;
import cz.zcu.kiv.pia.kivbook.service.auth.UserValidatorImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
		ModelAndView modelAndView = new ModelAndView("login", "newUser", new UserDto());
		modelAndView.addObject("user", new UserDto());
		return modelAndView;
	}

	@PostMapping("/login")
	public ModelAndView authenticate(@ModelAttribute UserDto user) {
		log.debug("Entering authenticate method.");
		log.debug(user.toString());
		// TODO: Return correct view based on authentication success.
		return new ModelAndView("redirect:login");
	}

	@PostMapping("/register")
	public ModelAndView register(@ModelAttribute UserDto newUser, BindingResult bindingResult) {
		log.debug("Entering register method.");
		log.debug(newUser.toString());
		userValidator.validate(newUser, bindingResult);
		if (bindingResult.hasErrors()) {
			log.debug("Errors while validating registration: {}.", bindingResult.getAllErrors());
			ModelAndView modelAndView = new ModelAndView("login", bindingResult.getModel());
			newUser.setPassword("");
			newUser.setPasswordRepeat("");
			modelAndView.addObject("newUser", newUser);
			modelAndView.addObject("user", new UserDto());

			return modelAndView;
		}

		userPersistenceService.save(newUser);
		securityService.autologin(newUser.getUsername(), newUser.getPassword());

		//TODO: Return proper view.
		return new ModelAndView("redirect:/profile");
	}

}
