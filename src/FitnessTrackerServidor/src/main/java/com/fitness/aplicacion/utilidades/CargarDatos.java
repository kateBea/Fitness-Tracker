package com.fitness.aplicacion.utilidades;

import com.fitness.aplicacion.documentos.*;
import com.fitness.aplicacion.servicio.IUsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

@Order(0)
@Component
public class CargarDatos implements CommandLineRunner {

    @Autowired
    @Qualifier("usuarioServicioImpl")
    IUsuarioServicio usuarioServicio;

    BCryptPasswordEncoder cifrar = new BCryptPasswordEncoder();


    @Override
    public void run(String... args) throws Exception {
        // Ejemplo de instancias de Cliente
        Usuario cliente1 = Usuario.builder()
                .email("juan.perez@correo.com")
                .nombre("Juan")
                .nombreUsuario("El Bicho")
                .primerApellido("Perez")
                .segundoApellido("Gomez")
                .contrasena(cifrar.encode("password123"))
                .fechaDeNacimiento(LocalDate.of(1990, 4, 25))
                .fechaRegistro(LocalDateTime.now())
                .altura(180.0f)
                .peso(75.0f)
                .sexo(Sexo.HOMBRE)
                .dietas(Collections.emptyList())
                .rutinas(Collections.emptyList())
                .build();

        Usuario cliente2 = Usuario.builder()
                .email("maria.garcia@correo.com")
                .nombre("Maria")
                .primerApellido("Garcia")
                .segundoApellido("Lopez")
                .contrasena(cifrar.encode("securepass"))
                .fechaDeNacimiento(LocalDate.of(1995, 6, 15))
                .fechaRegistro(LocalDateTime.now())
                .altura(165.0f)
                .peso(60.0f)
                .sexo(Sexo.MUJER)
                .dietas(Arrays.asList(
                        Dieta.builder()
                                .id("dieta1")
                                .caloriasTarget(2000.0f)
                                .fechaInicio(LocalDateTime.of(2024, 5, 1, 0, 0))
                                .fechaFin(LocalDateTime.of(2024, 6, 1, 0, 0))
                                .comidasSugeridas(Arrays.asList(
                                        Comida.builder()
                                                .id("comida1")
                                                .nombre("Ensalada")
                                                .descripcion("Ensalada verde con pollo")
                                                .unidades(1)
                                                .calorias(300.0f)
                                                .carbohidratos(15.0f)
                                                .vitaminas(Arrays.asList("A", "C"))
                                                .grasas(10.0f)
                                                .build()
                                ))
                                .consumoDeAgua(2.0f)
                                .build()
                ))
                .rutinas(Arrays.asList(
                        Rutina.builder()
                                .id("rutina1")
                                .tiempoDeSuenio(480.0f)
                                .caloriasQuemadas(500.0f)
                                .pasosRealizados(10000)
                                .frecuenciaCardiaca(70.0f)
                                .nivelOxigenoSangre(98.0f)
                                .presionArterial(120.0f)
                                .fechaSeguimiento(LocalDateTime.now())
                                .comidasConsumidas(Arrays.asList(
                                        Alimento.builder()
                                                .id("alimento1")
                                                .comida(Comida.builder()
                                                        .id("comida1")
                                                        .nombre("Manzana")
                                                        .descripcion("Una manzana roja")
                                                        .unidades(1)
                                                        .calorias(95.0f)
                                                        .carbohidratos(25.0f)
                                                        .vitaminas(Arrays.asList("C"))
                                                        .grasas(0.3f)
                                                        .build())
                                                .tipo(Tipo.DESAYUNO)
                                                .horaConsumo(LocalDateTime.of(2024, 5, 12, 8, 0))
                                                .orden(Orden.PRIMER_PLATO)
                                                .build()
                                ))
                                .build()
                ))
                .build();

        Usuario cliente3 = Usuario.builder()
                .email("ana.martinez@correo.com")
                .nombre("Ana")
                .primerApellido("Martinez")
                .segundoApellido("Rodriguez")
                .contrasena(cifrar.encode("strongpassword"))
                .fechaDeNacimiento(LocalDate.of(1985, 12, 10))
                .fechaRegistro(LocalDateTime.now())
                .altura(170.0f)
                .peso(65.0f)
                .sexo(Sexo.MUJER)
                .dietas(Arrays.asList(
                        Dieta.builder()
                                .id("dieta2")
                                .caloriasTarget(1800.0f)
                                .fechaInicio(LocalDateTime.of(2024, 4, 1, 0, 0))
                                .fechaFin(LocalDateTime.of(2024, 4, 30, 0, 0))
                                .comidasSugeridas(Arrays.asList(
                                        Comida.builder()
                                                .id("comida2")
                                                .nombre("Sopa de Verduras")
                                                .descripcion("Sopa de verduras con pollo")
                                                .unidades(1)
                                                .calorias(150.0f)
                                                .carbohidratos(20.0f)
                                                .vitaminas(Arrays.asList("A", "B", "C"))
                                                .grasas(5.0f)
                                                .build(),
                                        Comida.builder()
                                                .id("comida3")
                                                .nombre("Sandwich Integral")
                                                .descripcion("Sandwich con pan integral, tomate, y pavo")
                                                .unidades(1)
                                                .calorias(250.0f)
                                                .carbohidratos(30.0f)
                                                .vitaminas(Arrays.asList("B", "E"))
                                                .grasas(10.0f)
                                                .build()
                                ))
                                .consumoDeAgua(2.5f)
                                .build()
                ))
                .rutinas(Arrays.asList(
                        Rutina.builder()
                                .id("rutina2")
                                .tiempoDeSuenio(420.0f)
                                .caloriasQuemadas(300.0f)
                                .pasosRealizados(8000)
                                .frecuenciaCardiaca(65.0f)
                                .nivelOxigenoSangre(97.0f)
                                .presionArterial(115.0f)
                                .fechaSeguimiento(LocalDateTime.now())
                                .comidasConsumidas(Arrays.asList(
                                        Alimento.builder()
                                                .id("alimento2")
                                                .comida(Comida.builder()
                                                        .id("comida4")
                                                        .nombre("Banana")
                                                        .descripcion("Una banana fresca")
                                                        .unidades(1)
                                                        .calorias(105.0f)
                                                        .carbohidratos(27.0f)
                                                        .vitaminas(Arrays.asList("B6", "C"))
                                                        .grasas(0.3f)
                                                        .build())
                                                .tipo(Tipo.MERIENDA_MATUTINA)
                                                .horaConsumo(LocalDateTime.of(2024, 5, 12, 10, 0))
                                                .orden(Orden.PRIMER_PLATO)
                                                .build()
                                ))
                                .build()
                ))
                .build();


        Usuario cliente4 = Usuario.builder()
                .email("carlos.fernandez@correo.com")
                .nombre("Carlos")
                .primerApellido("Fernandez")
                .segundoApellido("Lopez")
                .contrasena(cifrar.encode("mysecretpass"))
                .fechaDeNacimiento(LocalDate.of(1992, 3, 5))
                .fechaRegistro(LocalDateTime.now())
                .altura(175.0f)
                .peso(70.0f)
                .sexo(Sexo.HOMBRE)
                .dietas(Arrays.asList(
                        Dieta.builder()
                                .id("dieta3")
                                .caloriasTarget(2500.0f)
                                .fechaInicio(LocalDateTime.of(2024, 1, 1, 0, 0))
                                .fechaFin(LocalDateTime.of(2024, 1, 31, 0, 0))
                                .comidasSugeridas(Arrays.asList(
                                        Comida.builder()
                                                .id("comida5")
                                                .nombre("Pasta con Pollo")
                                                .descripcion("Pasta integral con pollo a la parrilla")
                                                .unidades(1)
                                                .calorias(400.0f)
                                                .carbohidratos(50.0f)
                                                .vitaminas(Arrays.asList("B", "E", "K"))
                                                .grasas(10.0f)
                                                .build(),
                                        Comida.builder()
                                                .id("comida6")
                                                .nombre("Yogur con Frutas")
                                                .descripcion("Yogur natural con fresas y arándanos")
                                                .unidades(1)
                                                .calorias(200.0f)
                                                .carbohidratos(30.0f)
                                                .vitaminas(Arrays.asList("C", "D"))
                                                .grasas(5.0f)
                                                .build()
                                ))
                                .consumoDeAgua(3.0f)
                                .build()
                ))
                .rutinas(Arrays.asList(
                        Rutina.builder()
                                .id("rutina3")
                                .tiempoDeSuenio(450.0f)
                                .caloriasQuemadas(600.0f)
                                .pasosRealizados(12000)
                                .frecuenciaCardiaca(75.0f)
                                .nivelOxigenoSangre(96.0f)
                                .presionArterial(118.0f)
                                .fechaSeguimiento(LocalDateTime.now())
                                .comidasConsumidas(Arrays.asList(
                                        Alimento.builder()
                                                .id("alimento3")
                                                .comida(Comida.builder()
                                                        .id("comida7")
                                                        .nombre("Naranja")
                                                        .descripcion("Una naranja jugosa")
                                                        .unidades(1)
                                                        .calorias(62.0f)
                                                        .carbohidratos(15.4f)
                                                        .vitaminas(Arrays.asList("C"))
                                                        .grasas(0.2f)
                                                        .build())
                                                .tipo(Tipo.MERIENDA_VESPERTINA)
                                                .horaConsumo(LocalDateTime.of(2024, 5, 12, 15, 0))
                                                .orden(Orden.PRIMER_PLATO)
                                                .build()
                                ))
                                .build()
                ))
                .build();

        usuarioServicio.insertarDebug(cliente1);
        usuarioServicio.insertarDebug(cliente2);
        usuarioServicio.insertarDebug(cliente3);
        usuarioServicio.insertarDebug(cliente4);

        System.out.println("Insertado cliente nuevo");
    }
}
