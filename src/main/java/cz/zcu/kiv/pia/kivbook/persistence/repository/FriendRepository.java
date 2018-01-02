package cz.zcu.kiv.pia.kivbook.persistence.repository;

import cz.zcu.kiv.pia.kivbook.persistence.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository used to access User table.
 *
 * @author Jaroslav Klaus
 */
@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {

	List<Friend> findByUserId1(Integer userId);

	Friend findByUserId1AndUserId2(Integer userId1, Integer userId2);

}
