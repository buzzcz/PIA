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
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
		log.debug("Entering showMessages method");
		UserDto user = userService.getUser(securityService.getLoggedInUsername());
		Set<ConversationDto> conversations = conversationService.getAllForUser(user);
		List<UserDto> friends = userService.getUsersFromConversations(conversations, user);
		Set<MessageDto> messages = new TreeSet<>();
		Integer conversationId = null;
		if (!friends.isEmpty()) {
			friends.get(0).setSelected(true);
			ConversationDto c = conversations.iterator().next();
			messages = messageService.getAllForConversation(c);
			conversationId = c.getId();
		}

		ModelAndView modelAndView = new ModelAndView("/messages", "user", user);
		modelAndView.addObject("conversations", friends);
		modelAndView.addObject("messages", messages);
		modelAndView.addObject("conversationId", conversationId);
		modelAndView.addObject("message", new MessageDto());
		modelAndView.addObject("formatter", formatter);

		return modelAndView;
	}

	@GetMapping(value = "/messages", params = "user")
	public ModelAndView showMessages(@RequestParam("user") String username) {
		log.debug("Entering showMessages method for {}.", username);
		UserDto user = userService.getUser(securityService.getLoggedInUsername());
		if (user.getUsername().equals(username)) {
			return new ModelAndView("redirect:/messages");
		}

		Set<ConversationDto> conversations = conversationService.getAllForUser(user);
		List<UserDto> friends = userService.getUsersFromConversations(conversations, user);
		Set<MessageDto> messages = new TreeSet<>(Comparator.comparing(MessageDto::getCreated));
		Integer conversationId = loadMessages(conversations, friends, messages, username);

		if (conversationId == null) {
			ConversationDto c = new ConversationDto();
			UserDto f = userService.getUser(username);
			f.setSelected(true);
			friends.add(0, f);
			c.setUser1(user);
			c.setUser2(f);
			c.setCreated(Instant.now());
			c = conversationService.save(c);
			conversations.add(c);
			conversationId = c.getId();
			messages.addAll(messageService.getAllForConversation(c));
		}

		ModelAndView modelAndView = new ModelAndView("/messages", "user", user);
		modelAndView.addObject("conversations", friends);
		modelAndView.addObject("messages", messages);
		modelAndView.addObject("conversationId", conversationId);
		modelAndView.addObject("message", new MessageDto());
		modelAndView.addObject("formatter", formatter);

		return modelAndView;
	}

	private Integer loadMessages(Set<ConversationDto> conversations, List<UserDto> friends, Set<MessageDto> messages,
	                             String username) {
		Integer retVal = null;
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
					messages.addAll(messageService.getAllForConversation(c));
					retVal = c.getId();
					break;
				}
			}
		}

		return retVal;
	}

	@PostMapping("/new-message")
	public ModelAndView newMessage(@ModelAttribute MessageDto message) {
		log.debug("Entering newMessage method");
		UserDto user = userService.getUser(securityService.getLoggedInUsername());
		message.setOwner(user);
		message.setCreated(Instant.now());
		ConversationDto conversation = conversationService.get(message.getConversationId());
		if (conversation != null) {
			conversation.setCreated(Instant.now());
			conversationService.save(conversation);
			messageService.save(message);
		}

		return new ModelAndView("redirect:/messages");
	}

}
