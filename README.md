# GourmetSpices - E-Commerce Website

This repository contains the source code for a university project developed as part of the TSW course. **GourmetSpices** is a fully functional e-commerce platform designed for purchasing gourmet spices online. The project was developed based on the professor's specifications and features a robust architecture and user-friendly interface.
## Main developers
Developed by [superfra01](https://github.com/superfra01) and [BrunoNestico](https://github.com/BrunoNestico).


## Project Overview

The **GourmetSpices** e-commerce website allows users to browse a catalog of products, manage their shopping cart, and complete orders. The platform also includes a dedicated admin section for managing products and viewing customer orders.

### Key Features

- **Customer Features:**
  - Browse the product catalog and view detailed product information.
  - Add products to the cart, update quantities, or remove items.
  - Empty the cart entirely or proceed to checkout.
  - View past orders and order history.
  
- **Admin Features:**
  - Add, edit, view, and delete products from the catalog.
  - View customer orders by date range and filter orders by customer.

### Additional Highlights

- **Responsive Design:** Optimized for various devices, ensuring a seamless user experience across desktops, tablets, and mobile phones.
- **Form Validation:** Input forms are validated using JavaScript and regular expressions to ensure proper data entry.
- **AJAX Integration:** Asynchronous server communication enhances user experience by providing faster and more dynamic updates.
- **Session Management:** Persistent user sessions keep track of shopping carts and user interactions during their visit.

## Technical Details

### Architecture
The project follows the **Model-View-Controller (MVC)** architectural pattern, ensuring a clean separation of concerns between the business logic, user interface, and control logic.

### Technologies Used
- **Backend:** Java (JSP for dynamic content, Servlets for control logic).
- **Frontend:** JavaScript for interactivity and form validation, CSS for styling.
- **AJAX:** Used for asynchronous communication with the server, enhancing responsiveness.
- **Database:** Configurable via JDBC for persistent data storage.
- **Version Control:** Git for tracking project changes and collaboration.

### Directory Structure
- `src/control`: Servlet classes for handling requests and controlling logic.
- `src/model`: Classes representing the business logic and data models.
- `WebContent/scripts`: Client-side JavaScript files for dynamic behaviors.
- `WebContent/images`: Static images for the website.
- `WebContent/css`: External stylesheets for the user interface.
- `WebContent`: Contains JSP files for dynamic HTML generation.

## Setup Instructions

1. **Clone the Repository:**
   ```sh
   git clone https://github.com/superfra01/GourmetSpices.git
