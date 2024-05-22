import React from "react";

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

function DietGeneratorPage() {
  // Preparar datos del formulario
  const [edad, setEdad] = useState("");
  const [altura, setAltura] = useState("");
  const [peso, setPeso] = useState("");
  const [calorias, setCalorias] = useState("");
  const [tiempoCocina, setTiempoCocina] = useState("");
  const [fechaInicio, setFechaInicio] = useState("");
  const [fechaFin, setFechaFin] = useState("");
  // const [vegetariano, setVegetariano] = useState(false);

  const [state, setState] = React.useState({
    vegetariano: false,
    jason: false,
    antoine: false,
  });

  const handleChange = (event) => {
    setState({
      ...state,
      [event.target.name]: event.target.checked,
    });
  };

  const { vegetariano, jason, antoine } = state;

  function handleSubmit(event) {
    event.preventDefault();
    console.log(altura, calorias, tiempoCocina, fechaInicio, fechaFin, edad);
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
          <form onSubmit={handleSubmit} action={<Link to="/login" />}>
            {/* Primera fila de formulario */}
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
                  value={1}
                >
                  <MenuItem value={1}>Masculino</MenuItem>
                  <MenuItem value={2}>Femenino</MenuItem>
                  <MenuItem value={3}>Otro</MenuItem>
                </Select>
              </Grid>
            </Grid>
            {/* Segunda Fila de formulario */}
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
            {/* Tercera Fila de formulario */}
            <Grid container columnSpacing={2} sx={{ marginTop:'27px' }}>
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
                  value={3}
                >
                  <MenuItem value={1}>Sedentario</MenuItem>
                  <MenuItem value={2}>Moderado</MenuItem>
                  <MenuItem value={3}>Activo</MenuItem>
                  <MenuItem value={4}>Muy Activo</MenuItem>
                </Select>
              </Grid>
              <Grid item xs={6}>
                <Select
                  labelId="objectivo-principal-input"
                  id="objetivo-principal"
                  label="objectivo-principal"
                  fullWidth
                  sx={{ mb: 4 }}
                  value={4}
                >
                  <MenuItem value={1}>Perder Peso</MenuItem>
                  <MenuItem value={2}>Mantener Peso</MenuItem>
                  <MenuItem value={3}>Ganar Masa Muscular</MenuItem>
                  <MenuItem value={4}>Mejorar Salud</MenuItem>
                </Select>
              </Grid>
            </Grid>
            {/* Cuarta Fila de formulario */}
            <Grid container columnSpacing={2} sx={{ marginBottom: 0 }}>
              <Grid item xs={6}>
                <InputLabel id="nivel-actividad-input">Nivel de Actividad Física</InputLabel>
              </Grid>
              <Grid rowSpacing={2} columnSpacing={3}>
                <FormControl component="fieldset" variant="standard">
                  <FormGroup>
                    <FormControlLabel
                      control={
                        <Checkbox checked={vegetariano} onChange={handleChange} name="vegetariano" />
                      }
                      label="vegetariano"
                    />
                    
                  </FormGroup>
                </FormControl>
              </Grid>
            </Grid>
            <Grid>
              <InputLabel id="tiempo-cocina">Tiempo preferido cocina (en minutos)</InputLabel>
              <TextField
                type="text"
                variant="outlined"
                color="secondary"
                labelId="tiempo-cocina"
                onChange={(e) => setTiempoCocina(e.target.value)}
                value={tiempoCocina}
                fullWidth
                required
                sx={{ mb: 4 }}
              />
            </Grid>
            <InputLabel id="start-date">Fecha de inicio</InputLabel>
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
            </Select>
            <Button variant="outlined" color="primary" type="submit">
              GENERATE
            </Button>
          </form>
        </React.Fragment>
      </Container>
    </Box>
  );
}

export default DietGeneratorPage;
