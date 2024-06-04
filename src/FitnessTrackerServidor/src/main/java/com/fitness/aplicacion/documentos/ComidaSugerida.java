package com.fitness.aplicacion.documentos;

import lombok.*;

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
