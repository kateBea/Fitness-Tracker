import React from "react";
import { Drawer, Grid, Icon, List, ListItem, ListItemText } from "@mui/material";
import AccountBoxIcon from '@mui/icons-material/AccountBox';
import QueryStatsIcon from '@mui/icons-material/QueryStats';
import ChecklistRtlIcon from '@mui/icons-material/ChecklistRtl';
import FastfoodIcon from '@mui/icons-material/Fastfood';
import LogoutIcon from '@mui/icons-material/Logout';

const Sidebar = ({ isOpen, onClose }) => {
  return (
    <Drawer anchor="left" open={isOpen} onClose={onClose}>
         <div style={{ width: 280 }}> {/* Adjust the width as needed */}

      <List>
        <ListItem button>
        <Grid container spacing={2}>
                <Grid item>

            <Icon><QueryStatsIcon></QueryStatsIcon> </Icon>
                </Grid>
                <Grid item>
          <ListItemText primary="Overview" />
                    
                </Grid>
            </Grid>
        </ListItem>
        <ListItem button>
        <Grid container spacing={2}>
                <Grid item>

            <Icon><FastfoodIcon></FastfoodIcon> </Icon>
                </Grid>
                <Grid item>
          <ListItemText primary="Listado de dietas" />
                    
                </Grid>
            </Grid>
        </ListItem>
        <ListItem button>
        <Grid container spacing={2}>
                <Grid item>

            <Icon><ChecklistRtlIcon></ChecklistRtlIcon> </Icon>
                </Grid>
                <Grid item>
          <ListItemText primary="Listado de rutinas" />
                    
                </Grid>
            </Grid>
        </ListItem>
        <ListItem button>
            <Grid container spacing={2}>
                <Grid item>

            <Icon><AccountBoxIcon></AccountBoxIcon> </Icon>
                </Grid>
                <Grid item>
          <ListItemText primary="Información de cuenta" />
                    
                </Grid>
            </Grid>
        </ListItem>
        <ListItem button>
        <Grid container spacing={2}>
                <Grid item>

            <Icon><LogoutIcon></LogoutIcon> </Icon>
                </Grid>
                <Grid item>
          <ListItemText primary="Cerrar sesión" />
                    
                </Grid>
            </Grid>
        </ListItem>
        {/* Add more sidebar options as needed */}
      </List>
         </div>
    </Drawer>
  );
};

export default Sidebar;
