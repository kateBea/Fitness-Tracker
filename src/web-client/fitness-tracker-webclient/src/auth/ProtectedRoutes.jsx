import React from 'react'
import AuthProvider, { useAuthContext } from './AuthProvider'
import { Navigate, Outlet } from 'react-router-dom';
import { PAGE_ROUTES } from '../PageConstants';

function ProtectedRoutes() {
    const { /* componenetes para comprobar authenticacion */ userIsLogged } = useAuthContext();

    if (userIsLogged) {
        return <Navigate to={PAGE_ROUTES.Login} replace={true}/>
    }

  return <Outlet/>
}

export default ProtectedRoutes