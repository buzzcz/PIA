package cz.zcu.kiv.pia.kivbook.controller;

import cz.zcu.kiv.pia.kivbook.dto.ConversationDto;
import cz.zcu.kiv.pia.kivbook.dto.MessageDto;
import cz.zcu.kiv.pia.kivbook.dto.UserDto;
import cz.zcu.kiv.pia.kivbook.service.ConversationService;
import cz.zcu.kiv.pia.kivbook.service.MessageService;
import cz.zcu.kiv.pia.kivbook.service.UserService;
import cz.zcu.kiv.pia.kivbook.service.auth.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Controller of the messages page.
 *
 * @author Jaroslav Klaus
 */
@RestController
@Slf4j
public class MessagesController {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserService userService;

	@Autowired
	private ConversationService conversationService;

	@Autowired
	private MessageService messageService;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss").withZone(ZoneOffset.UTC);

	@GetMapping("/messages")
	public ModelAndView showMessages() {
		UserDto user = userService.getUser(securityService.getLoggedInUsername());
		List<ConversationDto> conversations = conversationService.getAllForUser(user);
		List<UserDto> friends = userService.getUsersFromConversations(conversations, user);
		Set<MessageDto> messages = new HashSet<>();
		if (!friends.isEmpty()) {
			friends.get(0).setSelected(true);
			messages = messageService.getAllForConversation(conversations.get(0));
		}

		ModelAndView modelAndView = new ModelAndView("/messages", "user", user);
		modelAndView.addObject("conversations", friends);
		modelAndView.addObject("messages", messages);
		modelAndView.addObject("conversationId", conversations.get(0).getId());
		modelAndView.addObject("message", new MessageDto());
		modelAndView.addObject("formatter", formatter);

		return modelAndView;
	}

	@GetMapping(value = "/messages", params = "user")
	public ModelAndView showMessagesForUser(@RequestParam("user") String username) {
		UserDto user = userService.getUser(securityService.getLoggedInUsername());
		List<ConversationDto> conversations = conversationService.getAllForUser(user);
		List<UserDto> friends = userService.getUsersFromConversations(conversations, user);
		Set<MessageDto> messages = new HashSet<>();
		if (!friends.isEmpty()) {
			UserDto f = null;
			for (UserDto u : friends) {
				if (u.getUsername().equals(username)) {
					u.setSelected(true);
					f = u;
					break;
				}
			}

			if (f != null) {
				for (ConversationDto c : conversations) {
					if (c.getUser1().equals(f) || c.getUser2().equals(f)) {
						messages = messageService.getAllForConversation(c);
						break;
					}
				}
			}
		}

		ModelAndView modelAndView = new ModelAndView("/messages", "user", user);
		modelAndView.addObject("conversations", friends);
		modelAndView.addObject("messages", messages);
		modelAndView.addObject("conversationId", conversations.get(0).getId());
		modelAndView.addObject("message", new MessageDto());
		modelAndView.addObject("formatter", formatter);

		return modelAndView;
	}

	@PostMapping("/new-message")
	public ModelAndView newMessage(@ModelAttribute MessageDto message) {
		UserDto user = userService.getUser(securityService.getLoggedInUsername());
		message.setOwner(user);
		message.setCreated(Instant.now());

		messageService.save(message);

		return new ModelAndView("redirect:/messages");
	}

}
