import React from "react";
import { useNavigate } from "react-router-dom";
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
  createTheme,
  Select,
  MenuItem
} from "@mui/material";

import ImageUploader from '../../components/ImageUploader';
import { toast, ToastContainer } from 'react-toastify';
import { useState } from "react";
import { Button } from "@mui/material";
import { API_ROUTES } from "../../ApiRoutes";

function DietGeneratorPage() {

  const navigate = useNavigate();
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
  const [sexo, setSexo] = useState("");

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
        altura: altura,
        imagen: image,
        sexo: sexo
      };
      console.log("requestData", requestData)
      const response = await axios.put(API_ROUTES.ModificarDatos, requestData);

      if (response.data.success) {
        // Navega a la página de inicio de sesión si el registro es exitoso.
        toast.success("Login successful!"); // Mostrar notificación de éxito
      } else {
        // Imprime los errores en la consola si hubo algún problema.
        console.log(response.data.errors);
        throw new Error("Error en el login: " + error.message);
      }

      setDataFecthSuccess(true);
      navigate("/perfil")
    } catch (error) {
      toast.error("Login failed: " + error.message); // Mostrar notificación de error
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

  // Función para cargar los datos del usuario en los campos del formulario
  const cargarDatos = () => {
    console.log("Cargando datos del usuario", datosUsuario);
    // Establecer los valores de los estados con los datos del usuario
    setNombre(datosUsuario.nombre || "");
    setNombreUsuario(datosUsuario.nombreUsuario || "");
    setPrimerApellido(datosUsuario.primerApellido || "");
    setSegundoApellido(datosUsuario.segundoApellido || "");
    setFechaDeNacimiento(datosUsuario.fechaDeNacimiento || "");
    setObjetivoPeso(datosUsuario.objetivoPeso || "");
    setPeso(datosUsuario.peso || "");
    setAltura(datosUsuario.altura || "");
    setImage(datosUsuario.imagen || "");
    setSexo(datosUsuario.sexo != null ? datosUsuario.sexo : "hombre");
  };

  // Efecto para cargar los datos del perfil al montar el componente
  useEffect(() => {
    loadPerfilData(); // Llamar a la función para cargar los datos del perfil
  }, []);

  // Efecto para cargar los datos del usuario cuando la carga de datos es exitosa o cuando los datos del usuario cambian
  useEffect(() => {
    if (dataLoadSuccess) {
      cargarDatos(); // Llamar a la función para cargar los datos del usuario en los campos del formulario
    }
  }, [dataLoadSuccess, datosUsuario]); // Dependencias: dataLoadSuccess y datosUsuario

  // Función para manejar la carga de la imagen del usuario
  const handleImageUpload = (file) => {
    setImage(file); // Establecer la imagen seleccionada en el estado 'image'
  };

  // Función para manejar el envío del formulario de edición de perfil
  const handleSubmit = async (event) => {
    event.preventDefault();
    editarPerfil(); // Llamar a la función para editar el perfil del usuario
  };

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
      <ToastContainer /> {/* Contenedor de notificaciones */}
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
              <Grid item xs={4}>
                <InputLabel id="nombre-input">Nombre</InputLabel>
              </Grid>
              <Grid item xs={4}>
                <InputLabel id="nombreUsuario-input">Username</InputLabel>
              </Grid>
              <Grid item xs={4}>
                <InputLabel id="genero-input">Género</InputLabel>
              </Grid>
              <Grid item xs={4}>
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
              <Grid item xs={4}>
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
              <Grid item xs={4}>
                <Select
                  labelId="genero-input"
                  id="genero"
                  label="genero"
                  fullWidth
                  sx={{ mb: 4 }}
                  value={sexo}
                  onChange={(e) => setSexo(e.target.value)}
                  required
                >
                  <MenuItem value={"Hombre"}>Hombre</MenuItem>
                  <MenuItem value={"Mujer"}>Mujer</MenuItem>
                  <MenuItem value={"Otro"}>Otro</MenuItem>
                </Select>
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
                <InputLabel id="fechaNacimiento-input">Fecha de Nacimiento(yyyy-mm-dd)</InputLabel>
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
                  type="number"
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
                  type="number"
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
            <Grid sx={{ marginTop: '20px', width: '100%', display: 'flex', justifyContent: 'center' }}>
              <ImageUploader onImageUpload={handleImageUpload} />
            </Grid>
            <Grid sx={{ [theme.breakpoints.down('800')]: { display: 'flex', justifyContent: 'center', marginTop: '40px' } }}>
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
