import React from 'react'

import LoginPage from '../public/pages/LoginPage.jsx'
import RegisterPage from '../public/pages/RegisterPage.jsx'
import MainPage from '../private/pages/MainPage.jsx'

import { BrowserRouter, Routes, Route } from 'react-router-dom'

import 'bootstrap/dist/css/bootstrap.min.css';

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<LoginPage/>}></Route>
        <Route path="/register" element={<RegisterPage/>}></Route>
        <Route path="/mainpage" element={<MainPage/>}></Route>
      </Routes>
    </BrowserRouter>
  )
}
