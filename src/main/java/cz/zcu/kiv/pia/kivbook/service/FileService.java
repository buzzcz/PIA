package cz.zcu.kiv.pia.kivbook.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * Interface for operations with files.
 *
 * @author Jaroslav Klaus
 */
public interface FileService {

	String save(MultipartFile file);

	Resource load(String filename);

}
