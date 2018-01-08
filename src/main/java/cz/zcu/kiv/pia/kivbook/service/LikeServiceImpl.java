package cz.zcu.kiv.pia.kivbook.service;

import cz.zcu.kiv.pia.kivbook.dto.LikeDto;
import cz.zcu.kiv.pia.kivbook.persistence.service.LikePersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

/**
 * Implementation of LikeService.
 *
 * @author Jaroslav Klaus
 */
@Service
@Slf4j
public class LikeServiceImpl implements LikeService {

	@Autowired
	private LikePersistenceService likePersistenceService;

	@Override
	public LikeDto save(LikeDto like) {
		log.debug("Saving like {}.", like);

		return likePersistenceService.save(like);
	}

	@Override
	@Modifying
	public void remove(Integer likeId) {
		log.debug("Removing like {}.", likeId);
		likePersistenceService.remove(likeId);
	}

}
