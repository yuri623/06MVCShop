<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>
<title>상품등록</title>

<link href="/css/admin.css" rel="stylesheet" type="text/css">

<script src="../javascript/calendar.js" type="text/javascript">
</script>

<script type="text/javascript">
<!--
function fncAddProduct(){
	//Form 유효성 검증
 	var name = document.detailForm.prodName.value;
	var detail = document.detailForm.prodDetail.value;
	var manuDate = document.detailForm.manuDate.value;
	var price = document.detailForm.price.value;

	if(name == null || name.length<1){
		alert("상품명은 반드시 입력하여야 합니다.");
		return;
	}
	if(detail == null || detail.length<1){
		alert("상품상세정보는 반드시 입력하여야 합니다.");
		return;
	}
	if(manuDate == null || manuDate.length<1){
		alert("제조일자는 반드시 입력하셔야 합니다.");
		return;
	}
	if(price == null || price.length<1){
		alert("가격은 반드시 입력하셔야 합니다.");
		return;
	}

	document.detailForm.action='/product/addProduct';
	document.detailForm.submit();
}

function resetData(){
	document.detailForm.reset();
}
-->
</script>
</head>

<form name="detailForm" method="post">

<table width="100%" height="37" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15" height="37">
			<img width="15" height="37" src="/images/ct_ttl_img01.gif">
		</td>
		<td width="100%" background="/images/ct_ttl_img02.gif" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">상품등록</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img width="12" height="37" src="/images/ct_ttl_img03.gif">
		</td>
	</tr>
</table>

<table width="100%" style="margin-top: 13px;" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="1" bgcolor="#d6d6d6" colspan="3"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			상품명 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td width="1" bgcolor="#d6d6d6"></td>
		<td class="ct_write01">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="105">
						<input name="prodName" class="ct_input_g" style="width: 100px; height: 19px;" type="text" maxlength="20">
					</td>
				</tr>
			</table>
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
		<td class="ct_write01">
			<input name="prodDetail" class="ct_input_g" style="width: 100px; height: 19px;" type="text" maxlength="10" minlength="6">
		</td>
	</tr>
	<tr>
		<td height="1" bgcolor="#d6d6d6" colspan="3"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			제조일자 <img width="3" height="3" align="absmiddle" src="/images/ct_icon_red.gif">
		</td>
		<td width="1" bgcolor="#d6d6d6"></td>
		<td class="ct_write01">
			<input name="manuDate" class="ct_input_g" style="width: 100px; height: 19px;" type="text" maxlength="10" readonly="readonly" minlength="6">
				&nbsp;<img width="15" height="15" onclick="show_calendar('document.detailForm.manuDate', document.detailForm.manuDate.value)" src="../images/ct_icon_date.gif">
		</td>
	</tr>
	<tr>
		<td height="1" bgcolor="#d6d6d6" colspan="3"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			가격 <img width="3" height="3" align="absmiddle" src="/images/ct_icon_red.gif">
		</td>
		<td width="1" bgcolor="#d6d6d6"></td>
		<td class="ct_write01">
			<input name="price" class="ct_input_g" style="width: 100px; height: 19px;" type="text" maxlength="10">&nbsp;원
		</td>
	</tr>
	<tr>
		<td height="1" bgcolor="#d6d6d6" colspan="3"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">상품이미지</td>
		<td width="1" bgcolor="#d6d6d6"></td>
		<td class="ct_write01">
			<input name="fileName" class="ct_input_g" style="width: 200px; height: 19px;" type="file" maxlength="13">
		</td>
	</tr>
	<tr>
		<td height="1" bgcolor="#d6d6d6" colspan="3"></td>
	</tr>
</table>

<table width="100%" style="margin-top: 10px;" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="53%"></td>
		<td align="right">
		<table border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="17" height="23">
					<img width="17" height="23" src="/images/ct_btnbg01.gif">
				</td>
				<td class="ct_btn01" background="/images/ct_btnbg02.gif" style="padding-top: 3px;">
					<a href="javascript:fncAddProduct();">등록</a>
				</td>
				<td width="14" height="23">
					<img width="14" height="23" src="/images/ct_btnbg03.gif">
				</td>
				<td width="30"></td>
				<td width="17" height="23">
					<img width="17" height="23" src="/images/ct_btnbg01.gif">
				</td>
				<td class="ct_btn01" background="/images/ct_btnbg02.gif" style="padding-top: 3px;">
					<a href="javascript:resetData();">취소</a>
				</td>
				<td width="14" height="23">
					<img width="14" height="23" src="/images/ct_btnbg03.gif">
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>

</form>


</body></html>