const API_URL = "http://localhost:8282/api/v1/restaurant/restaurantList";

fetch(API_URL)
    .then(response => response.json())
    .then(data => {
        const container = document.getElementById("restaurantContainer");

        if (!data || data.length === 0) {
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
                    onclick="viewRestaurant('${restaurant.name}', ${restaurant.id})">
                    View Details
                </button>
            `;

            container.appendChild(card);
        });
    })
    .catch(err => console.error("Restaurant list fetch error:", err));

// ---------------- VIEW RESTAURANT ----------------
function viewRestaurant(name, id) {
    // 🔴 REQUIRED FOR CART FEATURE
    localStorage.setItem("restaurantId", id);

    window.location.href =
        "../restaurantDetails/restaurant-details.html?name="
        + encodeURIComponent(name);
}

// ---------------- BACK TO HOME ----------------
function goHome() {
    const role = localStorage.getItem("role");

    if (role === "ADMIN") {
        window.location.href = "../adminHome/admin-home.html";
    } else {
        window.location.href = "../customerHome/customer-home.html";
    }
}
