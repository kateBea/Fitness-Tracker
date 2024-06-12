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

function AuthProvider({ children }) {
  const [userUsername, setUsername] = useState("Emtpy");
  const [errors, setErrors] = useState([]);

  const loginUser = async (email, password) => {

    try {
        const requestData = { email: email, password: password };
        const response = await axios.post(API_ROUTES.Login, requestData);
  
        if (response.data.success) {
          setUsername(response.data.data.email);
  
          axios.defaults.headers.common["Authorization"] = `Bearer ${response.data.data.token}`;
          
          const tokenExpirationDate = new Date(Date.now());
          tokenExpirationDate.setSeconds(tokenExpirationDate.getSeconds() + parseInt(response.data.data.tokenDuration));

          localStorage.setItem("token", response.data.data.token);
          localStorage.setItem("tokenExpirationDate", tokenExpirationDate);
          localStorage.setItem("tokenDuration", response.data.data.tokenDuration);
        } else {
          console.log(response.data.errors);
          setErrors(response.data.errors);
        }
      } catch (error) {
        // Si hay alguno que otro error en la petición
        console.error("Error en el login: ", error);
      }
  };

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
