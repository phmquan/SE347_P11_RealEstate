# SE347_P11_RealEstate
A School WEB MVC Spring project

# Real Estate Transaction Platform

## Overview
This project is a real estate transaction platform that supports three main actors: **Admin**, **Agency**, and **User**. The platform enables users to search for properties, agencies to manage property listings, and admins to oversee platform operations. Below is a description of the technologies and tools used in this project.

---

## **Technologies Used**

### **1. Backend**
**Framework:** Spring MVC (Model-View-Controller)
- Used for developing the backend logic and APIs.
- Manages the business logic, routing, and interaction with the database.
- Provides a RESTful API for client-side interactions.

**Language:** Java
- The primary programming language for backend development.

**Database:** MySQL
- Stores user data, agency data, property listings, and administrative records.
- Relational database management system ensuring data consistency and reliability.

**ORM Tool:** Hibernate (part of Spring Data JPA)
- Simplifies database operations with object-relational mapping.

### **2. Frontend**
**HTML**
- Structures the content of web pages.

**CSS**
- Styles and formats the frontend elements for a visually appealing interface.

**Bootstrap**
- Provides a responsive and mobile-first design framework.
- Includes pre-designed components and grid systems for rapid development.

**JavaScript**
- Adds interactivity and dynamic behavior to the web pages.

**jQuery**
- Simplifies DOM manipulation, event handling, and AJAX requests.

**AJAX**
- Enables asynchronous data loading without refreshing the page.
- Used for real-time updates in search results, property listings, and admin approvals.



## **Features by Actor**

### **Admin**
- Manage property listings (approve/reject).
- Activate or deactivate agency accounts.
- Generate statistical reports.

### **Agency**
- Create, edit, and manage property listings.
- View the status of listings (approved/rejected/pending).

### **User**
- Search properties by location, price, and type.
- View property details and contact agencies.

---

## **Getting Started**

### **1. Prerequisites**
- Java 8 or higher
- MySQL 5.7 or higher
- Apache Maven (for dependency management)
- Node.js and npm (optional, for managing frontend assets)

### **2. Setup**
1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```

2. Configure the database:
   - Create a MySQL database named `real_estate`.
   - Update the database credentials in `application.properties`.


4. Run the application with gradle build:
   

5. Access the application in your browser:
   ```
   http://localhost:8080
   ```

---

## **Folder Structure**
- `src/main/java`: Contains Java files for backend logic.
- `src/main/resources`: Contains configuration files and static assets.
- `src/main/webapp`: Contains HTML, CSS, and JavaScript files for the frontend.

---

## **Contributing**
1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Submit a pull request with a detailed description of changes.

---

## **License**
This project is licensed under the MIT License.


