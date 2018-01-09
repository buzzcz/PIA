package cz.zcu.kiv.pia.kivbook.controller;

import cz.zcu.kiv.pia.kivbook.dto.CommentDto;
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
		Set<PostDto> posts = postService.getPostsForUser(user);
		for (PostDto p : posts) {
			if (p.getLikes().stream().anyMatch(likeDto -> likeDto.getOwner().equals(user))) {
				p.setLiked(true);
			}
		}

		ModelAndView modelAndView = new ModelAndView("/profile", "posts", posts);
		modelAndView.addObject("friends", friends);
		modelAndView.addObject("user", user);
		modelAndView.addObject("profile", user);
		modelAndView.addObject("comment", new CommentDto());
		modelAndView.addObject("myProfile", true);
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
		for (PostDto p : posts) {
			if (p.getLikes().stream().anyMatch(likeDto -> likeDto.getOwner().equals(user))) {
				p.setLiked(true);
			}
		}

		ModelAndView modelAndView = new ModelAndView("/profile", "profile", profile);
		modelAndView.addObject("user", user);
		modelAndView.addObject("friends", friends);
		modelAndView.addObject("posts", posts);
		modelAndView.addObject("comment", new CommentDto());
		if (friendship != null) {
			modelAndView.addObject("friendship", friendship);
		}
		modelAndView.addObject("formatter", formatter);

		return modelAndView;
	}

	@GetMapping("/new-friendship")
	public ModelAndView newFriendship(@RequestParam("user") String username) {
		UserDto user = userService.getUser(securityService.getLoggedInUsername());
		if (user.getUsername().equals(username)) {
			return new ModelAndView("redirect:/profile");
		}

		UserDto friend = userService.getUser(username);
		FriendDto friendship = friendService.areFriends(user, friend);
		if (friendship != null) {
			return new ModelAndView("redirect:/profile?user=" + username);
		}

		friendship = new FriendDto();
		friendship.setUserId1(user.getId());
		friendship.setUserId2(friend.getId());
		friendship.setAck(false);
		friendService.save(friendship);

		return new ModelAndView("redirect:/profile?user=" + username);
	}

	@GetMapping("/ack-friendship")
	public ModelAndView ackFriendship(@RequestParam("user") String username) {
		UserDto user = userService.getUser(securityService.getLoggedInUsername());
		if (user.getUsername().equals(username)) {
			return new ModelAndView("redirect:/profile");
		}

		UserDto friend = userService.getUser(username);
		FriendDto friendship = friendService.areFriends(user, friend);
		if (friendship == null || !friendship.getUserId2().equals(user.getId())) {
			return new ModelAndView("redirect:/profile?user=" + username);
		}

		friendship.setAck(true);
		friendService.save(friendship);

		return new ModelAndView("redirect:/profile?user=" + username);
	}

}
