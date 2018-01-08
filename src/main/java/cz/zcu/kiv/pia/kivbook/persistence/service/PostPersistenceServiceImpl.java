package cz.zcu.kiv.pia.kivbook.persistence.service;

import cz.zcu.kiv.pia.kivbook.dto.PostDto;
import cz.zcu.kiv.pia.kivbook.dto.UserDto;
import cz.zcu.kiv.pia.kivbook.persistence.entity.Post;
import cz.zcu.kiv.pia.kivbook.persistence.entity.User;
import cz.zcu.kiv.pia.kivbook.persistence.repository.PostRepository;
import cz.zcu.kiv.pia.kivbook.service.util.DtoConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jaroslav Klaus
 */
@Service
public class PostPersistenceServiceImpl implements PostPersistenceService {

	@Autowired
	private PostRepository repository;

	@Autowired
	private DtoConvertor mapper;

	@Override
	public List<PostDto> getAll(UserDto owner) {
		User user = mapper.map(owner, User.class);
		List<Post> posts = repository.findByOwnerOrderByCreatedDesc(user);
		List<PostDto> postDtos = new LinkedList<>();
		for (Post p : posts) {
			postDtos.add(mapper.map(p, PostDto.class));
		}

		return postDtos;
	}

	@Override
	public List<PostDto> getPublic(UserDto owner) {
		User user = mapper.map(owner, User.class);
		List<Post> posts = repository.findByOwnerAndPrivacyFalseOrderByCreatedDesc(user);
		List<PostDto> postDtos = new LinkedList<>();
		for (Post p : posts) {
			postDtos.add(mapper.map(p, PostDto.class));
		}

		return postDtos;
	}

	@Override
	public List<PostDto> getAllPublic() {
		List<Post> posts = repository.findByPrivacyFalseOrderByCreatedDesc();
		List<PostDto> postDtos = new LinkedList<>();
		for (Post p : posts) {
			postDtos.add(mapper.map(p, PostDto.class));
		}

		return postDtos;
	}

	@Override
	public PostDto save(PostDto post) {
		Post entity = mapper.map(post, Post.class);
		entity = repository.save(entity);

		return mapper.map(entity, PostDto.class);
	}

}
