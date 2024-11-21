<!DOCTYPE html>
<html lang="vn">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

            <head>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="" />
                <meta name="author" content="" />
                <title>Register - RealEstate</title>
                <!-- Google Web Fonts -->
                <link rel="preconnect" href="https://fonts.googleapis.com">
                <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                <link
                    href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
                    rel="stylesheet">

                <!-- Icon Font Stylesheet -->
                <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
                <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
                    rel="stylesheet">

                <!-- Libraries Stylesheet -->
                <link href="/client/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
                <link href="/client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


                <!-- Customized Bootstrap Stylesheet -->
                <link href="/client/css/bootstrap.min.css" rel="stylesheet">

            </head>


            <body class="bg-primary mt-5">
                <div id="layoutAuthentication">
                    <div id="layoutAuthentication_content">
                        <main>
                            <div class="container">
                                <div class="row justify-content-center">
                                    <div class="col-lg-7">
                                        <div class="card shadow-lg border-0 rounded-lg mt-5">
                                            <div class="card-header">
                                                <h3 class="text-center font-weight-light my-4">Create
                                                    Account</h3>
                                            </div>
                                            <div class="card-body">
                                                <form:form action="/register" method="post"
                                                    modelAttribute="registerUser">


                                                    <div class="row mb-3">
                                                        <div class="col-md-6">
                                                            <c:set var="errorFirstName">
                                                                <form:errors path="firstName"
                                                                    cssClass="invalid-feedback" />
                                                            </c:set>
                                                            <div class="form-floating mb-3 mb-md-0">
                                                                <form:input
                                                                    class="form-control ${not empty errorFirstName? 'is-invalid' : ''}"
                                                                    id="inputFirstName" type="text"
                                                                    placeholder="Enter your first name"
                                                                    path="firstName" />
                                                                <label>First name</label>
                                                                ${errorFirstName}
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <div class="form-floating">
                                                                <form:input class="form-control" id="inputLastName"
                                                                    type="text" placeholder="Enter your last name"
                                                                    path="lastName" />
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
                                                                <form:errors path="password"
                                                                    cssClass="invalid-feedback" />
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
                                                                    placeholder="Confirm password"
                                                                    path="confirmPassword" />
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
                            </div>
                        </main>
                    </div>
                </div>
            </body>

</html>