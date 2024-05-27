import React from "react";
import {
  Drawer,
  Grid,
  Icon,
  List,
  ListItem,
  ListItemText,
} from "@mui/material";
import AccountBoxIcon from "@mui/icons-material/AccountBox";
import QueryStatsIcon from "@mui/icons-material/QueryStats";
import ChecklistRtlIcon from "@mui/icons-material/ChecklistRtl";
import FastfoodIcon from "@mui/icons-material/Fastfood";
import LogoutIcon from "@mui/icons-material/Logout";
import { Navigate } from "react-router-dom";
import { useNavigate } from "react-router-dom";

const Sidebar = ({ isOpen, onClose }) => {
  const tokenValue = localStorage.getItem("token");

  const navigate = useNavigate();
  const cerrarSesion = async () => {
    if (tokenValue != null || tokenValue != undefined) {
        console.log('cerrando sesión')

      localStorage.removeItem("token");
      localStorage.removeItem("tokenExpirationDate");
      localStorage.removeItem("tokenDuration");

      //navigate("/Login");
      return <Navigate to="/Login" />;
    }
  };

  return (
    <Drawer anchor="left" open={isOpen} onClose={onClose}>
      <div style={{ width: 280 }}>
        {" "}
        {/* Adjust the width as needed */}
        <List>
          <ListItem button>
            <Grid container spacing={2}>
              <Grid item>
                <Icon>
                  <QueryStatsIcon></QueryStatsIcon>{" "}
                </Icon>
              </Grid>
              <Grid item>
                <ListItemText primary="Overview" />
              </Grid>
            </Grid>
          </ListItem>
          <ListItem button>
            <Grid container spacing={2}>
              <Grid item>
                <Icon>
                  <FastfoodIcon></FastfoodIcon>{" "}
                </Icon>
              </Grid>
              <Grid item>
                <ListItemText primary="Listado de dietas" />
              </Grid>
            </Grid>
          </ListItem>
          <ListItem button>
            <Grid container spacing={2}>
              <Grid item>
                <Icon>
                  <ChecklistRtlIcon></ChecklistRtlIcon>{" "}
                </Icon>
              </Grid>
              <Grid item>
                <ListItemText primary="Listado de rutinas" />
              </Grid>
            </Grid>
          </ListItem>
          <ListItem button>
            <Grid container spacing={2}>
              <Grid item>
                <Icon>
                  <AccountBoxIcon></AccountBoxIcon>{" "}
                </Icon>
              </Grid>
              <Grid item>
                <ListItemText primary="Información de cuenta" />
              </Grid>
            </Grid>
          </ListItem>
          <ListItem button>
            <Grid container spacing={2}>
              <Grid item>
                <Icon>
                  <LogoutIcon></LogoutIcon>{" "}
                </Icon>
              </Grid>
              <Grid item>
                <ListItemText primary="Cerrar sesión" onClick={cerrarSesion} />
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
