import { React, useState} from 'react';
import { 
    Box,
    MenuItem,
    IconButton,
    Container
} from '@mui/material';

import MenuIcon from '@mui/icons-material/Menu';
import Sidebar from "./Sidebar.jsx";

export const PrivateBar = () => {

    const [sidebarOpen, setSidebarOpen] = useState(false);

    const toggleSidebar = () => {
        setSidebarOpen(!sidebarOpen);
    };

    return (
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
                <MenuItem disableTouchRipple style={{ position: "sticky", backgroundColor: 'transparent', left: 200 }} >
                {/* Toggle sidebar button */}
                    <IconButton
                    sx={{ color: "#FFF", backgroundColor: "transparent" }}
                    onClick={toggleSidebar}
                    >
                        <MenuIcon/>
                    </IconButton>
                    {/* Sidebar component */}
                    <Sidebar isOpen={sidebarOpen} onClose={toggleSidebar} />
                </MenuItem>
                <MenuItem >Inicio</MenuItem>
                <MenuItem component='a' href='/perfil'>Perfil</MenuItem>
                <MenuItem component='a' href='/today'>Hoy</MenuItem>
                <MenuItem component='a' href='/DailyCalorie'>Calorias diaras</MenuItem>
                <MenuItem component='a' href='/ListadoDietas'>Dieta</MenuItem>
            </Box>
        </Container>
    )
}