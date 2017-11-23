<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--
	function fncGetUserList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
		document.detailForm.submit();
	}
	-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

	<div style="width: 98%; margin-left: 10px;">

		<form name="detailForm" action="/listProduct.do?menu=${param.menu}&order=${search.order}"
			method="post">

			<table width="100%" height="37" border="0" cellpadding="0"
				cellspacing="0">
					<tr>
						<td width="15" height="37">
						<img src="/images/ct_ttl_img01.gif" width="15" height="37">
						</td>
						<td background="/images/ct_ttl_img02.gif" width="100%"
							style="padding-left: 10px;">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="93%" class="ct_ttl01">
									${param.menu == 'search'? '상품 목록조회' : '상품 관리'}
									</td>
								</tr>
							</table>
						</td>
						<td width="12" height="37">
						<img src="/images/ct_ttl_img03.gif" width="12" height="37">
						</td>
					</tr>
			</table>


			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<c:if test="${!empty search.searchCondition}">
						<td align="right">
						<select name="searchCondition" class="ct_input_g" style="width: 80px">
								<c:if test="${search.searchCondition == 0}">
									<option value="0" selected>상품번호</option>
									<option value="1">상품명</option>
									<option value="2">상품가격</option>
								</c:if>
								<c:if test="${search.searchCondition == 1}">
									<option value="0">상품번호</option>
									<option value="1" selected>상품명</option>
									<option value="2">상품가격</option>
								</c:if>
								<c:if test="${search.searchCondition == 2}">
									<option value="0">상품번호</option>
									<option value="1">상품명</option>
									<option value="2" selected>상품가격</option>
								</c:if>
						</select> 
						<input type="text" name="searchKeyword"
							value="${! empty search.searchKeyword ? search.searchKeyword : ""}" class="ct_input_g"
							style="width: 200px; height: 19px">
						</td>
					</c:if>
					<c:if test="${empty search.searchCondition}">
						<td align="right">
						<select name="searchCondition" class="ct_input_g" style="width: 80px">
								<option value="0" selected>상품번호</option>
								<option value="1">상품명</option>
								<option value="2">상품가격</option>
						</select> 
						<input type="text" name="searchKeyword"
							value="${! empty search.searchKeyword ? search.searchKeyword : ""}" class="ct_input_g"
							style="width: 200px; height: 20px">
						</td>
					</c:if>
					<td align="right" width="70">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="17" height="23">
								<img src="/images/ct_btnbg01.gif" width="17" height="23"></td>
								<td background="/images/ct_btnbg02.gif" class="ct_btn01"
									style="padding-top: 3px;">
									<a href="javascript:fncGetUserList('1');">검색</a>
								</td>
								<td width="14" height="23">
								<img src="/images/ct_btnbg03.gif" width="14" height="23"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td colspan="7">전체 ${resultPage.totalCount}건수, 현재
						${resultPage.currentPage} 페이지
						</td>
						<td colspan="4" align="right">
						<a href="/listProduct.do?menu=${param.menu }&order=prod_name">상품명</a>
						&nbsp;<a href="/listProduct.do?menu=${param.menu }&order=manufacture_day">신상품순</a>
						&nbsp;<a href = "/listProduct.do?menu=${param.menu }&order=price">가격낮은순</a>
						
						&nbsp;&nbsp;
						<select name="pageSize" class="ct_input_g" style="width: 80px">
							<option value="3" ${param.pageSize == 3? 'selected':''}>3개씩 보기</option>
							<option value="5" ${param.pageSize==5? 'selected':''}>5개씩 보기</option>
							<option value="10" ${param.pageSize==10? 'selected':''}>10개씩 보기</option>
							<option value="15" ${param.pageSize==15? 'selected':''}>15개씩 보기</option>
							<option value="20" ${param.pageSize==20? 'selected':''}>20개씩 보기</option>
						</select>
							<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="17" height="23">
								<img src="/images/ct_btnbg01.gif" width="17" height="23"></td>
								<td background="/images/ct_btnbg02.gif" class="ct_btn01"
									style="padding-top: 3px;">
									<a href="javascript:fncGetUserList('1');">확인</a>
								</td>
								<td width="14" height="23">
								<img src="/images/ct_btnbg03.gif" width="14" height="23"></td>
							</tr>
						</table>
						</td>
				</tr>
				<tr>
					<td class="ct_list_b" width="100">No</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">상품명</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">가격</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">등록일</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">현재상태</td>
				</tr>
				<tr>
					<td colspan="11" bgcolor="808285" height="1"></td>
				</tr>
				<c:set var="n" value="0"/>
				<c:forEach var="i" items="${list}">
					<c:set var = "n" value="${n+1}"/>
					<tr class="ct_list_pop">
						<td align="center">${n}</td>
						<td></td>
						<td align="left"><c:if
								test="${param.menu == 'search' && !empty i.proTranCode }">
								${i.prodName}
							</c:if> 
							<c:if test="${param.menu != 'search' || empty i.proTranCode }">
								<a href="/getProduct.do?prodNo=${i.prodNo}&amp;menu=${param.menu}">${i.prodName}</a></td>
							</c:if>
						<td></td>
						<td align="left">${i.price}</td>
						<td></td>
						<td align="left">${i.manuDate}</td>
						<td></td>
						<td align="left">
							<c:if test="${user.role == 'admin' && !empty i.proTranCode}">
								<c:if test="${i.proTranCode == '1  '}">구매완료
									<c:if test="${param.menu == 'manage'}">
										<a href="/updateTranCodeByProd.do?prodNo=${i.prodNo}&tranCode=2">배송하기</a>
									</c:if>
							</c:if>
							<c:if test="${i.proTranCode == '2  '}">
							배송중
							</c:if>
								<c:if test="${i.proTranCode == '3  '}">
							배송완료
							</c:if>
							</c:if> <c:if test="${user.role != 'admin' || empty i.proTranCode}">
								<c:if test="${empty i.proTranCode}">
								판매중
							</c:if>
								<c:if test="${!empty i.proTranCode}">
								재고없음
							</c:if>
							</c:if></td>
					</tr>
					<tr>
						<td colspan="11" bgcolor="D6D7D6" height="1"></td>
					</tr>
				</c:forEach>
				</table>
				
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					style="margin-top: 10px;">
					<tr>
						<td align="center">
						<input type="hidden" id="currentPage" name="currentPage" value="" />
						<jsp:include page="../common/pageNavigator.jsp" />
						</td>
					</tr>
				</table>
				<!--  페이지 Navigator 끝 -->
		</form>

	</div>


</body>
</html>