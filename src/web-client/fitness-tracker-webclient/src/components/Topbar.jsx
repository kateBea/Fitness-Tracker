import * as React from 'react';
import { createTheme } from '@mui/material/styles';
import { 
    Box,
    AppBar,
    Container,
    Typography,
    MenuItem
} from '@mui/material';

import LogoFitness from '../img/logo-fitness-tracker.png';


export const TopBar = () => {
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
                    display:'flex',
                    alignContent:'space-between',
                    width:'100%',
                    maxWidth:'100%',
                    height:'70px'
                }}
            >
                <Box
                    sx={{
                        display: 'flex',
                        alignItems:'center',
                        width:'50%'
                    }}
                    
                >
                    <img src={LogoFitness} 
                        title="Logo Fitness-Tracker" 
                        alt="Logo Fitness-Tracker App" 
                        style={{
                            width:'55px',
                            height:'55px',
                            position: 'block',
                        }}
                    />
                    <Typography 
                        variant="h3"
                        component="h1"
                        href="/"
                        sx={{
                            color:'white',
                            textDecoration: 'none',
                            margin: '15px'
                        }}
                    >
                        Fitness-Tracker
                    </Typography>
                </Box>
                <Box 
                    sx={{
                        display:'flex',
                        justifyContent: 'flex-end',
                        alignContent:'right',
                        alignItems:'center',
                        width:'50%',
                    }}
                >
                    <MenuItem >Home</MenuItem>
                    <MenuItem >App</MenuItem>
                    <MenuItem >Sobre Nosotros</MenuItem>
                    <MenuItem >Nuestros Clientes</MenuItem>
                </Box>
            </Container>
        </AppBar>
    )
}