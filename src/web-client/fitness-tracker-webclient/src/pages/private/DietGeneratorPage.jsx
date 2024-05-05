import React from 'react'

function DietGeneratorPage() {
  return (
    <div>
      <form class="container mt-3">
        <div class="row mb-3">
          <div class="col-sm-1">
            <label for="nombre" class="col-sm-2 col-form-label">
              Nombre
            </label>
          </div>
          <div class="col-sm">
            <input
              type="text"
              id="nombre"
              class="form-control"
              placeholder="Nombre..."
              aria-label="Nombre"
            ></input>
          </div>
          <div class="col-sm-1">
            <label for="apellidos" class="col-sm-2 col-form-label">
              Apellidos
            </label>
          </div>
          <div class="col-sm">
            <input
              type="text"
              id="apellidos"
              class="form-control"
              placeholder="Apellidos..."
              aria-label="Apellidos"
            ></input>
          </div>
        </div>

        <div class="row mb-3">
          <div class="col-sm-1">
            <label for="correo-electronico" class="col-sm-2 col-form-label">
              Email
            </label>
          </div>
          <div class="col-sm">
            <input
              type="email"
              class="form-control"
              id="correo-electronico"
              placeholder="Correo electrónico..."
              aria-label="Correo electrónico"
            ></input>
          </div>
        </div>

        <div class="row mb-3">
          <div class="col-sm-1">
            <label for="direccion" class="col-sm-2 col-form-label">
              Dirección
            </label>
          </div>
          <div class="col-sm">
            <input
              type="text"
              class="form-control"
              id="direccion"
              placeholder="Dirección..."
              aria-label="Dirección"
            ></input>
          </div>
        </div>

        <div class="row mb-3">
          <div class="col-sm-1">
            <label for="ciclo" class="form-label">
              Ciclo
            </label>
          </div>

          <div class="col-sm">
            <select
              class="form-select col-3"
              id="cfgs"
              aria-label="Grado superior"
            >
              <option
                value="Desarrollo de Aplicaciones Multiplataforma"
                id="grado-dam"
                onselect="saveCicloModulo(value)"
              >
                DAM (Desarrollo de Aplicaciones multiplataforma)
              </option>
              <option
                value="Desarrollo de Aplicaciones Web"
                id="grado-daw"
                onselect="saveCicloModulo(value)"
              >
                DAW (Desarrollo de Aplicaciones Web)
              </option>
              <option
                value="Administración de Sistemas informáticos en Red"
                id="grado-asir"
                onselect="saveCicloModulo(value)"
              >
                ASIR (Administración de Sistemas Informáticos en Red)
              </option>
            </select>

            <select
              class="form-select"
              id="cfgm"
              aria-label="Grado medio"
              hidden
            >
              <option
                value="Sistemas microinformáticos y redes"
                id="grado-smr"
                onselect="saveCicloModulo(value)"
              >
                SMR (Sistemas Microinformáticos y Redes)
              </option>
            </select>
          </div>

          <div class="col-sm">
            <div class="form-check">
              <input
                class="form-check-input"
                type="radio"
                name="grado"
                value="Grado Medio"
                id="grado-medio"
                onclick="seleccionGrado(value)"
              ></input>
              <label class="form-check-label" for="grado-medio">
                Grado medio
              </label>
            </div>
            <div class="form-check">
              <input
                class="form-check-input"
                type="radio"
                name="grado"
                value="Grado Superior"
                id="grado-superior"
                onclick="seleccionGrado(value)"
                checked
              ></input>
              <label class="form-check-label" for="grado-superior">
                Grado Superior
              </label>
            </div>
          </div>
        </div>

        <div class="row mb-3">
          <div class="col">
            <button
              type="button"
              class="btn btn-success"
              onclick="generarPDF()"
            >
              Generar PDF
            </button>
          </div>

          <div class="col">
            <button
              type="button"
              class="btn btn-danger"
              onclick="resetearCampos()"
            >
              Resetear
            </button>
          </div>
        </div>
      </form>
    </div>
  );
}

export default DietGeneratorPage