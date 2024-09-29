package com.fitness.aplicacion.mapeo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;


/**
 * Utilidades para el mapeo de objetos usando ModelMapper.
 *
 * @version 1.0
 * */
public class ObjectMapperUtils {
	
	private static final ModelMapper modelMapper;
	
	 static {
	        modelMapper = new ModelMapper();
	        //modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	    }

	/**
	 * Mapea una colección de objetos de una clase a otra.
	 *
	 * @param inputList la colección de objetos de entrada
	 * @param outCLass  la clase de los objetos de salida
	 * @param <D>       el tipo de los objetos de salida
	 * @param <T>       el tipo de los objetos de entrada
	 * @return una lista de objetos mapeados a la clase de salida
	 * */
	public static <D, T> List<D> mapAll(final Collection<T> inputList, Class<D> outCLass)  {
		        return inputList.stream()
		                .map(input -> map(input, outCLass))
		                .collect(Collectors.toList());
	}

	/**
	 * Mapea un objeto de una clase a otra.
	 *
	 * @param input    el objeto de entrada
	 * @param outClass la clase del objeto de salida
	 * @param <D>      el tipo del objeto de salida
	 * @param <T>      el tipo del objeto de entrada
	 * @return el objeto mapeado a la clase de salida
	 * */
	public static <D, T> D map(final T input, Class<D> outClass) {
	        return modelMapper.map(input, outClass);
	    }

}