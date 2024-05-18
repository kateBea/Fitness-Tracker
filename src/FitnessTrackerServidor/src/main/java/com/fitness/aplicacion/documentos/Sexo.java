package com.fitness.aplicacion.documentos;


public enum Sexo {
    HOMBRE {
        @Override
        public String toString() {
            return "Hombre";
        }
    },

    MUJER {
        @Override
        public String toString() {
            return "Mujer";
        }
    };
}
