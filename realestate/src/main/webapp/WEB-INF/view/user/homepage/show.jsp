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
                <script
                    src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
                </style>
                <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
                <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
                    rel="stylesheet">
                <link href="/client/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
                <link href="/client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
            </head>

            <body>

                <!-- Headbar -->
                <jsp:include page="../layout/header.jsp" />
                <!--end headbar-->
                <section class="mt-3 hero-section text-center">
                    <div class="container">
                        <div class="hero-text">
                            <h1>Buy, rent, or sell your property easily</h1>
                            <p>A great platform to buy, sell, or even rent your properties without any commissions.</p>
                        </div>
                        <div class="d-flex justify-content-center mt-4">
                            <ul class="nav nav-tabs">
                                <li class="nav-item">
                                    <a class="nav-link active" data-bs-toggle="tab" href="#rent">Rent</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-bs-toggle="tab" href="#buy">Buy</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-bs-toggle="tab" href="#sell">Sell</a>
                                </li>
                            </ul>
                        </div>
                        <div class="tab-content mt-3">
                            <div id="rent" class="tab-pane fade show active">
                                <form class="d-flex justify-content-center gap-3">
                                    <input type="text" class="form-control w-25" placeholder="Location">
                                    <input type="date" class="form-control w-25">
                                    <button class="btn btn-primary">Browse Properties</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </section>
                <section class="container py-5">
                    <div class="text-center mb-5">
                        <h2 class="section-title">Based on your location</h2>
                        <p class="section-subtitle">Some of our picked properties near your location.</p>
                    </div>

                    <!-- Filters -->
                    <div class="d-flex justify-content-center mb-4">
                        <button class="btn btn-outline-primary me-2 active">Rent</button>
                        <button class="btn btn-outline-primary me-2">Buy</button>
                        <button class="btn btn-outline-primary">Sell</button>
                        <input type="text" class="form-control ms-3 w-25" placeholder="Search...">
                    </div>

                    <!-- Property Listings -->
                    <div class="row g-4">
                        <div class="col-md-4">
                            <div class="card property-card">
                                <div class="position-relative">
                                    <span class="popular-badge">POPULAR</span>
                                    <img src="/images/property/MaskGroup.png" alt="Palm Harbor">
                                </div>
                                <div class="property-info">
                                    <p class="property-price">$2,095/month</p>
                                    <h5 class="property-title">Palm Harbor</h5>
                                    <p class="property-details">2699 Green Valley, Highland Lake, FL</p>
                                    <p class="property-details">3 Beds • 2 Bathrooms • 5x7 m²</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card property-card">
                                <div class="position-relative">
                                    <span class="popular-badge">POPULAR</span>
                                    <img src="/images/property/MaskGroup.png" alt="Beverly Springfield">
                                </div>
                                <div class="property-info">
                                    <p class="property-price">$2,700/month</p>
                                    <h5 class="property-title">Beverly Springfield</h5>
                                    <p class="property-details">2821 Lake Sevilla, Palm Harbor, TX</p>
                                    <p class="property-details">4 Beds • 2 Bathrooms • 6x7.5 m²</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card property-card">
                                <img src="/images/property/MaskGroup.png" alt="Faulkner Ave">
                                <div class="property-info">
                                    <p class="property-price">$4,550/month</p>
                                    <h5 class="property-title">Faulkner Ave</h5>
                                    <p class="property-details">909 Woodland St, Michigan, IN</p>
                                    <p class="property-details">5 Beds • 3 Bathrooms • 8x10 m²</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card property-card">
                                <div class="position-relative">
                                    <span class="popular-badge">POPULAR</span>
                                    <img src="/images/property/MaskGroup.png" alt="Palm Harbor">
                                </div>
                                <div class="property-info">
                                    <p class="property-price">$2,095/month</p>
                                    <h5 class="property-title">Palm Harbor</h5>
                                    <p class="property-details">2699 Green Valley, Highland Lake, FL</p>
                                    <p class="property-details">3 Beds • 2 Bathrooms • 5x7 m²</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card property-card">
                                <div class="position-relative">
                                    <span class="popular-badge">POPULAR</span>
                                    <img src="/images/property/MaskGroup.png" alt="Beverly Springfield">
                                </div>
                                <div class="property-info">
                                    <p class="property-price">$2,700/month</p>
                                    <h5 class="property-title">Beverly Springfield</h5>
                                    <p class="property-details">2821 Lake Sevilla, Palm Harbor, TX</p>
                                    <p class="property-details">4 Beds • 2 Bathrooms • 6x7.5 m²</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card property-card">
                                <img src="/images/property/MaskGroup.png" alt="Faulkner Ave">
                                <div class="property-info">
                                    <p class="property-price">$4,550/month</p>
                                    <h5 class="property-title">Faulkner Ave</h5>
                                    <p class="property-details">909 Woodland St, Michigan, IN</p>
                                    <p class="property-details">5 Beds • 3 Bathrooms • 8x10 m²</p>
                                </div>
                            </div>
                        </div>
                        <!-- Add more cards as needed -->
                    </div>

                    <!-- Browse Button -->
                    <div class="text-center browse-btn">
                        <button class="btn btn-primary">Browse more properties</button>
                    </div>
                </section>
                <jsp:include page="../layout/footer.jsp" />
                <script
                    src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
            </body>

</html>