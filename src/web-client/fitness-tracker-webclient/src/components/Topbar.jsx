import * as React from 'react';

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
                sx={{
                    display:'flex',
                    alignContent:'space-between',
                    width:'100%'
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
                            width:'60px',
                            height:'60px',
                            position: 'block',
                        }}
                    />
                    <Typography 
                        variant="h2"
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
                        alignContent:'right',
                        alignItems:'center',
                        width:'50%'
                        
                    }}
                    >
                    <MenuItem sx={{textAlign:'center'}}>Home</MenuItem>
                    <MenuItem sx={{textAlign:'center'}}>App</MenuItem>
                    <MenuItem sx={{textAlign:'center'}}>Nuestros Clientes</MenuItem>
                    <MenuItem sx={{textAlign:'center'}}>Sobre Nosotros</MenuItem>
                
                </Box>
            </Container>
        </AppBar>
    )
}