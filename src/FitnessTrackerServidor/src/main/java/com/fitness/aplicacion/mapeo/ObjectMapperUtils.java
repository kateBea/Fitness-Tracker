package com.fitness.aplicacion.mapeo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;


public class ObjectMapperUtils {
	
	private static final ModelMapper modelMapper;
	
	 static {
	        modelMapper = new ModelMapper();
	        //modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	    }
	

	public static <D, T> List<D> mapAll(final Collection<T> inputList, Class<D> outCLass)
	{
		        return inputList.stream()
		                .map(input -> map(input, outCLass))
		                .collect(Collectors.toList());
	}
	
	 public static <D, T> D map(final T input, Class<D> outClass) {
	        return modelMapper.map(input, outClass);
	    }

}