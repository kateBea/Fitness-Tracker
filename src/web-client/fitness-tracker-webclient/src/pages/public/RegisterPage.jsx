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
import PersonOutlineOutlinedIcon from '@mui/icons-material/PersonOutlineOutlined';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import MarkAsUnreadOutlinedIcon from '@mui/icons-material/MarkAsUnreadOutlined';
import LogoFitness from '../../img/logo-fitness-tracker.png';
import { TopBar } from '../../components/Topbar';
import { API_ROUTES } from '../../ApiRoutes';

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
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleRegister = async () => {
    try {
      const response = await axios.post(API_ROUTES.RegistrarUsuario, {
        email,
        username,
        password,
      });

      if (response.data.success) {
        navigate('/Login');
      } else {
        // Si succes es falso, en ambos casos el reponse descrption tiene un mensaje
        console.log(response.data.errors);
      }
    } catch (error) {
      // Si hay alguno que otro error en la petición
      console.error('Error registering:', error);
    }
  };

  return (
    <Box 
      sx={{
        display: 'flex',
        flexDirection: 'column',
        background: '#293B50',
        minHeight: '1000px',
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
          width: '35%',
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
                    margin: '15px'
                }}
              >
                Fitness-Tracker
            </Typography>
          </Box>
        </Box>
        <Box>
          <FormControl variant='standard'
              margin='normal'
              sx={{
                width: '100%'
              }}
            >
            <Box>
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
            </Box>
            <Box>
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
            </Box>
            
            <Box>
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
            </Box>
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
