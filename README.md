# 🍔 QuickBite – Food Ordering Microservices Application

A scalable food ordering application built using **Spring Boot microservices**, enabling customers to browse restaurants, order food, and track orders with a clean REST-based architecture.

---

## 📌 Project Description

QuickBite is a food ordering system designed to demonstrate real-world **microservices architecture** using Spring Boot.  
It allows customers to view restaurants and food items, place orders, and track order details, while admins manage restaurants and menus.  
The project focuses on clean API design, service-to-service communication, and modular backend development.

---

## 🚀 Features

- User login (Admin & Customer)
- Restaurant management (Add / View restaurants)
- Food item management per restaurant
- Search restaurants by food item name
- Order placement and order tracking
- Microservices communication using Feign Client
- Image support via static URLs
- RESTful API design

---

## 🛠 Tech Stack

**Backend**
- Java
- Spring Boot
- Spring Data JPA
- Spring Cloud OpenFeign

**Frontend**
- HTML
- CSS
- JavaScript

**Database**
- MySQL

**Architecture**
- REST APIs
- Microservices

**Tools**
- Git & GitHub
- Postman
- Eclipse

---

## 🧩 Architecture / Modules

- **Restaurant Service**
  - Manage restaurants and food items
  - Search by food item name

- **Order Service**
  - Place orders
  - Fetch order details
  - Communicates with Restaurant Service via Feign Client

- **Frontend UI**
  - Admin and Customer interfaces
  - Restaurant listing and order flow

---

## 🔗 Sample API Endpoints

**Restaurant Service**
```http
POST   /api/v1/restaurant/addRestaurant
POST   /api/v1/restaurant/addItems
GET    /api/v1/restaurant/restaurantList
GET    /api/v1/restaurant/item/{name}
```
**Order Service**
```http
POST   /api/v1/order/addorder
GET    /api/v1/order/getorderDetails/{customerName}
```
## 📸 Screenshots
## 🔐 User Login Page
<img width="1390" height="851" alt="Screenshot 2025-12-30 121440" src="https://github.com/user-attachments/assets/31735163-e604-4e9e-8c1b-52fa50b1ba5d" /><br>
## 👤 User Dashboard
<img width="1390" height="857" alt="Screenshot 2025-12-30 121514" src="https://github.com/user-attachments/assets/029f87bc-bbe9-478a-a60f-d6340666bdbf" /><br>
## 🍽 Restaurant List
<img width="1888" height="898" alt="Screenshot 2025-12-30 121539" src="https://github.com/user-attachments/assets/53564527-4e75-48c2-96c3-4b6ca648f77d" /><br>
## 🔍 Search Page
<img width="1129" height="898" alt="Screenshot 2025-12-30 123423" src="https://github.com/user-attachments/assets/736b2a24-b758-41bd-97ae-2f6f6992153e" /><br>
## 📋 Restaurant Menu
<img width="1884" height="894" alt="Screenshot 2025-12-30 121558" src="https://github.com/user-attachments/assets/3fd31063-2035-4acf-b0d9-1a5333a03daa" /><br>
## 🛒 User Cart
<img width="1687" height="901" alt="Screenshot 2025-12-30 121634" src="https://github.com/user-attachments/assets/8bb61c11-528d-4b8b-9b46-2c9d865379a7" /><br>
## 🛠 Admin Home
<img width="1364" height="876" alt="Screenshot 2025-12-30 124123" src="https://github.com/user-attachments/assets/0e9675f8-1ebf-4f5b-a36d-6bfca3f307e1" /><br>
## ➕ Add Restaurant Page
<img width="1331" height="867" alt="Screenshot 2025-12-30 124136" src="https://github.com/user-attachments/assets/9e419476-02e3-4031-b55e-95faa769f1df" /><br>
## 🍔 Add Food Item Page<img width="1880" height="834" alt="Screenshot 2025-12-30 124337" src="https://github.com/user-attachments/assets/90e8ce55-961e-4460-8a67-295154cc7596" />







