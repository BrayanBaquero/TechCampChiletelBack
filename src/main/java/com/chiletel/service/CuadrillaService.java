package com.chiletel.service;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chiletel.dto.CuadrillaDTO;
import com.chiletel.dto.NuevaCuadrillaDTO;
import com.chiletel.entity.Cuadrilla;
import com.chiletel.entity.CuadrillasTecnicos;
import com.chiletel.entity.Zona;
import com.chiletel.exceptionHandler.BadRequestException;
import com.chiletel.mapper.CuadrillaMapper;
import com.chiletel.repository.ICuadrillaRepository;
import com.chiletel.repository.IZonaRepository;

import oracle.net.aso.c;

@Service
@Transactional
public class CuadrillaService implements ICuadrillaService{
	
	@Autowired
	private ICuadrillaRepository cuadrillaRepo;
	@Autowired
	private IZonaRepository zonaRepo;
	@Autowired
	private CuadrillaMapper cuadrillaMapper;
	
	
	@Override
	public List<CuadrillaDTO> getAll() {
		List<Cuadrilla> cuadrilla=cuadrillaRepo.getAllCuadrillasActivas();
		List<CuadrillasTecnicos> nTecnicos= cuadrillaRepo.getAllCuadrillasConTecnicos();
		List<CuadrillaDTO> cuadrillaDTO=cuadrillaMapper.toDtos(cuadrilla);
		//Mapear las cuadrillas con el numero de tecnicos al dto
		cuadrillaDTO.forEach(cu->{
			nTecnicos.forEach(tec->{
				if(cu.getNombre().equals(tec.getNombre()))
					cu.setMiembros(tec.getMiembros());
			});
		});
		return cuadrillaDTO;
	}

	@Override
	public void add(NuevaCuadrillaDTO nuevaCuadrillaDTO) {
		
		if(cuadrillaRepo.findBynombre(nuevaCuadrillaDTO.getNombre()).isPresent())
			throw new BadRequestException("El nombre no esta disponibles, selcciones otro!!");
		Cuadrilla cuadrilla=new Cuadrilla();
		Set<Zona> zonas=nuevaCuadrillaDTO.getZona().stream()
				.map(m->zonaRepo.findByNombre(m)
						.orElseThrow(()->new BadRequestException("zona "+m+" no existe")))
				.collect(Collectors.toSet());
		
		cuadrilla.setZona(zonas);
		cuadrilla.setNombre(nuevaCuadrillaDTO.getNombre());
		cuadrilla.setBorrado(0);
		cuadrillaRepo.save(cuadrilla);
		
	}

	@Override
	public void delete(String nombre) {
		Optional<Cuadrilla> cuadrilla=cuadrillaRepo.findBynombre(nombre);
		if(cuadrilla.isEmpty())
			throw new BadRequestException("La cuadrilla"+nombre+" no existe!!");
		cuadrilla.get().setBorrado(1);
		cuadrillaRepo.save(cuadrilla.get());
	}

	
	@Override
	public void update(String nombre, NuevaCuadrillaDTO nuevaCuadrillaDTO) {
		Optional<Cuadrilla> cuadrilla=cuadrillaRepo.findBynombre(nombre);
		if(cuadrilla.isEmpty())
			throw new BadRequestException("La cuadrilla"+nombre+" no existe!!");
		
		Set<Zona> zonas=nuevaCuadrillaDTO.getZona().stream()
				.map(m->zonaRepo.findByNombre(m)
						.orElseThrow(()->new BadRequestException("zona "+m+" no existe")))
				.collect(Collectors.toSet());
		
		cuadrilla.get().setNombre(nuevaCuadrillaDTO.getNombre());
		cuadrilla.get().setZona(zonas);
		cuadrillaRepo.save(cuadrilla.get());
		//cuadrillaMapper.toEntity(nuevaCuadrillaDTO);
	}

}
