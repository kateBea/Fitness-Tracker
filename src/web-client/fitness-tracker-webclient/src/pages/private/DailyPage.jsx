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
    Grid
} from '@mui/material'

import PeepoDJ from '../../img/peepo_dj.png'

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
            console.log("Ultima Rutina" ,ultimaRutinas)
            console.log("Comidas sugeridas " ,ultimaDietas.comidasSugeridas.length); 
            setComidasSugeridas(ultimaDietas.comidasSugeridas.length > 0 ? ultimaDietas.comidasSugeridas.map(comida => comida.nombre).join(', ') : 'No hay comidas sugeridas');
            setComidasConsumidas(ultimaRutinas.comidasConsumidas.length > 0 ? ultimaRutinas.comidasConsumidas.map(comida => comida.nombre).join(', ') : 'No hay comidas consumidas');
            console.log(responseDietas);
            console.log(responseRutinas);
            console.log("Listado dietas", ultimaDietas)
            console.log("Listado rutinas" ,ultimaRutinas)
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
        } catch (error) {
            console.log(error);
        }
    };


    useEffect(() => {
        loadRutineAndDiet();
        loadPerfilData();
    }, [fetchDataSuccesfull]);

    return (
        <Box
            sx={{
                display: 'flex',
                flexDirection: 'column',
                background: '#293B50',
                minHeight: '1000px',
                justifyContent: 'start',
                alignItems: 'center'
            }}
        >
            <TopBar />
            <PrivateBar />
            <Container
                maxWidth='none'
                sx={{
                    display: 'inline-flex',
                    width: '100%',
                    height: '100%'
                }}
            >
                <Box
                    sx={{
                        background: '#FFF',
                        width: '30%',
                        height: '580px',
                        marginTop: '40px',
                        marginLeft: '40px',
                        borderRadius: '40px'
                    }}
                >
                    <Grid container rowSpacing={5}>
                        <Grid item lg={12} sx={{ display: 'flex', justifyContent: 'center' }}>
                            <img src={PeepoDJ} style={{ width: '70%', borderRadius: '100%' }}></img>
                        </Grid>
                        <Grid item lg={12} sx={{ display: 'flex', justifyContent: 'center' }}>
                            <Typography
                                variant='h2'
                                component='h2'
                                sx={{ textAlign: 'center' }}
                            >
                                {datosUsuario.nombreUsuario}
                            </Typography>
                        </Grid>
                    </Grid>
                </Box>
                <Box
                    sx={{
                        background: '#FFF',
                        width: '63%',
                        height: '70%px',
                        marginTop: '40px',
                        marginLeft: '40px',
                        borderRadius: '40px',
                        padding: '40px',
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
                            >{ultimaRutinas.tiempoDeSuenio}</ListItemText>
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
                            >{ultimaRutinas.pasosRealizados}</ListItemText>
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
                            >{ultimaRutinas.caloriasQuemadas}</ListItemText>
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
                            >{ultimaDietas.caloriasTarget}</ListItemText>
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
                            >{ultimaDietas.consumoDeAgua}</ListItemText>
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
                            >{comidasSugeridas}</ListItemText>
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
                            >{ultimaRutinas.frecuenciaCardiaca}</ListItemText>
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
                            >{ultimaRutinas.nivelOxigenoSangre}</ListItemText>
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
                            >{comidasConsumidas}</ListItemText>
                        </ListItem>
                    </List>
                </Box>
            </Container>
        </Box>
    );
}

export default DailyPage