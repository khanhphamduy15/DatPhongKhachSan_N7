<%@page import="java.util.ArrayList"%>
<%@page import="model.Booking"%>
<%@page import="controller.bookingController.*"%>
<%@page import="dao.*"%>
<%@page import="db.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>Hotel Booking</title>
<meta name="description" content="">
<meta name="keywords" content="">

<!-- Fonts -->
<link href="https://fonts.googleapis.com" rel="preconnect">
<link href="https://fonts.gstatic.com" rel="preconnect" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Raleway:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="UserFrontendAssets/assets/css/bootstrap.min.css"
	rel="stylesheet">


<!-- Main CSS File -->
<link href="UserFrontendAssets/assets/css/main.css" rel="stylesheet">

<!-- ======================================================= -->
</head>


<body class="service-details-page">
	<%
	String mail = (String) session.getAttribute("email");
	%>
	<header id="header" class="header d-flex align-items-center fixed-top">
		<div
			class="container-fluid container-xl position-relative d-flex align-items-center justify-content-between">

			<a href="/DatPhongKhachSan"
				class="logo d-flex align-items-center me-auto me-lg-0"> <!-- Uncomment the line below if you also wish to use an image logo -->
				<!-- <img src="assets/img/logo.png" alt=""> -->
				<h1 class="sitename">Hotel Booking</h1> <span>.</span>
			</a>

			<nav id="navmenu" class="navmenu">
				<ul>
					<li><a href="/DatPhongKhachSan/#hero" class="active">Trang
							chủ<br>
					</a></li>
					<li><a href="/DatPhongKhachSan/#about">Giới thiệu</a></li>
					<li><a href="/DatPhongKhachSan/#list">Danh sách phòng</a></li>
					<li><a href="/DatPhongKhachSan/#contact">Liên hệ với
							chúng tôi</a></li>
				</ul>
				<i class="mobile-nav-toggle d-xl-none bi bi-list"></i>
			</nav>

			<%
			if (mail == null) {
			%>
			<a class="btn-getstarted" href="#login-modal" data-bs-toggle="modal">Đăng
				nhập</a>
			<%
			} else {
			%>
			<div class="dropdown">
				<button class="btn btn-secondary dropdown-toggle" type="button"
					id="userMenu" data-bs-toggle="dropdown" aria-expanded="false">
					Xin Chào,
					<%=mail%>
				</button>
				<ul class="dropdown-menu" aria-labelledby="userMenu">
					<li><a class="dropdown-item" href="profile.jsp">Thông tin
							cá nhân</a></li>
					<li><a class="dropdown-item" href="CustomerHistory">Lịch
							sử đặt phòng</a></li>
					<li><a class="dropdown-item" href="GuestLogout">Đăng xuất</a></li>
				</ul>
			</div>
			<%
			}
			%>

		</div>
	</header>
<body>
	<main class="main">
		<div class="page-title mt-5 pt-5" data-aos="fade">
			<nav class="breadcrumbs">
				<div class="container">
					<ol>
						<li><a href="#">Home</a></li>
						<li class="current">Lịch sử đặt phòng</li>
					</ol>
				</div>
			</nav>
		</div>

		<section id="booking-history" class="section">
			<div class="container section-title" data-aos="fade-up">
				<h2>Lịch sử đặt phòng</h2>
			</div>

			<div class="container" data-aos="fade-up">
				<table class="table table-bordered table-striped">
					<thead class="table-dark">
						<tr>
							<th>ID Đơn Đặt</th>
							<th>ID Khách Hàng</th>
							<th>ID Phòng</th>
							<th>Ngày Đặt</th>
							<th>Ngày Nhận Phòng</th>
							<th>Ngày Trả Phòng</th>
							<th>Số Lượng Người</th>
							<th>Trạng Thái</th>
						</tr>
					</thead>
					<tbody>
						<%
						// Lấy danh sách lịch sử đặt phòng từ request attribute
						ArrayList<Booking> bookingHistory = (ArrayList<Booking>) request.getAttribute("history");
						if (bookingHistory != null) {
							for (Booking booking : bookingHistory) {
						%>
						<tr>
							<td><%=booking.getBookingID()%></td>
							<td><%=booking.getCustomerID()%></td>
							<td><%=booking.getRoomID()%></td>
							<td><%=booking.getCreateDate()%></td>
							<td><%=booking.getStartDate()%></td>
							<td><%=booking.getEndDate()%></td>
							<td><%=booking.getGuestNum()%></td>
							<td><%=booking.getStatus()%></td>
						</tr>
						<%
						}
						} else {
						%>
						<tr>
							<td colspan="8" class="text-center">Không có lịch sử đặt
								phòng</td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
		</section>

	</main>



	<footer id="footer" class="footer dark-background">
		<div class="container">
			<div class="row gy-4">
				<div class="col-lg-9 col-md-9 footer-about d-flex">
					<div>
						<a href="index.html" class="logo d-flex align-items-center"> <span
							class="sitename">GP</span>
						</a>
					</div>
					<div class="pt-3 d-flex">
						<p>A108 Adam Street</p>
						<p>New York, NY 535022</p>
						<p>
							<strong>Phone:</strong> <span>+1 5589 55488 55</span>
						</p>
						<p>
							<strong>Email:</strong> <span>info@example.com</span>
						</p>
					</div>
				</div>
			</div>
			<div class="row  justify-content-end">
				<div class="col-lg-3  footer-about d-flex">
					<a href="/DatPhongKhachSan/AdminAccess" class="stretched-link">Đăng
						nhập quản trị</a>
				</div>
			</div>



		</div>

	</footer>


	<!-- Scroll Top -->
	<a href="#" id="scroll-top"
		class="scroll-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Preloader -->
	<div id="preloader"></div>

	<!-- Vendor JS Files -->
	<script
		src="UserFrontendAssets/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script
		src="UserFrontendAssets/assets/vendor/php-email-form/validate.js"></script>
	<script src="UserFrontendAssets/assets/vendor/aos/aos.js"></script>
	<script
		src="UserFrontendAssets/assets/vendor/swiper/swiper-bundle.min.js"></script>
	<script
		src="UserFrontendAssets/assets/vendor/glightbox/js/glightbox.min.js"></script>
	<script
		src="UserFrontendAssets/assets/vendor/imagesloaded/imagesloaded.pkgd.min.js"></script>
	<script
		src="UserFrontendAssets/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
	<script
		src="UserFrontendAssets/assets/vendor/purecounter/purecounter_vanilla.js"></script>

	<!-- Main JS File -->
	<script src="UserFrontendAssets/assets/js/main.js"></script>

</body>

</html>