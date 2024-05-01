import React from 'react'

import LoginPage from './pages/public/LoginPage.jsx'
import RegisterPage from './pages/public/RegisterPage.jsx'
import MainPage from './pages/private/MainPage.jsx'
import AIAssistancePage from './pages/private/AIAssistancePage.jsx'

import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'

import 'bootstrap/dist/css/bootstrap.min.css';

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Navigate replace to="/login"/>}/>
        <Route path="/login" element={<LoginPage/>}></Route>
        <Route path="/register" element={<RegisterPage/>}></Route>

        <Route path="/aiassistance" element={<AIAssistancePage/>}></Route>
        <Route path="/mainpage" element={<MainPage/>}></Route>
      </Routes>
    </BrowserRouter>
  )
}
