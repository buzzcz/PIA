package cz.zcu.kiv.pia.kivbook.service;

import cz.zcu.kiv.pia.kivbook.dto.CommentDto;
import cz.zcu.kiv.pia.kivbook.persistence.service.CommentPersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of CommentService.
 *
 * @author Jaroslav Klaus
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentPersistenceService commentPersistenceService;

	@Override
	public CommentDto save(CommentDto comment) {
		log.debug("Saving comment {}.", comment);

		return commentPersistenceService.save(comment);
	}
}
