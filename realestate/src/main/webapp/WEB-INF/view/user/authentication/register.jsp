<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

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
                                <div class="card shadow-lg border-0 rounded-lg mt-5 mb-5" style=" max-width: 600px;">
                                    <div class="card-header">
                                        <h3 class="text-center font-weight-light my-4">Create
                                            Account</h3>
                                    </div>
                                    <div class="card-body">
                                        <form:form action="/register" method="post" modelAttribute="registerUser">


                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <c:set var="errorFirstName">
                                                        <form:errors path="firstName" cssClass="invalid-feedback" />
                                                    </c:set>
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <form:input
                                                            class="form-control ${not empty errorFirstName? 'is-invalid' : ''}"
                                                            id="inputFirstName" type="text"
                                                            placeholder="Enter your first name" path="firstName" />
                                                        <label>First name</label>
                                                        ${errorFirstName}
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-floating">
                                                        <form:input class="form-control" id="inputLastName" type="text"
                                                            placeholder="Enter your last name" path="lastName" />
                                                        <label>Last name</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <c:set var="errorEmail">
                                                    <form:errors path="email" cssClass="invalid-feedback" />
                                                </c:set>
                                                <form:input
                                                    class="form-control ${not empty errorEmail ? 'is-invalid':''}"
                                                    id="inputEmail" type="email" placeholder="name@example.com"
                                                    path="email" />
                                                <label>Email address</label>
                                                ${errorEmail}
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <c:set var="errorPasswordLength">
                                                        <form:errors path="password" cssClass="invalid-feedback" />
                                                    </c:set>
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <form:input
                                                            class="form-control ${not empty errorPasswordLength ? 'is-invalid':''}"
                                                            id="inputPassword" type="password"
                                                            placeholder="Create a password" path="password" />
                                                        <label>Password</label>
                                                        ${errorPasswordLength}
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <c:set var="errorConfirmPassword">
                                                        <form:errors path="confirmPassword"
                                                            cssClass="invalid-feedback" />
                                                    </c:set>
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <form:input
                                                            class="form-control ${not empty errorConfirmPassword ? 'is-invalid':''}"
                                                            id="inputPasswordConfirm" type="password"
                                                            placeholder="Confirm password" path="confirmPassword" />
                                                        <label>Confirm
                                                            Password</label>
                                                        ${errorConfirmPassword}
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="mt-4 mb-0">
                                                <div class="d-grid"><button type="submit"
                                                        class="btn btn-primary btn-block">Create
                                                        Account</a>
                                                </div>
                                            </div>
                                        </form:form>
                                    </div>
                                    <div class="card-footer text-center py-3">
                                        <div class="small"><a href="/login">Have an account?
                                                Go to
                                                login</a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <jsp:include page="../layout/footer.jsp" />
                </body>

</html>