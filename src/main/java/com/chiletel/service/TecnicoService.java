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
import com.chiletel.entity.TipoDa�o;
import com.chiletel.exceptionHandler.BadRequestException;
import com.chiletel.exceptionHandler.NotFoundException;
import com.chiletel.mapper.MapperTecnico;
import com.chiletel.repository.ICuadrillaRepository;
import com.chiletel.repository.ITecnicoRepository;
import com.chiletel.repository.ITipoDa�oRepository;
@Service
public class TecnicoService implements ITecnicoService{
	@Autowired
	private ITecnicoRepository tecnicoRepo;
	@Autowired
	private ITipoDa�oRepository tipoDa�oRepo;
	@Autowired
	private ICuadrillaRepository cuadrillaRepo;
	@Autowired
	private MapperTecnico mapperTecnico;
	
	
	
	
	@Override
	public List<TecnicoDTO> obtenerTecnicos() {
		List<Tecnico> tecnico=tecnicoRepo.getAllTecnicosActivos();
		List<TecnicoDTO> tecnicoDTO=mapperTecnico.ToDTOs(tecnico);
		return tecnicoDTO;
	}

	@Override
	public void AddTecnico(TecnicoDTO tecnicoDTO) {
		if(tecnicoDTO.getTDano().isEmpty())
			throw new BadRequestException("El tecnico debe tener al menos un tipo de da�o asociado");
		
		Tecnico tecnico= mapperTecnico.ToEntity(tecnicoDTO);
		Set<TipoDa�o> tda�os=new HashSet<>();
		tecnicoDTO.getTDano().forEach(da�o->{
			tda�os.add(tipoDa�oRepo.findByNombre(da�o).get());
		});
		tecnico.setCuadrilla(cuadrillaRepo.findByNombre(tecnicoDTO.getCuadrilla()).get());
		tecnico.setTDa�o(tda�os);
		try {
			tecnicoRepo.save(tecnico);
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException("Ya existe un tecnico con el mismo numero de identificacion");
		}
		
		
	}

	@Override
	public void UpdateTecnico(int ident,TecnicoDTO tecnicoDTO) {
		if(!tecnicoRepo.existsBynumeroIden(ident))
			throw new NotFoundException("Tecnico no existe");
		if(tecnicoDTO.getTDano().isEmpty())
			throw new BadRequestException("Debe haber al menos un tipo de da�o asociado");
		if(!cuadrillaRepo.existsByNombre(tecnicoDTO.getCuadrilla()))
			throw new BadRequestException("Cuadrilla no existe");
		
		Optional<Tecnico> temp=tecnicoRepo.findBynumeroIden(ident);
		//Mapeo de dto a entidad-------------------------
		Tecnico tecnico=mapperTecnico.ToEntity(tecnicoDTO);
		Set<TipoDa�o> tda�os=new HashSet<>();
		tecnicoDTO.getTDano().forEach(da�o->{
			tda�os.add(tipoDa�oRepo.findByNombre(da�o).get());
		});
		tecnico.setCuadrilla(cuadrillaRepo.findByNombre(tecnicoDTO.getCuadrilla()).get());
		tecnico.setTDa�o(tda�os);
		tecnico.setId(temp.get().getId());
		//-----------------------------------------------
		tecnicoRepo.save(tecnico);
	}

	@Override
	public void DeleteTecnico(int ident) {
		if(!tecnicoRepo.existsBynumeroIden(ident))
			throw new NotFoundException("Tecnico no existe");
		Optional<Tecnico> tecnico=tecnicoRepo.findBynumeroIden(ident);
		tecnico.get().setBorrado(1);
		tecnico.get().setTDa�o(null);
		tecnicoRepo.save(tecnico.get());
	}

	@Override
	public TecnicoDTO getTecnicoByIdentificacion(int ident) {
		Optional<Tecnico> tecnico= tecnicoRepo.findBynumeroIden(ident);
		if(!tecnico.isPresent()) {
			throw new NotFoundException("El usuario con numero de identificacion no existe");
		}
		return mapperTecnico.ToDTO(tecnico.get()); 
	}

	

	

}
