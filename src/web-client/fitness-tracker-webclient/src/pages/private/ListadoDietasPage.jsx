import React, { useState, useEffect } from "react";
import axios from "axios";
import { TopBar } from "../../components/Topbar";
import {
  Container,
  Box,
  Grid,
  Card,
  CardContent,
  Typography,
  CardActionArea,
} from "@mui/material";
import { API_ROUTES } from "../../ApiRoutes.jsx";
import ImageCard from "../../components/ImageCard.jsx";
import moment from "moment";

import { PrivateBar } from "../../components/Privatebar.jsx";

const DietCard = ({ diet }) => (
  <Card sx={{ height: "100%" }}>
    <CardActionArea>
      <CardContent>
        <h1>Objetivo en calorías</h1>
        <Typography variant="h5" component="div">
          {diet?.caloriasTarget}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          Fecha de inicio:
          {moment(Date.parse(diet?.fechaInicio)).format('LL')}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          Fecha de fin:
          {moment(Date.parse(diet?.fechaFin)).format('LL')}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {diet?.consumoAgua}
        </Typography>
      </CardContent>
    </CardActionArea>
  </Card>
);

function ListadoDietasPage() {
  // State setup
  const [dataFecthSuccess, setDataFecthSuccess] = useState(false);
  const [dietaActiva, setDietaActiva] = useState({});
  const [dietas, setDietas] = useState([]);

  // Axios setup
  const tokenLS = localStorage.getItem("token");
  axios.defaults.headers.common["Authorization"] = `Bearer ${tokenLS}`;

  const loadDietas = async () => {
    try {
      const response = await axios.get(API_ROUTES.GetListDietasUsuario);

      setDataFecthSuccess(true);
      setDietas(response.data.data);

      for (let dietaIndex in dietas) {
        console.log(dietas[dietaIndex])
        if (dietas[dietaIndex].activa) {
          setDietaActiva(dietas[dietaIndex])
        }
      }

      console.log(response);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    loadDietas();
  }, [dataFecthSuccess]);

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
      <PrivateBar />
      <Container maxWidth="lg" sx={{ mt: 4 }}>
        <Box sx={{ mb: 4 }}>
          <Typography variant="h6" component="div" color="#FFF">
            Dieta actual
          </Typography>
          <DietCard diet={dietaActiva} />
        </Box>
        <Box>
          <Typography variant="h6" component="div" color="#FFF" sx={{ mb: 2 }}>
            Listado de dietas
          </Typography>
          <Grid container spacing={2}>
            {
            dietas.map(diet => (
              <Grid item xs={12} sm={6} md={4} key={diet.id}>
                <ImageCard
                  fechaFin={diet?.fechaFin}
                  fechaInicio={diet?.fechaInicio}
                  caloriasTarget={diet?.caloriasTarget}
                  dieta={diet}
                ></ImageCard>
              </Grid>
            ))}
          </Grid>
        </Box>
      </Container>
    </Box>
  );
}

export default ListadoDietasPage;
