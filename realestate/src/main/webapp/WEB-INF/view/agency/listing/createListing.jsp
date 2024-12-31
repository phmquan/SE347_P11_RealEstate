<!DOCTYPE html>
<html lang="en">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                        <div class="row p-3">
                            <select class="form-select gap-3" id="selectPropertyType"
                                aria-label="Default select example">
                                <option value="" selected>Chọn danh mục tin đăng</option>
                                <option value="/agency/listing/create?type=house">Bất động sản - Nhà ở</option>
                                <option value="/agency/listing/create?type=land">Bất động sản - Đất</option>
                                <option value="/agency/listing/create?type=apartment">Bất động sản - Căn hộ/Chung cư
                                </option>
                            </select>

                            <script>
                                document.getElementById("selectPropertyType").addEventListener("change", function () {
                                    const selectedValue = this.value;
                                    if (selectedValue) {
                                        window.location.href = selectedValue; // Redirect to the selected URL
                                    }
                                });
                            </script>
                        </div>
                    </div>
                </div>
                <jsp:include page="../layout/footer.jsp" />
            </body>

</html>