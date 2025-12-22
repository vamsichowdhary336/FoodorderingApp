function getFoodItems() {
    const restaurantName = document.getElementById("restaurantName").value.trim();
    const foodList = document.getElementById("foodList");

    if (restaurantName === "") {
        alert("Please enter restaurant name");
        return;
    }

    foodList.innerHTML = "Loading...";

    fetch(`http://localhost:8282/api/v1/restaurant/fooditemsList/by-nameOf/${restaurantName}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("No food items found");
            }
            return response.json();
        })
        .then(data => {
            foodList.innerHTML = "";

            if (data.length === 0) {
                foodList.innerHTML = "<p>No food items available</p>";
                return;
            }

            data.forEach(item => {
                foodList.innerHTML += `
                    <div class="food-card">
                        <img src="${item.imageUrl}" alt="${item.name}">
                        <h4>${item.name}</h4>
                        <p class="price">₹ ${item.price}</p>
                    </div>
                `;
            });
        })
        .catch(error => {
            foodList.innerHTML = `<p style="color:red;">${error.message}</p>`;
        });
}
