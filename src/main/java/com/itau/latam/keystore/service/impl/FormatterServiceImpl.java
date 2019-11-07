package com.itau.latam.keystore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;

public class FormatterServiceImpl {
	public static <T> T convertDTOToEntity(Object value, Class<T> type) {
		return parser(type,value);
	}

	public static <O, D> List<D> convertEntityToDTO(List<O> originalObjects, Class<D> destinationObject) {
		List<D> destinationObjects = new ArrayList<D>();
		for (Object originalObject : originalObjects) {
			destinationObjects.add(parser(destinationObject, originalObject));
		}
		return destinationObjects;
	}

	private static <D> D parser(Class<D> destinationObject, Object originalObject) {
		return new DozerBeanMapper().map(originalObject, destinationObject);
	}
}
