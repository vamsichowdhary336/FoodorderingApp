document.getElementById("registerForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const data = {
        name: document.getElementById("name").value.trim(),
        username: document.getElementById("username").value.trim(),
        email: document.getElementById("email").value.trim(),
        mobile: document.getElementById("mobile").value.trim(),
        password: document.getElementById("password").value.trim(),
        address: document.getElementById("address").value.trim(),
        role: document.getElementById("role").value.toUpperCase()
    };

    const message = document.getElementById("message");

    // Validation
    for (let key in data) {
        if (!data[key]) {
            showMessage("Please fill all fields", "red");
            return;
        }
    }

    fetch("http://localhost:8282/api/v1/user/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(res => {
        if (!res.ok) throw new Error();
        return res.json();
    })
    .then(() => {
        showMessage("Registration successful!", "green");
        document.getElementById("registerForm").reset();
    })
    .catch(() => {
        showMessage("Registration failed. Try again.", "red");
    });
});

function showMessage(text, color) {
    const message = document.getElementById("message");
    message.textContent = text;
    message.style.color = color;
}

function togglePassword() {
    const pwd = document.getElementById("password");
    pwd.type = pwd.type === "password" ? "text" : "password";
}
