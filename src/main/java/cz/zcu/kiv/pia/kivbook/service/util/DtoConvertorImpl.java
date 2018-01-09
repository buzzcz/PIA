package cz.zcu.kiv.pia.kivbook.service.util;

import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.dozer.converters.ConversionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Service for converting entities to dtos and vice versa.
 *
 * @author Jaroslav Klaus
 */
@Service
@Slf4j
public class DtoConvertorImpl implements DtoConvertor {

	@Autowired
	private ApplicationContext ctx;

	@Value("classpath*:mappings.xml")
	private String mappings;

	private DozerBeanMapper mapper;

	@PostConstruct
	private void init() {
		mapper = new DozerBeanMapper();
		try {
			Resource[] resources = ctx.getResources(mappings);
			for (Resource r : resources) {
				mapper.addMapping(r.getInputStream());
			}
		} catch (IOException e) {
			log.error("Error during loading mappings.xml.", e);
			throw new ConversionException(e);
		}
	}

	@Override
	public <T> T map(Object source, Class<T> destinationClass) {
		return mapper.map(source, destinationClass);
	}

	@Override
	public <T> List<T> map(List<?> sourceList, Class<T> destinationClass) {
		List<T> retVal = new LinkedList<>();
		for (Object o : sourceList) {
			retVal.add(mapper.map(o, destinationClass));
		}

		return retVal;
	}

	@Override
	public <T> Set<T> map(Set<?> sourceList, Class<T> destinationClass) {
		Set<T> retVal = new HashSet<>();
		for (Object o : sourceList) {
			retVal.add(mapper.map(o, destinationClass));
		}

		return retVal;
	}

}
