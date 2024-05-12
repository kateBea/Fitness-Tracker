import React from "react";

import { TopBar } from "../../components/Topbar";
import { Container, Box, MenuItem, TextField, Select, InputLabel, Grid } from "@mui/material";

import { useState } from "react";
import { Button } from "@mui/material";
import { Link } from "react-router-dom";

function DietGeneratorPage() {
  // Preparar datos del formulario
  const [altura, setAltura] = useState("");
  const [calorias, setCalorias] = useState("");
  const [tiempoCocina, setTiempoCocina] = useState("");
  const [fechaInicio, setFechaInicio] = useState("");
  const [fechaFin, setFechaFin] = useState("");

  function handleSubmit(event) {
    event.preventDefault();
    console.log(altura, calorias, tiempoCocina, fechaInicio, fechaFin);
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
      <Container
        maxWidth="false"
        sx={{
          display: "flex",
          justifyContent: "center",
          alignContent: "center",
          alignItems: "center",
          height: "40px",
          background: "#869CB5",
        }}
      >
        <Box
          sx={{
            display: "flex",
            justifyContent: "flex-end",
            alignContent: "right",
            alignItems: "center",
            color: "#FFF",
          }}
        >
          <MenuItem>Inicio</MenuItem>
          <MenuItem>Perfil</MenuItem>
          <MenuItem>Hoy</MenuItem>
          <MenuItem>Calorias diaras</MenuItem>
        </Box>
      </Container>

      <Container sx={{ paddingTop: 2 }}>
        <React.Fragment>
          <h2>Modelar dieta</h2>
        

          <form onSubmit={handleSubmit} action={<Link to="/login" />}>


            <Grid container spacing={2} sx={{ marginBottom: 0 }}>
            <Grid item xs={6}>

            <InputLabel id="altura-input">Altura (en centímetros)</InputLabel>


            </Grid>
            <Grid item xs={6}>

            <InputLabel id="calorias-input">Calorías a quemar</InputLabel>
              
            </Grid>
            <Grid item xs={6}>

            <TextField
                type="text"
                labelId="altura-input"
                variant="outlined"
                color="secondary"
                onChange={(e) => setAltura(e.target.value)}
                value={altura}
                fullWidth
                required
              />
            </Grid>
            <Grid item xs={6}>
            
            <TextField
              type="text"
              variant="outlined"
              labelId="calorias-input"
              color="secondary"
              onChange={(e) => setCalorias(e.target.value)}
              value={tiempoCocina}
              fullWidth
              required
              sx={{ mb: 4 }}
            />
            </Grid>
          </Grid>
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
