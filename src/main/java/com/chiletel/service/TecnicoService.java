package com.chiletel.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import com.chiletel.dto.TecnicoDTO;
import com.chiletel.entity.Tecnico;
import com.chiletel.entity.TipoDaño;
import com.chiletel.exceptionHandler.BadRequestException;
import com.chiletel.exceptionHandler.NotFoundException;
import com.chiletel.mapper.MapperTecnico;
import com.chiletel.repository.ICuadrillaRepository;
import com.chiletel.repository.ITecnicoRepository;
import com.chiletel.repository.ITipoDañoRepository;
@Service
public class TecnicoService implements ITecnicoService{
	@Autowired
	private ITecnicoRepository iTecnicoRepository;
	@Autowired
	private ITipoDañoRepository ITipoDañoRepository;
	@Autowired
	private ICuadrillaRepository iCuadrillaRepository;
	@Autowired
	private MapperTecnico mapperTecnico;
	
	
	
	
	@Override
	public List<TecnicoDTO> obtenerTecnicos() {
		List<Tecnico> tecnico=iTecnicoRepository.findAllTecnicosActivos();
		List<TecnicoDTO> tecnicoDTO=mapperTecnico.ToDTOs(tecnico);
		return tecnicoDTO;
	}

	@Override
	public void AddTecnico(TecnicoDTO tecnicoDTO) {
		if(tecnicoDTO.getTDano().isEmpty())
			throw new BadRequestException("El tecnico debe tener al menos un tipo de daño asociado");
		
		Tecnico tecnico= mapperTecnico.ToEntity(tecnicoDTO);
		Set<TipoDaño> tdaños=new HashSet<>();
		tecnicoDTO.getTDano().forEach(daño->{
			tdaños.add(ITipoDañoRepository.findByNombre(daño).get());
		});
		tecnico.setCuadrilla(iCuadrillaRepository.findByNombre(tecnicoDTO.getCuadrilla()).get());
		tecnico.setTDaño(tdaños);
		try {
			iTecnicoRepository.save(tecnico);
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException("Ya existe un tecnico con el mismo numero de identificacion");
		}
		
		
	}

	@Override
	public void UpdateTecnico(int ident,TecnicoDTO tecnicoDTO) {
		if(!iTecnicoRepository.existsBynumeroIden(ident))
			throw new NotFoundException("Tecnico no existe");
		if(tecnicoDTO.getTDano().isEmpty())
			throw new BadRequestException("Debe haber al menos un tipo de daño asociado");
		if(!iCuadrillaRepository.existsByNombre(tecnicoDTO.getCuadrilla()))
			throw new BadRequestException("Cuadrilla no existe");
		
		Optional<Tecnico> temp=iTecnicoRepository.findBynumeroIden(ident);
		//Mapeo de dto a entidad-------------------------
		Tecnico tecnico=mapperTecnico.ToEntity(tecnicoDTO);
		Set<TipoDaño> tdaños=new HashSet<>();
		tecnicoDTO.getTDano().forEach(daño->{
			tdaños.add(ITipoDañoRepository.findByNombre(daño).get());
		});
		tecnico.setCuadrilla(iCuadrillaRepository.findByNombre(tecnicoDTO.getCuadrilla()).get());
		tecnico.setTDaño(tdaños);
		tecnico.setId(temp.get().getId());
		//-----------------------------------------------
		iTecnicoRepository.save(tecnico);
	}

	@Override
	public void DeleteTecnico(int ident) {
		if(!iTecnicoRepository.existsBynumeroIden(ident))
			throw new NotFoundException("Tecnico no existe");
		Optional<Tecnico> tecnico=iTecnicoRepository.findBynumeroIden(ident);
		tecnico.get().setBorrado(1);
		iTecnicoRepository.save(tecnico.get());
	}

	@Override
	public TecnicoDTO getTecnicoByIdentificacion(int ident) {
		Optional<Tecnico> tecnico= iTecnicoRepository.findBynumeroIden(ident);
		if(!tecnico.isPresent()) {
			throw new NotFoundException("El usuario con numero de identificacion no existe");
		}
		return mapperTecnico.ToDTO(tecnico.get()); 
	}

	

	

}
