import React from 'react';
import { useState, useEffect } from 'react';
import { TopBar } from '../../components/Topbar'
import axios from 'axios';
import {
    Container,
    Box,
    MenuItem,
    Typography,
    List,
    ListItem,
    ListItemText,
    Grid,
    Avatar,
    Button
} from '@mui/material'

import PeepoDJ from '../../img/peepo_dj.png'
import Prueba from "../../img/profile2.jpg"

import { createTheme } from '@mui/material/styles';
import { PrivateBar } from '../../components/Privatebar';
import { API_ROUTES } from '../../ApiRoutes';



function DailyPage() {
    const [fetchDataSuccesfull, setFetchDataSuccesfull] = useState(false);
    const [ultimaDietas, setUltimaDietas] = useState({});
    const [ultimaRutinas, setUltimaRutinas] = useState({});
    const [comidasSugeridas, setComidasSugeridas] = useState();
    const [comidasConsumidas, setComidasConsumidas] = useState();

    const [dataLoadSucces, setDataLoadSucces] = useState(false);
    const [datosUsuario, setDatosUsuario] = useState({});
    const [edad, setEdad] = useState(0)

    const tokenLS = localStorage.getItem("token");
    axios.defaults.headers.common["Authorization"] = `Bearer ${tokenLS}`;

    const loadRutineAndDiet = async () => {

        try {
            const responseDietas = await axios
                .get(API_ROUTES.GetListDietasUsuario);

            const responseRutinas = await axios
                .get(API_ROUTES.GetListRutinasUsuario, { params: { fetchAll: true } });

            setUltimaDietas(responseDietas.data.data[responseDietas.data.data.length - 1])
            setUltimaRutinas(responseRutinas.data.data[responseDietas.data.data.length - 1])

            setFetchDataSuccesfull(true)
            console.log("Ultima Rutina", ultimaRutinas)
            console.log("Comidas sugeridas ", ultimaDietas.comidasSugeridas.length);
            setComidasSugeridas(ultimaDietas.comidasSugeridas.length > 0 ? ultimaDietas.comidasSugeridas.map(comida => comida.nombre).join(', ') : 'No hay comidas sugeridas');
            setComidasConsumidas(ultimaRutinas.comidasConsumidas.length > 0 ? ultimaRutinas.comidasConsumidas.map(comida => comida.nombre).join(', ') : 'No hay comidas consumidas');
            console.log(responseDietas);
            console.log(responseRutinas);
            console.log("Listado dietas", ultimaDietas)
            console.log("Listado rutinas", ultimaRutinas)
        } catch (error) {
            console.log(error);
        }
    };

    const loadPerfilData = async () => {
        try {
            const response = await axios.get(API_ROUTES.GetDatosUsuario);
            setDataLoadSucces(true);
            setDatosUsuario(response.data.data);
            setEdad(new Date(Date.now()).getFullYear() - parseInt(response.data.data.fechaDeNacimiento.split('-')[0]))
            console.log(dataLoadSucces)
            console.log(datosUsuario)
            console.log(edad)
            console.log("suenio", ultimaRutinas.tiempoDeSuenio)
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        loadRutineAndDiet();
        loadPerfilData();
    }, [fetchDataSuccesfull]);

    const theme = createTheme();

    return (
        <Box
            sx={{
                display: 'flex',
                flexDirection: 'column',
                background: '#293B50',
                minHeight: '100vh',
                justifyContent: 'start',
                alignItems: 'center'
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
                                <Avatar alt={datosUsuario.nombreUsuario} src={datosUsuario.imagen != null ? datosUsuario.imagen : Prueba} style={{ width: '70%', height: '100%', marginTop: '20px' }} />
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
                                    ElGuilleDEV
                                </Typography>
                            </Grid>
                            <Grid item lg={12} sx={{ display: 'flex', justifyContent: 'center', width: '100%' }}>
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
                                        [theme.breakpoints.down('900')]: {

                                            fontSize: '20pt',
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
                            component='h3'
                            sx={{
                                fontSize: '20pt',
                                fontWeight: 'bold',
                                marginLeft: '40px',

                            }}
                        >
                            Rutina
                        </Typography>
                        <Box sx={{
                            borderWidth: '1px',
                            borderBlockStyle: 'solid',
                        }}></Box>
                        <List
                            sx={{
                                marginLeft: '25px'
                            }}
                        >
                            <ListItem
                                dense={true}
                                sx={{
                                    display: 'flex',
                                    alignContent: 'space-between',
                                    width: '100%',
                                }}
                            >
                                <ListItemText>Tiempo de Sueño</ListItemText>
                                <ListItemText
                                    sx={{
                                        display: 'flex',
                                        justifyContent: 'flex-end',
                                        alignContent: 'right',
                                        alignItems: 'center',
                                    }}
                                >{ultimaRutinas != null ? ultimaRutinas.tiempoDeSuenio : "No hay tiempo de Sueño"}</ListItemText>
                            </ListItem>
                            <ListItem
                                dense={true}
                                sx={{
                                    display: 'flex',
                                    alignContent: 'space-between',
                                    width: '100%',
                                }}
                            >
                                <ListItemText>Pasos Realizados</ListItemText>
                                <ListItemText
                                    sx={{
                                        display: 'flex',
                                        justifyContent: 'flex-end',
                                        alignContent: 'right',
                                        alignItems: 'center',
                                    }}
                                >{ultimaRutinas != null ? ultimaRutinas.pasosRealizados : "No se han encontrado pasos"}</ListItemText>
                            </ListItem>
                            <ListItem
                                dense={true}
                                sx={{
                                    display: 'flex',
                                    alignContent: 'space-between',
                                    width: '100%',
                                }}
                            >
                                <ListItemText>Calorias Quemadas</ListItemText>
                                <ListItemText
                                    sx={{
                                        display: 'flex',
                                        justifyContent: 'flex-end',
                                        alignContent: 'right',
                                        alignItems: 'center',
                                    }}
                                >{ultimaRutinas != null ? ultimaRutinas.caloriasQuemadas : "No se han encontrado calorias"}</ListItemText>
                            </ListItem>
                        </List>
                        <Typography
                            component='h3'
                            sx={{
                                fontSize: '20pt',
                                fontWeight: 'bold',
                                marginLeft: '40px',

                            }}
                        >
                            Dieta
                        </Typography>
                        <Box sx={{
                            borderWidth: '1px',
                            borderBlockStyle: 'solid',
                        }}></Box>
                        <List
                            sx={{
                                marginLeft: '25px'
                            }}
                        >
                            <ListItem
                                dense={true}
                                sx={{
                                    display: 'flex',
                                    alignContent: 'space-between',
                                    width: '100%',
                                }}
                            >
                                <ListItemText>Calorias Requeridas</ListItemText>
                                <ListItemText
                                    sx={{
                                        display: 'flex',
                                        justifyContent: 'flex-end',
                                        alignContent: 'right',
                                        alignItems: 'center',
                                    }}
                                >{ultimaDietas != null ? ultimaDietas.caloriasTarget : "No se han encontrado calorias"}</ListItemText>
                            </ListItem>
                            <ListItem
                                dense={true}
                                sx={{
                                    display: 'flex',
                                    alignContent: 'space-between',
                                    width: '100%',
                                }}
                            >
                                <ListItemText>Agua Requerida</ListItemText>
                                <ListItemText
                                    sx={{
                                        display: 'flex',
                                        justifyContent: 'flex-end',
                                        alignContent: 'right',
                                        alignItems: 'center',
                                    }}
                                >{ultimaDietas != null ? ultimaDietas.consumoDeAgua : "No se ha encontrado consumo de agua"}</ListItemText>
                            </ListItem>
                            <ListItem
                                dense={true}
                                sx={{
                                    display: 'flex',
                                    alignContent: 'space-between',
                                    width: '100%',
                                }}
                            >
                                <ListItemText>Comidas Sugeridas</ListItemText>
                                <ListItemText
                                    sx={{
                                        display: 'flex',
                                        justifyContent: 'flex-end',
                                        alignContent: 'right',
                                        alignItems: 'center',
                                    }}
                                >{comidasSugeridas != null ? comidasSugeridas : "No se han encontrado comidas"}</ListItemText>
                            </ListItem>
                        </List>
                        <Typography
                            component='h3'
                            sx={{
                                fontSize: '20pt',
                                fontWeight: 'bold',
                                marginLeft: '40px',

                            }}
                        >
                            Físico
                        </Typography>
                        <Box sx={{
                            borderWidth: '1px',
                            borderBlockStyle: 'solid',
                        }}></Box>
                        <List
                            sx={{
                                marginLeft: '25px'
                            }}
                        >
                            <ListItem
                                dense={true}
                                sx={{
                                    display: 'flex',
                                    alignContent: 'space-between',
                                    width: '100%',
                                }}
                            >
                                <ListItemText>Frecuencia Cardiaca</ListItemText>
                                <ListItemText
                                    sx={{
                                        display: 'flex',
                                        justifyContent: 'flex-end',
                                        alignContent: 'right',
                                        alignItems: 'center',
                                    }}
                                >{ultimaRutinas != null ? ultimaRutinas.frecuenciaCardiaca : "No se ha encontrado frecuencia"}</ListItemText>
                            </ListItem>
                            <ListItem
                                dense={true}
                                sx={{
                                    display: 'flex',
                                    alignContent: 'space-between',
                                    width: '100%',
                                }}
                            >
                                <ListItemText>Oxigeno Sangre</ListItemText>
                                <ListItemText
                                    sx={{
                                        display: 'flex',
                                        justifyContent: 'flex-end',
                                        alignContent: 'right',
                                        alignItems: 'center',
                                    }}
                                >{ultimaRutinas != null ? ultimaRutinas.nivelOxigenoSangre : "No se han encontrado niveles"}</ListItemText>
                            </ListItem>
                            <ListItem
                                dense={true}
                                sx={{
                                    display: 'flex',
                                    alignContent: 'space-between',
                                    width: '100%',
                                }}
                            >
                                <ListItemText>Comidas Consumidas</ListItemText>
                                <ListItemText
                                    sx={{
                                        display: 'flex',
                                        justifyContent: 'flex-end',
                                        alignContent: 'right',
                                        alignItems: 'center',
                                    }}
                                >{comidasConsumidas != null ? comidasConsumidas : "No se han encotrado comidas"}</ListItemText>
                            </ListItem>
                        </List>
                    </Box>
                </Grid>
            </Grid>
        </Box>
    );
}

export default DailyPage