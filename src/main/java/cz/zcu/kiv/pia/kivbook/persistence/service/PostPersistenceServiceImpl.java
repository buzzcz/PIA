package cz.zcu.kiv.pia.kivbook.persistence.service;

import cz.zcu.kiv.pia.kivbook.dto.PostDto;
import cz.zcu.kiv.pia.kivbook.dto.UserDto;
import cz.zcu.kiv.pia.kivbook.persistence.entity.Post;
import cz.zcu.kiv.pia.kivbook.persistence.entity.User;
import cz.zcu.kiv.pia.kivbook.persistence.repository.PostRepository;
import cz.zcu.kiv.pia.kivbook.service.util.DtoConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

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
	public PostDto get(Integer postId) {
		Post post = repository.findOne(postId);
		if (post != null) {
			return mapper.map(post, PostDto.class);
		}

		return null;
	}

	@Override
	public Set<PostDto> getAll(UserDto owner) {
		User user = mapper.map(owner, User.class);
		Set<Post> posts = new TreeSet<>(Comparator.comparing(Post::getCreated).reversed());
		posts.addAll(repository.findByOwnerOrderByCreatedDesc(user));

		return mapper.map(posts, PostDto.class);
	}

	@Override
	public Set<PostDto> getAll(UserDto owner, int pageNumber, int pageSize) {
		User user = mapper.map(owner, User.class);
		Set<Post> posts = new TreeSet<>(Comparator.comparing(Post::getCreated).reversed());
		posts.addAll(repository.findByOwnerOrderByCreatedDesc(user, new PageRequest(pageNumber, pageSize)).getContent
				());

		return mapper.map(posts, PostDto.class);
	}

	@Override
	public Set<PostDto> getAllPublic(int pageNumber, int pageSize) {
		Set<Post> posts = new TreeSet<>(Comparator.comparing(Post::getCreated).reversed());
		posts.addAll(repository.findByPrivacyFalseOrderByCreatedDesc(new PageRequest(pageNumber, pageSize)).getContent
				());

		return mapper.map(posts, PostDto.class);
	}

	@Override
	public PostDto save(PostDto post) {
		Post entity = mapper.map(post, Post.class);
		entity = repository.save(entity);

		return mapper.map(entity, PostDto.class);
	}

}
