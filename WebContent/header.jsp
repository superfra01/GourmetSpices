<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header>
    <a href="<%= request.getContextPath() %>/pages/HomePage.jsp"><img src="<%= request.getContextPath() %>/images/logo.webp" alt="logo" class="logo"></a>
    <nav>
        <ul>
            <li><a class="selected" href="<%= request.getContextPath() %>/pages/HomePage.jsp">Home</a></li>
            <li><a href="<%= request.getContextPath() %>/pages/shop.jsp">Shop</a></li>
            <li><a href="<%= request.getContextPath() %>/pages/about.jsp">About</a></li>
            <li><a href="<%= request.getContextPath() %>/pages/contact.jsp">Contact</a></li>
            <li><a href="<%= request.getContextPath() %>/pages/cart.jsp"><i class="fa fa-shopping-cart fa-lg"></i></a></li>
            <li><a href="<%= request.getContextPath() %>/pages/login.jsp"><i class="fa fa-user fa-lg"></i></a></li>
        </ul>
    </nav>
</header>