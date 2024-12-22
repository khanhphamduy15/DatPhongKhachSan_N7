<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Đăng nhập</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="${pageContext.request.contextPath}/AdminFrontendAssets/img/favicon.png" rel="icon">
<link href="${pageContext.request.contextPath}/AdminFrontendAssets/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="${pageContext.request.contextPath}/AdminFrontendAssets/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/AdminFrontendAssets/css/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/AdminFrontendAssets/css/boxicons.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/AdminFrontendAssets/css/quill.snow.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/AdminFrontendAssets/css/quill.bubble.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/AdminFrontendAssets/css/remixicon.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/AdminFrontendAssets/css/style.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="${pageContext.request.contextPath}/AdminFrontendAssets/css/stylex.css" rel="stylesheet">

<!-- =======================================================
  * Template Name: NiceAdmin
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Updated: Apr 20 2024 with Bootstrap v5.3.3
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>


	<main>
    <div class="container">

      <section class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
        <div class="container">
          <div class="row justify-content-center">
            <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">

              <div class="d-flex justify-content-center py-4">
                <a href="/" class="logo d-flex align-items-center w-auto">
                  <img src="img/logox.png" alt=""><span>Hotel Booking Admin Station</span>                </a>
              </div><!-- End Logo -->

              <div class="card mb-3">

                <div class="card-body">

                  <div class="pt-4 pb-2">
                    <h5 class="card-title text-center pb-0 fs-4">Đăng nhập</h5>
                    <p class="text-center small">Nhập tài khoản và mật khẩu của bạn để đăng nhập</p>
                  </div>

                  <form class="row g-3 needs-validation" action="/DatPhongKhachSan/AdminLogin" method="post" novalidate>
                    <div class="col-12">
                        <label for="admin-email" class="form-label">Tài khoản</label>
                        <div class="input-group has-validation">
                            <span class="input-group-text" id="inputGroupPrepend"><i class="bi bi-person"></i></span>
                            <input type="text" name="admin-email" class="form-control" id="admin-email" required>
                            <div class="invalid-feedback">Hãy nhập tài khoản của bạn.</div>
                        </div>
                    </div>

                    <div class="col-12">
                        <label for="admin-password" class="form-label">Mật khẩu</label>
                        <input type="password" name="admin-password" class="form-control" id="admin-password" required>
                        <div class="invalid-feedback">Hãy nhập mật khẩu của bạn!</div>
                    </div>

                    <div class="col-12">
                        <button class="btn btn-primary w-100" type="submit">Đăng nhập</button>
                    </div>

                    <% if (request.getParameter("error") != null) { %>
                    <div class="col-12">
                        <div class="alert alert-danger" role="alert">
                            Tài khoản hoặc mật khẩu không đúng. Vui lòng thử lại.
                        </div>
                    </div>
                    <% } %>
                </form>

                </div>
              </div>

              <div class="credits">
                <!-- All the links in the footer should remain intact. -->
                <!-- You can delete the links only if you purchased the pro version. -->
                <!-- Licensing information: https://bootstrapmade.com/license/ -->
                <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->
<!--                 Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
 -->              </div>

            </div>
          </div>
        </div>

      </section>

    </div>
  </main><!-- End #main -->

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>
	
	<!-- Vendor JS Files -->
	<script src="${pageContext.request.contextPath}AdminFrontendAssets/js/apexcharts.min.js"></script>
	<script
		src="${pageContext.request.contextPath}AdminFrontendAssets/bootstrap.bundle.min.js"></script>

	<script src="${pageContext.request.contextPath}/AdminFrontendAssets/js/echarts.min.js"></script>

	<script src="${pageContext.request.contextPath}/AdminFrontendAssets/js/tinymce.min.js"></script>
	<script src="${pageContext.request.contextPath}/AdminFrontendAssets/js/validate.js"></script>
	<!-- Template Main JS File -->
	<script src="${pageContext.request.contextPath}/AdminFrontendAssets/js/main.js"></script>
	<script>
        (function () {
            'use strict'
            var forms = document.querySelectorAll('.needs-validation')
            Array.prototype.slice.call(forms).forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }
                    form.classList.add('was-validated')
                }, false)
            })
        })()
    </script>

</body>

</html>