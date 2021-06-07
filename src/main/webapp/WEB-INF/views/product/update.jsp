<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<c:import url="../template/setting.jsp"></c:import>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<title>Hello, world!</title>
</head>
<body>
	<h2>Product Update Page</h2>
	<form action="./update" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="productNum" value="${vo.productNum}">

		<div>
			<label>상품 이미지</label>
			<div id="thumbnail">
				<div id="img_container"></div>
				<div id="inputimg">
					<div>
						<input type="file" name="file" id="images" multiple
							onchange="setThumbnail(event);"
							value="/resources/upload/product/${file.fileName}"> <input
							type="button" id="del" value="Delete">
					</div>
				</div>
			</div>
			<button type="button" id="add" value="ADD">ADD</button>
		</div>

		<c:forEach items="${files}" var="files">
			<div class="uploadResult">
				<a href="#">${files.originName}</a><input type="button" value="X">
				<img src="/resources/upload/product/${files.fileName}" width="10%"
					height="10%">
			</div>
		</c:forEach>



		<div class="form-group">
			<label>상품 명</label> <input type="text" name="productName"
				value="${vo.productName}">
		</div>
		<div class="form-group">
			<label>판매자</label> <input type="text" name="username"
				value="${vo.username}">
		</div>
		<div>
			<div class="form-group">
				<label for="category">카테고리</label> <select class="form-control"
					id="category" name="categoryCode" value="${vo.categoryCode}">
					<option value="1">디지털/가전</option>
					<option value="2">가구/인테리어</option>
					<option value="3">유아동/유아도서</option>
					<option value="4">생활/가공식품</option>
					<option value="5">스포츠/레저</option>
					<option value="6">여성의류/잡화</option>
					<option value="7">남성의류/잡화</option>
					<option value="8">게임/취미</option>
					<option value="9">뷰티/미용</option>
					<option value="10">반려동물용품</option>
					<option value="11">도서/티켓/음반</option>
					<option value="12">삽니다</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label>상품 설명</label> <input type="text" name="productContent"
				value="${vo.productContent}">
		</div>
		<div class="form-group">
			<label>상품 가격</label> <input type="text" name="productPrice"
				value="${vo.productPrice}">
		</div>

		<button class="btn btn-outline-secondary">Write</button>
		<br>
		<br>
		<br>
	</form>

	<script type="text/javascript" src="../resources/js/fileAdd.js"></script>
</body>
</html>
