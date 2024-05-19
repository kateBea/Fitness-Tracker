import React from "react"
import axios from "axios"

import { TopBar } from '../../components/Topbar'
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
import { PrivateBar } from "../../components/Privatebar";

// Routes
import { API_ROUTES } from "../../ApiRoutes.jsx"

function ChatAssistancePage() {
    // Axios test
    axios
        .get(API_ROUTES.DebugBaseUrl)
        .then((response) => {
            console.log(response.data)
        });

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
        <Container>
        <List sx={{ width: '100%', maxWidth: 1200, marginTop: 5}}>
      <ListItem alignItems="flex-start" sx={{ marginTop: 5,  bgcolor: 'background.paper', borderRadius: 2}}>
        <ListItemAvatar>
          <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg" />
        </ListItemAvatar>
        <ListItemText
          primary="Username"
          secondary={
            <React.Fragment>
              <Typography
                sx={{ display: 'inline' }}
                component="span"
                variant="body2"
                color="text.primary"
              >
                Assistant
              </Typography>
              {" — I'll be in your neighborhood doing errands this…"}
            </React.Fragment>
          }
        />
      </ListItem>
      <Divider variant="inset" component="li" />
      <ListItem alignItems="flex-start" sx={{ marginTop: 5,  bgcolor: 'background.paper', borderRadius: 2}}>
        <ListItemAvatar>
          <Avatar alt="Travis Howard" src="/static/images/avatar/2.jpg" />
        </ListItemAvatar>
        <ListItemText
          primary="Assistant"
          secondary={
            <React.Fragment>
              <Typography
                sx={{ display: 'inline' }}
                component="span"
                variant="body2"
                color="text.primary"
              >
                {"22 de abril"}
              </Typography>
              {" — Wish I could come, but I'm out of town this…"}
            </React.Fragment>
          }
        />
      </ListItem>
      <Divider variant="inset" component="li" />
      <ListItem alignItems="flex-start" sx={{ marginTop: 5,  bgcolor: 'background.paper', borderRadius: 2}}>
        <ListItemAvatar>
          <Avatar alt="Cindy Baker" src="/static/images/avatar/3.jpg" />
        </ListItemAvatar>
        <ListItemText
          primary="Username"
          secondary={
            <React.Fragment>
              <Typography
                sx={{ display: 'inline' }}
                component="span"
                variant="body2"
                color="text.primary"
              >
                Sandra Adams
              </Typography>
              {' — Do you have Paris recommendations? Have you ever…'}
            </React.Fragment>
          }
        />
      </ListItem>
    </List>
        </Container>
    </Box>
  );
}

export default ChatAssistancePage;
