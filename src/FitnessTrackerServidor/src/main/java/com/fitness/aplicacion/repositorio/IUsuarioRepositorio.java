package com.fitness.aplicacion.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fitness.aplicacion.documentos.Usuario;

@Repository
public interface IUsuarioRepositorio extends MongoRepository<Usuario, String>{

}
