package cz.zcu.kiv.pia.kivbook.persistence.repository;

import cz.zcu.kiv.pia.kivbook.persistence.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository used to access User table.
 *
 * @author Jaroslav Klaus
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUserId(Integer userId);

	List<Post> findByUserIdAndPrivacyFalse(Integer userId);

}
