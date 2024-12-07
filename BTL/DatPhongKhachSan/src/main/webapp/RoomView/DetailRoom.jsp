<%@page import="java.util.ArrayList"%>
<%@page import="model.Room"%>
<%@page import="controller.roomController.*"%>
<%@page import="dao.*"%>
<%@page import="db.*"%>
<%
Room room = (Room) request.getAttribute("room");
%>
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

	<div>

		<main class="main">
			<div class="container my-5 pt-5">
				<div class="row">
					<!-- Phần hiển thị thông tin phòng -->
					<div class="col-lg-8">
						<div class="mb-4">
							<img src="<%=room.getRoomImg()%>" alt="Hình ảnh phòng"
								class="img-fluid rounded">
						</div>
						<h2 class="mb-3"><%=room.getRoomName()%></h2>
						<p class="text-muted">
							Loại phòng:
							<%
						String roomType = "";
						int roomTypeID = room.getRoomTypeID();

						if (roomTypeID == 1) {
							roomType = "Lớn";
						} else if (roomTypeID == 2) {
							roomType = "Vừa";
						} else if (roomTypeID == 3) {
							roomType = "Nhỏ";
						}
						%>
							<%=roomType%>
							| Sức chứa:
							<%=room.getMaxPerson()%>
							người
						</p>

						<h4>Mô tả</h4>
						<p>
							<!-- Thêm mô tả phòng nếu có -->
							Phòng được thiết kế sang trọng, hiện đại với đầy đủ tiện nghi.
						</p>
						<ul>
							<li>Diện tích: 35m²</li>
							<li>Trang bị: TV màn hình phẳng, minibar, két sắt</li>
							<li>Phòng tắm riêng với bồn tắm và vòi sen</li>
							<li>Dịch vụ dọn phòng hàng ngày</li>
						</ul>
					</div>

					<!-- Mini form đặt phòng -->
					<div class="col-lg-4">
						<div class="card">
							<div class="card-body">
								<h5 class="card-title text-center mb-4">Đặt Phòng Nhanh</h5>
								<form id="bookingForm">
									<!-- Tên phòng -->
									<div class="mb-3">
										<label for="roomName" class="form-label">Tên phòng</label> <input
											type="text" class="form-control" id="roomName"
											name="roomName" value="<%=room.getRoomName()%>" readonly>
									</div>
									<div class="mb-3">
										<label for="roomID" class="form-label">ID phòng</label> <input
											type="text" class="form-control" id="roomID" name="roomID"
											value="<%=room.getRoomID()%>" readonly>
									</div>

									<!-- Số lượng người ở -->
									<div class="mb-3">
										<label class="form-label">Số lượng người ở</label> <input
											type="number" class="form-control" id="guestCount"
											name="guestCount" value="1" min="1"
											max="<%=room.getMaxPerson()%>" required>
									</div>

									<!-- Ngày bắt đầu -->
									<div class="mb-3">
										<label for="startDate" class="form-label">Ngày bắt đầu</label>
										<input type="date" class="form-control" id="startDate"
											name="startDate" required>
									</div>

									<!-- Ngày kết thúc -->
									<div class="mb-3">
										<label for="endDate" class="form-label">Ngày kết thúc</label>
										<input type="date" class="form-control" id="endDate"
											name="endDate" required>
									</div>

									<!-- Nút đặt phòng -->
									<div class="d-grid">
										<input type="hidden" name="roomID"
											value="<%=room.getRoomID()%>">
										<button type="button" id="btnBookRoom" class="btn btn-primary">Đặt
											phòng</button>
									</div>
								</form>
							</div>
						</div>
					</div>

				</div>
			</div>
		</main>


	</div>

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

	<div class="modal fade" id="successModal" tabindex="-1"
		aria-labelledby="successModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="successModalLabel">Thông báo</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">Đặt phòng thành công! Bạn sẽ được
					chuyển hướng trong giây lát...</div>
			</div>
		</div>
	</div>

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

	<div class="modal" id="confirmBookingModal" tabindex="-1"
		aria-labelledby="confirmBookingModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="confirmBookingModalLabel">Xác nhận
						đặt phòng</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p>
						ID phòng: <span id="confirmRoomId"></span>
					</p>
					<p>
						Tên phòng: <span id="confirmRoomName"></span>
					</p>
					<p>
						Số lượng khách: <span id="confirmGuestCount"></span>
					</p>
					<p>
						Ngày bắt đầu: <span id="confirmStartDate"></span>
					</p>
					<p>
						Ngày kết thúc: <span id="confirmEndDate"></span>
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Hủy</button>
					<button type="button" class="btn btn-primary" id="confirmBooking">Xác
						nhận</button>
				</div>
			</div>
		</div>
	</div>


	<script>
    document.getElementById("btnBookRoom").addEventListener("click", function() {
        var guestCount = document.getElementById("guestCount").value;
        var startDate = document.getElementById("startDate").value;
        var endDate = document.getElementById("endDate").value;
        var roomName = document.getElementById("roomName").value;
        var roomID = document.getElementById("roomID").value;

        // Kiểm tra trạng thái đăng nhập
        <%if (mail == null) {%>
            alert("Bạn chưa đăng nhập. Vui lòng đăng nhập trước khi đặt phòng.");
            location.reload(); // Reload trang hiện tại
        <%} else {%>
            // Kiểm tra thông tin đầy đủ
            if (!guestCount || !startDate || !endDate || !roomName) {
                alert("Bạn chưa điền đầy đủ thông tin. Vui lòng kiểm tra lại.");
                location.reload(); // Reload trang hiện tại
            } else {
                // Hiển thị thông tin trong modal xác nhận
                document.getElementById("confirmGuestCount").textContent = guestCount;
                document.getElementById("confirmStartDate").textContent = startDate;
                document.getElementById("confirmEndDate").textContent = endDate;
                document.getElementById("confirmRoomName").textContent = roomName;
                document.getElementById("confirmRoomId").textContent = roomID;


                // Mở modal xác nhận
                var confirmBookingModal = new bootstrap.Modal(document.getElementById("confirmBookingModal"));
                confirmBookingModal.show();
            }
        <%}%>
    });
</script>


	<script>
document.getElementById("confirmBooking").addEventListener("click", function() {
    // Lấy thông tin từ các thẻ span trong modal
    const upRoomId = document.getElementById('confirmRoomId').innerText; // Lấy ID phòng
    const upRoomName = document.getElementById('confirmRoomName').innerText; // Lấy tên phòng
    const upGuestCount = document.getElementById('confirmGuestCount').innerText; // Lấy số lượng khách
    const upStartDate = document.getElementById('confirmStartDate').innerText; // Lấy ngày bắt đầu
    const upEndDate = document.getElementById('confirmEndDate').innerText; // Lấy ngày kết thúc

    // Tạo một đối tượng FormData để gửi dữ liệu
    const formData = new FormData();
    formData.append('upRoomId', upRoomId); // Thêm ID phòng vào formData
    formData.append('upRoomName', upRoomName); // Thêm tên phòng vào formData
    formData.append('upGuestCount', upGuestCount); // Thêm số lượng khách vào formData
    formData.append('upStartDate', upStartDate); // Thêm ngày bắt đầu vào formData
    formData.append('upEndDate', upEndDate); // Thêm ngày kết thúc vào formData

    // Gửi dữ liệu đến BookingServlet
    fetch("BookingServlet", {
        method: "POST",
        body: formData
    })
    .then(response => response.text()) // Chờ nhận phản hồi dạng text
    .then(result => {
        // Kiểm tra xem phản hồi có chứa thông báo thành công không
    	if (result.includes("Đặt phòng thành công!")) {
            // Hiển thị modal
            var bookingModal = bootstrap.Modal.getInstance(document.getElementById('confirmBookingModal'));
            bookingModal.hide(); // Đóng modal hiện tại
					
            var modal = new bootstrap.Modal(document.getElementById('successModal'));
            modal.show();

            // Chuyển hướng sau 3 giây
            setTimeout(function() { 
                window.location.href = 'CustomerHistory'; 
            }, 3000); 
        } else {
            // Nếu có lỗi, bạn có thể thông báo cho người dùng
            alert("Đặt phòng thất bại! Vui lòng thử lại sau.");
        }

    })
    .catch(error => {
        console.error('Lỗi:', error);
        alert("Có lỗi xảy ra! Vui lòng thử lại sau.");
    });

});
</script>



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