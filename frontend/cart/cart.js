const customerName = localStorage.getItem("username");

fetch(`http://localhost:9090/api/v1/cart/view/${customerName}`)
    .then(res => res.json())
    .then(data => {
        const container = document.getElementById("cartContainer");
        container.innerHTML = "";

        let total = 0; // ✅ NEW (safe)

        if (!data || data.length === 0) {
            container.innerHTML = "<p style='color:white;text-align:center'>Cart is empty</p>";
            document.getElementById("totalAmount").innerText = 0; // ✅ NEW
            return;
        }

        data.forEach(item => {
            total += item.price * item.quantity; // ✅ NEW

            const card = document.createElement("div");
            card.className = "cart-card";

            card.innerHTML = `
                <img src="${item.imageUrl || '/images/default-food.jpg'}">
                <h4>${item.foodItemName}</h4>
                <p>₹ ${item.price}</p>

                <div class="qty-box">
                    <button class="qty-btn" 
                        onclick="changeQty(${item.id}, ${item.quantity - 1})"
                        ${item.quantity <= 1 ? "disabled" : ""}>
                        −
                    </button>

                    <span class="qty-num">${item.quantity}</span>

                    <button class="qty-btn"
                        onclick="changeQty(${item.id}, ${item.quantity + 1})">
                        +
                    </button>
                </div>

                <button class="remove-btn" onclick="removeItem(${item.id})">
                    REMOVE
                </button>
            `;

            container.appendChild(card);
        });

        document.getElementById("totalAmount").innerText = total; // ✅ NEW
    })
    .catch(err => {
        console.error(err);
        alert("Error loading cart");
    });

function changeQty(cartId, qty) {
    if (qty < 1) return;

    fetch(`http://localhost:9090/api/v1/cart/update/${cartId}/${qty}`, {
        method: "PUT"
    })
    .then(() => location.reload())
    .catch(err => console.error(err));
}

function removeItem(cartId) {
    fetch(`http://localhost:9090/api/v1/cart/remove/${cartId}`, {
        method: "DELETE"
    })
    .then(res => res.text())
    .then(msg => {
        alert(msg);
        location.reload();
    })
    .catch(err => console.error(err));
}

function goBack() {
    window.history.back();
}

 function placeOrder() {

    const customerName = localStorage.getItem("username");

    // 1️⃣ Get address from input
    const addressInput = document.getElementById("address");
    const address = addressInput ? addressInput.value.trim() : "";

    // 🚫 Address required
    if (!address) {
        alert("❗ Please enter delivery address");
        return;
    }

    // 2️⃣ Get cart items
    fetch(`http://localhost:9090/api/v1/cart/view/${customerName}`)
        .then(res => res.json())
        .then(cartItems => {

            // 🚫 Cart empty check
            if (!cartItems || cartItems.length === 0) {
                alert("Cart is empty");
                return;
            }

            // 3️⃣ Place order for each cart item
            Promise.all(
                cartItems.map(item =>
                    fetch("http://localhost:9090/api/v1/order/addorder", {
                        method: "POST",
                        headers: { "Content-Type": "application/json" },
                        body: JSON.stringify({
                            restaurantId: item.restaurantId,
                            foodItemId: item.foodItemId,
                            customerName: customerName,
                            address: address
                        })
                    }).then(res => res.json())
                )
            )
            .then(orders => {

                const orderId = orders[0]?.id;

                // 4️⃣ Clear cart AFTER successful order
                fetch(`http://localhost:9090/api/v1/cart/clear/${customerName}`, {
                    method: "DELETE"
                })
                .then(() => {
                    alert(
                        orderId
                            ? "Order placed successfully ✅\nCheck Order Details page"
                            : "Order placed successfully ✅"
                    );
                    location.reload();
                });
            });

        })
        .catch(err => {
            console.error(err);
            alert("Order failed ❌");
        });
}
