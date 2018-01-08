package cz.zcu.kiv.pia.kivbook.persistence.repository;

import cz.zcu.kiv.pia.kivbook.persistence.entity.Message;
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
public interface MessageRepository extends JpaRepository<Message, Integer> {

	List<Message> findByConversationIdAndOwnerOrderByCreated(Integer conversationId, User owner);

}
