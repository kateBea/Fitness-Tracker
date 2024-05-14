import * as React from 'react';
import { 
    Box,
    MenuItem
} from '@mui/material';

export const PrivateBar = () => {
    return (
        <Box 
            sx={{
                display:'flex',
                justifyContent: 'flex-end',
                alignContent:'right',
                alignItems:'center',
                color:'#FFF'
            }}
        >
            <MenuItem >Inicio</MenuItem>
            <MenuItem component='a' href='/perfil'>Perfil</MenuItem>
            <MenuItem component='a' href='/today'>Hoy</MenuItem>
            <MenuItem component='a' href='/DailyCalorie'>Calorias diaras</MenuItem>
            <MenuItem component='a' href='/Dieta'>Dieta</MenuItem>
        </Box>
    )
}