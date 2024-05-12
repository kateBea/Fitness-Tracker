import React from "react";

import { TopBar } from '../../components/Topbar'
import {
    Container,
    Box,
    MenuItem,
} from '@mui/material'

function ChatAssistancePage() {
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
        <Container
            maxWidth='false'
            sx={{
                display:'flex',
                justifyContent: 'center',
                alignContent:'center',
                alignItems:'center',
                height:'40px',
                background:'#869CB5'
            }}
        >
            <Box 
                sx={{
                    display:'flex',
                    justifyContent: 'flex-end',
                    alignContent:'right',
                    alignItems:'center',
                    color:'#FFF'
                }}
            >
                <MenuItem>Inicio</MenuItem>
                <MenuItem>Perfil</MenuItem>
                <MenuItem>Hoy</MenuItem>
                <MenuItem>Calorias diaras</MenuItem>
            </Box>
        </Container>
        
    </Box>
  );
}

export default ChatAssistancePage;
