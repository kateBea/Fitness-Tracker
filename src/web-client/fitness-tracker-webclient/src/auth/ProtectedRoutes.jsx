import React from 'react'
import { Navigate, Outlet } from 'react-router-dom';

function ProtectedRoutes() {
  const tokenLS = localStorage.getItem("token");

  if (tokenLS == null || tokenLS == undefined) {
      console.log("User not logged")
      return <Navigate to="/login" />;
    }

  return <Outlet/>
}

export default ProtectedRoutes