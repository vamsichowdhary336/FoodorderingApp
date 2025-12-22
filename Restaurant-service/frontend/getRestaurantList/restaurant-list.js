const API_URL = "http://localhost:8282/api/v1/restaurant/restaurantList";

fetch(API_URL)
    .then(response => response.json())
    .then(data => {
        const container = document.getElementById("restaurantContainer");

        if (data.length === 0) {
            container.innerHTML = "<p>No restaurants found</p>";
            return;
        }

        data.forEach(restaurant => {
            const card = document.createElement("div");
            card.className = "card";

            card.innerHTML = `
                <img src="${restaurant.imageUrl || 'https://via.placeholder.com/300'}">
                <h3>${restaurant.name}</h3>
                <p>${restaurant.location || ''}</p>
                <button class="view-btn"
                    onclick="viewRestaurant('${restaurant.name}')">
                    View Details
                </button>
            `;

            container.appendChild(card);
        });
    });

function viewRestaurant(name) {
    window.location.href =
        "../restaurantDetails/restaurant-details.html?name="
        + encodeURIComponent(name);
}
function goHome() {
    window.location.href = "../home/home.html";
}