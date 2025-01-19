package com.fitness.aplicacion.repositorio;

import com.fitness.aplicacion.documentos.Dieta;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IDietaRepositorio extends MongoRepository<Dieta, String> {
}
