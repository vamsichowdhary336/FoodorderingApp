document.getElementById("orderForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const orderData = {
        restaurantId: document.getElementById("restaurantId").value,
        foodItemId: document.getElementById("foodItemId").value,
        customerName: document.getElementById("customerName").value
    };

    fetch("http://localhost:9090/api/v1/order/addorder", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(orderData)
    })
    .then(res => {
        if (!res.ok) {
            throw new Error("Order failed");
        }
        return res.json();
    })
    .then(data => {
        const msg = document.getElementById("message");
        msg.style.color = "green";
        msg.innerText = "Order placed successfully! Order ID: " + data.id;

        document.getElementById("orderForm").reset();
    })
    .catch(() => {
        const msg = document.getElementById("message");
        msg.style.color = "red";
        msg.innerText = "Error placing order";
    });
});
