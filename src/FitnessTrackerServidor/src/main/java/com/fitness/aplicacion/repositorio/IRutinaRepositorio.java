package com.fitness.aplicacion.repositorio;

import com.fitness.aplicacion.documentos.Rutina;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IRutinaRepositorio extends MongoRepository<Rutina, String> {
}
