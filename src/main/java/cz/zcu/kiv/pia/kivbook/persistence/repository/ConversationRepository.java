package cz.zcu.kiv.pia.kivbook.persistence.repository;

import cz.zcu.kiv.pia.kivbook.persistence.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository used to access User table.
 *
 * @author Jaroslav Klaus
 */
@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Integer> {

	List<Conversation> findByUserId1(Integer userId);

}
