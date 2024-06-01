import React from "react";
import {
  Drawer,
  Grid,
  Icon,
  List,
  ListItemButton,
  ListItemText,
} from "@mui/material";
import AccountBoxIcon from "@mui/icons-material/AccountBox";
import QueryStatsIcon from "@mui/icons-material/QueryStats";
import ChecklistRtlIcon from "@mui/icons-material/ChecklistRtl";
import FastfoodIcon from "@mui/icons-material/Fastfood";
import LogoutIcon from "@mui/icons-material/Logout";
import { useNavigate } from "react-router-dom";
import { PAGE_ROUTES } from "../PageConstants";
import RamenDiningIcon from '@mui/icons-material/RamenDining';

const Sidebar = ({ isOpen, onClose }) => {
  const navigate = useNavigate();
  const tokenValue = localStorage.getItem("token");

  const cerrarSesion = () => {
    if (tokenValue != null || tokenValue != undefined) {
      console.log('cerrando sesión');

      localStorage.removeItem("token");
      localStorage.removeItem("tokenExpirationDate");
      localStorage.removeItem("tokenDuration");

      navigate("/Login");
    }
  };

  const listadoDietas = () => {
    console.log(`Navegando a dietas`);
    navigate("/ListadoDietas");
  };

  const listadoRutinas = () => {
    console.log(`Navegando a rutinas`);
    navigate("/ListadoRutinas");
  };

  const perfil = () => {
    console.log(`Navegando a perfil`);
    navigate("/Perfil");
  };

  const inicio = () => {
    console.log(`Navegando a Overview`);
    navigate("/Today");
  };

  const generarDieta = () => {
    console.log(`Navegando a generar dieta`);
    navigate("/GenerarDieta");
  };
  

  return (
    <Drawer anchor="left" open={isOpen} onClose={onClose}>
      <div style={{ width: 280 }}>
        <List>
          <ListItemButton onClick={inicio}>
            <Grid container spacing={2}>
              <Grid item>
                <Icon>
                  <QueryStatsIcon />
                </Icon>
              </Grid>
              <Grid item>
                <ListItemText primary="Overview" />
              </Grid>
            </Grid>
          </ListItemButton>
          <ListItemButton onClick={generarDieta}>
            <Grid container spacing={2}>
              <Grid item>
                <Icon>
                  <RamenDiningIcon />
                </Icon>
              </Grid>
              <Grid item>
                <ListItemText primary="Generar dieta" />
              </Grid>
            </Grid>
          </ListItemButton>
          <ListItemButton onClick={listadoDietas}>
            <Grid container spacing={2}>
              <Grid item>
                <Icon>
                  <FastfoodIcon />
                </Icon>
              </Grid>
              <Grid item>
                <ListItemText primary="Listado de dietas" />
              </Grid>
            </Grid>
          </ListItemButton>
          <ListItemButton onClick={listadoRutinas}>
            <Grid container spacing={2}>
              <Grid item>
                <Icon>
                  <ChecklistRtlIcon />
                </Icon>
              </Grid>
              <Grid item>
                <ListItemText primary="Listado de rutinas" />
              </Grid>
            </Grid>
          </ListItemButton>
          <ListItemButton onClick={perfil}>
            <Grid container spacing={2}>
              <Grid item>
                <Icon>
                  <AccountBoxIcon />
                </Icon>
              </Grid>
              <Grid item>
                <ListItemText primary="Información de cuenta" />
              </Grid>
            </Grid>
          </ListItemButton>
          <ListItemButton onClick={cerrarSesion}>
            <Grid container spacing={2}>
              <Grid item>
                <Icon>
                  <LogoutIcon />
                </Icon>
              </Grid>
              <Grid item>
                <ListItemText primary="Cerrar sesión" />
              </Grid>
            </Grid>
          </ListItemButton>
        </List>
      </div>
    </Drawer>
  );
};

export default Sidebar;
