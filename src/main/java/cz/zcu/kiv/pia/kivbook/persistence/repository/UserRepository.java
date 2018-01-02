package cz.zcu.kiv.pia.kivbook.persistence.repository;

import cz.zcu.kiv.pia.kivbook.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository used to access User table.
 *
 * @author Jaroslav Klaus
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
