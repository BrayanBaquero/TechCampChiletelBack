package com.chiletel.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
//@Table(name = "CUADRILLAS")
@Table(
		name = "CUADRILLAS",
		indexes = @Index(name="IDX_CUADRILLAS_01",columnList = "NOMBRE",unique=true)
	)

public class Cuadrilla {
	@Id
	@GeneratedValue( strategy  = GenerationType.SEQUENCE, generator = "CUADRILLAS_SEQ")
    @SequenceGenerator(sequenceName = "CUADRILLAS_SEQ", allocationSize = 1, name = "CUADRILLAS_SEQ")
	@Column(name = "ID_CUADRILLA")
	private int id;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name = "BORRADO" ,length = 1)
	private Boolean borrado;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="ZONAS_CUADRILLAS",
			   joinColumns = @JoinColumn(foreignKey = @ForeignKey(name = "FK_ZONAS_CUADRILLAS_01") 
			   							,name="ID_ZONA"),
			   inverseJoinColumns = @JoinColumn(foreignKey = @ForeignKey(name = "FK_ZONAS_CUADRILLAS_02") 
												,name="ID_CUADRILLA"))
	private Set<Zona> zona=new HashSet<>();
}
