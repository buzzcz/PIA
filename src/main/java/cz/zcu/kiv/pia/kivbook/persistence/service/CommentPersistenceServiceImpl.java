package cz.zcu.kiv.pia.kivbook.persistence.service;

import cz.zcu.kiv.pia.kivbook.dto.CommentDto;
import cz.zcu.kiv.pia.kivbook.persistence.entity.Comment;
import cz.zcu.kiv.pia.kivbook.persistence.repository.CommentRepository;
import cz.zcu.kiv.pia.kivbook.service.DtoConvertor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jaroslav Klaus
 */
public class CommentPersistenceServiceImpl implements CommentPersistenceService {

	@Autowired
	private CommentRepository repository;

	@Autowired
	private DtoConvertor mapper;

	@Override
	public List<CommentDto> getAll(Integer postId) {
		List<Comment> comments = repository.findByPostId(postId);
		List<CommentDto> commentDtos = new LinkedList<>();
		for (Comment c : comments) {
			commentDtos.add(mapper.map(c, CommentDto.class));
		}

		return commentDtos;
	}

	@Override
	public CommentDto save(CommentDto comment) {
		Comment entity = mapper.map(comment, Comment.class);
		entity = repository.save(entity);

		return mapper.map(entity, CommentDto.class);
	}

}
