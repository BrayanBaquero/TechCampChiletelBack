package com.chiletel.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.AbstractConverter;

import com.chiletel.entity.TipoDa�o;

//Mapear propiedad de List<TipoDa�o> a List<String> para que solo se vean los nombres de tipo de da�o. 
public class TDa�osListConverter extends AbstractConverter<Set<TipoDa�o>, Set<String>> {

    @Override
    protected Set<String> convert(Set<TipoDa�o> da�os) {

        return da�os
          .stream()
          .map(TipoDa�o::getNombre)
          .collect(Collectors.toSet());
    }
}
