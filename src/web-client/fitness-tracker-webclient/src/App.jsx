import React from 'react'
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'

// styles
import 'bootstrap/dist/css/bootstrap.min.css';
import '../styles/styles.css';

import AuthProvider from './auth/AuthProvider.jsx';

import DietGeneratorPage from './pages/private/DietGeneratorPage.jsx'
import ChatAssistancePage from './pages/private/work-in-progress/ChatAssistancePage.jsx'
import LoginPage from './pages/public/LoginPage.jsx'
import RegisterPage from './pages/public/RegisterPage.jsx'
import InicioPage from './pages/public/InicioPage.jsx'
import PerfilPage from './pages/private/PerfilPage.jsx'
import DailyPage from './pages/private/DailyPage.jsx'
import DailyCalorie from './pages/private/DailyCalorie.jsx'
import ListadoDietas from './pages/private/ListadoDietasPage.jsx'
import EditarPerfilPage from './pages/private/EditarPerfilPage.jsx'

import ListadoRutinasPage from './pages/private/ListadoRutinasPage.jsx'
import ProtectedRoutes from './auth/ProtectedRoutes.jsx';

export default function App() { // Definimos el componente App
  return (
    <AuthProvider> {/* Envolvemos la aplicación con AuthProvider para manejar el contexto de autenticación */}
      <BrowserRouter> {/* Utilizamos BrowserRouter para manejar las rutas */}
        <Routes>
          {/* Rutas Públicas */}
          <Route path='/' element={<InicioPage />} /> {/* Ruta para la página de inicio */}
          <Route path="/Inicio" element={<Navigate replace to="/" />}></Route> {/* Redirige /Inicio a / */}
          <Route path="/Login" element={<LoginPage />}></Route> {/* Ruta para la página de login */}
          <Route path="/Register" element={<RegisterPage />}></Route> {/* Ruta para la página de registro */}

          <Route element={<ProtectedRoutes />}> {/* Envolvemos las rutas protegidas dentro de ProtectedRoutes */}
            {/* Usuario */}
            <Route path="/Perfil" element={<PerfilPage />}></Route> {/* Ruta para la página de perfil */}
            <Route path="/Editar" element={<EditarPerfilPage />}></Route> {/* Ruta para editar el perfil */}

            {/* Dietas */}
            <Route path="/GenerarDieta" element={<DietGeneratorPage />}></Route> {/* Ruta para generar dietas */}
            <Route path="/ListadoDietas" element={<ListadoDietas />}></Route> {/* Ruta para listar dietas */}

            {/* Rutinas */}
            <Route path="/Today" element={<DailyPage />}></Route> {/* Ruta para la página diaria */}
            <Route path="/ListadoRutinas" element={<ListadoRutinasPage />}></Route> {/* Ruta para listar rutinas */}

            {/* Pendiente de borrar o work in progress */}
            <Route path="/DailyCalorie" element={<DailyCalorie />}></Route> {/* Ruta para el cálculo diario de calorías */}
            <Route path="/ChatAssistance" element={<ChatAssistancePage />}></Route> {/* Ruta para la asistencia por chat */}
          </Route>
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  )
}
