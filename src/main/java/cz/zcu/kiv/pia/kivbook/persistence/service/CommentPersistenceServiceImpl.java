package cz.zcu.kiv.pia.kivbook.persistence.service;

import cz.zcu.kiv.pia.kivbook.dto.CommentDto;
import cz.zcu.kiv.pia.kivbook.persistence.entity.Comment;
import cz.zcu.kiv.pia.kivbook.persistence.repository.CommentRepository;
import cz.zcu.kiv.pia.kivbook.service.util.DtoConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jaroslav Klaus
 */
@Service
public class CommentPersistenceServiceImpl implements CommentPersistenceService {

	@Autowired
	private CommentRepository repository;

	@Autowired
	private DtoConvertor mapper;

	@Override
	public List<CommentDto> getAll(Integer postId) {
		List<Comment> comments = repository.findByPostId(postId);

		return mapper.map(comments, CommentDto.class);
	}

	@Override
	public CommentDto save(CommentDto comment) {
		Comment entity = mapper.map(comment, Comment.class);
		entity = repository.save(entity);

		return mapper.map(entity, CommentDto.class);
	}

}
