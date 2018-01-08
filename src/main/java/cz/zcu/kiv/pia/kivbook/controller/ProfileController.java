package cz.zcu.kiv.pia.kivbook.controller;

import cz.zcu.kiv.pia.kivbook.dto.FriendDto;
import cz.zcu.kiv.pia.kivbook.dto.PostDto;
import cz.zcu.kiv.pia.kivbook.dto.UserDto;
import cz.zcu.kiv.pia.kivbook.service.FriendService;
import cz.zcu.kiv.pia.kivbook.service.PostService;
import cz.zcu.kiv.pia.kivbook.service.UserService;
import cz.zcu.kiv.pia.kivbook.service.auth.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Controller of the profile page.
 *
 * @author Jaroslav Klaus
 */
@RestController
@Slf4j
public class ProfileController {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private PostService postService;

	@Autowired
	private UserService userService;

	@Autowired
	private FriendService friendService;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss").withZone(ZoneOffset.UTC);

	@GetMapping("/profile")
	public ModelAndView showProfile() {
		log.debug("Entering showProfile method.");
		UserDto user = userService.getUser(securityService.getLoggedInUsername());
		List<UserDto> friends = friendService.getFriends(user);

		ModelAndView modelAndView = new ModelAndView("/profile", "posts", postService.getPostsForUser(user));
		modelAndView.addObject("friends", friends);
		modelAndView.addObject("user", user);
		modelAndView.addObject("profile", user);
		modelAndView.addObject("formatter", formatter);

		return modelAndView;
	}

	@GetMapping(value = "/profile", params = "user")
	public ModelAndView showProfile(@RequestParam("user") String username) {
		log.debug("Entering showProfile method for {}.", username);
		UserDto user = userService.getUser(securityService.getLoggedInUsername());
		if (user.getUsername().equals(username)) {
			return new ModelAndView("redirect:/profile");
		}
		UserDto profile = userService.getUser(username);
		List<UserDto> friends = friendService.getFriends(profile);
		FriendDto friendship = friendService.areFriends(user, profile);
		Set<PostDto> posts = postService.getPostsForUser(profile);
		if ((friendship == null || !friendship.getAck()) && !user.getUsername().equals(username)) {
			posts = posts.stream().filter(postDto -> !postDto.getPrivacy()).collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(PostDto::getCreated).reversed())));
		}

		ModelAndView modelAndView = new ModelAndView("/profile", "profile", profile);
		modelAndView.addObject("user", user);
		modelAndView.addObject("friends", friends);
		modelAndView.addObject("posts", posts);
		if (friendship != null) {
			modelAndView.addObject("friendshipStatus", friendship.getAck());
		}
		modelAndView.addObject("formatter", formatter);

		return modelAndView;
	}

}
