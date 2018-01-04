package cz.zcu.kiv.pia.kivbook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller of the login page.
 * @author Jaroslav Klaus
 */
@RestController
@Slf4j
public class LoginController {

	@RequestMapping(path = "/login")
	public ModelAndView showLogin() {
		return new ModelAndView("login");
	}

}
