<html lang="en">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

            <head>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="Create Property" />

                <title>Create Property</title>


                <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
                <!-- Bootstrap CSS -->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
                    rel="stylesheet">
                <link rel="stylesheet" href="/css/user/headbar.css">
                <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />

                <!-- Bootstrap JS and Dependencies -->
                <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>

            </head>

            <body class="sb-nav-fixed">
                <jsp:include page="../layout/header.jsp" />



                <main>
                    <div class="container px-4">
                        <div class="bg-white p-4 shadow-sm rounded p-3">
                            <h1 class="mt-4">Đăng ký môi giới</h1>
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item"><a href="/">Homepage</a></li>
                                <li class="breadcrumb-item"><a href="/agency/listing/create?type=null">Đăng ký môi
                                        giới</a>
                                </li>
                                <li class="breadcrumb-item active">Tạo bài đăng BĐS đất</li>
                            </ol>
                            <div class="container mt-3">
                                <form:form method="post" action="/user/agency/register" modelAttribute="newAgency"
                                    enctype="multipart/form-data">
                                    <div class="row g-3">
                                        <c:set var="errorBrokerageArea">
                                            <form:errors path="brokerageArea" cssClass="invalid-feedback" />
                                        </c:set>
                                        <div class="col mb-3">
                                            <label for="exampleInputEmail1" class="form-label">Khu vực môi giới</label>
                                            <form:input type="text"
                                                class="form-control ${not empty errorBrokerageArea ? 'is-invalid' : ''}"
                                                path="brokerageArea" />
                                            ${errorBrokerageArea}
                                        </div>
                                        <div class="col mb-3">
                                            <c:set var="errorBrokerageType">
                                                <form:errors path="brokerageType" cssClass="invalid-feedback" />
                                            </c:set>
                                            <label for="exampleInputPassword1" class="form-label">Loại hình môi
                                                giới</label>
                                            <form:select
                                                class="form-control ${not empty errorBrokerageType ? 'is-invalid' : ''}"
                                                path="brokerageType">
                                                <form:option value="SELL">Bán</form:option>
                                                <form:option value="RENT">Cho thuê</form:option>
                                            </form:select>
                                            ${errorBrokerageType}
                                        </div>
                                    </div>
                                    <div class="row g-3">
                                        <div class="col mb-3">
                                            <c:set var="errorNameOnCertification">
                                                <form:errors path="brokerCertification.nameOnCertification"
                                                    cssClass="invalid-feedback" />
                                            </c:set>
                                            <label for="exampleInputPassword1" class="form-label">Tên trên chứng
                                                chỉ</label>
                                            <form:input type="text"
                                                class="form-control ${not empty errorNameOnCertification ? 'is-invalid':''}"
                                                path="brokerCertification.nameOnCertification" />
                                            ${errorNameOnCertification}
                                        </div>
                                    </div>

                                    <div class="row g-3">

                                        <div class="col mb-3">
                                            <c:set var="errorCertificationNumber">
                                                <form:errors path="brokerCertification.certificationNumber"
                                                    cssClass="invalid-feedback" />
                                            </c:set>
                                            <label class="form-label">Số chứng chỉ</label>
                                            <form:input type="text"
                                                class="form-control ${not empty errorCertificationNumber ? 'is-invalid':''}"
                                                path="brokerCertification.certificationNumber" />
                                            ${errorCertificationNumber}
                                        </div>
                                        <div class="col mb-3">
                                            <c:set var="errorCertificationAuthority">
                                                <form:errors path="brokerCertification.certificationAuthority"
                                                    cssClass="invalid-feedback" />
                                            </c:set>
                                            <label class="form-label">Cơ quan cấp chứng chỉ</label>
                                            <form:input type="text"
                                                class="form-control ${not empty errorCertificationAuthority ? 'is-invalid':''}"
                                                path="brokerCertification.certificationAuthority" />
                                            ${errorCertificationAuthority}
                                        </div>
                                    </div>
                                    <div class="row g-3">
                                        <div class="mb-3 col">
                                            <label for="formFile" class="form-label">Cập nhật avatar
                                            </label>
                                            <input class="form-control" type="file" id="File" name="propertyImage"
                                                multiple accept=".png, .jpg, .jpeg" />

                                        </div>
                                    </div>
                                    <div class="row g-3">
                                        <div class="col-12 mb-3">
                                            <img style="max-height: 250px;display: none" src="" alt="avatar preview"
                                                id="avatarPreview" mutiple>
                                        </div>
                                    </div>
                                    <div class="col-12 mb-5">
                                        <button type="submit" class="btn btn-primary">Submit</button>
                                    </div>




                                </form:form>
                            </div>
                        </div>
                    </div>
                    <jsp:include page="../layout/footer.jsp" />
                </main>



            </body>

</html>