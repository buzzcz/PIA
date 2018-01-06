package cz.zcu.kiv.pia.kivbook.controller;

import cz.zcu.kiv.pia.kivbook.dto.UserDto;
import cz.zcu.kiv.pia.kivbook.persistence.service.UserPersistenceService;
import cz.zcu.kiv.pia.kivbook.service.auth.SecurityService;
import cz.zcu.kiv.pia.kivbook.service.auth.UserValidatorImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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
		ModelAndView modelAndView = new ModelAndView("/login", "newUser", new UserDto());
		modelAndView.addObject("user", new UserDto());
		return modelAndView;
	}

	@PostMapping("/login")
	public ModelAndView authenticate(@ModelAttribute UserDto user) {
		log.debug("Entering authenticate method.");
		log.debug(user.toString());
		// TODO: Return correct view based on authentication success.
		return new ModelAndView("redirect:/login");
	}

	@PostMapping("/register")
	public ModelAndView register(@ModelAttribute("newUser") UserDto newUser, BindingResult bindingResult) {
		log.debug("Entering register method.");
		userValidator.validate(newUser, bindingResult);
		if (bindingResult.hasErrors()) {
//			Clear password and passwordRepeat fields. We need to remove them from bindingResult because Spring sets
//              them back into the form also from there.
			BeanPropertyBindingResult result = new BeanPropertyBindingResult(newUser, bindingResult.getObjectName());
			for (ObjectError error : bindingResult.getGlobalErrors()) {
				result.addError(error);
			}
			for (FieldError error : bindingResult.getFieldErrors()) {
				if ("password".equals(error.getField()) || "passwordRepeat".equals(error.getField())) {
					result.addError(new FieldError(error.getObjectName(), error.getField(), null, error
							.isBindingFailure(), error.getCodes(), error.getArguments(), error.getDefaultMessage()));
				} else {
					result.addError(error);
				}
			}
			newUser.setPassword("");
			newUser.setPasswordRepeat("");

			ModelAndView modelAndView = new ModelAndView("/login", result.getModel());
			modelAndView.addObject("user", new UserDto());

			return modelAndView;
		}

		newUser = userPersistenceService.save(newUser);
		securityService.authenticate(newUser.getUsername(), newUser.getPassword());

		//TODO: Return proper view.
		return new ModelAndView("redirect:/feed");
	}

}
