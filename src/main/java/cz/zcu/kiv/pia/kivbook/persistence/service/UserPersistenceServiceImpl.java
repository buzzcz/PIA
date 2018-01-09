package cz.zcu.kiv.pia.kivbook.persistence.service;

import cz.zcu.kiv.pia.kivbook.dto.UserDto;
import cz.zcu.kiv.pia.kivbook.persistence.entity.User;
import cz.zcu.kiv.pia.kivbook.persistence.repository.UserRepository;
import cz.zcu.kiv.pia.kivbook.service.util.DtoConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for working with User persistence.
 *
 * @author Jaroslav Klaus
 */
@Service
public class UserPersistenceServiceImpl implements UserPersistenceService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private DtoConvertor mapper;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public List<UserDto> getAll() {
		List<User> users = repository.findAll();
		return mapper.map(users, UserDto.class);
	}

	@Override
	public UserDto get(Integer id) {
		User user = repository.findOne(id);
		if (user != null) {
			return mapper.map(user, UserDto.class);
		}

		return null;
	}

	@Override
	public UserDto get(String username) {
		User user = repository.findByUsername(username);
		if (user != null) {
			return mapper.map(user, UserDto.class);
		}

		return null;
	}

	@Override
	public List<UserDto> searchUsers(String text) {
		String query = String.format("%s%s%s", "%", text, "%");
		List<User> users = repository.findByUsernameLike(query);
		users.addAll(repository.findByFirstNameLike(query));
		users.addAll(repository.findByLastNameLike(query));
		users.addAll(repository.findByEmailLike(query));

		return mapper.map(users, UserDto.class);
	}

	@Override
	public UserDto save(UserDto user) {
		User entity = mapper.map(user, User.class);
		entity.setPassword(passwordEncoder.encode(user.getPassword()));
		entity = repository.save(entity);
		return mapper.map(entity, UserDto.class);
	}
}
