package cz.zcu.kiv.pia.kivbook.persistence.service;

import cz.zcu.kiv.pia.kivbook.dto.PostDto;
import cz.zcu.kiv.pia.kivbook.persistence.entity.Post;
import cz.zcu.kiv.pia.kivbook.persistence.repository.PostRepository;
import cz.zcu.kiv.pia.kivbook.service.util.DtoConvertor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jaroslav Klaus
 */
public class PostPersistenceServiceImpl implements PostPersistenceService {

	@Autowired
	private PostRepository repository;

	@Autowired
	private DtoConvertor mapper;

	@Override
	public List<PostDto> getAll(Integer userId) {
		List<Post> posts = repository.findByUserId(userId);
		List<PostDto> postDtos = new LinkedList<>();
		for (Post p : posts) {
			postDtos.add(mapper.map(p, PostDto.class));
		}

		return postDtos;
	}

	@Override
	public List<PostDto> getPublic(Integer userId) {
		List<Post> posts = repository.findByUserIdAndPrivacyFalse(userId);
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
