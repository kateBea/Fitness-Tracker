import React from "react";

function ChatAssistancePage() {
  return (
    <div>
      <form class="container mt-3">
        <div class="row mb-11">
          <div class="col-sm-1">
            <label for="nombre" class="col-sm-2 col-form-label">
              User
            </label>
          </div>
          <div class="col-sm">
            <input
              type="text"
              id="user-input"
              class="form-control"
              placeholder="Un input del usuario"
              aria-label="user-input"
            ></input>
          </div>
        </div>

        <div class="row mb-12 mt-3">
          <div class="col-sm-1">
            <label for="correo-electronico" class="col-sm-2 col-form-label">
              FT Assistant
            </label>
          </div>
          <div class="col-sm">
            <input
              type="email"
              class="form-control"
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
