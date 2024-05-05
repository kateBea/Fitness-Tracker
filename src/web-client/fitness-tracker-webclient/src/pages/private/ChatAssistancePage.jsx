import React from "react";

function ChatAssistancePage() {
  return (
    <div>
      <h1>Fitness Tracker AI Assistant</h1>

      <form className="container mt-3">
        <div className="row mb-11">
          <div className="col-sm-1">
            <label for="nombre" className="col-sm-2 col-form-label">
              User
            </label>
          </div>
          <div className="col-sm">
            <input
              type="text"
              id="user-input"
              className="form-control"
              placeholder="Un input del usuario"
              aria-label="user-input"
            ></input>
          </div>
        </div>

        <div className="row mb-12 mt-3">
          <div className="col-sm-1">
            <label for="correo-electronico" className="col-sm-2 col-form-label">
              FT Assistant
            </label>
          </div>
          <div className="col-sm">
            <input
              type="email"
              className="form-control"
              id="assistant-answer"
              placeholder="Una respuesta elaborada"
              aria-label="assistant-answer"
            ></input>
          </div>
        </div>

      </form>
    </div>
  );
}

export default ChatAssistancePage;
