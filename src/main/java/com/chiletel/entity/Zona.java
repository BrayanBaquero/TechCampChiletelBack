package com.chiletel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ZONAS")
public class Zona {
	@Id
	@GeneratedValue( strategy  = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    @SequenceGenerator(sequenceName = "ZONAS_SEQ", allocationSize = 1, name = "CUST_SEQ")
	@Column(name="ID_ZONAS")
	private int id;
	@Column(name = "NOMBRE")
	private String nombre;

}
