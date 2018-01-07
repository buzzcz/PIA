package cz.zcu.kiv.pia.kivbook.controller;

import cz.zcu.kiv.pia.kivbook.dto.UserDto;
import cz.zcu.kiv.pia.kivbook.service.FriendService;
import cz.zcu.kiv.pia.kivbook.service.PostService;
import cz.zcu.kiv.pia.kivbook.service.UserService;
import cz.zcu.kiv.pia.kivbook.service.auth.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

	@Autowired
	private PostService postService;

	@Autowired
	private UserService userService;

	@Autowired
	private FriendService friendService;

	@GetMapping("/feed")
	public ModelAndView showFeed() {
		log.debug("Entering showFeed method.");
		UserDto user = userService.getUser(securityService.getLoggedInUsername());
		List<UserDto> friends = friendService.getFriends(user);
		ModelAndView modelAndView = new ModelAndView("/feed", "posts", postService.getPostsForUserAndFriends(user,
				friends));
		modelAndView.addObject("friends", friends);
		modelAndView.addObject("user", user);

		return modelAndView;
	}

}
