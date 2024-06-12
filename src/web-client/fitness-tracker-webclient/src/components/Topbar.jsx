import * as React from 'react';
import { useNavigate } from 'react-router-dom';
import { createTheme } from '@mui/material/styles';
import {
    Box,
    AppBar,
    Container,
    Typography,
    MenuItem,
    Grid
} from '@mui/material';

import LogoFitness from '../img/logo-fitness-tracker.png';


export const TopBar = () => {

    const navigate = useNavigate();

    const handleClick = (path) => {
        navigate(path);
    };

    const theme = createTheme();

    return (
        <AppBar
            position="static"
            sx={{
                background: '#436489',

            }}
        >
            <Container
                maxWidth='false'
                sx={{
                    display: 'flex',
                    alignContent: 'space-between',
                    width: '100%',
                    maxWidth: '100%',
                    height: '70px'
                }}
            >
                <Box
                    sx={{
                        display: 'flex',
                        alignItems: 'center',
                        width: '50%'
                    }}

                >
                    <img src={LogoFitness}
                        title="Logo Fitness-Tracker"
                        alt="Logo Fitness-Tracker App"
                        style={{
                            width: '55px',
                            height: '55px',
                            position: 'block',
                        }}
                    />
                    <Typography
                        variant="h3"
                        component="h1"
                        sx={{
                            color: 'white',
                            textDecoration: 'none',
                            margin: '15px',
                            [theme.breakpoints.down('900')]: {
                                fontSize: '20pt'
                            },
                            [theme.breakpoints.down('750')]: {
                                fontSize: '15pt'
                            },
                            [theme.breakpoints.down('600')]: {
                                fontSize: '0pt'
                            }

                        }}
                    >
                        Fitness-Tracker
                    </Typography>
                </Box>
                <Grid container columnSpacing={1}
                    sx={{
                        display: 'flex',
                        
                        [theme.breakpoints.up('600')]: {
                            justifyContent: 'flex-end',
                            alignContent: 'right',
                            alignItems: 'center',
                        },
                        width: '50%',
                    }}
                >
                    <Grid item xl={1} lg={1.2} md={1.5} sm={1.8} xs={12} sx={{height:'100%'}}>
                        <MenuItem onClick={() => handleClick('/')} sx={{
                            [theme.breakpoints.up('600')]: {
                                height:'100%',
                                alignContent:'center',
                            },
                            [theme.breakpoints.down('600')]: {
                                height:'100%',
                                justifyContent:'right',
                                fontSize:'20pt'
                            },
                        }}>Home</MenuItem>
                    </Grid>
                    <Grid item xl={1} lg={1} md={1.5} sm={1.2} xs={0} >
                        <MenuItem onClick={() => handleClick('/')} sx={{
                            [theme.breakpoints.down('600')]: {
                                fontSize: '0pt'
                            }
                        }}>App</MenuItem>
                    </Grid>
                    <Grid item xl={2} lg={2.5}md={3.5} sm={4} xs={0}>
                        <MenuItem onClick={() => handleClick('/')} sx={{
                            [theme.breakpoints.down('600')]: {
                                fontSize: '0pt'
                            }
                        }}>Sobre Nosotros</MenuItem>
                    </Grid>
                    <Grid item xl={2} lg={2.5}md={4} sm={5} xs={0}>
                        <MenuItem onClick={() => handleClick('/')} sx={{
                            [theme.breakpoints.down('600')]: {
                                fontSize: '0pt'
                            }
                        }}>Nuestros Clientes</MenuItem>
                    </Grid>
                </Grid>
            </Container>
        </AppBar>
    )
}