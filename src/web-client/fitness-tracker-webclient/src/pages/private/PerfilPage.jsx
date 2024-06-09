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
        minHeight: "1000px",
        justifyContent: "start",
        alignItems: "center",
      }}
    >
      <TopBar />
      <PrivateBar />
      <Grid container columnSpacing={7} rowSpacing={4} sx={{ padding: '50px' }}>
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
                <Avatar alt={datosUsuario.nombreUsuario} src={datosUsuario.imagen != null ? datosUsuario.imagen : Prueba} style={{ width: '70%', height: '100%', marginTop: '20px' }} />
              </Grid>
              <Grid item lg={12} md={12} sx={{ display: 'flex', justifyContent: 'center', width:'100%',}}>
                <Typography
                  variant='h2'
                  component='h2'
                  sx={{
                    textAlign: 'center',
                    marginTop: '50px',
                    [theme.breakpoints.down('1200')]: {
                      fontSize: '30pt'
                    },
                    [theme.breakpoints.down('900')]:{
                      
                      fontSize:'20pt',
                    }
                  }}
                >
                  ElGuilleDEV
                </Typography>
              </Grid>
              <Grid item lg={12} sx={{ display: 'flex', justifyContent: 'center',width:'100%' }}>
                <Button
                  variant="contained"
                  sx={{
                    background: '#D92668',
                    margin: '20px',
                    borderTopLeftRadius: '30px',
                    borderBottomLeftRadius: '0px',
                    borderTopRightRadius: '0px',
                    borderBottomRightRadius: '30px',
                    textTransform: 'none',
                    [theme.breakpoints.up('1200')]: {
                      fontSize: '30pt'
                    },
                    [theme.breakpoints.down('1200')]: {
                      fontSize: '30pt'
                    },
                    [theme.breakpoints.down('900')]:{
                      
                      fontSize:'20pt',
                    }
                  }}
                  type='submit'
                >
                  Editar
                </Button>
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
              padding: "40px",
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
                <ListItemText>Correo Electronico</ListItemText>
                <ListItemText
                  sx={{
                    display: "flex",
                    justifyContent: "flex-end",
                    alignContent: "right",
                    alignItems: "center",
                  }}
                >
                  {datosUsuario.nombreUsuario}
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
            </List>
            <Typography
              component="h3"
              sx={{
                fontSize: "20pt",
                fontWeight: "bold",
                marginLeft: "40px",
              }}
            >
              Resultados
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
                <ListItemText>Dato1</ListItemText>
                <ListItemText
                  sx={{
                    display: "flex",
                    justifyContent: "flex-end",
                    alignContent: "right",
                    alignItems: "center",
                  }}
                >
                  Valor_1
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
                <ListItemText>Dato2</ListItemText>
                <ListItemText
                  sx={{
                    display: "flex",
                    justifyContent: "flex-end",
                    alignContent: "right",
                    alignItems: "center",
                  }}
                >
                  Valor_2
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
                <ListItemText>Dato3</ListItemText>
                <ListItemText
                  sx={{
                    display: "flex",
                    justifyContent: "flex-end",
                    alignContent: "right",
                    alignItems: "center",
                  }}
                >
                  Valor_3
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
                <ListItemText>Dato1</ListItemText>
                <ListItemText
                  sx={{
                    display: "flex",
                    justifyContent: "flex-end",
                    alignContent: "right",
                    alignItems: "center",
                  }}
                >
                  Valor_1
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
                <ListItemText>Dato2</ListItemText>
                <ListItemText
                  sx={{
                    display: "flex",
                    justifyContent: "flex-end",
                    alignContent: "right",
                    alignItems: "center",
                  }}
                >
                  valor_2
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
                <ListItemText>Dato3</ListItemText>
                <ListItemText
                  sx={{
                    display: "flex",
                    justifyContent: "flex-end",
                    alignContent: "right",
                    alignItems: "center",
                  }}
                >
                  Valor_3
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
