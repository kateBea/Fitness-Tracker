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

import ListadoRutinasPage from './pages/private/ListadoRutinasPage.jsx'
import ProtectedRoutes from './auth/ProtectedRoutes.jsx';

export default function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          {/* Public */}
          <Route path='/' element={<Navigate replace to="/Inicio" />} />
          <Route path="/Inicio" element={<InicioPage />}></Route>
          <Route path="/Login" element={<LoginPage />}></Route>
          <Route path="/Register" element={<RegisterPage />}></Route>


          {/* Meter todo dentro de protectedRoutes desde aqui para abajo */}
          { /* Usuario */}
          <Route path="/Perfil" element={<PerfilPage />}></Route>


          { /* Dietas */}
          <Route path="/GenerarDieta" element={<DietGeneratorPage />}></Route>
          <Route path="/ListadoDietas" element={<ListadoDietas />}></Route>


          { /* Rutinas */}
          <Route path="/Today" element={<DailyPage />}></Route>
          <Route path="/ListadoRutinas" element={<ListadoRutinasPage />}></Route>


          { /* Pendiente de borrar o work in progress */}
          <Route path="/DailyCalorie" element={<DailyCalorie />}></Route>
          <Route path="/ChatAssistance" element={<ChatAssistancePage />}></Route>
          {/* Private */}
          <Route element={<ProtectedRoutes />}>

          </Route>
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  )
}
