package com.chiletel.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name = "INCIDENCIAS")
@Table(
		name = "INCIDENCIAS",
		indexes = @Index(name="IDX_INCIDENCIAS_01",columnList = "FECHA_REGISTRO",unique=false)
	)
public class Daño {
	@Id
	@GeneratedValue( strategy  = GenerationType.SEQUENCE, generator = "INCIDENCIAS_SEQ")
    @SequenceGenerator(sequenceName = "INCIDENCIAS_SEQ", allocationSize = 1, name = "INCIDENCIAS_SEQ")
	@Column(name="ID_INCIDENCIA")
	private int id;
	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CLIENTE",foreignKey = @ForeignKey(name = "FK_INCIDENCIAS_CLIENTES_01") )
	private Cliente cliente;
	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_INCIDENCIA",foreignKey = @ForeignKey(name = "FK_INCIDENCIAS_TIPOS_INCIDENCIA_02") )
	private TipoDaño tipoDaño;
	@Column(name = "DESCRIPCION",length = 150)
	private String descripcion;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_REGISTRO",nullable = false)
	private Date fechaRegistro;
}
