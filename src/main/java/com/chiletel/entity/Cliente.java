package com.chiletel.entity;


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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
	name = "CLIENTES", 
	indexes = @Index(name="IDX_CLIENTES_01",columnList = "IDENTIFICACION",unique=true)
)
//@Table(name="CLIENTES")
public class Cliente {
	@Id
	@GeneratedValue( strategy  = GenerationType.SEQUENCE, generator = "clientes_seq")
    @SequenceGenerator(sequenceName = "clientes_seq", allocationSize = 1, name = "clientes_seq")
	@Column(name="ID_CLIENTE")
	private Integer id;
	@Column(name = "NOMBRE",length = 50)
	private String nombre;
	@Column(name="APELLIDO",length = 50)
	private String apellido;
	@Column(name = "IDENTIFICACION",nullable = false)
	private long numeroIden;
	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_CLIENTE",foreignKey = @ForeignKey(name = "FK_CLIENTE_TIPO_CLIENTE_01") )
	private TipoCliente tipoCliente;
	@Column(name = "EMAIL",length = 100)
	private String email;
	@Column(name = "TELEFONO",length = 20)
	private String telefono;
	@Column(name = "DIRECCION",length = 40)
	private String direccion;
	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ZONA",foreignKey = @ForeignKey(name = "FK_CLIENTE_ZONAS_02") )
	private Zona zona;
	

}
