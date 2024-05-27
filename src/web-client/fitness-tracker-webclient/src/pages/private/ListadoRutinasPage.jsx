import React from "react";
import axios from "axios";

import { TopBar } from "../../components/Topbar";
import { Container, Box,  Grid, Typography, } from "@mui/material";
import { API_ROUTES } from "../../ApiRoutes.jsx";
import RutinaCard from "../../components/RutinaCard.jsx";  // Adjust the import path as necessary
import { useState } from "react";
import { useEffect } from "react";

import { PrivateBar } from "../../components/Privatebar.jsx";


const ListadoRutinasPage = () => {
  const [rutinas, setRutinas] = useState([]);

  const loadRutinas = async () => {
    try {
      const response = await axios
        .get(API_ROUTES.GetListRutinasUsuario, { params: { fetchAll: true }});

        setRutinas(response.data)
      console.log(response);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    const tokenLS = localStorage.getItem("token");
    axios.defaults.headers.common["Authorization"] = `Bearer ${tokenLS}`;
    loadRutinas();
  }, []);

  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "column",
        background: "#293B50",
        minHeight: "100vh",
        justifyContent: "start",
        alignItems: "center",
      }}
    >
      <TopBar />
      <PrivateBar/>
      <Container maxWidth="lg" sx={{ mt: 4 }}>
        <Typography variant="h4" component="div" color="#FFF" sx={{ mb: 4 }}>
          Listado de Rutinas
        </Typography>
        <Grid container spacing={2}>
          {rutinas.map((rutina) => (
            <Grid item xs={12} sm={6} md={4} key={rutina._id}>
              <RutinaCard rutina={rutina} />
            </Grid>
          ))}
        </Grid>
      </Container>
    </Box>
  );
};

export default ListadoRutinasPage;
