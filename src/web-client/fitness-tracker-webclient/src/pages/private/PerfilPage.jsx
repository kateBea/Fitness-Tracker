import React from 'react';
import { TopBar } from '../../components/Topbar'
import { PrivateBar } from '../../components/Privatebar'
import {
    Container,
    Box,
    Typography,
    List,
    ListItem,
    ListItemText
} from '@mui/material'

import { createTheme } from '@mui/material/styles';

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

function PerfilPage() {
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
                    Datos Personales
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
                        dense='true'
                        sx={{
                            display:'flex',
                            alignContent:'space-between',
                            width:'100%',
                        }}
                    >
                        <ListItemText>Nick</ListItemText>
                        <ListItemText
                            sx={{
                                display:'flex',
                                justifyContent: 'flex-end',
                                alignContent:'right',
                                alignItems:'center',
                            }}
                        >FastMarisa33</ListItemText>
                    </ListItem>
                    <ListItem
                        dense='true'
                        sx={{
                            display:'flex',
                            alignContent:'space-between',
                            width:'100%',
                        }}
                    >
                        <ListItemText>Nombre</ListItemText>
                        <ListItemText
                            sx={{
                                display:'flex',
                                justifyContent: 'flex-end',
                                alignContent:'right',
                                alignItems:'center',
                            }}
                        >Marisa Lopez Sánchez</ListItemText>
                    </ListItem>
                    <ListItem
                        dense='true'
                        sx={{
                            display:'flex',
                            alignContent:'space-between',
                            width:'100%',
                        }}
                    >
                        <ListItemText>Edad</ListItemText>
                        <ListItemText
                            sx={{
                                display:'flex',
                                justifyContent: 'flex-end',
                                alignContent:'right',
                                alignItems:'center',
                            }}
                        >25</ListItemText>
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
                    Resultados
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
                        dense='true'
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
                        dense='true'
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
                        dense='true'
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
                    Objetivos
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
                        dense='true'
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
                        dense='true'
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
                        dense='true'
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

export default PerfilPage