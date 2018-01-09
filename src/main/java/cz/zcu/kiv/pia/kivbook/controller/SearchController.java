package cz.zcu.kiv.pia.kivbook.controller;

import cz.zcu.kiv.pia.kivbook.dto.SearchDto;
import cz.zcu.kiv.pia.kivbook.dto.UserDto;
import cz.zcu.kiv.pia.kivbook.service.UserService;
import cz.zcu.kiv.pia.kivbook.service.auth.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Controller of the search page.
 *
 * @author Jaroslav Klaus
 */
@RestController
@Slf4j
public class SearchController {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserService userService;

	@GetMapping("/search")
	public ModelAndView showSearch() {
		log.debug("Entering showSearch method.");
		UserDto user = userService.getUser(securityService.getLoggedInUsername());
		List<UserDto> result = new LinkedList<>();

		ModelAndView modelAndView = new ModelAndView("/search", "user", user);
		modelAndView.addObject("text", new SearchDto());
		modelAndView.addObject("result", result);

		return modelAndView;
	}

	@PostMapping("/search")
	public ModelAndView searchUsers(@ModelAttribute SearchDto text) {
		log.debug("Entering searchUsers method for {}.", text);
		Set<UserDto> result = new HashSet<>(userService.searchUsers(text.getText()));

		ModelAndView modelAndView = showSearch();
		modelAndView.addObject("text", text);
		modelAndView.addObject("result", result);

		return modelAndView;
	}

}
