<%@page import="java.util.ArrayList"%>
<%@page import="model.Room"%>
<%@page import="controller.roomController.*"%>
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

<style>
.card {
	width: 100%; /* Đảm bảo card chiếm toàn bộ chiều rộng của cột */
	max-width: 500px; /* Giới hạn chiều rộng tối đa của card */
	height: 100%; /* Đặt chiều cao card chiếm toàn bộ chiều cao của cột */
	display: flex; /* Sử dụng flexbox để căn giữa nội dung */
	flex-direction: column; /* Xếp chồng nội dung theo chiều dọc */
	justify-content: space-between; /* Đảm bảo khoảng cách giữa các phần */
	border: none; /* Bỏ viền thẻ card nếu cần */
	transition: transform 0.2s; /* Thêm hiệu ứng hover */
}

.card:hover {
	transform: scale(1.05); /* Tăng kích thước thẻ khi hover */
}

.card-img-top {
	height: 250px; /* Chiều cao cố định cho ảnh */
	width: 100%; /* Đảm bảo ảnh chiếm toàn bộ chiều rộng của card */
	object-fit: cover; /* Giữ tỉ lệ ảnh mà không bị méo */
}

.card-body {
	flex-grow: 1; /* Đảm bảo phần body chiếm không gian còn lại */
	display: flex;
	align-items: center; /* Căn giữa nội dung trong body */
	justify-content: center; /* Căn giữa nội dung trong body */
}
</style>
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

	<div>


		<!-- Room list Section -->
		<section id="room-list" class="py-5 pt-5 mt-5">
			<div class="container">
				<h2 class="text-center mb-5">Danh sách phòng</h2>
				<div class="row g-4">
					<%
					ArrayList<Room> roomList = (ArrayList<Room>) request.getAttribute("roomList");
					for (Room room : roomList) {
					%>
					<div class="col-lg-4 col-md-6 col-sm-12">
						<div class="card h-100 text-center"
							style="<%=room.getStatus().equals("DaDat") ? "opacity: 0.5;" : ""%>">
							<%
							if (room.getStatus().equals("DaDat")) {
							%>
							<img src="<%=room.getRoomImg()%>" class="card-img-top"
								alt="<%=room.getRoomName()%>"
								style="height: 250px; width: 100%; object-fit: cover;">
							<div class="card-body">
								<h5 class="card-title"><%=room.getRoomName()%></h5>
								<p class="text-danger">Hết phòng</p>
							</div>
							<%
							} else {
							%>
							<a
								href="<%=request.getContextPath()%>/DetailedRoom?roomID=<%=room.getRoomID()%>">
								<img src="<%=room.getRoomImg()%>" class="card-img-top"
								alt="<%=room.getRoomName()%>"
								style="height: 250px; width: 100%; object-fit: cover;">
								<div class="card-body">
									<h5 class="card-title"><%=room.getRoomName()%></h5>
								</div>
							</a>
							<%
							}
							%>
						</div>
					</div>
					<%
					}
					%>
				</div>
			</div>
		</section>



	</div>

	<footer id="footer" class="footer dark-background pt-5 mt-5">
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
