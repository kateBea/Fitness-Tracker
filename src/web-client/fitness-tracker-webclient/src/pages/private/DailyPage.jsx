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
    ListItemText
} from '@mui/material'

import { createTheme } from '@mui/material/styles';
import { PrivateBar } from '../../components/Privatebar';
import { API_ROUTES } from '../../ApiRoutes';

const theme = createTheme({
    components: {
        // Name of the component
        Container: {
            styleOverrides: {
            // Name of the slot
                root: {
                    // Some CSS
                    maxWidth:'100%'
                },
            },
        },
        MenuItem: {
            current: {
            },
            deactivated: {
                
            }
        }
    },
});

function DailyPage() {
    const [fetchDataSuccesfull, setFetchDataSuccesfull] = useState(false);
    const [listadoDietas, setListadoDietas] = useState({});
    const [listadoRutinas, setListadoRutinas] = useState({});

    const loadRutineAndDiet = async () => {
        const tokenLS = localStorage.getItem("token");
        axios.defaults.headers.common["Authorization"] = `Bearer ${tokenLS}`;

        try {
          const responseDietas = await axios
            .get(API_ROUTES.GetListDietasUsuario);

            const responseRutinas = await axios
            .get(API_ROUTES.GetListRutinasUsuario, { params: { fetchAll: true }});

            setListadoDietas(responseDietas.data)
            setListadoRutinas(responseRutinas.data)

            setFetchDataSuccesfull(true)
    
          console.log(responseDietas);
          console.log(responseRutinas);
        } catch (error) {
          console.log(error);
        }
      };
    
      
      useEffect(() => {
          loadRutineAndDiet();
      }, [fetchDataSuccesfull]);

    return (
    <Box
        sx={{
            display:'flex',
            flexDirection:'column',
            background: '#293B50',
            minHeight: '1000px',
            justifyContent:'start',
            alignItems:'center'
        }}
    >
        <TopBar/>
        <PrivateBar/>
        <Container
            maxWidth='none'
            sx={{
                display:'inline-flex',
                width:'100%',
                height:'100%'
            }}
        >
            <Box
                sx={{
                    background:'#FFF',
                    width:'30%',
                    height:'580px',
                    marginTop:'40px',
                    marginLeft:'40px',
                    borderRadius:'40px'
                }}
            >
            </Box>
            <Box
                sx={{
                    background:'#FFF',
                    width:'63%',
                    height:'70%px',
                    marginTop:'40px',
                    marginLeft:'40px',
                    borderRadius:'40px',
                    padding:'40px',
                    // paddingTop:'10px'
                }}
            >
                <Typography
                    component='h3'
                    sx={{
                        fontSize:'20pt',
                        fontWeight:'bold',
                        marginLeft:'40px',
                        
                    }}
                >
                    Rutina
                </Typography>
                <Box sx={{
                    borderWidth:'1px',
                    borderBlockStyle:'solid',
                }}></Box>
                <List
                    sx={{
                        marginLeft:'25px'
                    }}
                >
                    <ListItem
                        dense={true}
                        sx={{
                            display:'flex',
                            alignContent:'space-between',
                            width:'100%',
                        }}
                    >
                        <ListItemText>Dato1</ListItemText>
                        <ListItemText
                            sx={{
                                display:'flex',
                                justifyContent: 'flex-end',
                                alignContent:'right',
                                alignItems:'center',
                            }}
                        >Valor_1</ListItemText>
                    </ListItem>
                    <ListItem
                        dense={true}
                        sx={{
                            display:'flex',
                            alignContent:'space-between',
                            width:'100%',
                        }}
                    >
                        <ListItemText>Dato2</ListItemText>
                        <ListItemText
                            sx={{
                                display:'flex',
                                justifyContent: 'flex-end',
                                alignContent:'right',
                                alignItems:'center',
                            }}
                        >Valor_2</ListItemText>
                    </ListItem>
                    <ListItem
                        dense={true}
                        sx={{
                            display:'flex',
                            alignContent:'space-between',
                            width:'100%',
                        }}
                    >
                        <ListItemText>Dato3_</ListItemText>
                        <ListItemText
                            sx={{
                                display:'flex',
                                justifyContent: 'flex-end',
                                alignContent:'right',
                                alignItems:'center',
                            }}
                        >Valor_3</ListItemText>
                    </ListItem>
                </List>
                <Typography
                    component='h3'
                    sx={{
                        fontSize:'20pt',
                        fontWeight:'bold',
                        marginLeft:'40px',
                        
                    }}
                >
                    Dieta
                </Typography>
                <Box sx={{
                    borderWidth:'1px',
                    borderBlockStyle:'solid',
                }}></Box>
                <List
                    sx={{
                        marginLeft:'25px'
                    }}
                >
                    <ListItem
                        dense={true}
                        sx={{
                            display:'flex',
                            alignContent:'space-between',
                            width:'100%',
                        }}
                    >
                        <ListItemText>Dato1</ListItemText>
                        <ListItemText
                            sx={{
                                display:'flex',
                                justifyContent: 'flex-end',
                                alignContent:'right',
                                alignItems:'center',
                            }}
                        >Valor_1</ListItemText>
                    </ListItem>
                    <ListItem
                        dense={true}
                        sx={{
                            display:'flex',
                            alignContent:'space-between',
                            width:'100%',
                        }}
                    >
                        <ListItemText>Dato2</ListItemText>
                        <ListItemText
                            sx={{
                                display:'flex',
                                justifyContent: 'flex-end',
                                alignContent:'right',
                                alignItems:'center',
                            }}
                        >Valor_2</ListItemText>
                    </ListItem>
                    <ListItem
                        dense={true}
                        sx={{
                            display:'flex',
                            alignContent:'space-between',
                            width:'100%',
                        }}
                    >
                        <ListItemText>Dato3</ListItemText>
                        <ListItemText
                            sx={{
                                display:'flex',
                                justifyContent: 'flex-end',
                                alignContent:'right',
                                alignItems:'center',
                            }}
                        >Valor_3</ListItemText>
                    </ListItem>
                </List>
                <Typography
                    component='h3'
                    sx={{
                        fontSize:'20pt',
                        fontWeight:'bold',
                        marginLeft:'40px',
                        
                    }}
                >
                    Físico
                </Typography>
                <Box sx={{
                    borderWidth:'1px',
                    borderBlockStyle:'solid',
                }}></Box>
                <List
                    sx={{
                        marginLeft:'25px'
                    }}
                >
                    <ListItem
                        dense={true}
                        sx={{
                            display:'flex',
                            alignContent:'space-between',
                            width:'100%',
                        }}
                    >
                        <ListItemText>Dato1</ListItemText>
                        <ListItemText
                            sx={{
                                display:'flex',
                                justifyContent: 'flex-end',
                                alignContent:'right',
                                alignItems:'center',
                            }}
                        >Valor_1</ListItemText>
                    </ListItem>
                    <ListItem
                        dense={true}
                        sx={{
                            display:'flex',
                            alignContent:'space-between',
                            width:'100%',
                        }}
                    >
                        <ListItemText>Dato2</ListItemText>
                        <ListItemText
                            sx={{
                                display:'flex',
                                justifyContent: 'flex-end',
                                alignContent:'right',
                                alignItems:'center',
                            }}
                        >valor_2</ListItemText>
                    </ListItem>
                    <ListItem
                        dense={true}
                        sx={{
                            display:'flex',
                            alignContent:'space-between',
                            width:'100%',
                        }}
                    >
                        <ListItemText>Dato3</ListItemText>
                        <ListItemText
                            sx={{
                                display:'flex',
                                justifyContent: 'flex-end',
                                alignContent:'right',
                                alignItems:'center',
                            }}
                        >Valor_3</ListItemText>
                    </ListItem>
                </List>
            </Box>
        </Container>
    </Box>
    );
}

export default DailyPage