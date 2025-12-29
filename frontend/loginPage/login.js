document.getElementById("loginForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();
    const message = document.getElementById("message");

    fetch("http://localhost:8282/api/v1/user/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
    })
    .then(res => res.json())
    .then(data => {
        localStorage.setItem("username", username);
        localStorage.setItem("role", data.role);

        if (data.role === "CUSTOMER") {
            window.location.href = "../customerHome/customer-home.html";
        } else {
            window.location.href = "../adminHome/admin-home.html";
        }
    })
    .catch(() => alert("Login failed"));
});
