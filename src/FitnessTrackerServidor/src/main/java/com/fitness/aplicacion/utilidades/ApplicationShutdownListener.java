package com.fitness.aplicacion.utilidades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * Para depuración. Para MongoDB en Spring Boot, no hay un equivalente directo
 * a: spring.jpa.properties.hibernate.hbm2ddl.auto=create-drop.
 * */
public class ApplicationShutdownListener implements ApplicationListener<ContextClosedEvent> {

    private MongoTemplate mongoTemplate;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        // Limpia la base de datos
        mongoTemplate.getDb().drop();
    }
}