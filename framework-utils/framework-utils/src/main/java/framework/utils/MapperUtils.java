package framework.utils;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class MapperUtils {

	private static final Mapper mapper = new DozerBeanMapper(); 
	
	public static Mapper getMapper() {
		return mapper;
	}
}
