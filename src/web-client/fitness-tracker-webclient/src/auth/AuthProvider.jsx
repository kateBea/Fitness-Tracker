import React, { createContext, useContext, useState, useEffect } from "react";
import { API_ROUTES } from "../ApiRoutes";
import axios from "axios";

export const AuthContext = createContext();

export const useAuthContext = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth debe ser usado dentro de un provider");
  }

  return context;
};

function AuthProvider({children}) {
  const [userUsername, setUsername] = useState("Emtpy");
  const [errors, setErrors] = useState([]);

  const loginUser = async (email,password) => {
    try {
      const requestData = { email: email, password: password }; // Datos de la petición.
      const response = await axios.post(API_ROUTES.Login, requestData); // Realiza la petición POST para iniciar sesión.
  
      if (response.data.success) {
        setUsername(response.data.data.email); // Establece el nombre de usuario.
  
        // Configura el token de autorización en los encabezados por defecto.
        axios.defaults.headers.common["Authorization"] = `Bearer ${response.data.data.token}`; 
        const tokenExpirationDate = new Date(Date.now());
        // Calcula la fecha de expiración del token.
        tokenExpirationDate.setSeconds(tokenExpirationDate.getSeconds() + parseInt(response.data.data.tokenDuration)); 
  
        // Almacena el token, la fecha de expiración y la duración en el localStorage.
        localStorage.setItem("token", response.data.data.token);
        localStorage.setItem("tokenExpirationDate", tokenExpirationDate);
        localStorage.setItem("tokenDuration", response.data.data.tokenDuration);
      } else {
        console.log(response.data.errors); // Imprime los errores en la consola.
        setErrors(response.data.errors); // Establece los errores en el estado.
        throw new Error(response.data.errors.join(', '));
        
      }
    } catch (error) {
      // Si hay algún error en la petición
      throw new Error("Error en el login: " + error.message);
      console.error("Error en el login: ", error); // Imprime el error en la consola.
    }
  }
  const logoutUser = async () => {
    try {
      // limpiar local storage
    } catch (error) {
      // Si hay alguno que otro error en la petición
      console.error("Error logging in:", error);
    }
  };

  return (
    <AuthContext.Provider
      value={{
        loginUser,
        logoutUser,
        userUsername,
        errors,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}

export default AuthProvider;
