import React from "react";
import { useEffect } from "react";
import axios from "axios";
import { TopBar } from "../../components/Topbar";
import { PrivateBar } from '../../components/Privatebar';
import {
  Container,
  Box,
  MenuItem,
  TextField,
  Select,
  InputLabel,
  Grid,
  FormGroup,
  FormControlLabel,
  Checkbox,
  FormControl,
} from "@mui/material";

import { useState } from "react";
import { Button } from "@mui/material";
import { Link } from "react-router-dom";
import { API_ROUTES } from "../../ApiRoutes";

function DietGeneratorPage() {
   // State setup
   const [dataFecthSuccess, setDataFecthSuccess] = useState(false);
   // Preparar datos del formulario
  const [edad, setEdad] = useState("");
  const [genero, setGenero] = useState("")
  const [altura, setAltura] = useState("");
  const [peso, setPeso] = useState("");
  const [actividad, setActividad] = useState("");
  const [objetivo, setObjetivo] = useState("");
  const [habilidad, setHabilidad] = useState("");
  const [tiempoCocina, setTiempoCocina] = useState("");
  // const [fechaInicio, setFechaInicio] = useState("");
  // const [fechaFin, setFechaFin] = useState("");
  const [notas, setNotas] = useState("");

  const [restricciones, setRestricciones] = React.useState({
    vegetariano: false,
    vegano: false,
    gluten: false,
    lacteos: false,
    frutoSeco: false,
    otros: false
  });

  const handleChange = (event) => {
    setRestricciones({
      ...restricciones,
      [event.target.name]: event.target.checked,
    });
  };

  const { vegetariano, vegano, gluten, lacteos, frutoSeco, otros } = restricciones;
 
   // Axios setup
   const token = localStorage.getItem("token");
   axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
 
   const generarDieta = async () => {
     try {
      // Recoger datos del usuario
      const responseUserData = await axios.get(API_ROUTES.GetDatosUsuario);
      const datosUsuario = responseUserData?.data;

      // Restricciones
      const rest = [];

      if (restricciones.vegetariano) { rest.push("vegetariano"); }
      if (restricciones.vegano) { rest.push("vegano"); }
      if (restricciones.gluten) { rest.push("gluten"); }
      if (restricciones.lacteos) { rest.push("lacteos"); }
      if (restricciones.frutoSeco) { rest.push("frutoSeco"); }

      const requestData = {
        fechaNacimiento: datosUsuario?.fechaDeNacimiento,
        sexo: genero,
        altura: datosUsuario?.altura,
        nivelActividadFisica: actividad,
        objetivoPrincipal: objetivo,
        restricionesAlimenticias: rest.length == 0 ? "sin restricciones" : rest.join(", "),
        comentariosAdicionales: notas,
        fechaInicio: "2024-06-10",
        fechaFin: "2024-09-10",
      };
      
       const response = await axios.post(API_ROUTES.GenerarDieta, requestData);
 
       setDataFecthSuccess(true);
       console.log(response);
     } catch (error) {
       console.log(error);
     }
   };

   const handleSubmit = async (event) => {
    event.preventDefault();
    generarDieta();
  }

  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "column",
        background: "#293B50",
        minHeight: "1000px",
        justifyContent: "start",
        alignItems: "center",
      }}
    >
      <TopBar />
      <PrivateBar />

      <Container sx={{
        background: '#FFF',
        marginTop: '30px',
        borderRadius: '30px',
        padding: 3
      }}>
        <React.Fragment>
          <h2>Modelar dieta</h2>
          <form onSubmit={handleSubmit}>
            {/* EDAD Y GENERO */}
            <Grid container columnSpacing={2} sx={{ marginBottom: 0 }}>
              <Grid item xs={6}>
                <InputLabel id="edad-input">Edad</InputLabel>
              </Grid>
              <Grid item xs={6}>
                <InputLabel id="genero-input">Género</InputLabel>
              </Grid>
              <Grid item xs={6}>
                <TextField
                  type="number"
                  labelId="edad-input"
                  variant="outlined"
                  color="secondary"
                  onChange={(e) => setEdad(e.target.value)}
                  value={edad}
                  fullWidth
                  required
                />
              </Grid>
              <Grid item xs={6}>
                <Select
                  labelId="genero-input"
                  id="genero"
                  label="genero"
                  fullWidth
                  sx={{ mb: 4 }}
                  value={genero}
                  onChange={(e) => setGenero(e.target.value)}
                  required
                >
                  <MenuItem value={"hombre"}>Hombre</MenuItem>
                  <MenuItem value={"mujer"}>Mujer</MenuItem>
                  <MenuItem value={"otro"}>Otro</MenuItem>
                </Select>
              </Grid>
            </Grid>
            {/* PESO Y ALTURA */}
            <Grid container columnSpacing={2} sx={{ marginBottom: 0 }}>
              <Grid item xs={6}>
                <InputLabel id="peso-input">Peso (kg)</InputLabel>
              </Grid>
              <Grid item xs={6}>
                <InputLabel id="altura-input">Altura (en centimetros)</InputLabel>
              </Grid>
              <Grid item xs={6}>
                <TextField
                  type="number"
                  labelId="peso-input"
                  variant="outlined"
                  color="secondary"
                  onChange={(e) => setPeso(e.target.value)}
                  value={peso}
                  fullWidth
                  required
                />
              </Grid>
              <Grid item xs={6}>
                <TextField
                  type="number"
                  labelId="altura-input"
                  variant="outlined"
                  color="secondary"
                  onChange={(e) => setAltura(e.target.value)}
                  value={altura}
                  fullWidth
                  required
                />
              </Grid>
            </Grid>
            {/* NIVEL DE ACTIVIDAD FISICA Y OBJETIVO PRINCIPAL*/}
            <Grid container columnSpacing={2} sx={{ marginTop: '27px' }}>
              <Grid item xs={6}>
                <InputLabel id="nivel-actividad-input">Nivel de Actividad Física</InputLabel>
              </Grid>
              <Grid item xs={6}>
                <InputLabel id="objetivo-principal-input">Objetivo Principal</InputLabel>
              </Grid>
              <Grid item xs={6}>
                <Select
                  labelId="nivel-actividad-input"
                  id="nivel-actividad"
                  label="nivel-actividad"
                  fullWidth
                  sx={{ mb: 4 }}
                  value={actividad}
                  onChange={(e) => setActividad(e.target.value)}
                  required
                >
                  <MenuItem value={"sedentario"}>Sedentario</MenuItem>
                  <MenuItem value={"moderado"}>Moderado</MenuItem>
                  <MenuItem value={"activo"}>Activo</MenuItem>
                  <MenuItem value={"muyActivo"}>Muy Activo</MenuItem>
                </Select>
              </Grid>
              <Grid item xs={6}>
                <Select
                  labelId="objectivo-principal-input"
                  id="objetivo-principal"
                  label="objectivo-principal"
                  fullWidth
                  sx={{ mb: 4 }}
                  value={objetivo}
                  onChange={(e) => setObjetivo(e.target.value)}
                  required
                >
                  <MenuItem value={"perder"}>Perder Peso</MenuItem>
                  <MenuItem value={"mantener"}>Mantener Peso</MenuItem>
                  <MenuItem value={"ganar"}>Ganar Masa Muscular</MenuItem>
                  <MenuItem value={"mejorar"}>Mejorar Salud</MenuItem>
                </Select>
              </Grid>
            </Grid>
            {/* RESTRICCIONES ALIMENTICIAS CHECKS*/}
            <Grid container sx={{ marginBottom: 0, display: 'flex', alignItems: 'center', alignContent: 'center', justifyContent: 'center' }}>
              <Grid item lg={2} md={3} sm={12} >
                <InputLabel id="nivel-actividad-input">Restricciones Alimenticias</InputLabel>
              </Grid>
              <Grid item lg={10} md={9} sm={12}>
                <FormControl component="fieldset" variant="standard" fullWidth>
                  <FormGroup>
                    <Grid container >
                      <Grid item lg={2} md={2.3} sm={3} xs={4}>
                        <FormControlLabel
                          control={
                            <Checkbox checked={vegetariano} onChange={handleChange} name="vegetariano" />
                          }
                          label="Vegetariano"
                        />
                      </Grid>
                      <Grid item lg={2} md={1.7} sm={2.3} xs={4}>
                        <FormControlLabel
                          control={
                            <Checkbox checked={vegano} onChange={handleChange} name="vegano" />
                          }
                          label="Vegano"
                        />
                      </Grid>
                      <Grid item lg={2} md={1.5} sm={2.5} xs={4}>
                        <FormControlLabel
                          control={
                            <Checkbox checked={gluten} onChange={handleChange} name="gluten" />
                          }
                          label="Gluten"
                        />
                      </Grid>
                      <Grid item lg={2} md={1.7} sm={2.2} xs={4}>
                        <FormControlLabel
                          control={
                            <Checkbox checked={lacteos} onChange={handleChange} name="lacteos" />
                          }
                          label="Lacteos"
                        />
                      </Grid>
                      <Grid item lg={2} md={2.3} sm={3} xs={4}>
                        <FormControlLabel
                          control={
                            <Checkbox checked={frutoSeco} onChange={handleChange} name="frutoSeco" />
                          }
                          label="Frutos Secos"
                          sx={{ width: '139px' }}
                        />
                      </Grid>
                      <Grid item lg={2} md={2} sm={6} xs={4}>
                        <FormControlLabel
                          control={
                            <Checkbox checked={otros} onChange={handleChange} name="otros" />
                          }
                          label="Otros"
                          sx={{ width: '139px' }}
                        />
                      </Grid>
                    </Grid>
                  </FormGroup>
                </FormControl>
              </Grid>
            </Grid>
            {/* HABILIDAD EN LA COCINA Y TIEMPO DE COCINA */}
            <Grid container columnSpacing={2} sx={{ marginTop: '20px'}}>
              <Grid item xs={6}>
                <InputLabel id="nivel-habilidad-cocina">
                  Habilidad en la Cocina
                </InputLabel>
                <Select
                  labelId="nivel-habilidad-cocina"
                  id="habilidad-cocina"
                  label="cocina"
                  fullWidth
                  sx={{ mb: 4 }}
                  value={habilidad}
                  onChange={(e) => setHabilidad(e.target.value)}
                  required
                >
                  <MenuItem value={"principiante"}>Principiante</MenuItem>
                  <MenuItem value={"intermedio"}>Intermedio</MenuItem>
                  <MenuItem value={"avanzado"}>Avanzado</MenuItem>
                </Select>
              </Grid>
              <Grid item xs={6}>
                <InputLabel id="tiempo-disponible-cocina">
                  Tiempo disponible para Cocinar
                </InputLabel>
                <Select
                  labelId="ntiempo-disponible-cocina"
                  id="tiempo-cocina"
                  label="tiempo"
                  fullWidth
                  sx={{ mb: 4 }}
                  value={tiempoCocina}
                  onChange={(e) => setTiempoCocina(e.target.value)}
                  required
                >
                  <MenuItem value={"menos30"}>Menos de 30 minutos</MenuItem>
                  <MenuItem value={"30/60"}>30-60 minutos</MenuItem>
                  <MenuItem value={"mas60"}>Más de 60 minutos</MenuItem>
                </Select>
              </Grid> 
            </Grid>
            {/* Comentarios Adicionales */}
            <Grid>
              <InputLabel id="tiempo-cocina">Notas y comentarios adicionales</InputLabel>
              <TextField
                type="text"
                variant="outlined"
                color="secondary"
                labelId="tiempo-cocina"
                onChange={(e) => setNotas(e.target.value)}
                value={notas}
                fullWidth
                required
                sx={{ mb: 4 }}
              />
            </Grid>
            {/* <InputLabel id="start-date">Fecha de inicio</InputLabel>
            <TextField
              type="date"
              labelId="start-date"
              variant="outlined"
              color="secondary"
              onChange={(e) => setFechaInicio(e.target.value)}
              value={fechaInicio}
              fullWidth
              required
              sx={{ mb: 4 }}
            />
            <InputLabel id="end-date">Fecha de finalización</InputLabel>
            <TextField
              type="date"
              labelId="end-date"
              variant="outlined"
              color="info"
              onChange={(e) => setFechaFin(e.target.value)}
              value={fechaInicio}
              fullWidth
              required
              sx={{ mb: 4 }}
            />
            <InputLabel id="nivel-actividad-fisica">
              Actividad física
            </InputLabel>
            <Select
              labelId="nivel-actividad-fisica"
              id="nivel-actividad"
              label="Actividad física"
              fullWidth
              sx={{ mb: 4 }}
              value={1}
            >
              <MenuItem value={1}>Moderado</MenuItem>
              <MenuItem value={2}>Activo</MenuItem>
              <MenuItem value={3}>Muy activo</MenuItem>
            </Select> */}
            <Button variant="outlined" color="primary" type="submit" sx={{
              background:'#D92668',
              margin:'10px',
              width:'230px',
              borderTopLeftRadius:'30px',
              borderBottomLeftRadius:'0px',
              borderTopRightRadius:'0px',
              borderBottomRightRadius:'30px',
              textTransform: 'none',
              fontSize:'20pt',
              
            }}>
              GENERATE
            </Button>
          </form>
        </React.Fragment>
      </Container>
    </Box>
  );
}

export default DietGeneratorPage;
