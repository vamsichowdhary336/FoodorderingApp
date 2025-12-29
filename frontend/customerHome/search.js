function search() {
  const q = document.getElementById("searchInput").value.trim();
  const results = document.getElementById("results");
  results.innerHTML = "";

  if (!q) return;

  // 1️⃣ FOOD SEARCH
  fetch(`http://localhost:8282/api/v1/restaurant/by-item/${q}`)
    .then(res => res.ok ? res.json() : [])
    .then(foodList => {
      if (Array.isArray(foodList) && foodList.length > 0) {
        foodList.forEach(item => {
          results.innerHTML += `
            <div class="card" onclick="openRestaurant('${item.restaurantName}')">
              <img src="${item.imageUrl || '../images/food.jpg'}">
              <h3>${item.name}</h3>
<p>₹ ${item.price}</p>
<p>${item.restaurantName}</p>
<p>${item.restaurantLocation}</p>
            </div>
          `;
        });
      } else {
        searchRestaurant(q);
      }
    })
    .catch(() => searchRestaurant(q));
}

function searchRestaurant(name) {
  fetch(`http://localhost:8282/api/v1/restaurant/by-name/${name}`)
    .then(res => {
      if (!res.ok) throw new Error();
      return res.json();
    })
    .then(data => {
      document.getElementById("results").innerHTML = `
        <div class="card" onclick="openRestaurant('${data.name}')">
          <img src="${data.imageUrl || '../images/kfc.jpg'}">
          <h3>${data.name}</h3>
          <p>${data.location}</p>
        </div>
      `;
    })
    .catch(() => {
      document.getElementById("results").innerHTML =
        "<p style='color:white;text-align:center'>No results found</p>";
    });
}

function openRestaurant(name) {
  window.location.href =
    `../restaurantDetails/restaurant-details.html?name=${encodeURIComponent(name)}`;
}
function goBack() {
    window.location.href = "../customerHome/customer-home.html";
}