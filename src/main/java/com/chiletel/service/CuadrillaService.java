package com.chiletel.service;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chiletel.dto.CuadrillaDTO;
import com.chiletel.dto.CuadrillasTecnicos;
import com.chiletel.dto.NuevaCuadrillaDTO;
import com.chiletel.entity.Cuadrilla;
import com.chiletel.entity.Zona;
import com.chiletel.exceptionHandler.BadRequestException;
import com.chiletel.exceptionHandler.NotFoundException;
import com.chiletel.mapper.CuadrillaMapper;
import com.chiletel.repository.ICuadrillaRepository;
import com.chiletel.repository.ITecnicoRepository;
import com.chiletel.repository.IZonaRepository;

/**
 * <strong>Descrcipción:</strong>
 * Clase encargada de implementar los metodos definidos en {@link ICuadrillaService}
 * @author Brayan Baquero
 */
@Service
@Transactional
public class CuadrillaService implements ICuadrillaService{
	
	@Autowired
	private ICuadrillaRepository cuadrillaRepo;
	@Autowired
	private IZonaRepository zonaRepo;
	@Autowired
	private ITecnicoRepository tecnicoRepo;
	@Autowired
	private CuadrillaMapper cuadrillaMapper;
	
	
	@Override
	public Page<CuadrillaDTO> getCuadrillas(Pageable pageable) {
		Page<Cuadrilla> cuadrilla=cuadrillaRepo.getAllCuadrillasActivas(pageable);
		List<CuadrillasTecnicos> nTecnicos= cuadrillaRepo.getAllCuadrillasConTecnicos();
		List<CuadrillaDTO> cuadrillaDTO=cuadrillaMapper.toDtos(cuadrilla.getContent());
		//Mapear las cuadrillas con el numero de tecnicos al dto
		cuadrillaDTO.forEach(cu->{
			nTecnicos.forEach(tec->{
				if(cu.getNombre().equals(tec.getNombre()))
					cu.setMiembros(tec.getMiembros());
			});
		});
		return new PageImpl<>(cuadrillaDTO, pageable, cuadrilla.getTotalElements());
	}

	@Override
	public void addCuadrilla(NuevaCuadrillaDTO nuevaCuadrillaDTO) {
		if(cuadrillaRepo.findBynombre(nuevaCuadrillaDTO.getNombre()).isPresent())
			throw new BadRequestException("El nombre no esta disponible, selcciones otro!!");
		Cuadrilla cuadrilla=new Cuadrilla();
		//Obtener set de zonas validando si existen en DB
		Set<Zona> zonas=nuevaCuadrillaDTO.getZona().stream()
				.map(m->zonaRepo.findByNombre(m)
						.orElseThrow(()->new NotFoundException("Zona "+m+" no existe")))
				.collect(Collectors.toSet());
		cuadrilla.setZona(zonas);
		cuadrilla.setNombre(nuevaCuadrillaDTO.getNombre());
		cuadrilla.setBorrado(0);
		cuadrillaRepo.save(cuadrilla);
	}

	@Override
	public void deleteCuadrilla(String nombre) {
		Cuadrilla cuadrilla=cuadrillaRepo.findBynombre(nombre)
				.orElseThrow(()->new BadRequestException("La cuadrilla "+nombre+" no existe."));
		cuadrilla.setBorrado(1);
		cuadrilla.setZona(null);
		tecnicoRepo.updateCuadrillaTecnicos(cuadrilla.getId());
		cuadrillaRepo.save(cuadrilla);
	}

	
	@Override
	public void updateCuadrilla(String nombre, NuevaCuadrillaDTO nuevaCuadrillaDTO) {
		Cuadrilla cuadrilla=cuadrillaRepo.findBynombre(nombre)
				.orElseThrow(()->new BadRequestException("La cuadrilla "+nombre+" no existe."));
		//Obtener set de zonas validando si existen en DB
		Set<Zona> zonas=nuevaCuadrillaDTO.getZona().stream()
				.map(m->zonaRepo.findByNombre(m)
						.orElseThrow(()->new BadRequestException("Zona "+m+" no existe")))
				.collect(Collectors.toSet());
		cuadrilla.setNombre(nuevaCuadrillaDTO.getNombre());
		cuadrilla.setZona(zonas);
		cuadrillaRepo.save(cuadrilla);
	}

	@Override
	public List<String> getAllNombres() {
		return cuadrillaRepo.getAllNombres();
	}

}
