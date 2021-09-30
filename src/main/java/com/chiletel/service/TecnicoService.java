package com.chiletel.service;

import java.util.HashSet;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.chiletel.dto.TecnicoDTO;
import com.chiletel.entity.Cuadrilla;
import com.chiletel.entity.Tecnico;
import com.chiletel.entity.TipoDano;
import com.chiletel.exceptionHandler.BadRequestException;
import com.chiletel.exceptionHandler.NotFoundException;
import com.chiletel.mapper.TecnicoMapper;
import com.chiletel.repository.ICuadrillaRepository;
import com.chiletel.repository.ITecnicoRepository;
import com.chiletel.repository.ITipoDanoRepository;

/**
 * <strong>Descrcipci�n:</strong>
 * Clase encargada de implementar los metodos definidos en {@link ITecnicoService}
 * @author Brayan Baquero
 */
@Service
public class TecnicoService implements ITecnicoService{
	@Autowired
	private ITecnicoRepository tecnicoRepo;
	@Autowired
	private ITipoDanoRepository tipoDanoRepo;
	@Autowired
	private ICuadrillaRepository cuadrillaRepo;
	@Autowired
	private TecnicoMapper mapperTecnico;
	
	
	@Override
	public Page<TecnicoDTO> getTecnicos(Pageable pageable) {
		Page<Tecnico> tecnico=tecnicoRepo.getAllTecnicosActivos(pageable);
		List<TecnicoDTO> tecnicoDTO=mapperTecnico.toDTOs(tecnico.getContent());
		return new PageImpl<TecnicoDTO>(tecnicoDTO,pageable,tecnico.getTotalElements());
	}

	@Override
	public void addTecnico(TecnicoDTO tecnicoDTO) {
		if(tecnicoRepo.existsBynumeroIden(tecnicoDTO.getNumeroIden()))
			throw new BadRequestException("Ya existe un t�cnico con el mismo numero de identificaci�n");
		Cuadrilla cuadrilla=cuadrillaRepo.findByNombre(tecnicoDTO.getCuadrilla())
				.orElseThrow(()->new BadRequestException("Cuadrilla no existe"));
		Tecnico tecnico= mapperTecnico.ToEntity(tecnicoDTO);
		Set<TipoDano> tdanos=new HashSet<>();
		tecnicoDTO.getTDano().forEach(dano->{
			TipoDano td=tipoDanoRepo.findByNombre(dano)
					.orElseThrow(()->new NotFoundException("El tipo de da�o "+dano+" no existe"));
			tdanos.add(td);
		});
		tecnico.setCuadrilla(cuadrilla);
		tecnico.setTDano(tdanos);
		tecnicoRepo.save(tecnico);
	}

	@Override
	public void updateTecnico(Long ident,TecnicoDTO tecnicoDTO) {
		Tecnico temp=tecnicoRepo.findBynumeroIden(ident)
				.orElseThrow(()->new NotFoundException("Tecnico no existe"));
		Cuadrilla cuadrilla=cuadrillaRepo.findByNombre(tecnicoDTO.getCuadrilla())
				.orElseThrow(()->new BadRequestException("Cuadrilla no existe"));
		//Mapeo de dto a entidad-------------------------
		Tecnico tecnico=mapperTecnico.ToEntity(tecnicoDTO);
		Set<TipoDano> tdanos=new HashSet<>();
		tecnicoDTO.getTDano().forEach(dano->{
			TipoDano td=tipoDanoRepo.findByNombre(dano)
					.orElseThrow(()->new NotFoundException("El tipo de da�o "+dano+" no existe"));
			tdanos.add(td);
		});
		tecnico.setCuadrilla(cuadrilla);
		tecnico.setTDano(tdanos);
		tecnico.setId(temp.getId());
		//-----------------------------------------------
		tecnicoRepo.save(tecnico);
	}

	@Override
	public void deleteTecnico(Long ident) {
		Tecnico tecnico=tecnicoRepo.findBynumeroIden(ident)
				.orElseThrow(()->new NotFoundException("Tecnico con identificacion :"+ident+" no existe o ya ha sido eliminado"));
		tecnico.setBorrado(1);
		tecnico.setTDano(null);//Borrar relaciones de tabla pivot
		tecnicoRepo.save(tecnico);
	}

	@Override
	public TecnicoDTO getTecnicoByIdentificacion(Long ident) {
		Tecnico tecnico= tecnicoRepo.findBynumeroIden(ident)
				.orElseThrow(()->new NotFoundException("El tecnico con numero de identificacion no existe"));
		return mapperTecnico.toDTO(tecnico); 
	}

}
