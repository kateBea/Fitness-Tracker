import React from 'react'

function LoginPage() {
  return (
    <diV>
      <div className='top-bar'>
        <div className='logo'>
          <img></img>
          <h1 title='Fitness-Tracker'>Fitness-Tracker</h1>
        </div>
        <nav className='navbar'>
          <p>Home</p>
          <p>App</p>
          <p>Nuestros Clientes</p>
          <p>Sobre Nosotros</p>
        </nav>
      </div>
      <div>
        <div>
          <img></img>
        </div>
        <div>
          <img></img>
          <p>Usuario</p>
          {/* Linea para subrayar las dos cosas */}
        </div>
        <div>
          <img></img>
          <p>Email</p>
          {/* Linea para subrayar */}
        </div>
        <div>
          <img></img>
          <p>Contraseña</p>
          {/* Linea para subrayar */}
        </div>
        <div>
          <div>
            <p>Log In</p>
          </div>
        </div>
        <div>
          <p>¿Ya tienes cuenta aqui? </p><a>Entra aquí</a>
        </div>

      </div>
    </diV>
  )
}

export default LoginPage