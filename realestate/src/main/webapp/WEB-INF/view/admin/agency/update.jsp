<html lang="en">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

            <head>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="Hỏi Dân IT - Dự án laptopshop" />
                <meta name="author" content="Hỏi Dân IT" />
                <title>Create User</title>

                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                <script>
                    $(document).ready(() => {
                        const avatarFile = $("#avatarFile");
                        avatarFile.change(function (e) {
                            const imgURL = URL.createObjectURL(e.target.files[0]);
                            $("#avatarPreview").attr("src", imgURL);
                            $("#avatarPreview").css({ "display": "block" });
                        });
                    });
                </script>
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
            </head>

            <body class="sb-nav-fixed">
                <jsp:include page="../layout/header.jsp" />
                <div id="layoutSidenav">
                    <jsp:include page="../layout/sidebar.jsp" />
                    <div id="layoutSidenav_content">
                        <main>
                            <div class="container-fluid px-4">
                                <h1 class="mt-4">Create Agency</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                    <li class="breadcrumb-item active">Create Agency</li>
                                </ol>
                                <div class="container mt-5">
                                    <div class="row">
                                        <div class="col-12 mx-auto">
                                            <h3>Create a Agency</h3>
                                            <form:form method="post" action="/admin/agency/update"
                                                modelAttribute="updateAgency" enctype="multipart/form-data">

                                                <div class="row g-3">
                                                    <div class="col mb-3">
                                                        <div class="mb-3" style="display:none">
                                                            <label for="exampleInputPassword1"
                                                                class="form-label">ID</label>
                                                            <form:input type="text" class="form-control" path="id" />
                                                        </div>
                                                        <c:set var="errorFullName">
                                                            <form:errors path="user.fullName"
                                                                cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <label for="exampleInputPassword1" class="form-label">Full
                                                            Name</label>
                                                        <form:input type="text"
                                                            class="form-control ${not empty errorFullName ? 'is-invalid':''}"
                                                            path="user.fullName" />
                                                        ${errorFullName}
                                                    </div>

                                                    <div class="col mb-3">
                                                        <label for="exampleInputPassword1"
                                                            class="form-label">Phone</label>
                                                        <form:input type="text" class="form-control"
                                                            path="user.phone" />
                                                    </div>
                                                </div>
                                                <div class="row g-3">
                                                    <div class="col mb-3">
                                                        <c:set var="errorBrokerageArea">
                                                            <form:errors path="brokerageArea"
                                                                cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <label class="form-label">Khu vực
                                                            môi giới</label>
                                                        <form:input type="text"
                                                            class="form-control ${not empty errorBrokerageArea ? 'is-invalid':''}"
                                                            path="brokerageArea" />
                                                        ${errorBrokerageArea}
                                                    </div>
                                                    <div class="col mb-3">
                                                        <c:set var="errorBrokerageType">
                                                            <form:errors path="brokerageType"
                                                                cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <label class="form-label">Loại hình môi giới</label>
                                                        <form:input type="text"
                                                            class="form-control ${not empty errorBrokerageType ? 'is-invalid':''}"
                                                            path="brokerageType" />
                                                        ${errorBrokerageType}
                                                    </div>
                                                </div>

                                                <div class="row g-3">
                                                    <div class="col mb-3">
                                                        <c:set var="errorCertificationNumber">
                                                            <form:errors path="brokerCertification.certificationNumber"
                                                                cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <label for="exampleInputPassword1" class="form-label">Số Chứng
                                                            Chỉ</label>
                                                        <form:input type="text"
                                                            class="form-control ${not empty errorCertificationNumber ? 'is-invalid':''}"
                                                            path="brokerCertification.certificationNumber" />
                                                        ${errorCertificationNumber}
                                                    </div>
                                                    <div class="col mb-3">
                                                        <c:set var="errorCertificationAuthority">
                                                            <form:errors
                                                                path="brokerCertification.certificationAuthority"
                                                                cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <label for="exampleInputPassword1" class="form-label">Nơi cấp
                                                            chứng chỉ</label>
                                                        <form:input type="text"
                                                            class="form-control ${not empty errorCertificationAuthority? 'is-invalid':''}"
                                                            path="brokerCertification.certificationAuthority" />
                                                        ${errorCertificationAuthority}
                                                    </div>
                                                </div>
                                                <div class="mb-3">
                                                    <div class="col mb-3">
                                                        <c:set var="errorNameOnCertification">
                                                            <form:errors path="brokerCertification.nameOnCertification"
                                                                cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <label for="exampleInputPassword1" class="form-label">Tên trên
                                                            chứng chỉ</label>
                                                        <form:input type="text"
                                                            class="form-control ${not empty errorNameOnCertification? 'is-invalid':''}"
                                                            path="brokerCertification.nameOnCertification" />
                                                        ${errorCertificationAuthority}
                                                    </div>
                                                </div>
                                                <div class="mb-3">
                                                    <label for="exampleInputPassword1"
                                                        class="form-label">Address</label>
                                                    <form:input type="text" class="form-control" path="user.address" />
                                                </div>
                                                <div class="row g-3">
                                                    <div class="mb-3 col">
                                                        <label class="mb-2">Role: </label>
                                                        <form:select class="form-select col" path="user.role.id">
                                                            <option selected></option>
                                                            <form:option value="3">AGENCY</form:option>

                                                        </form:select>
                                                    </div>
                                                    <div class="mb-3 col">
                                                        <label for="formFile" class="form-label">Avatar: </label>
                                                        <input class="form-control" type="file" id="avatarFile"
                                                            accept=".png,.jpg,.jpeg" name="file" />
                                                    </div>
                                                </div>
                                                <div class="col-12 mb-3">
                                                    <img style="max-height: 250px;display: none" src=""
                                                        alt="avatar preview" id="avatarPreview" mutiple>
                                                </div>
                                                <div class="col-12 mb-5">
                                                    <button type="submit" class="btn btn-primary">Submit</button>
                                                </div>
                                            </form:form>
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
