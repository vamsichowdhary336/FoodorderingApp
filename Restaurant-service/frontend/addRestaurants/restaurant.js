document.getElementById("restaurantForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const restaurantData = {
        name: document.getElementById("name").value,
        location: document.getElementById("location").value,
        imageUrl: document.getElementById("imageUrl").value
    };

    fetch("http://localhost:8282/api/v1/restaurant/addRestaurant", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(restaurantData)
    })
    .then(res => res.text())
    .then(() => {
        const msg = document.getElementById("message");
        msg.style.color = "green";
        msg.innerText = "Restaurant added successfully";

        setTimeout(() => {
            window.location.href = "../getRestaurantList/restaurant-list.html";
        }, 1500);
    })
    .catch(() => {
        document.getElementById("message").innerText = "Error adding restaurant";
    });
});

// Home button
function goHome() {
    window.location.href = "../home/home.html";
}
