import React from 'react'

import DietGeneratorPage from './pages/private/DietGeneratorPage.jsx'
import ChatAssistancePage from './pages/private/ChatAssistancePage.jsx'
import LoginPage from './pages/public/LoginPage.jsx'
import RegisterPage from './pages/public/RegisterPage.jsx'
import InicioPage from './pages/public/InicioPage.jsx'
import PerfilPage from './pages/private/PerfilPage.jsx'
import DailyPage from './pages/private/DailyPage.jsx'
import DailyCalorie from './pages/private/DailyCalorie.jsx'
import EditarDieta from './pages/private/VisualizarDietaPage.jsx'
import ListadoDietas from './pages/private/ListadoDietasPage.jsx'

import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'

import 'bootstrap/dist/css/bootstrap.min.css';
import ListadoRutinasPage from './pages/private/ListadoRutinasPage.jsx'

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* Public */}
        <Route path='/' element={<Navigate replace to="/Inicio"/>}/>
        <Route path="/Inicio" element={<InicioPage/>}></Route>
        <Route path="/Login" element={<LoginPage/>}></Route>
        <Route path="/Register" element={<RegisterPage/>}></Route>

        {/* Private */}
        <Route path="/ChatAssistance" element={<ChatAssistancePage/>}></Route>
        <Route path="/DailyCalorie" element={<DailyCalorie/>}></Route>
        <Route path="/Today" element={<DailyPage/>}></Route>
        <Route path="/GenerarDieta" element={<DietGeneratorPage/>}></Route>
        <Route path="/EditarDieta" element={<EditarDieta/>}></Route>
        <Route path="/ListadoDietas" element={<ListadoDietas/>}></Route>
        <Route path="/ListadoRutinas" element={<ListadoRutinasPage/>}></Route>
        <Route path="/Perfil" element={<PerfilPage/>}></Route>
      </Routes>
    </BrowserRouter>
  )
}
