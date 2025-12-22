const params = new URLSearchParams(window.location.search);
const restaurantName = params.get("name");

// Restaurant details
fetch(`http://localhost:8282/api/v1/restaurant/by-name/${restaurantName}`)
    .then(res => res.json())
    .then(data => {
        document.getElementById("restaurantName").innerText = data.name;
        document.getElementById("restaurantLocation").innerText = data.location;
        document.getElementById("restaurantImage").src =
            data.imageUrl || "https://via.placeholder.com/800x400";
    });

// Food items
fetch(`http://localhost:8282/api/v1/restaurant/fooditemsList/by-nameOf/${restaurantName}`)
    .then(res => res.json())
    .then(data => {
        const container = document.getElementById("menuContainer");

        if (data.length === 0) {
            container.innerHTML = "<p style='text-align:center;'>No food items found</p>";
            return;
        }

        data.forEach(item => {
            const card = document.createElement("div");
            card.className = "card";
            card.innerHTML = `
                <img src="${item.imageUrl || 'https://via.placeholder.com/200'}">
                <h4>${item.name}</h4>
                <p>₹ ${item.price}</p>
            `;
            container.appendChild(card);
        });
    });

    function goToAddFood() {
    window.location.href =
"../addFoodItems/add-food-item.html?restaurantName=" + encodeURIComponent(restaurantName);
}

function goBack() {
    window.location.href = "../getRestaurantList/restaurant-list.html";
}
