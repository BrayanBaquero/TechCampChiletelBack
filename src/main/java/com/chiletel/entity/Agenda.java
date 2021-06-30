package com.chiletel.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
//@Table(name = "AGENDAS")

@Table(
		name = "AGENDAS",
		indexes = {@Index(name="IDX_AGENDAS_01",columnList = "ID_TECNICO"),
				   @Index(name="IDX_AGENDAS_02",columnList = "FECHA"),
				   @Index(name="UK_AGENDAS_01",columnList = "ID_ORDEN_ATENCION",unique = true)
		}
	)
public class Agenda {
	@Id
	@GeneratedValue( strategy  = GenerationType.SEQUENCE, generator = "AGENDAS_SEQ")
    @SequenceGenerator(sequenceName = "AGENDAS_SEQ", allocationSize = 1, name = "AGENDAS_SEQ")
	@Column(name="ID_AGENDA")
	private int id;
	@OneToOne(optional = false)
    @JoinColumn(name = "ID_ORDEN_ATENCION",foreignKey = @ForeignKey(name = "FK_AGENDAS_ORDENES_ATENCION_01"),referencedColumnName = "id_orden_atencion")
	private OrdenAtencion ordenAtencion;
	@Column(name = "ID_TECNICO")
	private int tecnico;
	@Column(name = "T_ATENCION")
	private int tAtencion;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "H_INICIO")
	private Date horaInicio;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "H_FINAL")
	private Date horaFinal;
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA",nullable = false)
	private Date fecha;
}
