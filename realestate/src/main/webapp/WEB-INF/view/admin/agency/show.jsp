<html lang="en">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Document</title>
            <!-- Latest compiled and minified CSS -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <!-- Latest compiled JavaScript -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
            <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
            <link href="/css/styles.css" rel="stylesheet" />
            <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
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
                                <li class="breadcrumb-item active">Agency</li>
                            </ol>
                            <div>Agency Table</div>
                            <div class="mt-5">
                                <div class="row">
                                    <div class="mx-auto">
                                        <div class="border-bottom border-2 border-primary">
                                            <div class="d-flex justify-content-between">

                                                <a href="/admin/agency/create" class="btn btn-primary mb-1">Create
                                                    Agency</a>
                                            </div>
                                        </div>

                                        <div class="my-3">
                                            <table class="table table-bordered table-hover">
                                                <thead>
                                                    <tr>
                                                        <th scope="col">#</th>
                                                        <th scope="col">Full Name</th>
                                                        <th scope="col">Address</th>
                                                        <th scope="col">Phone</th>
                                                        <th scope="col">Email</th>
                                                        <th scope="col">Certification Number</th>
                                                        <th scope="col">Account Status</th>
                                                        <th scope="col">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="agency" items="${agencies}">
                                                        <tr>
                                                            <td>${agency.user.id}</td>
                                                            <td>${agency.user.fullName}</td>
                                                            <td>${agency.user.address}</td>
                                                            <td>${agency.user.phone}</td>

                                                            <td>${agency.user.email}</td>
                                                            <td>${agency.brokerCertification.certificationNumber}</td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${agency.activationStatus eq 'true'}">
                                                                        <span class="badge bg-success">Active</span>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <span class="badge bg-danger">Inactive</span>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>


                                                            <td>

                                                                <a href="/admin/agency/${id}"
                                                                    class="btn btn-success">View</a>
                                                                <a href="/admin/agency/update/${id}"
                                                                    class="btn btn-warning">Update</a>
                                                                <a href="/admin/agency/delete/${id}"
                                                                    class="btn btn-danger">
                                                                    Delete
                                                                </a>
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