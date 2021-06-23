package com.chiletel.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.AbstractConverter;

import com.chiletel.entity.TipoDaño;
import com.chiletel.entity.Zona;

public class ZonasListConverter extends AbstractConverter<Set<Zona>, Set<String>> {

	@Override
	protected Set<String> convert(Set<Zona> source) {
		// TODO Auto-generated method stub
		return source
		          .stream()
		          .map(Zona::getNombre)
		          .collect(Collectors.toSet());
	}

}
