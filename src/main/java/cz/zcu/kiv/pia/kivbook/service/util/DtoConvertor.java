package cz.zcu.kiv.pia.kivbook.service.util;

import java.util.List;

/**
 * Interface for converting entities to dtos and vice versa.
 *
 * @author Jaroslav Klaus
 */
public interface DtoConvertor {

	<T> T map(Object source, Class<T> destinationClass);

	<T> List<T> map(List<?> sourceList, Class<T> destinationClass);

}
