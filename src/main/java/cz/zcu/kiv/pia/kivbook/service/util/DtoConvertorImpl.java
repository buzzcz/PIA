package cz.zcu.kiv.pia.kivbook.service.util;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Service for converting entities to dtos and vice versa.
 *
 * @author Jaroslav Klaus
 */
@Service
public class DtoConvertorImpl implements DtoConvertor {

	@Autowired
	private DozerBeanMapper mapper;

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

}
