package com.chiletel.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.AbstractConverter;

import com.chiletel.entity.TipoDaño;

//Mapear propiedad de List<TipoDaño> a List<String> para que solo se vean los nombres de tipo de daño. 
public class TDañosListConverter extends AbstractConverter<Set<TipoDaño>, Set<String>> {

    @Override
    protected Set<String> convert(Set<TipoDaño> daños) {

        return daños
          .stream()
          .map(TipoDaño::getNombre)
          .collect(Collectors.toSet());
    }
}
