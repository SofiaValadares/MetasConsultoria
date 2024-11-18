import React from "react";
import "./Login.css";

const Login = () => {
  return (
    <div className="login-container">
      <header className="login-header">
        <div className="logo">Metas Consultoria</div>
      </header>
      <div className="login-form">
        <h1>LOGIN</h1>
        <form>
          <label htmlFor="email">EMAIL</label>
          <input type="email" id="email" placeholder="Digite seu email" />

          <label htmlFor="password">SENHA</label>
          <input type="password" id="password" placeholder="Digite sua senha" />

          <div className="button-group">
            <button type="button" className="cancel-button">
              CANCELAR
            </button>
            <button type="submit" className="login-button">
              LOGIN
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Login;
