document.addEventListener("DOMContentLoaded", () => {

    const customerName = localStorage.getItem("username");

    if (!customerName) {
        alert("Please login again");
        window.location.href = "../loginPage/login.html";
        return;
    }

    fetch(`http://localhost:9090/api/v1/order/latest/${customerName}`)
        .then(res => {
            if (!res.ok) throw new Error();
            return res.json();
        })
        .then(data => {
            document.getElementById("orderCard").style.display = "block";
            document.getElementById("orderId").innerText = data.orderId;
            document.getElementById("restaurantName").innerText = data.restaurantName;
            document.getElementById("foodItemName").innerText = data.foodItemName;
            document.getElementById("customerName").innerText = data.customerName;
            document.getElementById("address").innerText = data.address;
        })
        .catch(() => alert("No orders found"));
});

function goBack() {
    window.location.href = "../customerHome/customer-home.html";
}

