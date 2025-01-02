<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="utf-8" />
            <meta http-equiv="X-UA-Compatible" content="IE=edge" />
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
            <meta name="description" content="Hỏi Dân IT - Dự án laptopshop" />
            <meta name="author" content="Hỏi Dân IT" />
            <title>Order</title>

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
                            <h1 class="mt-4">Agency Detail</h1>
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                <li class="breadcrumb-item active">Agency Detail</li>
                            </ol>
                            <div class="mt-5">
                                <div class="row">
                                    <div class="mx-auto">
                                        <div class="form-group row">
                                            <div
                                                class="d-flex justify-content-between mb-3 border-bottom border-4 border-light">
                                                <h2>Agency Information With ID = ${agency.id}
                                                </h2>
                                                <a href="/admin/agency" class="btn btn-success">Back</a>
                                            </div>

                                            <div class="border-top border-light border-2">
                                                <div class="card">
                                                    <div class="card-header">
                                                        Agency Information
                                                    </div>
                                                    <ul class="list-group list-group-flush">
                                                        <li class="list-group-item">Full Name: ${agency.user.fullName}
                                                        </li>
                                                        <li class="list-group-item">Email: ${agency.user.email}</li>
                                                        <li class="list-group-item">Số điện thoại: ${agency.user.phone}
                                                        </li>
                                                        <li class="list-group-item">Địa chỉ: ${user.address}</li>
                                                        <li class="list-group-item">Tên trên chứng chỉ:
                                                            ${agency.brokerCertification.nameOnCertification}</li>
                                                        <li class="list-group-item">Số chứng chỉ hành nghề:
                                                            ${agency.brokerCertification.certificationNumber}</li>
                                                        <li class="list-group-item">Cơ quan cấp chứng chỉ:
                                                            ${agency.brokerCertification.certificationAuthority}</li>
                                                    </ul>
                                                </div>
                                            </div>
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