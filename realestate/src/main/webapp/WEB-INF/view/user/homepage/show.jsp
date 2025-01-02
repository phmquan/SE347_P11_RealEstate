<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ page language="java" contentType="text/html; charset=UTF-8" %>
                <!DOCTYPE html>
                <html lang="en">


                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Estatery</title>
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
                        rel="stylesheet">
                    <link rel="stylesheet" href="/css/user/headbar.css">

                    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />




                    <!-- Bootstrap JS and dependencies -->
                    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
                    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
                    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

                </head>

                <body>

                    <jsp:include page="../layout/header.jsp" />
                    <!--end headbar-->
                    <section class="mt-3 hero-section text-center">
                        <div class="container">
                            <div class="hero-text">
                                <h1>Mua bán và thuê bất động sản dễ dàng</h1>
                                <p>Một nơi tốt để bạn giao dịch bất động sản với không phí hoa hồng</p>
                            </div>
                            <div class="d-flex justify-content-center mt-4">

                            </div>

                        </div>
                    </section>
                    <section class="container py-5">
                        <div class="text-center mb-5">
                            <h2 class="section-title">Một số bất động sản tiêu biểu</h2>

                        </div>

                        <!-- Filters -->
                        <form action="/user/listing" method="GET" class="d-flex justify-content-center mb-4">
                            <select class="form-select w-25 me-3" name="district">
                                <option value="null" selected>Chọn quận...</option>
                                <option value="1">Quận 1</option>
                                <option value="2">Quận 2</option>
                                <option value="3">Quận 3</option>
                                <option value="4">Quận 4</option>
                                <option value="5">Quận 5</option>
                                <option value="6">Quận 6</option>
                                <option value="7">Quận 7</option>
                                <option value="8">Quận 8</option>
                                <option value="9">Quận 9</option>
                                <option value="10">Quận 10</option>
                                <option value="11">Quận 11</option>
                                <option value="12">Quận 12</option>
                                <option value="Bình Thạnh">Bình Thạnh</option>
                                <option value="Gò Vấp">Gò Vấp</option>
                                <option value="Phú Nhuận">Phú Nhuận</option>
                                <option value="Tân Bình">Tân Bình</option>
                                <option value="Tân Phú">Tân Phú</option>
                                <option value="Thủ Đức">Thủ Đức</option>
                            </select>
                            <select class="form-select w-25 me-3" name="price">
                                <option value="null" selected>Chọn giá...</option>
                                <option value="1">Dưới 1 tỷ</option>
                                <option value="2">1 tỷ - 3 tỷ</option>
                                <option value="3">3 tỷ - 5 tỷ</option>
                                <option value="4">5 tỷ - 7 tỷ</option>
                                <option value="5">7 tỷ - 10 tỷ</option>
                                <option value="6">Trên 10 tỷ</option>
                            </select>
                            <select class="form-select w-25 me-3" name="type" id="">
                                <option value="null" selected>Chọn loại hình giao dịch...</option>
                                <option value="SELL">Mua</option>
                                <option value="RENT">Thuê</option>
                            </select>
                            <select class="form-select w-25 me-3" name="propertyType">
                                <option value="null" selected>Chọn loại...</option>
                                <option value="1">Nhà</option>
                                <option value="2">Căn hộ</option>
                                <option value="3">Đất</option>
                            </select>
                            <button type="submit" class="btn btn-primary ms-3">Filter</button>
                        </form>

                        <!-- Property Listings -->
                        <div class="row g-4">

                            <c:forEach var="listing" items="${listings}">

                                <div class="col-md-4">
                                    <div class="card property-card">
                                        <div class="position-relative">
                                            <span class="popular-badge">Nổi Bật</span>
                                            <a href="user/listing/detail/${listing.id}">
                                                <img src="/images/images/listing/${listing.property.propertyImages[0]}"
                                                    alt="${listing.listingTitle}" style="max-width:400px">
                                            </a>

                                        </div>
                                        <div class="property-info">
                                            <p class="property-price">
                                                <fmt:formatNumber value="${listing.property.propertyPrice}"
                                                    type="currency" currencySymbol="đ " maxFractionDigits="0"
                                                    minFractionDigits="0" />
                                            </p>
                                            <h5 class="property-title">${listing.listingTitle}</h5>
                                            <p class="property-details">${listing.property.address}</p>
                                            <p class="property-details">Quận ${listing.property.district} •
                                                ${listing.property.propertyArea}
                                                m²</p>
                                        </div>
                                    </div>
                                </div>


                            </c:forEach>

                        </div>

                        <c:if test="${totalPages > 0}">
                            <div class="pagination justify-content-center my-2">
                                <c:if test="${currentPage > 0}">
                                    <a href="?page=${currentPage - 1}&size=${size}"
                                        class="btn btn-primary mx-2">Previous</a>
                                </c:if>
                                <c:forEach var="i" begin="0" end="${totalPages - 1}">
                                    <c:choose>
                                        <c:when test="${i == currentPage}">
                                            <span class="btn btn-secondary mx-2">${i + 1}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="?page=${i}&size=${size}" class="btn btn-primary">${i + 1}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <c:if test="${currentPage < totalPages - 1}">
                                    <a href="?page=${currentPage + 1}&size=${size}"
                                        class="btn btn-primary mx-2">Next</a>
                                </c:if>
                            </div>
                        </c:if>



                    </section>
                    <jsp:include page="../layout/footer.jsp" />
                    <script
                        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
                </body>

                </html>