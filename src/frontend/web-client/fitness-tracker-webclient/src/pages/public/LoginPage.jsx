import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { useForm } from 'react-hook-form';
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
import PersonOutlineOutlinedIcon from '@mui/icons-material/PersonOutlineOutlined';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import LogoFitness from '../../img/logo-fitness-tracker.png';
import { TopBar } from '../../components/Topbar';
import { API_ROUTES } from '../../ApiRoutes';
import { PAGE_ROUTES } from '../../PageConstants';
import { useAuthContext } from '../../auth/AuthProvider';

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

function LoginPage() {
  // Pre setup
  const navigate = useNavigate(); // Hook para la navegación.
  const { loginUser } = useAuthContext(); // Obtiene la función `loginUser` del contexto de autenticación.

  // State setup
  const [email, setEmail] = useState(''); // Estado para el email del usuario.
  const [password, setPassword] = useState(''); // Estado para la contraseña del usuario.
  const token = localStorage.getItem("token"); // Obtiene el token del localStorage.

  // Función para manejar el inicio de sesión.
  const handleLogin = async () => {
    await loginUser(email, password); // Llama a la función `loginUser` con el email y la contraseña.
  };

  // Efecto para verificar el token y redirigir si el usuario ya está autenticado.
  useEffect(() => {
    console.log("tokenls " + token); // Imprime el token en la consola.

    // Verifica si el token no es nulo ni indefinido.
    if (token != null || token != undefined) {
      console.log("User is already logged"); 
      navigate(PAGE_ROUTES.Today); // Redirige al usuario a la página de hoy.
    }
  }, [token]); // El efecto se ejecuta cada vez que el valor del token cambia.

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
          [theme.breakpoints.up('1200')]:{
            width: '40%',
          },
          [theme.breakpoints.down('1200')]:{
            width: '60%',
          },
          [theme.breakpoints.down('700')]:{
            width: '100%',
          }
        }}
      >
        <Box>
          <Box
            sx={{
              display: 'flex',
              justifyContent: 'center',
              width: '100%',
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
                    [theme.breakpoints.down('450')]:{
                      fontSize:'27pt'
                    }
                }}
              >Fitness-Tracker</Typography>
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
              placeholder='Email'
              required={true}
              type='text'
              fullWidth={true}
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
              required={true}
              type='password'
              fullWidth={true}
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
            onClick={handleLogin}
            type='submit'
          >
            Log in
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
            ¿No tienes cuenta?
          </Typography>
          <Typography
            component='a'
            href='/register'
            sx={{
              fontWeight: 'bold',
              textDecoration: 'none',
              color: 'white'
            }}
          >
            Registrate aquí
          </Typography>
        </Box>
      </Container>
    </Box>
  );
}

export default LoginPage;
