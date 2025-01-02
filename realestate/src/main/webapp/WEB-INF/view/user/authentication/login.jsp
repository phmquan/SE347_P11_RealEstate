<!DOCTYPE html>
<html lang="en">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Estatery</title>
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
                        rel="stylesheet">
                    <link rel="stylesheet" href="/css/user/headbar.css">

                    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
                    <!-- Bootstrap CSS -->
                    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
                        rel="stylesheet">

                    <!-- jQuery and Popper.js -->
                    <script
                        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

                </head>


                <body>
                    <jsp:include page="../layout/header.jsp" />

                    <div class="container ">


                        <div class="container-sm mt-2 mb-2">
                            <div class="row justify-content-center">
                                <!-- Main Content -->
                                <div class="card shadow-lg border-0 rounded-lg mt-5 mb-5 justify-content-center"
                                    style=" max-width: 600px;">
                                    <div class="card-header">
                                        <h3 class="text-center font-weight-light my-4">Login</h3>
                                    </div>
                                    <div class="card-body justify-content-center">
                                        <form method="post" action="/login">
                                            <c:if test="${param.error != null}">
                                                <div class="my-2" style="color: red;">Invalid email or password.
                                                </div>
                                            </c:if>
                                            <c:if test="${param.logout != null}">
                                                <div class="my-2" style="color: green;">Logout Success.
                                                </div>
                                            </c:if>

                                            <div class="form-floating mb-3">
                                                <input class="form-control" type="email" placeholder="name@example.com"
                                                    name="username" />
                                                <label>Email address</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" type="password" placeholder="Password"
                                                    name="password" />
                                                <label>Password</label>
                                            </div>
                                            <div>
                                                <input type="hidden" name="${_csrf.parameterName}"
                                                    value="${_csrf.token}" />
                                            </div>

                                            <div class="mt-4 mb-0">
                                                <div class="d-grid">
                                                    <button class="btn btn-primary btn-block">
                                                        Login
                                                    </button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="card-footer text-center py-3">
                                        <div class="small"><a href="/register">Need an account? Sign up!</a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <jsp:include page="../layout/footer.jsp" />
                </body>

</html>