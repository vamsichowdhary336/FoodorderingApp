document.getElementById("foodForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const submitBtn = document.querySelector("#foodForm button[type='submit']");
    submitBtn.disabled = true;
    submitBtn.innerText = "Adding...";

    const foodData = {
        name: document.getElementById("name").value,
        price: document.getElementById("price").value,
        imageUrl: document.getElementById("imageUrl").value,
        restaurantName: document.getElementById("restaurantName").value
    };

    fetch("http://localhost:8282/api/v1/restaurant/addItems", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(foodData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Failed to add food item");
        }
        return response.text();
    })
    .then(data => {
        const msg = document.getElementById("message");
        msg.style.color = "green";
        msg.innerText = data;

        document.getElementById("foodForm").reset();

        // ✅ Redirect to RESTAURANT MENU
        setTimeout(() => {
  window.location.href =
    "/restaurant_service/restaurantDetails/restaurant-details.html?name="
    + encodeURIComponent(foodData.restaurantName);
}, 1500);
    })
    .catch(error => {
        const msg = document.getElementById("message");
        msg.style.color = "red";
        msg.innerText = "Error adding food item";

        console.error(error);

        submitBtn.disabled = false;
        submitBtn.innerText = "➕ Add Food Item";
    });
});

// Home button
function goHome() {
    window.location.href = "../home/home.html";
}
