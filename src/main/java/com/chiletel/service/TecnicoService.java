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
import com.chiletel.entity.TipoDaño;
import com.chiletel.exceptionHandler.BadRequestException;
import com.chiletel.exceptionHandler.NotFoundException;
import com.chiletel.mapper.TecnicoMapper;
import com.chiletel.repository.ICuadrillaRepository;
import com.chiletel.repository.ITecnicoRepository;
import com.chiletel.repository.ITipoDañoRepository;

/**
 * <strong>Descrcipción:</strong>
 * Clase encargada de implementar los metodos definidos en {@link ITecnicoService}
 * @author Brayan Baquero
 */
@Service
public class TecnicoService implements ITecnicoService{
	@Autowired
	private ITecnicoRepository tecnicoRepo;
	@Autowired
	private ITipoDañoRepository tipoDañoRepo;
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
			throw new BadRequestException("Ya existe un técnico con el mismo numero de identificación");
		Cuadrilla cuadrilla=cuadrillaRepo.findByNombre(tecnicoDTO.getCuadrilla())
				.orElseThrow(()->new BadRequestException("Cuadrilla no existe"));
		Tecnico tecnico= mapperTecnico.ToEntity(tecnicoDTO);
		Set<TipoDaño> tdaños=new HashSet<>();
		tecnicoDTO.getTDano().forEach(daño->{
			TipoDaño td=tipoDañoRepo.findByNombre(daño)
					.orElseThrow(()->new NotFoundException("El tipo de daño "+daño+" no existe"));
			tdaños.add(td);
		});
		tecnico.setCuadrilla(cuadrilla);
		tecnico.setTDaño(tdaños);
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
		Set<TipoDaño> tdaños=new HashSet<>();
		tecnicoDTO.getTDano().forEach(daño->{
			TipoDaño td=tipoDañoRepo.findByNombre(daño)
					.orElseThrow(()->new NotFoundException("El tipo de daño "+daño+" no existe"));
			tdaños.add(td);
		});
		tecnico.setCuadrilla(cuadrilla);
		tecnico.setTDaño(tdaños);
		tecnico.setId(temp.getId());
		//-----------------------------------------------
		tecnicoRepo.save(tecnico);
	}

	@Override
	public void deleteTecnico(Long ident) {
		Tecnico tecnico=tecnicoRepo.findBynumeroIden(ident)
				.orElseThrow(()->new NotFoundException("Tecnico con identificacion :"+ident+" no existe o ya ha sido eliminado"));
		tecnico.setBorrado(1);
		tecnico.setTDaño(null);//Borrar relaciones de tabla pivot
		tecnicoRepo.save(tecnico);
	}

	@Override
	public TecnicoDTO getTecnicoByIdentificacion(Long ident) {
		Tecnico tecnico= tecnicoRepo.findBynumeroIden(ident)
				.orElseThrow(()->new NotFoundException("El tecnico con numero de identificacion no existe"));
		return mapperTecnico.toDTO(tecnico); 
	}

}
