<html lang="en">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ page language="java" contentType="text/html; charset=UTF-8" %>

                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Document</title>
                    <!-- Latest compiled and minified CSS -->
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
                        rel="stylesheet">
                    <!-- Latest compiled JavaScript -->
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css"
                        rel="stylesheet" />
                    <link href="/css/styles.css" rel="stylesheet" />
                    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
                        crossorigin="anonymous"></script>
                </head>

                <body class="sb-nav-fixed">
                    <jsp:include page="../layout/header.jsp" />
                    <div id="layoutSidenav">
                        <jsp:include page="../layout/sidebar.jsp" />
                        <div id="layoutSidenav_content">
                            <main>
                                <div class="container-fluid px-4">
                                    <h1 class="mt-4">Manage Agency</h1>
                                    <ol class="breadcrumb mb-4">
                                        <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                        <li class="breadcrumb-item active">Listing</li>
                                    </ol>

                                    <div class="mt-5">
                                        <div class="row">
                                            <div class="mx-auto">


                                                <div class="my-3">
                                                    <table class="table table-bordered table-hover">
                                                        <thead>
                                                            <tr>
                                                                <th scope="col">#</th>
                                                                <th scope="col">Tiêu đề bài đăng</th>
                                                                <th scope="col">Loại hình BĐS</th>
                                                                <th scope="col">Diện tích</th>
                                                                <th scope="col">Giá bán</th>
                                                                <th scope="col">Giấy tờ pháp lý</th>
                                                                <th scope="col">Tên Môi Giới</th>
                                                                <th scope="col">Trạng thái bài đăng</th>
                                                                <th scope="col">Action</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="listing" items="${listings.content}">
                                                                <tr>
                                                                    <td>${listing.id}</td>
                                                                    <td>${listing.listingTitle}</td>
                                                                    <td>${listing.propertyType}</td>
                                                                    <td>${listing.property.propertyArea} m2</td>

                                                                    <td>
                                                                        <fmt:formatNumber
                                                                            value="${listing.property.propertyPrice}"
                                                                            type="currency" currencySymbol="đ "
                                                                            maxFractionDigits="0"
                                                                            minFractionDigits="0" />
                                                                    </td>
                                                                    <td>${listing.property.legalDocument}</td>
                                                                    </td>


                                                                    <td>
                                                                        ${listing.agency.user.fullName}
                                                                    </td>
                                                                    <td>
                                                                        <span class="badge bg-warning">Pending</span>
                                                                    </td>
                                                                    <td>

                                                                        <a href="/admin/listing/${id}"
                                                                            class="btn btn-success">View</a>
                                                                        <!-- Duyệt Form -->
                                                                        <form:form
                                                                            action="/admin/listing/accept/${listing.id}"
                                                                            method="post">
                                                                            <input type="hidden" name="status"
                                                                                value="true" />
                                                                            <button type="submit"
                                                                                class="btn btn-warning">Duyệt</button>
                                                                        </form:form>

                                                                        <!-- Từ Chối Form -->
                                                                        <form:form
                                                                            action="/admin/listing/accept/${listing.id}"
                                                                            method="post">
                                                                            <input type="hidden" name="status"
                                                                                value="false" />
                                                                            <button type="submit"
                                                                                class="btn btn-danger">Từ Chối</button>
                                                                        </form:form>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </main>

                        </div>
                    </div>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                        crossorigin="anonymous"></script>
                    <script src="js/scripts.js"></script>

                </body>

</html>