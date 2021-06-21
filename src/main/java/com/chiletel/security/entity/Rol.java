package com.chiletel.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.chiletel.security.enums.RolNombre;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "ROLES")
public class Rol {
	@Id
	@GeneratedValue( strategy  = GenerationType.SEQUENCE, generator = "ROLES_SEQ")
    @SequenceGenerator(sequenceName = "ROLES_SEQ", allocationSize = 1, name = "ROLES_SEQ")
	@Column(name = "ID_ROLE")
	private int id;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "NOMBRE")
	private RolNombre rolNombre;
}
