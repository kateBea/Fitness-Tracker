import React from "react";
import { useEffect } from "react";
import axios from "axios";
import { TopBar } from "../../components/Topbar";
import { PrivateBar } from '../../components/Privatebar';
import {
  Container,
  Box,
  TextField,
  InputLabel,
  Grid,
  Typography,
  createTheme
} from "@mui/material";

import ImageUploader from '../../components/ImageUploader';

import { useState } from "react";
import { Button } from "@mui/material";
import { API_ROUTES } from "../../ApiRoutes";

function DietGeneratorPage() {
  // State setup
  const [dataLoadSuccess, setDataLoadSuccess] = useState(false);
  const [datosUsuario, setDatosUsuario] = useState({});
  // State setup
  const [dataFecthSuccess, setDataFecthSuccess] = useState(false);
  // Preparar datos del formulario
  const [nombre, setNombre] = useState("");
  const [nombreUsuario, setNombreUsuario] = useState("");
  const [primerApellido, setPrimerApellido] = useState("");
  const [segundoApellido, setSegundoApellido] = useState("");
  const [fechaDeNacimiento, setFechaDeNacimiento] = useState("");
  const [image, setImage] = useState("");
  const [objetivoPeso, setObjetivoPeso] = useState("");
  const [peso, setPeso] = useState("");
  const [altura, setAltura] = useState("");

  // Axios setup
  const token = localStorage.getItem("token");
  axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;

  const editarPerfil = async () => {
    try {
      const requestData = {
        nombre: nombre,
        nombreUsuario: nombreUsuario,
        primerApellido: primerApellido,
        segundoApellido: segundoApellido,
        fechaDeNacimiento: fechaDeNacimiento,
        objetivoPeso: objetivoPeso,
        peso: peso,
        algura: altura,
        image: image
      };
      console.log("requestData", requestData)
      const response = await axios.post(API_ROUTES.ModificarDatos, requestData);

      setDataFecthSuccess(true);
      console.log(response);
    } catch (error) {
      console.log(error);
    }
  };

  const loadPerfilData = async () => {
    try {
      const response = await axios.get(API_ROUTES.GetDatosUsuario);
      setDatosUsuario(response.data.data);
      setDataLoadSuccess(true);
      console.log(response.data.data)
    } catch (error) {
      console.log(error);
    }
  };

  const cargarDatos = () => {
    console.log("Cargando datos del usuario", datosUsuario);
    setNombre(datosUsuario.nombre || "");
    setNombreUsuario(datosUsuario.nombreUsuario || "");
    setPrimerApellido(datosUsuario.primerApellido || "");
    setSegundoApellido(datosUsuario.segundoApellido || "");
    setFechaDeNacimiento(datosUsuario.fechaDeNacimiento || "");
    setObjetivoPeso(datosUsuario.objetivoPeso || "");
    setPeso(datosUsuario.peso || "");
    setAltura(datosUsuario.altura || "");
    setImage(datosUsuario.imagen || "");
  };

  useEffect(() => {
    loadPerfilData();
  }, []);

  useEffect(() => {
    if (dataLoadSuccess) {
      cargarDatos();
    }
  }, [dataLoadSuccess, datosUsuario]);

  const handleImageUpload = (file) => {
    setImage(file);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    editarPerfil();
  }

  const theme = createTheme();

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
          <h2>Editar Perfil</h2>
          <form onSubmit={handleSubmit}>
            {/* EDAD Y GENERO */}
            <Grid container columnSpacing={2} sx={{ marginBottom: 0 }}>
              <Grid item xs={6}>
                <InputLabel id="nombre-input">Nombre</InputLabel>
              </Grid>
              <Grid item xs={6}>
                <InputLabel id="nombreUsuario-input">Username</InputLabel>
              </Grid>
              <Grid item xs={6}>
                <TextField
                  type="text"
                  variant="outlined"
                  color="secondary"
                  onChange={(e) => setNombre(e.target.value)}
                  value={nombre}
                  fullWidth
                  required
                />
              </Grid>
              <Grid item xs={6}>
                <TextField
                  type="text"
                  variant="outlined"
                  color="secondary"
                  onChange={(e) => setNombreUsuario(e.target.value)}
                  value={nombreUsuario}
                  fullWidth
                  required
                />
              </Grid>
            </Grid>
            {/* Nick Edad */}
            <Grid container columnSpacing={2} sx={{ marginTop: '20px' }}>
              <Grid item xs={6}>
                <InputLabel id="primerApellido-input">Primer Apellido</InputLabel>
              </Grid>
              <Grid item xs={6}>
                <InputLabel id="segundoApellido-input">Segundo Apellido</InputLabel>
              </Grid>
              <Grid item xs={6}>
                <TextField
                  type="text"
                  variant="outlined"
                  color="secondary"
                  onChange={(e) => setPrimerApellido(e.target.value)}
                  value={primerApellido}
                  fullWidth
                  required
                />
              </Grid>
              <Grid item xs={6}>
                <TextField
                  type="text"
                  variant="outlined"
                  color="secondary"
                  onChange={(e) => setSegundoApellido(e.target.value)}
                  value={segundoApellido}
                  fullWidth
                />
              </Grid>
            </Grid>
            {/* Objetivos */}
            <Grid container columnSpacing={2} sx={{ marginTop: '20px' }}>
              <Grid item xs={6}>
                <InputLabel id="fechaNacimiento-input">Fecha de Nacimiento</InputLabel>
              </Grid>
              <Grid item xs={6}>
                <InputLabel id="pesoObjetivo-input">Objetivo de Peso</InputLabel>
              </Grid>
              <Grid item xs={6}>
                <TextField
                  type="calendar"
                  variant="outlined"
                  color="secondary"
                  onChange={(e) => setFechaDeNacimiento(e.target.value)}
                  value={fechaDeNacimiento}
                  fullWidth
                  required
                />
              </Grid>
              <Grid item xs={6}>
                <TextField
                  type="text"
                  variant="outlined"
                  color="secondary"
                  onChange={(e) => setObjetivoPeso(e.target.value)}
                  value={objetivoPeso}
                  fullWidth
                  required
                />
              </Grid>
            </Grid>
            <Grid container columnSpacing={2} sx={{ marginTop: '20px' }}>
              <Grid item xs={6}>
                <InputLabel id="peso-input">Peso</InputLabel>
              </Grid>
              <Grid item xs={6}>
                <InputLabel id="altura-input">Altura</InputLabel>
              </Grid>
              <Grid item xs={6}>
                <TextField
                  type="text"
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
                  type="text"
                  variant="outlined"
                  color="secondary"
                  onChange={(e) => setAltura(e.target.value)}
                  value={altura}
                  fullWidth
                  required
                />
              </Grid>
            </Grid>
            {/* Comentarios Adicionales */}
            <Grid sx={{ marginTop: '20px' }}>
              <ImageUploader onImageUpload={handleImageUpload} />
            </Grid>
            <Grid sx={{[theme.breakpoints.down('800')]:{display:'flex',justifyContent:'center',marginTop:'40px'}}}>
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
                Editar
              </Button>
            </Grid>
          </form>
        </React.Fragment>
      </Container>
    </Box>
  );
}

export default DietGeneratorPage;
