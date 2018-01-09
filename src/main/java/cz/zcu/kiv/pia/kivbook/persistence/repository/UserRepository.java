package cz.zcu.kiv.pia.kivbook.persistence.repository;

import cz.zcu.kiv.pia.kivbook.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository used to access User table.
 *
 * @author Jaroslav Klaus
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);

	List<User> findByUsernameLike(String text);

	List<User> findByFirstNameLike(String text);

	List<User> findByLastNameLike(String text);

	List<User> findByEmailLike(String text);

}
