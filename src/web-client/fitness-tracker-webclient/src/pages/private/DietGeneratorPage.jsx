import React from "react";
import { useNavigate } from "react-router-dom";
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
  const navigate = useNavigate();

  // State setup
  const [dataFecthSuccess, setDataFecthSuccess] = useState(false);
  // Preparar datos del formulario
  const [genero, setGenero] = useState("")
  const [actividad, setActividad] = useState("");
  const [objetivo, setObjetivo] = useState("");
  const [notas, setNotas] = useState("");
  const [fechaInicio, setFechaInicio] = useState("");
  const [fechaFin, setFechaFin] = useState("");

  //Estado de los diferentes checkbox 
  const [restricciones, setRestricciones] = React.useState({
    vegetariano: false,
    vegano: false,
    gluten: false,
    lacteos: false,
    frutoSeco: false,
  });

  // Función para manejar cambios en las opciones de los checkbox
  const handleChange = (event) => {
    setRestricciones({
      ...restricciones,
      [event.target.name]: event.target.checked,
    });
  };

  // Desestructurar opciones de dieta
  const { vegetariano, vegano, gluten, lacteos, frutoSeco, otros } = restricciones;

  // Axios setup: Obtener y establecer el token de autorización
  const token = localStorage.getItem("token");
  axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;

  // Función asincrónica para generar la dieta
  const generarDieta = async () => {
    console.log("se llama generar dieta")

    try {
      // Obtener datos del usuario
      const responseUserData = await axios.get(API_ROUTES.GetDatosUsuario);
      const datosUsuario = responseUserData?.data;

      const items = []

      if (restricciones.frutoSeco) { items.push("fruto seco") }
      if (restricciones.vegetariano) { items.push("vegetariano") }
      if (restricciones.vegano) { items.push("vegano") }
      if (restricciones.gluten) { items.push("gluten") }
      if (restricciones.lacteos) { items.push("lacteos") }

      const restriccionesList = items.join(", ")

      // Construir objeto de datos para enviar en la solicitud POST
      const requestData = {
        sexo: datosUsuario?.sexo, // Asumiendo que 'genero' está definido en otro lugar del código
        fechaNacimiento: datosUsuario?.fechaDeNacimiento,
        nivelActividadFisica: actividad,
        altura: datosUsuario?.altura,
        objetivoPrincipal: objetivo, 
        restriccionesAlimenticias: restriccionesList,
        comentariosAdicionales: notas, 
        fechaInicio: fechaInicio,
        fechaFin: fechaFin,
      };
      // Realizar solicitud POST para generar la dieta
      const response = await axios.post(API_ROUTES.GenerarDieta, requestData);
      // Marcar que la solicitud de datos fue exitosa
      setDataFecthSuccess(true);

      console.log(response)
      
      const comidas = []
      const dieta = response.data.dieta;

      if (response.data.success) {

        for (let i = 0; i < dieta.comidas.length; ++i) {
          comidas.push({
              idComida: dieta.comidas[i].id,
              orden: dieta.comidas[i].ordenComida,
              tipo: dieta.comidas[i].tipoComida,
              nombre: dieta.comidas[i].nombre,
              descripcion: dieta.comidas[i].descripcion,
              calorias:dieta.comidas[i].caloriasConsumidas,
              proteinas: dieta.comidas[i].proteinas,
              grasas: dieta.comidas[i].grasas,
              carbohidratos: dieta.comidas[i].carbohidratos,
              vitaminas: dieta.comidas[i].vitaminas
          });
        }
        
        const registrarDietaData = {
          caloriasTarget: dieta?.gastoCalorias,
          fechaInicio: dieta?.fechaInicio,
          fechaFin: dieta?.fechaFin,
          consumoDeAgua: dieta?.consumoAgua,
          comidasSugeridas: comidas
        };

        const response = await axios.post(API_ROUTES.RegistrarDieta, registrarDietaData);

        navigate("/ListadoDietas");
      }
    } catch (error) {
      // Manejar errores mostrándolos en la consola
      console.log(error);
    }
  };
  // Función para manejar la presentación del formulario
  const handleSubmit = async (event) => {
    event.preventDefault();
    generarDieta(); // Llamar a la función asincrónica para generar la dieta
  };

  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "column",
        background: "#293B50",
        minHeight: '100vh',
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
            {/* FECHA INICIO Y FECHA FIN*/}
            <Grid container columnSpacing={2} sx={{ marginTop: '27px' }}>
              <Grid item xs={6}>
                <InputLabel id="fecha-fin">Fecha de inicio</InputLabel>
              </Grid>
              <Grid item xs={6}>
                <InputLabel id="fecha-inicio">Fecha de inicio</InputLabel>
              </Grid>
              <Grid item xs={6}>
              <TextField
                type="text"
                variant="outlined"
                color="secondary"
                labelId="fecha-inicio"
                placeholder="YYYY-MM-DD"
                onChange={(e) => setFechaInicio(e.target.value)}
                value={fechaInicio}
                fullWidth
                required
                sx={{ mb: 4 }}
              />
              </Grid>
              <Grid item xs={6}>
              <TextField
                type="text"
                variant="outlined"
                color="secondary"
                labelId="fecha-fin"
                placeholder="YYYY-MM-DD"
                onChange={(e) => setFechaFin(e.target.value)}
                value={fechaFin}
                fullWidth
                required
                sx={{ mb: 4 }}
              />
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
                    </Grid>
                  </FormGroup>
                </FormControl>
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
            <Button variant="outlined" color="primary" type="submit" sx={{
                backgroundColor: '#293B50',
                fontWeight: 'bold',
                color: 'white',
                borderRadius: '20px',
                paddingLeft: '10px',
                paddingRight: '10px',
                fontSize: '18pt',
                '&:hover': {
                  background: '#436489',
                },
              }}>
                Generar
              </Button>
          </form>
        </React.Fragment>
      </Container>
    </Box>
  );
}

export default DietGeneratorPage;
