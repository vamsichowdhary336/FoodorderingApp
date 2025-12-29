function goToSearch() {
    window.location.href = "search.html";
}


function placeOrder() {
    window.location.href = "../getRestaurantList/restaurant-list.html";
}

function viewOrders() {
    window.location.href = "../getOrderDetails/order-details.html";
}
function logout() {
    // optional: clear login data
    localStorage.clear();
    window.location.href = "../loginPage/login.html";
}