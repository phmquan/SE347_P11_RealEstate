<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
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
                    <div class="bg-white">
                        <div class="container py-3 d-flex align-items-center">
                            <div class="d-flex align-items-center me-auto">
                                <div class="rounded-circle bg-light d-flex align-items-center justify-content-center"
                                    style="width: 48px; height: 48px;">
                                    <img src="/images/avatar/${avatar}" alt="" class="rounded-circle img-fluid">
                                </div>
                                <div class="ms-3">
                                    <h5 class="mb-0 fw-bold">${fullName}</h5>

                                </div>
                            </div>
                            <div class="ms-auto p-2">
                                <a href="/agency/listing/create?type=null" class="btn btn-primary">Đăng Bài</a>
                            </div>
                        </div>
                    </div>
                    <div class="bg-white border-top border-bottom">
                        <div class="container d-flex flex-nowrap overflow-auto">
                            <a href="/agency/listing?status=displaying" class="btn btn-link text-dark px-3">ĐANG HIỂN
                                THỊ (${displayingCount})</a>
                            <a href="/agency/listing?status=rejected" class="btn btn-link text-dark px-3">BỊ TỪ CHỐI
                                (${rejectedCount})</a>
                            <a href="/agency/listing?status=pending" class="btn btn-link text-dark px-3">CHỜ DUYỆT
                                (${pendingCount})</a>
                            <a href="/agency/listing?status=hidden" class="btn btn-link text-dark px-3">ĐÃ ẨN
                                (${hiddenCount})</a>
                        </div>
                    </div>
                    <div class="container mt-2">
                        <div class="row">
                            <!-- Main Content -->
                            <div class="col-12">
                                <c:if test="${empty listings.content}">
                                    <p>No listings available.</p>
                                </c:if>
                                <c:if test="${not empty listings.content}">
                                    <c:forEach var="listing" items="${listings.content}">
                                        <div class="card mb-2">
                                            <div class="card-body d-flex mb-3 ">
                                                <!-- Image -->
                                                <div class="position-relative p-2">
                                                    <img src="/images/images/listing/${listing.property.propertyImages[0]}"
                                                        alt="Listing Image" alt="Listing Image"
                                                        class="img-fluid rounded w-200 "
                                                        style="max-width: 200px; height: auto;">

                                                </div>
                                                <div class="p-2">
                                                    <h5 class="card-title text-truncate">${listing.listingTitle}</h5>
                                                    <p class="text-danger fw-bold mb-0">
                                                        <fmt:formatNumber value="${listing.property.propertyPrice}"
                                                            type="currency" currencySymbol="đ " maxFractionDigits="0"
                                                            minFractionDigits="0" />
                                                    </p>
                                                    <p class="text-muted small">${listing.property.address}</p>
                                                </div>
                                                <!-- Details -->

                                                <div class="ms-auto p-2">

                                                    <a href="/agency/listing/delete/${listing.id}"
                                                        class="btn btn-danger mx-2">
                                                        <i class="fa-solid fa-xmark"></i>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:if>
                            </div>
                        </div>
                        <c:if test="${not empty listings && listings.totalPages > 1}">
                            <nav aria-label="Navigation">
                                <ul class="pagination justify-content-center my-2">
                                    <c:if test="${listings.hasPrevious()}">
                                        <li class="page-item">
                                            <a class="page-link"
                                                href="?status=${param.status}&page=${listings.number - 1}&size=${listings.size}"
                                                aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                    </c:if>
                                    <c:forEach var="i" begin="0" end="${listings.totalPages - 1}">
                                        <li class="page-item ${i == listings.number ? 'active' : ''}">
                                            <a class="page-link"
                                                href="?status=${param.status}&page=${i}&size=${listings.size}">${i +
                                                1}</a>
                                        </li>
                                    </c:forEach>
                                    <c:if test="${listings.hasNext()}">
                                        <li class="page-item">
                                            <a class="page-link"
                                                href="?status=${param.status}&page=${listings.number + 1}&size=${listings.size}"
                                                aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                            </a>
                                        </li>
                                    </c:if>
                                </ul>
                            </nav>
                        </c:if>
                    </div>
                    <jsp:include page="../layout/footer.jsp" />
            </body>

</html>