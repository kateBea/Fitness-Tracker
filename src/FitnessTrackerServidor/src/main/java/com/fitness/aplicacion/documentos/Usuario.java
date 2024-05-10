package com.fitness.aplicacion.documentos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Document(value = "Usuarios")
public class Usuario {
	@Id
	@EqualsAndHashCode.Include
	private String email;
	private String nombre;
	private String contrasena;
}