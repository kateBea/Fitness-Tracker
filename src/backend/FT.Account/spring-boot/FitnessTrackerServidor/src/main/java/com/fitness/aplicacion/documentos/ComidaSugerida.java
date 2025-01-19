package com.fitness.aplicacion.documentos;

import lombok.*;

/**
 * Modelo que representa una Comida sugerida
 *
 * @version 1.0
 * */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class ComidaSugerida {
    @EqualsAndHashCode.Include
    private String id;
    private Tipo tipo;
    private Orden orden;
}
