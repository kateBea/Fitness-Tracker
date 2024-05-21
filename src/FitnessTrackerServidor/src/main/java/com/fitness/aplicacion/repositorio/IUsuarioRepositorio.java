package com.fitness.aplicacion.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fitness.aplicacion.documentos.Usuario;

@Repository
public interface IUsuarioRepositorio extends MongoRepository<Usuario, String>{

    /**
     * Recoger la contraseña del usuario con el email especificado.
     * */
    @Query(value = "{ '_id': ?0 }", fields = "{ 'password': 1, '_id': 0 }")
    String findPasswordByEmail(String email);

}
