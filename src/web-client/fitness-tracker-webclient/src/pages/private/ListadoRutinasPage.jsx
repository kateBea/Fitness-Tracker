import React from "react";

import { TopBar } from "../../components/Topbar";
import { Container, Box,  Grid, Typography, } from "@mui/material";

import RutinaCard from "../../components/RutinaCard.jsx";  // Adjust the import path as necessary
import { useState } from "react";
import { useEffect } from "react";

import { PrivateBar } from "../../components/Privatebar.jsx";


const ListadoRutinasPage = () => {
  const [rutinas, setRutinas] = useState([]);
  
  /**
   * 
   * useEffect(() => {
    axios.get("YOUR_API_ENDPOINT")  // Replace with your API endpoint
      .then((response) => {
        setRutinas(response.data);
      })
      .catch((error) => {
        console.error("Error fetching the rutinas:", error);
      });
  }, []);
   * 
   */
  
  useEffect(() => {
    const sampleRutinas = [
      {
        "_id": "rutina1",
        "tiempo_suenio": 420.0,
        "calorias_quemadas": 550.0,
        "pasos_realizados": 11000,
        "frecuencia_cardiaca": 72.0,
        "nivel_oxigeno_sangre": 95.0,
        "presion_arterial": 120.0,
        "fecha_seguimiento": "2024-05-16T16:27:34.112+0000",
        "comidas_consumidas": [
          {
            "_id": "alimento1",
            "comida": {
              "_id": "comida1",
              "nombre": "Manzana",
              "descripcion": "Una manzana fresca",
              "unidades": 1,
              "calorias": 52.0,
              "carbohidratos": 14.0,
              "vitaminas": ["A", "C"],
              "grasas": 0.2
            },
            "tipo": "MERIENDA",
            "hora_consumo": "2024-05-12T10:00:00.000+0000",
            "orden": "PRIMER_PLATO"
          }
        ]
      },
      {
        "_id": "rutina2",
        "tiempo_suenio": 480.0,
        "calorias_quemadas": 620.0,
        "pasos_realizados": 13000,
        "frecuencia_cardiaca": 70.0,
        "nivel_oxigeno_sangre": 97.0,
        "presion_arterial": 118.0,
        "fecha_seguimiento": "2024-05-15T16:27:34.112+0000",
        "comidas_consumidas": [
          {
            "_id": "alimento2",
            "comida": {
              "_id": "comida2",
              "nombre": "Plátano",
              "descripcion": "Un plátano maduro",
              "unidades": 1,
              "calorias": 105.0,
              "carbohidratos": 27.0,
              "vitaminas": ["B6", "C"],
              "grasas": 0.4
            },
            "tipo": "DESAYUNO",
            "hora_consumo": "2024-05-13T08:00:00.000+0000",
            "orden": "UNICO_PLATO"
          }
        ]
      },
      {
        "_id": "rutina3",
        "tiempo_suenio": 390.0,
        "calorias_quemadas": 580.0,
        "pasos_realizados": 12500,
        "frecuencia_cardiaca": 73.0,
        "nivel_oxigeno_sangre": 96.0,
        "presion_arterial": 122.0,
        "fecha_seguimiento": "2024-05-14T16:27:34.112+0000",
        "comidas_consumidas": [
          {
            "_id": "alimento3",
            "comida": {
              "_id": "comida3",
              "nombre": "Pera",
              "descripcion": "Una pera jugosa",
              "unidades": 1,
              "calorias": 85.0,
              "carbohidratos": 22.0,
              "vitaminas": ["C", "K"],
              "grasas": 0.3
            },
            "tipo": "ALMUERZO",
            "hora_consumo": "2024-05-13T13:00:00.000+0000",
            "orden": "PRIMER_PLATO"
          }
        ]
      },
      {
        "_id": "rutina4",
        "tiempo_suenio": 410.0,
        "calorias_quemadas": 570.0,
        "pasos_realizados": 11800,
        "frecuencia_cardiaca": 75.0,
        "nivel_oxigeno_sangre": 95.0,
        "presion_arterial": 119.0,
        "fecha_seguimiento": "2024-05-13T16:27:34.112+0000",
        "comidas_consumidas": [
          {
            "_id": "alimento4",
            "comida": {
              "_id": "comida4",
              "nombre": "Uvas",
              "descripcion": "Un racimo de uvas",
              "unidades": 1,
              "calorias": 60.0,
              "carbohidratos": 15.0,
              "vitaminas": ["K"],
              "grasas": 0.2
            },
            "tipo": "MERIENDA",
            "hora_consumo": "2024-05-12T16:00:00.000+0000",
            "orden": "UNICO_PLATO"
          }
        ]
      },
      {
        "_id": "rutina5",
        "tiempo_suenio": 440.0,
        "calorias_quemadas": 590.0,
        "pasos_realizados": 12250,
        "frecuencia_cardiaca": 71.0,
        "nivel_oxigeno_sangre": 98.0,
        "presion_arterial": 121.0,
        "fecha_seguimiento": "2024-05-12T16:27:34.112+0000",
        "comidas_consumidas": [
          {
            "_id": "alimento5",
            "comida": {
              "_id": "comida5",
              "nombre": "Sandía",
              "descripcion": "Una rodaja de sandía",
              "unidades": 1,
              "calorias": 30.0,
              "carbohidratos": 8.0,
              "vitaminas": ["A", "C"],
              "grasas": 0.1
            },
            "tipo": "CENA",
            "hora_consumo": "2024-05-11T20:00:00.000+0000",
            "orden": "PRIMER_PLATO"
          }
        ]
      }
    ];
    
  setRutinas(sampleRutinas);
  }, []);
  const [sidebarOpen, setSidebarOpen] = useState(false);

  const toggleSidebar = () => {
    setSidebarOpen(!sidebarOpen);
  };

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
