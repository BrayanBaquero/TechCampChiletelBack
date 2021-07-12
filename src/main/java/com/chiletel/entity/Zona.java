package com.chiletel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
//@Table(name = "ZONAS")
@Table(
		name = "ZONAS",
		indexes = @Index(name="IDX_ZONAS_01",columnList = "NOMBRE",unique=true)
	)
public class Zona {
	@Id
	@GeneratedValue( strategy  = GenerationType.SEQUENCE, generator = "ZONAS_SEQ")
    @SequenceGenerator(sequenceName = "ZONAS_SEQ", allocationSize = 1, name = "ZONAS_SEQ")
	@Column(name="ID_ZONA")
	private int id;
	@Column(name = "NOMBRE",length = 40)
	private String nombre;

}
