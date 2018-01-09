package cz.zcu.kiv.pia.kivbook.persistence.repository;

import cz.zcu.kiv.pia.kivbook.persistence.entity.Post;
import cz.zcu.kiv.pia.kivbook.persistence.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	List<Post> findByOwnerOrderByCreatedDesc(User owner);

	Page<Post> findByOwnerOrderByCreatedDesc(User owner, Pageable pageable);

	Page<Post> findByPrivacyFalseOrderByCreatedDesc(Pageable pageable);

}
