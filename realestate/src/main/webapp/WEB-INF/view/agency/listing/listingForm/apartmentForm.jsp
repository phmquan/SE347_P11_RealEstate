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
                            <h1 class="mt-4">Tạo bài đăng</h1>
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item"><a href="/agency">Dashboard</a></li>
                                <li class="breadcrumb-item active">Tạo bài đăng BĐS chung cư</li>
                            </ol>
                            <div class="container mt-3">
                                <form:form method="post" action="/agency/listing/create?type=apartment"
                                    modelAttribute="newListing" enctype="multipart/form-data">

                                    <input type="text" path="propertyType" value="apartment" hidden />
                                    <div class="row g-3">
                                        <c:set var="errorListingType">
                                            <form:errors path="listingType" cssClass="invalid-feedback" />
                                        </c:set>
                                        <div class="col mb-3">
                                            <label for="exampleInputEmail1" class="form-label">Loại hình
                                                giao dịch</label>
                                            <form:select
                                                class="form-select ${not empty errorListingType ? 'is-invalid':''}"
                                                path="listingType">
                                                <form:option value="SALE">Bán</form:option>
                                                <form:option value="RENT">Cho thuê</form:option>
                                            </form:select>
                                            ${listingType}
                                        </div>
                                        <div class="col mb-3">
                                            <c:set var="errorListingTitle">
                                                <form:errors path="listingTitle" cssClass="invalid-feedback" />
                                            </c:set>
                                            <label for="exampleInputPassword1" class="form-label">Nhập
                                                tiêu
                                                đề bài đăng</label>
                                            <form:input type="text"
                                                class="form-control ${not empty errorListingTitle ? 'is-invalid' : ''}"
                                                path="listingTitle" />
                                            ${errorListingTitle}
                                        </div>
                                    </div>
                                    <div class="row g-3">
                                        <div class="col mb-3">
                                            <c:set var="errorListingDescription">
                                                <form:errors path="listingDescription" cssClass="invalid-feedback" />
                                            </c:set>
                                            <label for="exampleInputPassword1" class="form-label">Viết
                                                mô tả
                                                cho bài đăng</label>
                                            <form:input type="text"
                                                class="form-control ${not empty errorListingDescription ? 'is-invalid':''}"
                                                path="listingDescription" />
                                            ${errorListingDescription}
                                        </div>

                                    </div>
                                    <div class="border-top border-dark my-4" style="border-width: 3px;">
                                    </div>
                                    <div class="row g-3">
                                        <h3>Thông tin chung về bất động sản</h3>
                                        <div class="col mb-3">
                                            <c:set var="errorApartmentAddress">
                                                <form:errors path="property.address" cssClass="invalid-feedback" />
                                            </c:set>
                                            <label class="form-label">Địa chỉ</label>
                                            <form:input type="text"
                                                class="form-control ${not empty errorApartmentAddress ? 'is-invalid':''}"
                                                path="property.address" />
                                            ${errorApartmentAddress}
                                        </div>
                                        <div class="col mb-3">
                                            <c:set var="errorApartmentDistrict">
                                                <form:errors path="property.district" cssClass="invalid-feedback" />
                                            </c:set>
                                            <label class="form-label">Quận</label>
                                            <form:select
                                                class="form-select ${not empty errorApartmentDistrict ? 'is-invalid':''}"
                                                path="property.district">
                                                <form:option value="1">Quận 1</form:option>
                                                <form:option value="2">Quận 2</form:option>
                                                <form:option value="3">Quận 3</form:option>
                                                <form:option value="4">Quận 4</form:option>
                                                <form:option value="5">Quận 5</form:option>
                                                <form:option value="6">Quận 6</form:option>
                                                <form:option value="7">Quận 7</form:option>
                                                <form:option value="8">Quận 8</form:option>
                                                <form:option value="9">Quận 9</form:option>
                                                <form:option value="10">Quận 10</form:option>
                                                <form:option value="11">Quận 11</form:option>
                                                <form:option value="12">Quận 12</form:option>
                                                <form:option value="Bình Thạnh">Bình Thạnh</form:option>
                                                <form:option value="Gò Vấp">Gò Vấp</form:option>
                                                <form:option value="Phú Nhuận">Phú Nhuận</form:option>
                                                <form:option value="Tân Bình">Tân Bình</form:option>
                                                <form:option value="Tân Phú">Tân Phú</form:option>
                                                <form:option value="Thủ Đức">Thủ Đức</form:option>
                                            </form:select>
                                            ${errorApartmentDistrict}
                                        </div>
                                    </div>
                                    <div class="row g-3">
                                        <div class="col mb-3">
                                            <label for="exampleInputPassword1" class="form-label">Nhập
                                                chiều
                                                dài chung cư (m)</label>
                                            <form:input type="number" class="form-control"
                                                path="property.propertyLength" />
                                        </div>
                                        <div class="col mb-3">
                                            <label for="exampleInputPassword1" class="form-label">Nhập
                                                chiều
                                                rộng chung cư (m)</label>
                                            <form:input type="number" class="form-control"
                                                path="property.propertyWidth" />
                                        </div>
                                        <div class="row g-3">
                                            <div class="col mb-3">
                                                <c:set var="errorApartmentArea">
                                                    <form:errors path="property.propertyArea"
                                                        cssClass="invalid-feedback" />
                                                </c:set>
                                                <label for="exampleInputPassword1" class="form-label">Diện
                                                    tích chung cư (m2)</label>
                                                <form:input type=""
                                                    class="form-control ${not empty errorApartmentArea ? 'is-invalid':''}"
                                                    path="property.propertyArea" />
                                                ${errorApartmentArea}
                                            </div>
                                            <div class="col mb-3">
                                                <c:set var="errorApartmentPrice">
                                                    <form:errors path="property.propertyPrice"
                                                        cssClass="invalid-feedback" />
                                                </c:set>
                                                <label for="exampleInputPassword1" class="form-label">Giá
                                                    khu chung cư</label>
                                                <form:input type="text"
                                                    class="form-control ${not empty errorApartmentPrice? 'is-invalid':''}"
                                                    path="property.propertyPrice" />
                                                ${errorApartmentPrice}
                                            </div>
                                        </div>


                                        <div class="row g-3">

                                            <div class="mb-3 col">
                                                <div class="mb-3 col">
                                                    <label class="form-label">Giấy tờ pháp lý</label>
                                                    <form:select class="form-select" path="property.legalDocument">
                                                        <form:option value="Sổ hồng">Sổ hồng</form:option>
                                                        <form:option value="Sổ đỏ">Sổ đỏ</form:option>
                                                        <form:option value="Giấy tờ khác">Giấy tờ khác</form:option>
                                                    </form:select>

                                                </div>
                                                <label for="formFile" class="form-label">Hình ảnh lô
                                                    chung cư:
                                                </label>
                                                <input class="form-control" type="file" id="File" name="propertyImage"
                                                    multiple accept=".png, .jpg, .jpeg" />

                                            </div>
                                        </div>
                                        <div class="col-12 mb-3">
                                            <img style="max-height: 250px;display: none" src="" alt="avatar preview"
                                                id="avatarPreview" mutiple>
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