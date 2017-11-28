<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html><head>
<title>상품등록</title>

<link href="/css/admin.css" rel="stylesheet" type="text/css">

</head>

<body text="#000000" bgcolor="#ffffff">

<table width="100%" height="37" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15" height="37">
			<img width="15" height="37" src="/images/ct_ttl_img01.gif">
		</td>
		<td width="100%" background="/images/ct_ttl_img02.gif" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tbody><tr>
					<td width="93%" class="ct_ttl01">상품등록</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img width="12" height="37" src="/images/ct_ttl_img03.gif">
		</td>
	</tr>
</tbody></table>

<table width="100%" style="margin-top: 13px;" border="0" cellspacing="0" cellpadding="0">
	<tbody><tr>
		<td height="1" bgcolor="#d6d6d6" colspan="3"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			상품명 <img width="3" height="3" align="absmiddle" src="/images/ct_icon_red.gif">
		</td>
		<td width="1" bgcolor="#d6d6d6"></td>
		<td class="ct_write01">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tbody><tr>
					<td width="105">${product.prodName}<td/>
					<td></td>
				</tr>
			</tbody></table>
		</td>
	</tr>
	<tr>
		<td height="1" bgcolor="#d6d6d6" colspan="3"></td>
	</tr>

	<tr>
		<td width="104" class="ct_write">
			상품상세정보 <img width="3" height="3" align="absmiddle" src="/images/ct_icon_red.gif">
		</td>
		<td width="1" bgcolor="#d6d6d6"></td>
		<td class="ct_write01">${product.prodDetail}</td>
	</tr>
	<tr>
		<td height="1" bgcolor="#d6d6d6" colspan="3"></td>
	</tr>

	<tr>
		<td width="104" class="ct_write">
			제조일자<img width="3" height="3" align="absmiddle" src="/images/ct_icon_red.gif">
		</td>
		<td width="1" bgcolor="#d6d6d6"></td>
		<td class="ct_write01">${product.manuDate}</td>
	</tr>
	<tr>
		<td height="1" bgcolor="#d6d6d6" colspan="3"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			가격<img width="3" height="3" align="absmiddle" src="/images/ct_icon_red.gif">
		</td>
		<td width="1" bgcolor="#d6d6d6"></td>
		<td class="ct_write01">${product.price}</td>
	</tr>

	<tr>
		<td height="1" bgcolor="#d6d6d6" colspan="3"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">상품이미지</td>
		<td width="1" bgcolor="#d6d6d6"></td>
		<td class="ct_write01">
			<!-- 테이블 시작 -->
			<table border="0" cellspacing="0" cellpadding="0">
				<tbody><tr>
					<td height="26">
						<img src="../WebContent/images/uploadFiles/${product.fileName}">
					</td>
				</tr>
			</tbody></table>
		</td>
	</tr>
	<tr>
		<td height="1" bgcolor="#d6d6d6" colspan="3"></td>
	</tr>
</tbody></table>

<table width="100%" style="margin-top: 10px;" border="0" cellspacing="0" cellpadding="0">
	<tbody><tr>
		<td width="53%"></td>
		<td align="right">
			<table border="0" cellspacing="0" cellpadding="0">
				<tbody><tr>					
					<td width="17" height="23">
						<img width="17" height="23" src="/images/ct_btnbg01.gif">
					</td>
					<td class="ct_btn01" background="/images/ct_btnbg02.gif" style="padding-top: 3px;">
						<a href="/product/listProduct?menu=manage">확인</a>
					</td>
					<td width="14" height="23">
						<img width="14" height="23" src="/images/ct_btnbg03.gif">
					</td>
					<td width="17" height="23">
						<img width="17" height="23" src="/images/ct_btnbg01.gif">
					</td>
					<td class="ct_btn01" background="/images/ct_btnbg02.gif" style="padding-top: 3px;">
						<a href="../product/addProductView.jsp;">추가등록</a>
					</td>
					<td width="14" height="23">
						<img width="14" height="23" src="/images/ct_btnbg03.gif">
					</td>
				</tr>
			</tbody></table>
		</td>
	</tr>
</tbody></table>



</body></html>