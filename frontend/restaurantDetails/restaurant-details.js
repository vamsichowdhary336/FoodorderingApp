// ✅ ROLE-BASED UI CONTROL (CODE RED RULE)
document.addEventListener("DOMContentLoaded", () => {
    const role = localStorage.getItem("role"); // ADMIN / CUSTOMER

    const addBtn = document.getElementById("addFoodBtn");
    const cartBtn = document.getElementById("viewCartBtn");

    // Admin → can add food
    if (role !== "ADMIN" && addBtn) {
        addBtn.style.display = "none";
    }

    // Customer → can view cart
    if (role !== "CUSTOMER" && cartBtn) {
        cartBtn.style.display = "none";
    }
});


const params = new URLSearchParams(window.location.search);
const restaurantName = params.get("name");

// ---------------- RESTAURANT DETAILS ----------------
fetch(`http://localhost:8282/api/v1/restaurant/by-name/${restaurantName}`)
    .then(res => res.json())
    .then(data => {
        document.getElementById("restaurantName").innerText = data.name;
        document.getElementById("restaurantLocation").innerText = data.location;
        document.getElementById("restaurantImage").src =
            data.imageUrl || "https://via.placeholder.com/800x400";
    })
    .catch(err => console.error("Restaurant fetch error:", err));

// ---------------- FOOD ITEMS LIST ----------------
fetch(`http://localhost:8282/api/v1/restaurant/fooditemsList/by-nameOf/${restaurantName}`)
    .then(res => res.json())
    .then(data => {
        const container = document.getElementById("menuContainer");
        const role = localStorage.getItem("role");

        if (!data || data.length === 0) {
            container.innerHTML =
                "<p style='text-align:center;color:white;'>No food items found</p>";
            return;
        }

        data.forEach(item => {
            const card = document.createElement("div");
            card.className = "card";

            card.innerHTML = `
    <img src="${item.imageUrl || 'https://via.placeholder.com/200'}">
    <h4>${item.name}</h4>
    <p>₹ ${item.price}</p>

    ${
    role === "CUSTOMER"
        ? `<button class="add-item-btn"
             onclick='addToCart(${JSON.stringify(item)})'>➕ ADD</button>`
        : ""
}
`;
            container.appendChild(card);
        });
    })
    .catch(err => console.error("Food items fetch error:", err));

// ---------------- ACTION FUNCTIONS ----------------
function goToAddFood() {
    window.location.href =
        "../addFoodItems/add-food-item.html?restaurantName=" +
        encodeURIComponent(restaurantName);
}

function goBack() {
    window.location.href = "../getRestaurantList/restaurant-list.html";
}

// ---------------- ADD TO CART ----------------
function addToCart(item) {
    const restaurantId = localStorage.getItem("restaurantId");
    const customerName = localStorage.getItem("username");

    if (!restaurantId || !customerName) {
        alert("Please login again");
        return;
    }

    fetch("http://localhost:9090/api/v1/cart/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            customerName: customerName,
            restaurantId: restaurantId,
            foodItemId: item.id,
            foodItemName: item.name,
            price: item.price,
            imageUrl: item.imageUrl
        })
    })
    .then(res => res.text())
    .then(msg => alert(msg))
    .catch(err => {
        console.error(err);
        alert("Error adding item to cart");
    });
}

function goToCart() {
    window.location.href = "../cart/cart.html";
}
