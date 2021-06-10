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
@Table(name = "ZONA")
public class Zona {
	@Id
	@GeneratedValue( strategy  = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    @SequenceGenerator(sequenceName = "ZONA_SEQ", allocationSize = 1, name = "CUST_SEQ")
	@Column(name="ZONA_ID")
	private int id;
	@Column(name = "NOMBRE")
	private int nombre;

}
