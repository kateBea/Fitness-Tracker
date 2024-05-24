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
import java.util.List;

@Order(0)
@Component
public class CargarDatos implements CommandLineRunner {

    @Autowired
    @Qualifier("usuarioServicioImpl")
    IUsuarioServicio usuarioServicio;

    BCryptPasswordEncoder cifrar = new BCryptPasswordEncoder();


    @Override
    public void run(String... args) throws Exception {
// Crear instancias de Comida para el primer usuario
        Comida comida1 = Comida.builder()
                .id("1")
                .nombre("Ensalada César")
                .descripcion("Ensalada con lechuga, pollo, y aderezo César")
                .calorias(150)
                .proteinas(10)
                .grasas(5)
                .carbohidratos(10)
                .vitaminas(Arrays.asList("A", "C", "K"))
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        Comida comida2 = Comida.builder()
                .id("2")
                .nombre("Pollo Asado")
                .descripcion("Pollo asado con especias")
                .calorias(250)
                .proteinas(30)
                .grasas(10)
                .carbohidratos(0)
                .vitaminas(Arrays.asList("B6", "B12"))
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        Comida comida3 = Comida.builder()
                .id("3")
                .nombre("Batido de Proteínas")
                .descripcion("Batido con proteínas y frutas")
                .calorias(200)
                .proteinas(20)
                .grasas(5)
                .carbohidratos(25)
                .vitaminas(Arrays.asList("A", "C", "E"))
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        // Crear instancias de Alimento para el primer usuario
        Alimento alimento1 = Alimento.builder()
                .id("1")
                .comida(comida1)
                .tipo(Tipo.ALMUERZO)
                .horaConsumo(LocalDateTime.of(2024, 5, 21, 12, 30))
                .orden(Orden.PRIMER_PLATO)
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        Alimento alimento2 = Alimento.builder()
                .id("2")
                .comida(comida2)
                .tipo(Tipo.CENA)
                .horaConsumo(LocalDateTime.of(2024, 5, 21, 19, 0))
                .orden(Orden.PLATO_UNICO)
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        Alimento alimento3 = Alimento.builder()
                .id("3")
                .comida(comida3)
                .tipo(Tipo.DESAYUNO)
                .horaConsumo(LocalDateTime.of(2024, 5, 21, 7, 0))
                .orden(Orden.PLATO_UNICO)
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        // Crear instancia de Dieta para el primer usuario
        Dieta dieta1 = Dieta.builder()
                .id("1")
                .caloriasTarget(2000)
                .fechaInicio(LocalDateTime.of(2024, 5, 1, 0, 0))
                .fechaFin(LocalDateTime.of(2024, 5, 31, 23, 59))
                .consumoDeAgua(2.5f)
                .activa(true)
                .comidasSugeridas(Arrays.asList(ComidaSugerida.builder().id("1").orden(Orden.PLATO_UNICO).tipo(Tipo.CENA).build(),
                        ComidaSugerida.builder().id("2").orden(Orden.PLATO_UNICO).tipo(Tipo.CENA).build()))
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        // Crear instancia de Rutina para el primer usuario
        Rutina rutina1 = Rutina.builder()
                .id("1")
                .tiempoDeSuenio(8)
                .caloriasQuemadas(500)
                .pasosRealizados(10000)
                .frecuenciaCardiaca(60)
                .nivelOxigenoSangre(98)
                .fechaSeguimiento(LocalDateTime.of(2024, 5, 21, 0, 0))
                .fechaUltimaModificacion(LocalDateTime.now())
                .comidasConsumidas(Arrays.asList(alimento1, alimento2, alimento3))
                .build();

        // Crear primer usuario
        Usuario usuario1 = Usuario.builder()
                .email("usuario1@example.com")
                .nombreUsuario("usuario123")
                .contrasena(cifrar.encode("password"))
                .nombre("Juan")
                .primerApellido("Pérez")
                .segundoApellido("García")
                .fechaDeNacimiento(LocalDate.of(1990, 1, 1))
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .altura(175)
                .peso(70)
                .sexo(Sexo.HOMBRE)
                .dietas(Collections.singletonList(dieta1))
                .comidasRegistradas(Arrays.asList(comida1, comida2, comida3))
                .rutinas(Collections.singletonList(rutina1))
                .build();

        // Crear más comidas
        Comida comida4 = Comida.builder()
                .id("4")
                .nombre("Tostadas con Aguacate")
                .descripcion("Tostadas de pan integral con aguacate y tomate")
                .calorias(300)
                .proteinas(8)
                .grasas(15)
                .carbohidratos(40)
                .vitaminas(Arrays.asList("E", "B5"))
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        Comida comida5 = Comida.builder()
                .id("5")
                .nombre("Sopa de Verduras")
                .descripcion("Sopa con zanahoria, papa y apio")
                .calorias(100)
                .proteinas(3)
                .grasas(2)
                .carbohidratos(15)
                .vitaminas(Arrays.asList("A", "C", "K"))
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        Comida comida6 = Comida.builder()
                .id("6")
                .nombre("Yogur con Frutas")
                .descripcion("Yogur natural con fresas y arándanos")
                .calorias(150)
                .proteinas(8)
                .grasas(5)
                .carbohidratos(20)
                .vitaminas(Arrays.asList("C", "B12"))
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        // Crear instancias de Alimento para el segundo usuario
        Alimento alimento4 = Alimento.builder()
                .id("4")
                .comida(comida4)
                .tipo(Tipo.DESAYUNO)
                .horaConsumo(LocalDateTime.of(2024, 5, 22, 8, 0))
                .orden(Orden.PRIMER_PLATO)
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        Alimento alimento5 = Alimento.builder()
                .id("5")
                .comida(comida5)
                .tipo(Tipo.ALMUERZO)
                .horaConsumo(LocalDateTime.of(2024, 5, 22, 13, 0))
                .orden(Orden.PLATO_UNICO)
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        Alimento alimento6 = Alimento.builder()
                .id("6")
                .comida(comida6)
                .tipo(Tipo.MERIENDA_VESPERTINA)
                .horaConsumo(LocalDateTime.of(2024, 5, 22, 16, 0))
                .orden(Orden.PLATO_UNICO)
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        // Crear instancia de Dieta para el segundo usuario
        Dieta dieta2 = Dieta.builder()
                .id("2")
                .caloriasTarget(1800)
                .fechaInicio(LocalDateTime.of(2024, 5, 1, 0, 0))
                .fechaFin(LocalDateTime.of(2024, 5, 31, 23, 59))
                .consumoDeAgua(2.0f)
                .activa(true)
                .comidasSugeridas(Arrays.asList(ComidaSugerida.builder().id("4").orden(Orden.SEGUNDO_PLATO).tipo(Tipo.ALMUERZO).build(),
                        ComidaSugerida.builder().id("5").orden(Orden.TERCER_PLATO).tipo(Tipo.DESAYUNO).build()))
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        // Crear instancia de Rutina para el segundo usuario
        Rutina rutina2 = Rutina.builder()
                .id("2")
                .tiempoDeSuenio(7)
                .caloriasQuemadas(400)
                .pasosRealizados(8000)
                .frecuenciaCardiaca(70)
                .nivelOxigenoSangre(97)
                .fechaSeguimiento(LocalDateTime.of(2024, 5, 22, 0, 0))
                .fechaUltimaModificacion(LocalDateTime.now())
                .comidasConsumidas(Arrays.asList(alimento4, alimento5, alimento6))
                .build();

        // Crear segundo usuario
        Usuario usuario2 = Usuario.builder()
                .email("usuario2@example.com")
                .nombreUsuario("usuario234")
                .contrasena(cifrar.encode("password"))
                .nombre("Ana")
                .primerApellido("López")
                .segundoApellido("Martínez")
                .fechaDeNacimiento(LocalDate.of(1985, 6, 15))
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .altura(160)
                .peso(60)
                .sexo(Sexo.MUJER)
                .dietas(Collections.singletonList(dieta2))
                .comidasRegistradas(Arrays.asList(comida4, comida5, comida6))
                .rutinas(Collections.singletonList(rutina2))
                .build();

        // Crear más comidas
        Comida comida7 = Comida.builder()
                .id("7")
                .nombre("Sandwich de Pollo")
                .descripcion("Sandwich de pollo con vegetales y mayonesa")
                .calorias(350)
                .proteinas(25)
                .grasas(15)
                .carbohidratos(30)
                .vitaminas(Arrays.asList("A", "C", "B6"))
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        Comida comida8 = Comida.builder()
                .id("8")
                .nombre("Pizza Vegetariana")
                .descripcion("Pizza con vegetales, queso y salsa de tomate")
                .calorias(400)
                .proteinas(20)
                .grasas(18)
                .carbohidratos(50)
                .vitaminas(Arrays.asList("A", "C"))
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        Comida comida9 = Comida.builder()
                .id("9")
                .nombre("Fruta Mixta")
                .descripcion("Ensalada de frutas frescas")
                .calorias(120)
                .proteinas(2)
                .grasas(1)
                .carbohidratos(30)
                .vitaminas(Arrays.asList("C", "K"))
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        // Crear instancias de Alimento para el tercer usuario
        Alimento alimento7 = Alimento.builder()
                .id("7")
                .comida(comida7)
                .tipo(Tipo.ALMUERZO)
                .horaConsumo(LocalDateTime.of(2024, 5, 23, 12, 0))
                .orden(Orden.PRIMER_PLATO)
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        Alimento alimento8 = Alimento.builder()
                .id("8")
                .comida(comida8)
                .tipo(Tipo.CENA)
                .horaConsumo(LocalDateTime.of(2024, 5, 23, 19, 30))
                .orden(Orden.PLATO_UNICO)
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        Alimento alimento9 = Alimento.builder()
                .id("9")
                .comida(comida9)
                .tipo(Tipo.MERIENDA_VESPERTINA)
                .horaConsumo(LocalDateTime.of(2024, 5, 23, 16, 30))
                .orden(Orden.PLATO_UNICO)
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        // Crear instancia de Dieta para el tercer usuario
        Dieta dieta3 = Dieta.builder()
                .id("3")
                .caloriasTarget(2200)
                .fechaInicio(LocalDateTime.of(2024, 5, 1, 0, 0))
                .fechaFin(LocalDateTime.of(2024, 5, 31, 23, 59))
                .consumoDeAgua(3.0f)
                .activa(true)
                .comidasSugeridas(Arrays.asList(ComidaSugerida.builder().id("7").orden(Orden.PRIMER_PLATO).tipo(Tipo.CENA).build(),
                        ComidaSugerida.builder().id("8").orden(Orden.PLATO_UNICO).tipo(Tipo.MERIENDA_VESPERTINA).build()))
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        // Crear instancia de Rutina para el tercer usuario
        Rutina rutina3 = Rutina.builder()
                .id("3")
                .tiempoDeSuenio(6)
                .caloriasQuemadas(600)
                .pasosRealizados(12000)
                .frecuenciaCardiaca(55)
                .nivelOxigenoSangre(99)
                .fechaSeguimiento(LocalDateTime.of(2024, 5, 23, 0, 0))
                .fechaUltimaModificacion(LocalDateTime.now())
                .comidasConsumidas(Arrays.asList(alimento7, alimento8, alimento9))
                .build();

        // Crear tercer usuario
        Usuario usuario3 = Usuario.builder()
                .email("usuario3@example.com")
                .nombreUsuario("usuario345")
                .contrasena(cifrar.encode("password"))
                .nombre("Carlos")
                .primerApellido("Ramírez")
                .segundoApellido("Torres")
                .fechaDeNacimiento(LocalDate.of(1975, 3, 20))
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .altura(180)
                .peso(85)
                .sexo(Sexo.HOMBRE)
                .dietas(Collections.singletonList(dieta3))
                .comidasRegistradas(Arrays.asList(comida7, comida8, comida9))
                .rutinas(Collections.singletonList(rutina3))
                .build();

        // Crear más comidas
        Comida comida10 = Comida.builder()
                .id("10")
                .nombre("Huevos Revueltos")
                .descripcion("Huevos revueltos con jamón y queso")
                .calorias(250)
                .proteinas(20)
                .grasas(15)
                .carbohidratos(5)
                .vitaminas(Arrays.asList("B12", "D"))
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        Comida comida11 = Comida.builder()
                .id("11")
                .nombre("Lasaña")
                .descripcion("Lasaña de carne con salsa de tomate")
                .calorias(500)
                .proteinas(30)
                .grasas(25)
                .carbohidratos(50)
                .vitaminas(Arrays.asList("A", "C"))
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        Comida comida12 = Comida.builder()
                .id("12")
                .nombre("Smoothie Verde")
                .descripcion("Smoothie de espinaca, manzana y plátano")
                .calorias(180)
                .proteinas(5)
                .grasas(2)
                .carbohidratos(40)
                .vitaminas(Arrays.asList("A", "C", "K"))
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        // Crear instancias de Alimento para el cuarto usuario
        Alimento alimento10 = Alimento.builder()
                .id("10")
                .comida(comida10)
                .tipo(Tipo.DESAYUNO)
                .horaConsumo(LocalDateTime.of(2024, 5, 24, 7, 30))
                .orden(Orden.PLATO_UNICO)
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        Alimento alimento11 = Alimento.builder()
                .id("11")
                .comida(comida11)
                .tipo(Tipo.ALMUERZO)
                .horaConsumo(LocalDateTime.of(2024, 5, 24, 12, 30))
                .orden(Orden.PRIMER_PLATO)
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        Alimento alimento12 = Alimento.builder()
                .id("12")
                .comida(comida12)
                .tipo(Tipo.MERIENDA_MATUTINA)
                .horaConsumo(LocalDateTime.of(2024, 5, 24, 10, 0))
                .orden(Orden.PLATO_UNICO)
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        // Crear instancia de Dieta para el cuarto usuario
        Dieta dieta4 = Dieta.builder()
                .id("4")
                .caloriasTarget(2500)
                .fechaInicio(LocalDateTime.of(2024, 5, 1, 0, 0))
                .fechaFin(LocalDateTime.of(2024, 5, 31, 23, 59))
                .consumoDeAgua(3.5f)
                .activa(true)
                .comidasSugeridas(Arrays.asList(ComidaSugerida.builder().id("10").orden(Orden.PRIMER_PLATO).tipo(Tipo.CENA).build(),
                        ComidaSugerida.builder().id("11").orden(Orden.PLATO_UNICO).tipo(Tipo.MERIENDA_VESPERTINA).build()))
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .build();

        // Crear instancia de Rutina para el cuarto usuario
        Rutina rutina4 = Rutina.builder()
                .id("4")
                .tiempoDeSuenio(8)
                .caloriasQuemadas(700)
                .pasosRealizados(15000)
                .frecuenciaCardiaca(65)
                .nivelOxigenoSangre(96)
                .fechaSeguimiento(LocalDateTime.of(2024, 5, 24, 0, 0))
                .fechaUltimaModificacion(LocalDateTime.now())
                .comidasConsumidas(Arrays.asList(alimento10, alimento11, alimento12))
                .build();

        // Crear cuarto usuario
        Usuario usuario4 = Usuario.builder()
                .email("usuario4@example.com")
                .nombreUsuario("usuario456")
                .contrasena(cifrar.encode("password"))
                .nombre("María")
                .primerApellido("Gómez")
                .segundoApellido("Hernández")
                .fechaDeNacimiento(LocalDate.of(1995, 8, 10))
                .fechaRegistro(LocalDateTime.now())
                .fechaUltimaModificacion(LocalDateTime.now())
                .altura(165)
                .peso(55)
                .sexo(Sexo.MUJER)
                .dietas(Collections.singletonList(dieta4))
                .comidasRegistradas(Arrays.asList(comida10, comida11, comida12))
                .rutinas(Collections.singletonList(rutina4))
                .build();

        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2, usuario3, usuario4);

        usuarios.forEach(usuario -> usuarioServicio.insertarDebug(usuario));
        usuarios.forEach(System.out::println);
    }
}
