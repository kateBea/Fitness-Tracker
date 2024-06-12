import React from "react"
import axios from "axios"

import { TopBar } from '../../../components/Topbar'
import {
    Container,
    Box,
    MenuItem,
} from '@mui/material'

import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import Divider from '@mui/material/Divider';
import ListItemText from '@mui/material/ListItemText';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import { PrivateBar } from "../../../components/Privatebar";

// Routes
import { API_ROUTES } from "../../../ApiRoutes.jsx"

function ChatAssistancePage() {
    // Axios test
    axios
        .post(API_ROUTES.ChatAssistance)
        .then((response) => {
            console.log(response)
        });

  return (
    <Box
        sx={{
            display:'flex',
            flexDirection:'column',
            background: '#293B50',
            minHeight: '100vh',
            justifyContent:'start',
            alignItems:'center'
        }}
    >
        <TopBar/>
        <PrivateBar/>
        <Container>
            <h1>Feature Work in progress</h1>
        </Container>
    </Box>
  );
}

export default ChatAssistancePage;
