package com.fitness.aplicacion.repositorio;

import com.fitness.aplicacion.documentos.Alimento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IAlimentoRepositorio extends MongoRepository<Alimento, String> {
}
