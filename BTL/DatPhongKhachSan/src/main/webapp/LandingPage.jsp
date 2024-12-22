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
<link href="${pageContext.request.contextPath}/UserFrontendAssets/assets/css/bootstrap.min.css"
	rel="stylesheet">


<!-- Main CSS File -->
<link href="${pageContext.request.contextPath}/UserFrontendAssets/assets/css/main.css" rel="stylesheet">

<!-- ======================================================= -->
</head>

<body class="index-page">
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
					<li><a href="#hero" class="active">Trang chủ<br></a></li>
					<li><a href="#about">Giới thiệu</a></li>
					<li><a href="#list">Danh sách phòng</a></li>
					<li><a href="#contact">Liên hệ với chúng tôi</a></li>
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

	<main class="main">

		<!-- Hero Section -->
		<section id="hero" class="hero section dark-background">

			<img src="UserFrontendAssets/assets/img/mainBackground.avif" alt=""
				data-aos="fade-in">

			<div class="container">

				<div class="row justify-content-center text-center"
					data-aos="fade-up" data-aos-delay="100">
					<div class="col-xl-6 col-lg-8">
						<h2>
							Tận hưởng không gian yên tĩnh với đầy đủ tiện nghi hiện đại<span>.</span>
						</h2>
						<p>Kỳ nghỉ hoàn hảo tại trung tâm thành phố</p>
					</div>
				</div>
			</div>

		</section>
		<!-- /Hero Section -->

		<!-- About Section -->
		<section id="about" class="about section">

			<div class="container" data-aos="fade-up" data-aos-delay="100">

				<div class="row gy-4">
					<div class="col-lg-6 order-1 order-lg-2">
						<img src="assets/img/roomShowing.jpg" class="img-fluid" alt="">
					</div>
					<div class="col-lg-6 order-2 order-lg-1 content">
						<h3>Khách sạn ra đời với mong muốn mang đến cho khách hàng
							những trải nghiệm nghỉ dưỡng tuyệt vời nhất.</h3>
						<p class="fst-italic">Với hơn 10 năm hoạt động, chúng tôi luôn
							đặt sự hài lòng của khách hàng lên hàng đầu." "Sứ mệnh của chúng
							tôi là mang lại cảm giác thoải mái, tiện nghi và đáng nhớ cho mọi
							kỳ nghỉ của bạn.</p>
						<ul>
							<li><i class="bi bi-check2-all"></i> <span>Dịch vụ
									chu đáo, chuyên nghiệp</span></li>
							<li><i class="bi bi-check2-all"></i> <span>Không gian
									sang trọng, tinh tế</span></li>
							<li><i class="bi bi-check2-all"></i> <span>Hơn 50.000
									lượt khách mỗi năm</span></li>
						</ul>
						<p>Hãy đến và khám phá khách sạn của chúng tôi – nơi mọi trải
							nghiệm đều đáng nhớ</p>
					</div>
				</div>
			</div>

		</section>
		<!-- /About Section -->


		<!-- Room list Section -->
		<section id="list" class="services section">

			<!-- Section Title -->
			<div class="container section-title" data-aos="fade-up">
				<h2>Danh sách phòng</h2>
				<p>Loại phòng</p>
			</div>
			<!-- End Section Title -->

			<div class="container">

				<div class="row gy-4">

					<div class="col-lg-4 col-md-6" data-aos="fade-up"
						data-aos-delay="100">
						<div class="service-item position-relative">
							<div>
								<img
									src="${pageContext.request.contextPath}/UserFrontendAssets/assets/img/smallroom.webp"
									alt="" style="height: 300px; width: 300px;">
							</div>
							<a
								href="${pageContext.request.contextPath}/RoomList?roomTypeID=3"
								class="stretched-link">
								<h3>Phòng Nhỏ</h3>
							</a>
							<p>Phù hợp cho nhu cầu tìm kiếm một khu nghỉ dưỡng
								yên tĩnh.</p>
						</div>
					</div>

					<div class="col-lg-4 col-md-6" data-aos="fade-up"
						data-aos-delay="100">
						<div class="service-item position-relative">
							<div>
								<img
									src="${pageContext.request.contextPath}/UserFrontendAssets/assets/img/mediumroom.jpg"
									alt="" style="height: 300px; width: 300px;">
							</div>
							<a
								href="${pageContext.request.contextPath}/RoomList?roomTypeID=2"
								class="stretched-link">
								<h3>Phòng vừa</h3>
							</a>
							<p>Phù hợp cho nhóm từ 2 đến 3 người</p>
						</div>
					</div>
					<!-- End Service Item -->

					<div class="col-lg-4 col-md-6" data-aos="fade-up"
						data-aos-delay="100">
						<div class="service-item position-relative">
							<div>
								<img
									src="${pageContext.request.contextPath}/UserFrontendAssets/assets/img/largeroom.jpg"
									alt="" style="height: 300px; width: 300px;">
							</div>
							<a
								href="${pageContext.request.contextPath}/RoomList?roomTypeID=1"
								class="stretched-link">
								<h3>Phòng lớn</h3>
							</a>
							<p>Phù hợp cho nhóm từ 4 đến 5 người</p>
						</div>
					</div>
					<!-- End Service Item -->

				</div>

			</div>

		</section>
		<!-- /Services Section -->

		<!-- Contact Section -->
		<section id="contact" class="contact section">

			<!-- Section Title -->
			<div class="container section-title" data-aos="fade-up">
				<h2>Liên hệ</h2>
				<p>Có thắc mắc? Liên hệ ngay với chúng tôi tại</p>
			</div>
			<!-- End Section Title -->

			<div class="container" data-aos="fade-up" data-aos-delay="100">

				<div class="mb-4" data-aos="fade-up" data-aos-delay="200">
					<iframe style="border: 0; width: 100%; height: 270px;"
						src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d48389.78314118045!2d-74.006138!3d40.710059!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x89c25a22a3bda30d%3A0xb89d1fe6bc499443!2sDowntown%20Conference%20Center!5e0!3m2!1sen!2sus!4v1676961268712!5m2!1sen!2sus"
						frameborder="0" allowfullscreen="" loading="lazy"
						referrerpolicy="no-referrer-when-downgrade"></iframe>
				</div>
				<!-- End Google Maps -->

				<div class="row gy-4">

					<div class="col-lg-4">
						<div class="info-item d-flex" data-aos="fade-up"
							data-aos-delay="300">
							<i class="bi bi-geo-alt flex-shrink-0"></i>
							<div>
								<h3>Địa chỉ</h3>
								<p>Hà Nội</p>
							</div>
						</div>
						<!-- End Info Item -->

						<div class="info-item d-flex" data-aos="fade-up"
							data-aos-delay="400">
							<i class="bi bi-telephone flex-shrink-0"></i>
							<div>
								<h3>Gọi ngay</h3>
								<p>+1 5589 55488 55</p>
							</div>
						</div>
						<!-- End Info Item -->

						<div class="info-item d-flex" data-aos="fade-up"
							data-aos-delay="500">
							<i class="bi bi-envelope flex-shrink-0"></i>
							<div>
								<h3>Hòm thư điện tử</h3>
								<p>info@example.com</p>
							</div>
						</div>
						<!-- End Info Item -->


					</div>

				</div>

			</div>

		</section>
		<!-- /Contact Section -->

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


	<!-- Login Modal -->
	<div class="modal fade" id="login-modal" tabindex="-1"
		aria-labelledby="loginModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="GuestLogin" method="POST">
					<div class="modal-header">
						<h5 class="modal-title" id="loginModalLabel">Đăng Nhập</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<div class="mb-3">
							<label for="log-email" class="form-label">Email</label> <input
								type="text" class="form-control" id="email" name="email"
								required>
						</div>
						<div class="mb-3">
							<label for="log-password" class="form-label">Mật khẩu</label> <input
								type="password" class="form-control" id="pass" name="pass"
								required>
						</div>
						<div class="text-center">
							<span>Chưa có tài khoản? <a href="#"
								id="open-register-modal">Đăng ký</a>
							</span>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">Đăng Nhập</button>
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Hủy</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="modal fade" id="register-modal" tabindex="-1"
		aria-labelledby="registerModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="Register" method="POST">
					<div class="modal-header">
						<h5 class="modal-title" id="registerModalLabel">Đăng Ký</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<div class="mb-3">
							<label for="reg-name" class="form-label">Họ Tên</label> <input
								type="text" class="form-control" id="reg-name" name="reg-name"
								required>
						</div>
						<div class="mb-3">
							<label for="reg-email" class="form-label">Email</label> <input
								type="email" class="form-control" id="reg-email"
								name="reg-email" required>
						</div>
						<div class="mb-3">
							<label for="reg-password" class="form-label">Mật khẩu</label> <input
								type="password" class="form-control" id="reg-password"
								name="reg-password" required>
						</div>
						<div class="mb-3">
							<label for="reg-phone" class="form-label">Số điện
								thoại</label> <input type="phone" class="form-control" id="reg-phone"
								name="reg-phone" required>
						</div>
						<div class="mb-3">
							<label for="reg-address" class="form-label">Địa chỉ</label> <input
								type="address" class="form-control" id="reg-address"
								name="reg-address" required>
						</div>
						<div class="text-center">
							<span>Đã có tài khoản? <a href="#" id="open-login-modal">Đăng
									nhập</a>
							</span>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">Đăng Ký</button>
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Hủy</button>
					</div>
				</form>
			</div>
		</div>
	</div>



	<script>
		document.getElementById("open-register-modal").addEventListener(
				"click",
				function(event) {
					event.preventDefault(); // Ngăn chặn hành vi mặc định
					var loginModal = bootstrap.Modal.getInstance(document
							.getElementById("login-modal"));
					loginModal.hide(); // Đóng modal đăng nhập

					var registerModal = new bootstrap.Modal(document
							.getElementById("register-modal"));
					registerModal.show(); // Mở modal đăng ký
				});

		document.getElementById("open-login-modal").addEventListener(
				"click",
				function(event) {
					event.preventDefault(); // Ngăn chặn hành vi mặc định
					var registerModal = bootstrap.Modal.getInstance(document
							.getElementById("register-modal"));
					registerModal.hide(); // Đóng modal đăng ký

					var loginModal = new bootstrap.Modal(document
							.getElementById("login-modal"));
					loginModal.show(); // Mở modal đăng nhập
				});
	</script>




	<!-- Scroll Top -->
	<a href="#" id="scroll-top"
		class="scroll-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>


	<!-- Vendor JS Files -->
	<script
		src="${pageContext.request.contextPath}/UserFrontendAssets/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/UserFrontendAssets/assets/vendor/php-email-form/validate.js"></script>
	<script src="${pageContext.request.contextPath}/UserFrontendAssets/assets/vendor/aos/aos.js"></script>
	<script
		src="${pageContext.request.contextPath}/UserFrontendAssets/assets/vendor/swiper/swiper-bundle.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/UserFrontendAssets/assets/vendor/glightbox/js/glightbox.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/UserFrontendAssets/assets/vendor/imagesloaded/imagesloaded.pkgd.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/UserFrontendAssets/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/UserFrontendAssets/assets/vendor/purecounter/purecounter_vanilla.js"></script>

	<!-- Main JS File -->
	<script src="${pageContext.request.contextPath}/UserFrontendAssets/assets/js/main.js"></script>

</body>

</html>