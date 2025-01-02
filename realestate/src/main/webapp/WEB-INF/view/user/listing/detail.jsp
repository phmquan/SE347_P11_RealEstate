<!DOCTYPE html>
<html lang="en">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>
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
                    <!-- Bootstrap JS and dependencies -->
                    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
                    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

                </head>

                <body>
                    <jsp:include page="../layout/header.jsp" />

                    <div class="container mt-3 mb-3">

                        <div class="bg-white p-4 shadow-sm rounded p-3">
                            <div class="row p-3 justify-content-center">
                                <img src="/images/images/listing/${listing.property.propertyImages[0]}"
                                    alt="Property Image" class="img-fluid justify-content-center"
                                    style=" max-width: 800px; height: auto;">
                                <div class="container my-4" style="max-width:800px">
                                    <div class="card p-4 shadow">
                                        <h1 class="mb-3">Bán Tòa Nhà Mặt Tiền CAO CẤP dòng tiền lớn</h1>
                                        <div class="d-flex flex-wrap mb-3 text-muted">
                                            <span>nhiều hơn 10 PN</span>
                                            <span class="mx-2">•</span>
                                            <span>Hướng Đông</span>
                                            <span class="mx-2">•</span>
                                            <span>Nhà mặt phố, mặt tiền</span>
                                        </div>
                                        <div class="bg-light p-3 mb-3 rounded">
                                            <div class="d-flex justify-content-between align-items-center">
                                                <div>
                                                    <b class="fs-4">48 tỷ</b>
                                                    <span class="text-muted mx-2">•</span>
                                                    <strong>217,19 triệu/m²</strong>
                                                    <span class="text-muted mx-2">•</span>
                                                    <strong>221 m²</strong>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="d-flex flex-column gap-2 mb-3">
                                            <div class="d-flex align-items-center">
                                                <i class="bi bi-geo-alt-fill me-2"></i>
                                                <span>237, Đường Lê Đức Thọ, Phường 17, Quận Gò Vấp, Tp Hồ Chí
                                                    Minh</span>
                                            </div>
                                            <div class="d-flex align-items-center">
                                                <i class="bi bi-clock-fill me-2"></i>

                                            </div>
                                        </div>
                                        <div class="mb-4">
                                            <h5 class="mb-3">Đặc điểm bất động sản</h5>
                                            <div class="row">
                                                <c:if test="${type eq 'apartment'}">
                                                    <div class="col-6 mb-3">
                                                        <span>Giấy tờ pháp lý</span>:
                                                        <strong>${listing.property.legalDocument}</strong>
                                                    </div>
                                                    <div class="col-6 mb-3">
                                                        <span>Loại hình căn hộ </span>:
                                                        <strong>${listing.property.apartmentType}</strong>
                                                    </div>
                                                    <div class="col-6 mb-3">
                                                        <span>Số phòng ngủ </span>: <strong>
                                                            <fmt:formatNumber value="${listing.property.apartmentRoom}"
                                                                maxFractionDigits="0" />
                                                        </strong>
                                                    </div>
                                                    <div class="col-6 mb-3">
                                                        <span>Hướng chung cư </span>:
                                                        <strong>${listing.property.apartmentDirection}</strong>
                                                    </div>

                                                </c:if>
                                                <c:if test="${type eq 'house'}">
                                                    <div class="col-6 mb-3">
                                                        <span>Giấy tờ pháp lý</span>:
                                                        <strong>${listing.property.legalDocument}</strong>
                                                    </div>
                                                    <div class="col-6 mb-3">
                                                        <span>Loại nhà</span>:
                                                        <strong>${listing.property.houseType}</strong>
                                                    </div>
                                                    <div class="col-6 mb-3">
                                                        <span>Số phòng ngủ</span>: <strong>
                                                            <fmt:formatNumber value="${listing.property.houseRoom}"
                                                                maxFractionDigits="0" />
                                                        </strong>
                                                    </div>
                                                    <div class="col-6 mb-3">
                                                        <span>Hướng cửa chính</span>:
                                                        <strong>${listing.property.houseDirection}</strong>
                                                    </div>

                                                </c:if>
                                                <c:if test="${type eq 'land'}">
                                                    <div class="col-6 mb-3">
                                                        <span>Giấy tờ pháp lý</span>:
                                                        <strong>${listing.property.legalDocument}</strong>
                                                    </div>
                                                    <div class="col-6 mb-3">
                                                        <span>Loại hình đất</span>:
                                                        <strong>${listing.property.landType}</strong>
                                                    </div>
                                                    <div class="col-6 mb-3">
                                                        <span>Số phòng ngủ</span>:
                                                        <strong>${listing.property.landDirection}</strong>
                                                    </div>

                                            </div>
                                            </c:if>
                                            <!-- Add more property details similarly -->
                                        </div>
                                    </div>
                                    <div>
                                        <h5 class="mb-3 ">
                                            <strong>Mô tả chi tiết</strong>
                                        </h5>
                                        <div class="row">
                                            <div class="col-6 mb-3">
                                                <span>SĐT Liên hệ môi giới:</span> <strong
                                                    style:>${listing.agency.user.phone}</strong>
                                                </p>
                                            </div>

                                        </div>
                                        <div class="col-6 mb-3">
                                            <p>${listing.listingDescription}</p>
                                        </div>


                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    </div>
                    <jsp:include page="../layout/footer.jsp" />
                </body>

</html>