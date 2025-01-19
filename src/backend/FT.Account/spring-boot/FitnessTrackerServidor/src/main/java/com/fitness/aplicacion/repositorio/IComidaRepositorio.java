package com.fitness.aplicacion.repositorio;

import com.fitness.aplicacion.documentos.Comida;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IComidaRepositorio extends MongoRepository<Comida, String> {

}
