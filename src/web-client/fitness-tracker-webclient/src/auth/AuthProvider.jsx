import React, { createContext, useContext, useState } from 'react'
import { API_ROUTES } from '../ApiRoutes';

export const AuthContext = createContext();

export const useAuth = () => {
    const context = useContext(AuthContext);
    if (!context) {
        throw new Error("useAuth debe ser usado dentro de un provider");
    }
    
    return context;
};

function AuthProvider ({ children }) {
    
    const [userEmail, setUserEmail] = useState('');
    const [userUsername, setUsername] = useState('');
    const [userIsLogged, setUserIsLogged] = useState(false);
    const [errors, setErrors] = useState([]);
    
    const logUser = async (email, password) => {
        try {
            const requestData = { email: email, password: password }
            const response = await axios.post( API_ROUTES.Login, requestData );
      
            if (response.data.success) {
                console.log(response)
    
                setUserEmail(email)
                setUsername(/* username here from response*/ "unknown")
                setUserIsLogged(true);
            } else {
                console.log(response.data.errors);
                setErrors(response.data.errors);
            }
        }catch (error) {
            // Si hay alguno que otro error en la petición
            console.error('Error logging in:', error);
        }

    };

    return (
        <AuthContext.Provider
            value={{
                logUser,
                userEmail,
                userUsername,
                userIsLogged,
                errors
            }}
        >
            {children}
        </AuthContext.Provider>
    );
}

export default AuthProvider;