import React, { useEffect, useState } from "react";
import { TopBar } from "../../components/Topbar";
import { PrivateBar } from "../../components/Privatebar";
import {
  Container,
  Box,
  Typography,
  List,
  ListItem,
  ListItemText,
  Grid,
  Avatar,
  Button,
  createTheme
} from "@mui/material";

import PeepoDJ from "../../img/peepo_dj.png"
import Prueba from "../../img/profile2.jpg"

import { API_ROUTES } from "../../ApiRoutes";
import axios from "axios";


function PerfilPage() {
  // State setup
  const [dataLoadSucces, setDataLoadSucces] = useState(false);
  const [datosUsuario, setDatosUsuario] = useState({});
  const [edad, setEdad] = useState(0)

  // Axios setup
  const tokenLS = localStorage.getItem("token");
  axios.defaults.headers.common["Authorization"] = `Bearer ${tokenLS}`;

  const loadPerfilData = async () => {
    try {
      const response = await axios.get(API_ROUTES.GetDatosUsuario);
      setDataLoadSucces(true);
      setDatosUsuario(response.data.data);
      setEdad(new Date(Date.now()).getFullYear() - parseInt(response.data.data.fechaDeNacimiento.split('-')[0]))
      console.log(response.data.data)
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    loadPerfilData();
  }, []);

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
      <Grid container columnSpacing={7} rowSpacing={4} sx={{
        padding: '50px',
        [theme.breakpoints.down('600')]: {
          padding: '20px',
        }
      }}>
        <Grid item lg={4} md={4} sm={4} xs={12}>
          <Box
            sx={{
              background: "#FFF",
              width: "100%",
              height: "100%",
              borderRadius: "40px",
            }}
          ><Grid container>
              <Grid item lg={12} md={12} sx={{ display: 'flex', justifyContent: 'center' }}>
                <Avatar alt={datosUsuario.nombreUsuario} src={datosUsuario.image != null ? datosUsuario.image : Prueba} style={{ width: '70%', height: '100%', marginTop: '20px' }} />
              </Grid>
              <Grid item lg={12} md={12} sx={{ display: 'flex', justifyContent: 'center', width: '100%', }}>
                <Typography
                  variant='h2'
                  component='h2'
                  sx={{
                    textAlign: 'center',
                    marginTop: '50px',
                    [theme.breakpoints.down('1200')]: {
                      fontSize: '30pt'
                    },
                    [theme.breakpoints.down('900')]: {

                      fontSize: '20pt',
                    }
                  }}
                >
                  {datosUsuario.nombreUsuario}
                </Typography>
              </Grid>
              <Grid item lg={12} sx={{ display: 'flex', justifyContent: 'center', width: '100%' }}>
                <Button
                  href="/editar"
                  sx={{
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
                    margin:'30px'
                  }}>Editar</Button>
              </Grid>
            </Grid>
          </Box>
        </Grid>
        <Grid item lg={8} md={8} sm={8} xs={12}>
          <Box
            sx={{
              background: "#FFF",
              width: "100%",
              height: "100%",
              borderRadius: "40px",
              [theme.breakpoints.up('800')]: {
                padding: "40px",
              },
              [theme.breakpoints.up('600')]: {
                padding: "20px",
              },
              [theme.breakpoints.down('600')]: {
                padding: "10px",
              },

              // paddingTop:'10px'
            }}
          >
            <Typography
              component="h3"
              sx={{
                fontSize: "20pt",
                fontWeight: "bold",
                marginLeft: "40px",
              }}
            >
              Datos Personales
            </Typography>
            <Box
              sx={{
                borderWidth: "1px",
                borderBlockStyle: "solid",
              }}
            ></Box>
            <List
              sx={{
                marginLeft: "25px",
              }}
            >
              <ListItem
                dense={true}
                sx={{
                  display: "flex",
                  alignContent: "space-between",
                  width: "100%",
                }}
              >
                <ListItemText>Nombre</ListItemText>
                <ListItemText
                  sx={{
                    display: "flex",
                    justifyContent: "flex-end",
                    alignContent: "right",
                    alignItems: "center",
                  }}
                >
                  {datosUsuario.nombre}
                </ListItemText>
              </ListItem>
              <ListItem
                dense={true}
                sx={{
                  display: "flex",
                  alignContent: "space-between",
                  width: "100%",
                }}
              >
                <ListItemText>Apellidos</ListItemText>
                <ListItemText
                  sx={{
                    display: "flex",
                    justifyContent: "flex-end",
                    alignContent: "right",
                    alignItems: "center",
                  }}
                >
                  {datosUsuario.primerApellido} {datosUsuario.segundoApellido}
                </ListItemText>
              </ListItem>
              <ListItem
                dense={true}
                sx={{
                  display: "flex",
                  alignContent: "space-between",
                  width: "100%",
                }}
              >
                <ListItemText>Sexo</ListItemText>
                <ListItemText
                  sx={{
                    display: "flex",
                    justifyContent: "flex-end",
                    alignContent: "right",
                    alignItems: "center",
                  }}
                >
                  {datosUsuario.sexo}
                </ListItemText>
              </ListItem>
              <ListItem
                dense={true}
                sx={{
                  display: "flex",
                  alignContent: "space-between",
                  width: "100%",
                }}
              >
                <ListItemText>Edad</ListItemText>
                <ListItemText
                  sx={{
                    display: "flex",
                    justifyContent: "flex-end",
                    alignContent: "right",
                    alignItems: "center",
                  }}
                >
                  {edad}
                </ListItemText>
              </ListItem>
              <ListItem
                dense={true}
                sx={{
                  display: "flex",
                  alignContent: "space-between",
                  width: "100%",
                }}
              >
                <ListItemText>Fecha Nacimiento</ListItemText>
                <ListItemText
                  sx={{
                    display: "flex",
                    justifyContent: "flex-end",
                    alignContent: "right",
                    alignItems: "center",
                  }}
                >
                  {datosUsuario.fechaDeNacimiento}
                </ListItemText>
              </ListItem>
            </List>
            <Typography
              component="h3"
              sx={{
                fontSize: "20pt",
                fontWeight: "bold",
                marginLeft: "40px",
              }}
            >
              Actual
            </Typography>
            <Box
              sx={{
                borderWidth: "1px",
                borderBlockStyle: "solid",
              }}
            ></Box>
            <List
              sx={{
                marginLeft: "25px",
              }}
            >
              <ListItem
                dense={true}
                sx={{
                  display: "flex",
                  alignContent: "space-between",
                  width: "100%",
                }}
              >
                <ListItemText>Peso</ListItemText>
                <ListItemText
                  sx={{
                    display: "flex",
                    justifyContent: "flex-end",
                    alignContent: "right",
                    alignItems: "center",
                  }}
                >
                  {datosUsuario.peso != null? datosUsuario.peso: "No se ha encontrado peso"}
                </ListItemText>
              </ListItem>
              <ListItem
                dense={true}
                sx={{
                  display: "flex",
                  alignContent: "space-between",
                  width: "100%",
                }}
              >
                <ListItemText>Altura</ListItemText>
                <ListItemText
                  sx={{
                    display: "flex",
                    justifyContent: "flex-end",
                    alignContent: "right",
                    alignItems: "center",
                  }}
                >
                  {datosUsuario.altura != null? datosUsuario.altura: "No se ha encontrado altura"}
                </ListItemText>
              </ListItem>
            </List>
            <Typography
              component="h3"
              sx={{
                fontSize: "20pt",
                fontWeight: "bold",
                marginLeft: "40px",
              }}
            >
              Objetivos
            </Typography>
            <Box
              sx={{
                borderWidth: "1px",
                borderBlockStyle: "solid",
              }}
            ></Box>
            <List
              sx={{
                marginLeft: "25px",
              }}
            >
              <ListItem
                dense={true}
                sx={{
                  display: "flex",
                  alignContent: "space-between",
                  width: "100%",
                }}
              >
                <ListItemText>Peso Objetivo</ListItemText>
                <ListItemText
                  sx={{
                    display: "flex",
                    justifyContent: "flex-end",
                    alignContent: "right",
                    alignItems: "center",
                  }}
                >
                  {datosUsuario.objetivoPeso != null? datosUsuario.objetivoPeso : "No se ha encontrado peso objetivo"}
                </ListItemText>
              </ListItem>
            </List>
          </Box>
        </Grid>
      </Grid>
    </Box>
  );
}

export default PerfilPage;
