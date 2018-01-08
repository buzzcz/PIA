package cz.zcu.kiv.pia.kivbook.service;

import cz.zcu.kiv.pia.kivbook.dto.CommentDto;

/**
 * Interface for operations with comments.
 *
 * @author Jaroslav Klaus
 */
public interface CommentService {

	CommentDto save(CommentDto comment);

}
