package cz.zcu.kiv.pia.kivbook.controller;

import cz.zcu.kiv.pia.kivbook.service.auth.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller of the feed page.
 *
 * @author Jaroslav Klaus
 */
@RestController
@Slf4j
public class FeedController {

	@Autowired
	private SecurityService securityService;

	@GetMapping("/feed")
	public ModelAndView showFeed() {
		log.debug("Entering showFeed method.");

		return new ModelAndView("/feed");
	}

}
