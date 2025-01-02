<%@ page language="java" contentType="text/html; charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container">
                    <a class="navbar-brand" href="#">Estatery</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav me-auto">
                            <li class="nav-item">
                                <a class="nav-link active" href="user/listing?type=rent">Cho Thuê</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="user/listing?type=sell">Mua-bán</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="user/agency/register">Đăng ký tài khoản môi giới</a>
                            </li>
                        </ul>
                        <div class="d-flex m-3 me-0">
                            <c:if test="${not empty pageContext.request.userPrincipal}">
                                <!-- Avatar Dropdown -->
                                <div class="dropdown">

                                    <!-- Navbar-->
                                    <ul class="navbar-nav">
                                        <li class="nav-item dropdown">
                                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
                                                role="button" data-bs-toggle="dropdown" aria-expanded="false"><i
                                                    class="fas fa-user fa-fw"></i></a>
                                            <ul class="dropdown-menu dropdown-menu-end"
                                                aria-labelledby="navbarDropdown">
                                                <li><a class="dropdown-item" href="#!">Settings</a></li>

                                                <li>
                                                    <hr class="dropdown-divider" />
                                                </li>
                                                <li>
                                                    <form method="post" action="/logout">
                                                        <input type="hidden" name="${_csrf.parameterName}"
                                                            value="${_csrf.token}" />
                                                        <button class="dropdown-item">Logout</button>
                                                    </form>

                                                </li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                            </c:if>
                            <c:if test="${empty pageContext.request.userPrincipal}">
                                <div class="d-flex">
                                    <a href="/login" class="btn btn-outline-primary me-2" type="button">Login</a>
                                    <a href="/register" class="btn btn-primary" type="button">Sign
                                        Up</a>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </nav>