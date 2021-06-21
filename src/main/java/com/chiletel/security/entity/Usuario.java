package com.chiletel.security.entity;

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

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
//@Table(name = "USUARIOS")
@Table(
		name = "USUARIOS",
		indexes = @Index(name="IDX_USUARIOS_01",columnList = "NOMBRE_USUARIO",unique=true)
	)
//Encargada de acceso a BD
public class Usuario {
	@Id
	@GeneratedValue( strategy  = GenerationType.SEQUENCE, generator = "USUARIOS_SEQ")
    @SequenceGenerator(sequenceName = "USUARIOS_SEQ", allocationSize = 1, name = "USUARIOS_SEQ")
	@Column(name = "ID_USUARIO")
	private int id;
	@NotNull
	@Column(name = "NOMBRE",length = 20)
	private String nombre;
	@NotNull
	@Column(name = "NOMBRE_USUARIO")
	private String nombreUsuario;
	@NotNull
	@Column(name = "EMAIL",length = 100)
	private String email;
	@NotNull
	@Column(name = "PASSWORD",length = 20)
	private String password;
	@NotNull
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="USUARIOS_ROLES",
	   joinColumns = @JoinColumn(foreignKey = @ForeignKey(name = "FK_USUARIOS_ROLES_USUARIOS_01") 
	   							,name="ID_USUARIO"),
	   inverseJoinColumns = @JoinColumn(foreignKey = @ForeignKey(name = "FK_USUARIOS_ROLES_ROLES_02") 
										,name="ID_ROL"))
	private Set<Rol> roles=new HashSet<>();
	
	
	public Usuario(String nombre, String nombreUsuario, String email, String password) {
		super();
		this.nombre = nombre;
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.password = password;
	}
	
	
}
