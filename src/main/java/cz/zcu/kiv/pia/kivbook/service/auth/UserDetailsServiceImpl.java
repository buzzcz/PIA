package cz.zcu.kiv.pia.kivbook.service.auth;

import cz.zcu.kiv.pia.kivbook.dto.UserDto;
import cz.zcu.kiv.pia.kivbook.enums.Gender;
import cz.zcu.kiv.pia.kivbook.persistence.service.UserPersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

/**
 * Service creating Spring UserDetails by username.
 *
 * @author Jaroslav Klaus
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserPersistenceService userPersistenceService;

	@EventListener(ApplicationReadyEvent.class)
	public void init() {
		if (userPersistenceService.get("user1") == null) {
			String[] lastNames = {"One", "Two", "Three"};
			for (int i = 0; i < 3; i++) {
				UserDto userDto = new UserDto();
				userDto.setUsername("user" + Integer.toString(i + 1));
				userDto.setFirstName("User");
				userDto.setLastName(lastNames[i]);
				userDto.setEmail(userDto.getUsername() + "@kivbook.com");
				userDto.setBirthday(LocalDate.of(1994, Month.of(i + 1), i + 1));
				userDto.setPassword("User@1234");
				userDto.setGender(Gender.MALE);

				userPersistenceService.save(userDto);
			}
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		log.debug("Entering loadUserByUsername method.");
		UserDto user = userPersistenceService.get(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("%s not found. User does not exist", username));
		}

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("user"));

		return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}

}
