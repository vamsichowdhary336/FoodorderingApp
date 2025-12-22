function searchItem() {
    const itemName = document.getElementById("itemName").value.trim();
    const results = document.getElementById("results");
    const message = document.getElementById("message");

    results.innerHTML = "";
    message.innerText = "";

    if (itemName === "") {
        message.innerText = "Please enter a food item name";
        return;
    }

    fetch(`http://localhost:8282/api/v1/restaurant/by-item/${itemName}`)
        .then(res => res.json())
        .then(data => {
            if (data.length === 0) {
                message.innerText = "No restaurants found";
                return;
            }

            data.forEach(r => {
                const div = document.createElement("div");
                div.className = "restaurant";
                div.innerHTML = `
                    <strong>${r.restaurantName}</strong><br>
                    ${r.foodName} - ₹${r.price}
                `;
                results.appendChild(div);
            });
        })
        .catch(() => {
            message.innerText = "Error fetching data";
        });
}
