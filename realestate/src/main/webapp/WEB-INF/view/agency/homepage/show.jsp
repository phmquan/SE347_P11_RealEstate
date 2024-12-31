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
                                    <img src="https://cdn.chotot.com/uac2/27674407" alt=""
                                        class="rounded-circle img-fluid">
                                </div>
                                <div class="ms-3">
                                    <h5 class="mb-0 fw-bold">Quân Phan Hoàng Minh</h5>
                                    <a href="#" class="text-decoration-none text-primary d-flex align-items-center">
                                        <img src="https://static.chotot.com/storage/ads-dashboard/svg/Plus-outline.svg"
                                            alt="Add Icon" style="width: 16px; margin-right: 5px;">
                                        <span>Tạo cửa hàng</span>
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
                            <button class="btn btn-link text-dark px-3">ĐANG HIỂN THỊ (0)</button>
                            <button class="btn btn-link text-dark px-3">BỊ TỪ CHỐI (1)</button>
                            <button class="btn btn-link text-dark px-3">CHỜ DUYỆT (0)</button>
                            <button class="btn btn-link text-dark px-3">ĐÃ ẨN (1)</button>
                        </div>
                    </div>
                    <div class="container mt-2">
                        <div class="row">
                            <!-- Main Content -->
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-body d-flex mb-3 ">
                                        <!-- Image -->
                                        <div class="position-relative p-2">
                                            <img src="https://cdn.chotot.com/_IhIZO0Yjg0RZUhYZx2h9ntNIBjjOk7QnPRE7jYA3tc/preset:listing/plain/f5ae778fea80af0f74c3229e8168dca7-2911484381406458818.jpg"
                                                alt="Listing Image" class="img-fluid rounded w-200 "
                                                style="max-width: 200px; height: auto;">

                                        </div>
                                        <div class="p-2">
                                            <h5 class="card-title text-truncate">Test Đăng Bài</h5>
                                            <p class="text-danger fw-bold mb-0">9.000.000.000 đ</p>
                                            <p class="text-muted small">Phường 4, Quận 3, Tp Hồ Chí Minh</p>
                                        </div>
                                        <!-- Details -->

                                        <div class="ms-auto p-2">
                                            <a href="#!" class="btn btn-warning mx-2 ">
                                                <i class="fa-solid fa-pencil"></i>
                                            </a>
                                            <a href="#!" class="btn btn-danger mx-2">
                                                <i class="fa-solid fa-xmark"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <nav aria-label="Navigation">
                            <ul class="pagination justify-content-center my-2">
                                <li class="page-item">
                                    <a class="page-link" href="#" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <li class="page-item"><a class="page-link" href="#">1</a></li>
                                <li class="page-item"><a class="page-link" href="#">2</a></li>
                                <li class="page-item"><a class="page-link" href="#">3</a></li>
                                <li class="page-item">
                                    <a class="page-link" href="#" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                    <jsp:include page="../layout/footer.jsp" />
            </body>

</html>