package cz.zcu.kiv.pia.kivbook.persistence.service;

import cz.zcu.kiv.pia.kivbook.dto.LikeDto;
import cz.zcu.kiv.pia.kivbook.persistence.entity.Like;
import cz.zcu.kiv.pia.kivbook.persistence.repository.LikeRepository;
import cz.zcu.kiv.pia.kivbook.service.DtoConvertor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jaroslav Klaus
 */
public class LikePersistenceServiceImpl implements LikePersistenceService {

	@Autowired
	private LikeRepository repository;

	@Autowired
	private DtoConvertor mapper;

	@Override
	public List<LikeDto> getAll(Integer postId) {
		List<Like> likes = repository.findByPostId(postId);
		List<LikeDto> likeDtos = new LinkedList<>();
		for (Like l : likes) {
			likeDtos.add(mapper.map(l, LikeDto.class));
		}

		return likeDtos;
	}

	@Override
	public LikeDto save(LikeDto like) {
		Like entity = mapper.map(like, Like.class);
		entity = repository.save(entity);

		return mapper.map(entity, LikeDto.class);
	}

}
