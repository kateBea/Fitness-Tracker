import React from 'react'
import { Navigate, Outlet } from 'react-router-dom';
import moment from 'moment';

function ProtectedRoutes() {
  const dateNow = new Date(Date.now());
  const tokebValue = localStorage.getItem("token");
  const tokenDuration = parseInt(localStorage.getItem("tokenDuration"));

  const tokenExpirationDate = new Date(Date.parse(localStorage.getItem("tokenExpirationDate")));

  const expirationDatePassed = tokenExpirationDate <= dateNow;

  console.log(`now date: ${dateNow}, passed: ${expirationDatePassed}`)
  console.log(`expir date: ${tokenExpirationDate}, passed: ${expirationDatePassed}`)

  if ((tokebValue == null || tokebValue == undefined) || expirationDatePassed) {
      console.log("User not logged")

      // limpiar en caso de que esté el token
      localStorage.removeItem('token')
      localStorage.removeItem('tokenExpirationDate')
      localStorage.removeItem('tokenDuration')

      return <Navigate to="/Login" />;
    }

  return <Outlet/>
}

export default ProtectedRoutes