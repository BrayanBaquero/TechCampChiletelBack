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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
//@Table(name = "ORDENES_ATENCION")
@Table(
		name = "ORDENES_ATENCION",
		indexes = @Index(name="IDX_TORDENES_ATENCION_01",columnList = "ID_INCIDENCIA",unique=true)
	)
public class OrdenAtencion {
	@Id
	@GeneratedValue( strategy  = GenerationType.SEQUENCE, generator = "ORDENES_ATENCION_SEQ")
    @SequenceGenerator(sequenceName = "ORDENES_ATENCION_SEQ", allocationSize = 1, name = "ORDENES_ATENCION_SEQ")
	@Column(name="ID_ORDEN_ATENCION")
	private int id;
	@OneToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "ID_INCIDENCIA",foreignKey = @ForeignKey(name = "FK_ORDENES_ATENCION_INCIDENCIAS_01") )
	private Daño daño;
	@Column(name = "NUMERO_ORDEN",length = 100)
	private String numOrden;
	@Column(name = "AGENDADO",columnDefinition = "NUMBER(1)")
	private int agendado;
}
