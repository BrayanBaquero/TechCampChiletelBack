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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(
	name = "TECNICOS",
	indexes = @Index(name="IDX_TECNICOS_01",columnList = "IDENTIFICACION",unique=true)
)
//@Table(name = "TECNICOS")

public class Tecnico {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "TECNICOS_SEQ")
	@GeneratedValue( strategy  = GenerationType.SEQUENCE, generator = "TECNICOS_SEQ")
    @SequenceGenerator(sequenceName = "TECNICOS_SEQ", allocationSize = 1, name = "TECNICOS_SEQ")
	@Column(name="ID_TECNICO")
	private int id;
	@Column(name = "NOMBRE",length = 50)
	private String nombre;
	@Column(name = "APELLIDO",length = 50)
	private String apellido;
	//@Column(name = "IDENTIFICACION",columnDefinition = "NUMBER(20)")
	@Column(name = "IDENTIFICACION")
	private Long numeroIden;
	@Column(name = "EMAIL",length = 100)
	private String email;
	@Column(name = "TELEFONO",length = 20)
	private String telefono;
	@Column(name = "DIRECCION",length = 40)
	private String direccion;
	@ManyToOne(optional = true,fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CUADRILLA",foreignKey = @ForeignKey(name = "FK_TECNICO_CUADRILLA_01") )
	private Cuadrilla cuadrilla;
	@Column(name ="BORRADO",columnDefinition = "NUMBER(1) DEFAULT 0 NOT NULL")
	private int borrado;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="TIPOS_INCIDENCIA_TECNICOS",
			   joinColumns = @JoinColumn(foreignKey = @ForeignKey(name = "FK_TIPOS_INCIDENCIA_TECNICOS_02") 
			   							,name="ID_TECNICO"),
			   inverseJoinColumns = @JoinColumn(foreignKey = @ForeignKey(name = "FK_TIPOS_INCIDENCIA_TECNICOS_01") 
												,name="ID_TIPO_INCIDENCIA"))
	private Set<TipoDaño> tDaño=new HashSet<>();

}
