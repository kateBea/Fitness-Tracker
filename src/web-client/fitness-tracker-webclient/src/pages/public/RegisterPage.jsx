import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import {
  Box,
  Typography,
  Container,
  InputAdornment,
  Input,
  FormControl,
  Button
} from '@mui/material';
import { createTheme } from '@mui/material/styles';
import { toast, ToastContainer } from 'react-toastify';
import PersonOutlineOutlinedIcon from '@mui/icons-material/PersonOutlineOutlined';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import MarkAsUnreadOutlinedIcon from '@mui/icons-material/MarkAsUnreadOutlined';
import LogoFitness from '../../img/logo-fitness-tracker.png';
import { TopBar } from '../../components/Topbar';
import { API_ROUTES } from '../../ApiRoutes';
import { PAGE_ROUTES } from '../../PageConstants';

const theme = createTheme({
  palette: {
    primary: {
      main: '#FFF',
      light: '#FFF',
      dark: '#FFF',
      contrastText: '#FFF',
    },
    underline: {
      main: '#FFF',
      light: '#FFF',
      dark: '#FFF',
      contrastText: '#FFF',
    }
  },
});

function RegisterPage() {
  // Hook de navegación para redirigir a otras páginas.
  const navigate = useNavigate();

  // Estados para almacenar los datos del formulario de registro.
  const [email, setEmail] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  /**
   * Función para manejar el registro del usuario.
   * Envía una petición para registrar un nuevo usuario con los datos proporcionados.
   */
  const handleRegister = async () => {
    try {
      // Datos de la petición de registro.
      const requestData = { email, username, password };

      // Realiza la petición POST para registrar el usuario.
      const response = await axios.post(API_ROUTES.RegistrarUsuario, requestData);

      if (response.data.success) {
        // Navega a la página de inicio de sesión si el registro es exitoso.
        toast.success("Login successful!"); // Mostrar notificación de éxito
        navigate(PAGE_ROUTES.Login);
      } else {
        // Imprime los errores en la consola si hubo algún problema.
        console.log(response.data.errors);
        throw new Error("Error en el login: " + error.message);
      }
    } catch (error) {
      // Imprime cualquier error en la consola si la petición falla.
      toast.error("Login failed: " + error.message); // Mostrar notificación de error
      console.error('Error al registrar el usuario: ', error);
    }
  };

  return (
    <Box
      sx={{
        display: 'flex',
        flexDirection: 'column',
        background: '#293B50',
        minHeight: '100vh',
        justifyContent: 'start',
        alignItems: 'center'
      }}
    >
      <ToastContainer /> {/* Contenedor de notificaciones */}
      <TopBar />
      <Container
        sx={{
          display: 'flex',
          justifyContent: 'start',
          flexDirection: 'column',
          background: '#436489',
          borderRadius: '60px',
          padding: '40px',
          margin: '50px',
          [theme.breakpoints.up('1200')]: {
            width: '40%',
          },
          [theme.breakpoints.down('1200')]: {
            width: '60%',
          },
          [theme.breakpoints.down('700')]: {
            width: '100%',
          }
        }}
      >
        <Box>
          <Box
            sx={{
              display: 'flex',
              justifyContent: 'center',
              width: '100%'
            }}
          >
            <img src={LogoFitness}
              title="Logo Fitness-Tracker"
              alt="Logo Fitness-Tracker App"
              style={{
                width: '100px',
                height: '100px',
                display: 'block',
              }}
            />
          </Box>
          <Box
            sx={{
              display: 'flex',
              justifyContent: 'center',
              width: '100%'
            }}
          >
            <Typography
              variant="h3"
              component="h1"
              href="/"
              sx={{
                color: 'white',
                textDecoration: 'none',
                margin: '15px',
                [theme.breakpoints.down('450')]: {
                  fontSize: '27pt'
                }
              }}
            >
              Fitness-Tracker
            </Typography>
          </Box>
        </Box>
        <Box>
          <FormControl variant='standard' margin='normal' sx={{ width: '100%' }}>
            <Input
              id="user"
              startAdornment={
                <InputAdornment position="start">
                  <PersonOutlineOutlinedIcon
                    fontSize='large'
                    sx={{
                      marginLeft: '10px',
                      marginRight: '20px',
                      color: 'white',
                    }}
                  />
                </InputAdornment>
              }
              placeholder='Usuario'
              required
              type='text'
              fullWidth
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              sx={{
                color: '#FFF',
                fontSize: '14pt',
                borderBottomColor: '#FFF',
                marginTop: '10px',
                marginBottom: '10px',
                padding: '5px'
              }}
              theme={theme}
            />
          </FormControl>
          <FormControl variant='standard' margin='normal' sx={{ width: '100%' }}>
            <Input
              id="password"
              startAdornment={
                <InputAdornment position="start">
                  <LockOutlinedIcon
                    fontSize='large'
                    sx={{
                      marginLeft: '10px',
                      marginRight: '20px',
                      color: 'white',
                    }}
                  />
                </InputAdornment>
              }
              placeholder='Password'
              required
              type='password'
              fullWidth
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              sx={{
                color: '#FFF',
                fontSize: '14pt',
                borderBottomColor: '#FFF',
                marginTop: '10px',
                marginBottom: '10px',
                padding: '5px'
              }}
              theme={theme}
            />
          </FormControl>
          <FormControl variant='standard' margin='normal' sx={{ width: '100%' }}>
            <Input
              id="email"
              placeholder='Correo electrónico'
              startAdornment={
                <InputAdornment position="start">
                  <MarkAsUnreadOutlinedIcon
                    fontSize='large'
                    sx={{
                      marginLeft: '10px',
                      marginRight: '20px',
                      color: 'white',
                    }}
                  />
                </InputAdornment>
              }
              required
              type='text'
              fullWidth
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              sx={{
                color: '#FFF',
                fontSize: '14pt',
                borderBottomColor: '#FFF',
                marginTop: '10px',
                marginBottom: '10px',
                padding: '5px'
              }}
              theme={theme}
            />
          </FormControl>
        </Box>
        <Box
          sx={{
            display: 'flex',
            justifyContent: 'center',
          }}
        >
          <Button
            variant="contained"
            sx={{
              background: '#D92668',
              margin: '10px',
              width: '50%',
              borderTopLeftRadius: '30px',
              borderBottomLeftRadius: '0px',
              borderTopRightRadius: '0px',
              borderBottomRightRadius: '30px',
              textTransform: 'none',
              fontSize: '20pt'
            }}
            onClick={handleRegister}
          >
            Register
          </Button>
        </Box>
        <Box
          sx={{
            display: 'flex',
            justifyContent: 'center',
            marginTop: '20px',
            color: '#FFF'
          }}
        >
          <Typography
            sx={{
              marginRight: '5px'
            }}
          >
            ¿Ya tienes cuenta?
          </Typography>
          <Typography
            component='a'
            href='/login'
            sx={{
              fontWeight: 'bold',
              textDecoration: 'none',
              color: 'white'
            }}
          >
            Entra aquí
          </Typography>
        </Box>
      </Container>
    </Box>
  );
}

export default RegisterPage;
