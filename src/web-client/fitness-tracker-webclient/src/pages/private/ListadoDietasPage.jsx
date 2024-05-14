import React from "react";

import axios from "axios";

import { TopBar } from "../../components/Topbar";
import { Container, Box, MenuItem } from "@mui/material";

// Routes
import { DebugBaseUrl } from "../../ApiRoutes.jsx";

import Logo from "../../img/logo-fitness-tracker.png";
import ImageCard from "../../components/ImageCard.jsx";

function ListadoDietasPage() {
  // Axios test
  axios.get(DebugBaseUrl).then((response) => {
    console.log(response.data);
  });

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

      <Container>
        <Box sx={{ display: "grid", gridTemplateColumns: "repeat(3, 1fr)" }}>
          <ImageCard></ImageCard>
          <ImageCard></ImageCard>
          <ImageCard></ImageCard>
        </Box>
      </Container>
    </Box>
  );
}

export default ListadoDietasPage;
