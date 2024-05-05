import React from 'react'
import {
  Box,
  Typography,
  Container,
  TextField,
  InputLabel,
  InputAdornment,
  Input,
  FormControl,
  Button
} from '@mui/material'
import { createTheme } from '@mui/material/styles';

import LogoFitness from '../../img/logo-fitness-tracker.png';
import { TopBar } from '../../components/Topbar'

// Iconos
import PersonOutlineOutlinedIcon from '@mui/icons-material/PersonOutlineOutlined';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import MarkAsUnreadOutlinedIcon from '@mui/icons-material/MarkAsUnreadOutlined';

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
        sx={{
          display:'flex',
          justifyContent:'start',
          flexDirection:'column',
          background: '#436489',
          borderRadius:'60px',
          padding:'40px',
          margin:'50px',
          width:'35%',
        }}
      >
        <Box
        >
          <Box
            sx={{
              display:'flex',
              justifyContent:'center',
              width:'100%'
            }}
          >
            <img src={LogoFitness} 
              title="Logo Fitness-Tracker" 
              alt="Logo Fitness-Tracker App" 
              style={{
                width:'100px',
                height:'100px',
                position: 'block',
              }}
            />
          </Box>
          <Box
            sx={{
              display:'flex',
              justifyContent:'center',
              width:'100%'
            }}
          >
            <Typography
                variant="h3"
                component="h1"
                href="/"
                sx={{
                    color:'white',
                    textDecoration: 'none',
                    margin: '15px'
                }}
              >Fitness-Tracker</Typography>
          </Box>
        </Box>
        <Box>
          <FormControl variant='standard'
              margin='normal'
              sx={{
                width:'100% '
              }}
            >
            <Box >
              <Input
                id="user"
                startAdornment={
                  <InputAdornment position="start">
                    <PersonOutlineOutlinedIcon 
                      fontSize='large'
                      sx={{
                        marginLeft:'10px',
                        marginRight:'20px',
                        color:'white',
                      }}
                    />
                  </InputAdornment>
                }
                placeholder='Usuario'
                required='true'
                type='text'
                fullWidth='true'
                sx={{
                  color:'#FFF',
                  fontSize:'14pt',
                  borderBottomColor:'#FFF',
                  marginTop:'10px',
                  marginBottom:'10px',
                  padding:'5px'
                }}
                theme={theme}
              />
            </Box>
            <Box >
              <Input
                id="password"
                startAdornment={
                  <InputAdornment position="start">
                    <LockOutlinedIcon
                      fontSize='large'
                      sx={{
                        marginLeft:'10px',
                        marginRight:'20px',
                        color:'white',
                      }}
                    />
                  </InputAdornment>
                }
                placeholder='Password'
                required='true'
                type='password'
                fullWidth='true'
                sx={{
                  color:'#FFF',
                  fontSize:'14pt',
                  borderBottomColor:'#FFF',
                  marginTop:'10px',
                  marginBottom:'10px',
                  padding:'5px'
                }}
                theme={theme}
              />
            </Box>
            <Box >
              <Input
                id="email"
                placeholder='Email'
                startAdornment={
                  <InputAdornment position="start">
                    <MarkAsUnreadOutlinedIcon
                      fontSize='large'
                      sx={{
                        marginLeft:'10px',
                        marginRight:'20px',
                        color:'white',
                      }}
                    />
                  </InputAdornment>
                }
                required='true'
                type='text'
                fullWidth='true'
                sx={{
                  color:'#FFF',
                  fontSize:'14pt',
                  borderBottomColor:'#FFF',
                  marginTop:'10px',
                  marginBottom:'10px',
                  padding:'5px'
                }}
                theme={theme}
              />
            </Box>
          </FormControl>
        </Box>
        <Box 
          sx={{
            display:'flex',
            justifyContent:'center',
          }}
        >
        <Button variant="contained"
          sx={{
            background:'#D92668',
            margin:'10px',
            width:'50%',
            borderTopLeftRadius:'30px',
            borderBottomLeftRadius:'0px',
            borderTopRightRadius:'0px',
            borderBottomRightRadius:'30px',
            textTransform: 'none',
            fontSize:'20pt'
          }}
        >Register</Button>
        </Box>
        <Box
          sx={{
            display:'flex',
            justifyContent:'center',
            marginTop:'20px',
            color:'#FFF'
          }}
        >
          <Typography
            sx={{
              marginRight:'5px'
            }}
          >¿Ya tienes cuenta?</Typography>
          <Typography
            component='a'
            href='/login'
            sx={{
              fontWeight:'bold',
              textDecoration:'none',
              color:'white'
            }}
          >Entra aquí</Typography>
        </Box>
      </Container>
    </Box>
  )
}
export default RegisterPage