import React from 'react'

import DietGeneratorPage from './pages/private/DietGeneratorPage.jsx'
import ChatAssistancePage from './pages/private/ChatAssistancePage.jsx'
import LoginPage from './pages/public/LoginPage.jsx'
import RegisterPage from './pages/public/RegisterPage.jsx'
import MainPage from './pages/private/MainPage.jsx'
import PerfilPage from './pages/private/PerfilPage.jsx'
import DailyPage from './pages/private/DailyPage.jsx'
import DailyCalorie from './pages/private/DailyCalorie.jsx'

import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'

import 'bootstrap/dist/css/bootstrap.min.css';

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* Public */}
        <Route path='/' element={<Navigate replace to="/login"/>}/>
        <Route path="/login" element={<LoginPage/>}></Route>
        <Route path="/register" element={<RegisterPage/>}></Route>

        
        <Route path="/dietgenerator" element={<DietGeneratorPage/>}></Route>
        <Route path="/aiassistance" element={<ChatAssistancePage/>}></Route>
        <Route path="/mainpage" element={<MainPage/>}></Route>
        
        {/* Private */}
        <Route path="/perfil" element={<PerfilPage/>}></Route>
        <Route path="/today" element={<DailyPage/>}></Route>
        <Route path="/DailyCalorie" element={<DailyCalorie/>}></Route>
      </Routes>
    </BrowserRouter>
  )
}
