<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

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
                                        <a href="#" class="text-decoration-none text-primary d-flex align-items-center">
                                            <img src="https://static.chotot.com/storage/ads-dashboard/svg/Plus-outline.svg"
                                                alt="Add Icon" style="width: 16px; margin-right: 5px;">

                                        </a>
                                    </div>
                                </div>
                                <div class="ms-auto p-2">
                                    <a href="/agency/listing/create?type=null" class="btn btn-primary">Đăng Bài</a>
                                </div>
                            </div>
                        </div>
                        <div class="bg-white border-top border-bottom">
                            <div class="container d-flex flex-nowrap overflow-auto">
                                <a href="/agency/listing?status=displaying" class="btn btn-link text-dark px-3">ĐANG
                                    HIỂN
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
                                    <div class="card">
                                        <div class="card-body d-flex flex-column gap-3">
                                            <h5 class="card-title">Xác nhận xóa bài đăng</h5>
                                            <p class="card-text">Bạn có chắc chắn muốn xóa bài đăng này không?</p>
                                            <div class="d-flex gap-3">
                                                <form:form action="/agency/listing/delete/${listing.id}" method="post"
                                                    modelAttribute="listing" enctype="multipart/form-data">

                                                    <button type="submit" class="btn btn-danger">Xóa</button>
                                                </form:form>
                                                <a href="/agency/listing?status=pending"
                                                    class="btn btn-secondary">Hủy</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <jsp:include page="../layout/footer.jsp" />
                </body>

</html>