import React, { useState } from "react";
import axios from "axios";
import { TopBar } from "../../components/Topbar";
import { Container, Box, Grid, Card, CardContent, Typography, CardActionArea } from "@mui/material";
import { API_ROUTES } from "../../ApiRoutes.jsx";
import ImageCard from "../../components/ImageCard.jsx";

import { PrivateBar } from "../../components/Privatebar.jsx";

// Sample data for diets
const diets = [
  { id: 1, name: 'Keto Diet', description: 'Low-carb, high-fat diet.' },
  { id: 2, name: 'Vegan Diet', description: 'Plant-based diet without animal products.' },
  { id: 3, name: 'Paleo Diet', description: 'Diet based on the types of foods presumed to have been eaten by early humans.' },
  // Add more diet cards as needed
];

const currentDiet = { id: 4, name: 'Mediterranean Diet', description: 'Diet inspired by the eating habits of Mediterranean countries.' };

const DietCard = ({ diet }) => (
  <Card sx={{ height: '100%' }}>
    <CardActionArea>
      <CardContent>
        <Typography variant="h5" component="div">
          {diet.name}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {diet.description}
        </Typography>
      </CardContent>
    </CardActionArea>
  </Card>
);

function ListadoDietasPage() {
  // Axios test
  axios.get(API_ROUTES.DebugBaseUrl).then((response) => {
    console.log(response.data);
  });

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
        <Box sx={{ mb: 4 }}>
          <Typography variant="h6" component="div" color="#FFF">
            Current Diet
          </Typography>
          <DietCard diet={currentDiet} />
        </Box>
        <Box>
          <Typography variant="h6" component="div" color="#FFF" sx={{ mb: 2 }}>
            Your Designed Diets
          </Typography>
          <Grid container spacing={2}>
            {diets.map((diet) => (
              <Grid item xs={12} sm={6} md={4} key={diet.id}>
                <ImageCard></ImageCard>
              </Grid>
            ))}
          </Grid>
        </Box>
      </Container>
    </Box>
  );
}

export default ListadoDietasPage;

