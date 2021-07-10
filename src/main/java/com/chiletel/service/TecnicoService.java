package com.chiletel.service;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
	private ITecnicoRepository tecnicoRepo;
	@Autowired
	private ITipoDañoRepository tipoDañoRepo;
	@Autowired
	private ICuadrillaRepository cuadrillaRepo;
	@Autowired
	private MapperTecnico mapperTecnico;
	
	
	
	
	@Override
	public Page<TecnicoDTO> obtenerTecnicos(Pageable pageable) {
		Page<Tecnico> tecnico=tecnicoRepo.getAllTecnicosActivos(pageable);
		List<TecnicoDTO> tecnicoDTO=mapperTecnico.ToDTOs(tecnico.getContent());
		return new PageImpl<TecnicoDTO>(tecnicoDTO,pageable,tecnico.getTotalElements());
	}

	@Override
	public void AddTecnico(TecnicoDTO tecnicoDTO) {
		if(tecnicoDTO.getTDano().isEmpty())
			throw new BadRequestException("El tecnico debe tener al menos un tipo de daño asociado");
		
		Tecnico tecnico= mapperTecnico.ToEntity(tecnicoDTO);
		Set<TipoDaño> tdaños=new HashSet<>();
		tecnicoDTO.getTDano().forEach(daño->{
			tdaños.add(tipoDañoRepo.findByNombre(daño).get());
		});
		tecnico.setCuadrilla(cuadrillaRepo.findByNombre(tecnicoDTO.getCuadrilla()).get());
		tecnico.setTDaño(tdaños);
		try {
			tecnicoRepo.save(tecnico);
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException("Ya existe un tecnico con el mismo numero de identificacion");
		}
		
		
	}

	@Override
	public void UpdateTecnico(BigInteger ident,TecnicoDTO tecnicoDTO) {
		if(!tecnicoRepo.existsBynumeroIden(ident))
			throw new NotFoundException("Tecnico no existe");
		if(tecnicoDTO.getTDano().isEmpty())
			throw new BadRequestException("Debe haber al menos un tipo de daño asociado");
		if(!cuadrillaRepo.existsByNombre(tecnicoDTO.getCuadrilla()))
			throw new BadRequestException("Cuadrilla no existe");
		
		Optional<Tecnico> temp=tecnicoRepo.findBynumeroIden(ident);
		//Mapeo de dto a entidad-------------------------
		Tecnico tecnico=mapperTecnico.ToEntity(tecnicoDTO);
		Set<TipoDaño> tdaños=new HashSet<>();
		tecnicoDTO.getTDano().forEach(daño->{
			tdaños.add(tipoDañoRepo.findByNombre(daño).get());
		});
		tecnico.setCuadrilla(cuadrillaRepo.findByNombre(tecnicoDTO.getCuadrilla()).get());
		tecnico.setTDaño(tdaños);
		tecnico.setId(temp.get().getId());
		//-----------------------------------------------
		tecnicoRepo.save(tecnico);
	}

	@Override
	public void DeleteTecnico(BigInteger ident) {
		if(!tecnicoRepo.existsBynumeroIden(ident))
			throw new NotFoundException("Tecnico no existe");
		Optional<Tecnico> tecnico=tecnicoRepo.findBynumeroIden(ident);
		tecnico.get().setBorrado(1);
		tecnico.get().setTDaño(null);
		tecnicoRepo.save(tecnico.get());
	}

	@Override
	public TecnicoDTO getTecnicoByIdentificacion(BigInteger ident) {
		Optional<Tecnico> tecnico= tecnicoRepo.findBynumeroIden(ident);
		if(!tecnico.isPresent()) {
			throw new NotFoundException("El usuario con numero de identificacion no existe");
		}
		return mapperTecnico.ToDTO(tecnico.get()); 
	}

	

	

}
