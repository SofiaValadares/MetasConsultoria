import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Login.css";

const Login = function () {
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [mensagemErro, setMensagemErro] = useState("");
  const navigate = useNavigate();

  const handleSubmit = function (event) {
    event.preventDefault();

    setMensagemErro("");

    fetch("http://localhost:8080/users/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ email: email, password: senha }),
    })
      .then(function (response) {
        if (!response.ok) {
          throw new Error("Email ou senha inválidos.");
        }
        return response.text();
      })
      .then(function (message) {
        alert(message);
        // Define o estado de autenticação
        localStorage.setItem("isAuthenticated", "true");
        // Redireciona para o dashboard
        navigate("/dashboard");
      })
      .catch(function (error) {
        setMensagemErro(error.message);
      });
  };

  return React.createElement(
    "div",
    { className: "dashboard-container" },
    React.createElement(
      "div",
      { className: "dashboard-content" },
      React.createElement(
        "div",
        { className: "form-card" },
        React.createElement("h2", null, "Login"),
        React.createElement(
          "form",
          { onSubmit: handleSubmit },
          React.createElement(
            "label",
            { htmlFor: "email" },
            "Email:"
          ),
          React.createElement("input", {
            type: "email",
            id: "email",
            placeholder: "Digite seu email",
            value: email,
            onChange: function (e) {
              setEmail(e.target.value);
            },
            required: true,
          }),
          React.createElement(
            "label",
            { htmlFor: "password" },
            "Senha:"
          ),
          React.createElement("input", {
            type: "password",
            id: "password",
            placeholder: "Digite sua senha",
            value: senha,
            onChange: function (e) {
              setSenha(e.target.value);
            },
            required: true,
          }),
          React.createElement(
            "button",
            { type: "submit" },
            "Entrar"
          )
        ),
        mensagemErro &&
          React.createElement(
            "div",
            { className: "error-message" },
            mensagemErro
          )
      )
    )
  );
};

export default Login;
