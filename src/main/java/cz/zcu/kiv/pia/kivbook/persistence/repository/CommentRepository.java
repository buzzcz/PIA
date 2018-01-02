package cz.zcu.kiv.pia.kivbook.persistence.repository;

import cz.zcu.kiv.pia.kivbook.persistence.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository used to access User table.
 *
 * @author Jaroslav Klaus
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	List<Comment> findByPostId(Integer postId);

}
