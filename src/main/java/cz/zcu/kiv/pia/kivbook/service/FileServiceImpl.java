package cz.zcu.kiv.pia.kivbook.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.UUID;

/**
 * Implementation of FileService.
 *
 * @author Jaroslav Klaus
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {

	private static final Path UPLOAD_DIR = Paths.get("upload-dir");

	@PostConstruct
	public void init() {
		if (!UPLOAD_DIR.toFile().exists()) {
			try {
				Files.createDirectory(UPLOAD_DIR);
			} catch (IOException e) {
				String msg = "Cannot create directory for uploading pictures";
				log.error(msg);
				throw new UncheckedIOException(msg, e);
			}
		}
	}

	@Override
	public Resource load(String filename) {
		try {
			Path file = UPLOAD_DIR.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				log.error("Could not read file {}.", filename);
				throw new IllegalArgumentException(String.format("Could not read file %s.", filename));
			}
		} catch (MalformedURLException e) {
			log.error("Could not read file {}.", filename);
			throw new IllegalArgumentException(String.format("Could not read file %s.", filename), e);
		}
	}

	@Override
	public String save(MultipartFile file) {
		try {
			if (file.isEmpty()) {
				log.error("Failed to store an empty file.");
				throw new IllegalArgumentException("Failed to store an empty file.");
			}

			String filename = StringUtils.cleanPath(String.format("%s%s.%s", Instant.now().toString(), UUID.randomUUID
					().toString(), FilenameUtils.getExtension(file.getOriginalFilename())));
			Files.copy(file.getInputStream(), UPLOAD_DIR.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
			filename = UPLOAD_DIR.resolve(filename).toString();

			return filename;
		} catch (IOException e) {
			log.error(String.format("Failed to save a file %s", file.getOriginalFilename()), e);
			throw new IllegalArgumentException("Failed to save a picture.", e);
		}
	}

}
