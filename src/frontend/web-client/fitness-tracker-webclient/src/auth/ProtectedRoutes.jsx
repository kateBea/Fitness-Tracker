import React from 'react'
import { Navigate, Outlet } from 'react-router-dom';
import moment from 'moment';

function ProtectedRoutes() {
  // Obtener la fecha y hora actual
  const dateNow = new Date(Date.now());

  // Obtener el token de autenticación y su duración desde el localStorage
  const tokenValue = localStorage.getItem("token");
  const tokenDuration = parseInt(localStorage.getItem("tokenDuration"));

  // Obtener la fecha de expiración del token desde el localStorage
  const tokenExpirationDate = new Date(Date.parse(localStorage.getItem("tokenExpirationDate")));

  // Determinar si la fecha de expiración del token ha pasado
  const expirationDatePassed = tokenExpirationDate <= dateNow;

  // Verificar si no hay token o si ha expirado
  if (tokenValue == null || tokenValue == undefined || expirationDatePassed) {
    console.log("User not logged");

    // Limpiar el localStorage en caso de que el token esté presente pero haya expirado
    localStorage.removeItem('token');
    localStorage.removeItem('tokenExpirationDate');
    localStorage.removeItem('tokenDuration');

    // Redirigir al usuario a la página de inicio de sesión
    return <Navigate to="/Login" />;
  }

  // Si el usuario está autenticado y el token no ha expirado, renderizar las rutas protegidas
  return <Outlet />;
}


export default ProtectedRoutes