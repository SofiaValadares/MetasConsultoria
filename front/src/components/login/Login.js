document.getElementById("loginForm").addEventListener("submit", function (event) {
    event.preventDefault(); // Evita o envio padrão do formulário

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const errorMessage = document.getElementById("errorMessage");

    errorMessage.style.display = "none"; // Esconde mensagens de erro anteriores

    fetch("http://localhost:8080/users/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ email, password }),
    })
        .then((response) => {
            if (!response.ok) {
                throw new Error("Email ou senha inválidos.");
            }
            return response.text();
        })
        .then((message) => {
            alert(message); // Exibe mensagem de sucesso
            window.location.href = "/dashboard"; // Redireciona para o dashboard
        })
        .catch((error) => {
            errorMessage.textContent = error.message;
            errorMessage.style.display = "block";
        });
});
