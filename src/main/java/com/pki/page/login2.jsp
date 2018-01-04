<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<title>人脸业务应用平台</title>
<%String path = request.getContextPath();%>
<link href="<%=path%>/css/custom.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
	var isChrome = false;
	var initParam = "<\?xml version=\"1.0\" encoding=\"utf-8\"\?><authinfo><liblist><lib type=\"SKF\" version=\"1.1\" dllname=\"U2h1dHRsZUNzcDExXzMwMDBHTS5kbGw=\"><algid val=\"SHA1\" sm2_hashalg=\"SM3\" /></lib><lib type=\"CSP\" version=\"1.0\" dllname=\"RkVJVElBTiBlUGFzc05HIENTUCBGb3IgSklUM0sgVjEuMA==\"><algid val=\"SHA1\" sm2_hashalg=\"SHA1\" /></lib><lib type=\"SKF\" version=\"1.0\" dllname=\"RW50ZXJTYWZlIGVQYXNzMzAwMyBDU1AgdjEuMA==\"><algid val=\"SHA1\" sm2_hashalg=\"SM3\" /></lib><lib type=\"PM\" version=\"1.0\" dllname=\"Q3J5cHRPY3guZGxs\"><algid val=\"SHA1\" sm2_hashalg=\"SM3\" /></lib><lib type=\"CSP\" version=\"1.0\" dllname=\"RUlUSUFOIGVQYXNzTkcgUlNBIENyeXB0b2dyYXBoaWMgU2VydmljZSBQcm92aWRlcg==\"><algid val=\"SHA1\" sm2_hashalg=\"SHA1\" /></lib><lib type=\"CSP\" version=\"1.0\" dllname=\"SklUIFVTQiBLZXkgQ1NQIHYxLjA=\"><algid val=\"SHA1\" sm2_hashalg=\"SHA1\" /></lib><lib type=\"CSP\" version=\"1.0\" dllname=\"RW50ZXJTYWZlIGVQYXNzMjAwMSBDU1AgdjEuMA==\"><algid val=\"SHA1\" sm2_hashalg=\"SHA1\" /></lib><lib type=\"CSP\" version=\"1.0\" dllname=\"SklUIFVTQiBLZXkzMDAzIENTUCB2MS4w\"><algid val=\"SHA1\" sm2_hashalg=\"SHA1\" /></lib></liblist></authinfo>";
</script>
<%
	String browser = request.getHeader("User-Agent").toLowerCase();
	if (browser.indexOf("msie")>=0||browser.indexOf("trident")>=0) {
%>
	<object id="JITDSignOcx" classid="clsid:B0EF56AD-D711-412D-BE74-A751595F3633"  codebase="gw/JITComVCTK_S.cab"></object>
<%}	else if(browser.indexOf("chrome/")>=0&&Integer.parseInt(browser.substring(browser.indexOf("chrome/")+7,browser.indexOf(".",browser.indexOf("chrome/")+7)))>=45){%>
	<script type="text/javascript">isChrome = true;</script>

<%	}else{
%>
	<embed id="JITDSignOcx" type="application/x-jit-sign-vctk-s-plugin-boc" width="0" height="0"/>
<%}%>
</head>
<body class="">

<input type="hidden" name="signed_data" id="signed_data" /> 
<input type="hidden" name="original_jsp" id="original_jsp" />
<div class="loginPage">
  <input type="button" value="PKI 登录" class="logBtn" />
</div>
<script type="text/javascript" src="<%=path%>/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/main.js"></script>
<script type="text/javascript" src="<%=path%>/js/GWUtil.js"></script>
<script type="text/javascript" src="<%=path%>/js/custom.js"></script>
<link href="<%=path%>/static/css/bootstrap.css" type="text/css" rel="stylesheet" />
<link href="<%=path%>/static/css/bootstrap-theme.css" type="text/css" rel="stylesheet" />
<link href="<%=path%>/static/css/custom.css" type="text/css" rel="stylesheet" />
<script>
var companyFlag=1;
$(function(){
	$('#username').focus();
  
    $('.btnRows label').click(function () {
	    $(this).addClass("selected");
	    companyFlag = $(this).attr("value");
	    $(this).siblings().removeClass("selected");
	});
    
    //绑定回车事件
    $("#password").keydown(function(event) {
		if (event.which == 13) {
	    	setTimeout("loginOnClick()", 100);
	   	}
	});
	//绑定回车事件
	$("#username").keydown(function(event) {
		if (event.which == 13) {
	    	setTimeout("loginOnClick()", 100);
	   	}
	});
   
  });
 
//去掉首尾的空格
function trim(str)
{ 
    return str.replace(/(^\s*)|(\s*$)/g, ""); 
}
  
function loginOnClick(){
	var username = $('#username').val();
	var password = $('#password').val();
	//用户名非空验证
	//alert("[" + trim(username)+"]");
	if(trim(username) == "" || trim(username).length == 0){
		$('#userNameErrorMsg').addClass("errorShow");
		$('#userNameErrorMsg').html("登录账户不能为空！");
		$('#username').focus();
		return false;
	}else{
		$('#userNameErrorMsg').removeClass("errorShow");
		$('#userNameErrorMsg').html("");
	}
	//密码非空验证
	if(password == "" || password.length == 0){
		$('#passWordErrorMsg').addClass("errorShow");
		$('#passWordErrorMsg').html("密码不能为空！");
		$('#password').focus();
		return false;
	}else{
		$('#passWordErrorMsg').removeClass("errorShow");
		$('#passWordErrorMsg').html("");
	}
	
	
	var posturl = '${webRoot}/login_validateUser.action';
	$.ajax({
		type:	"Post",					
		url:posturl,
		data:$('#loginForm').serialize(),
		success:function(sysUser){
			sysUser = eval("("+sysUser+")");
			var validateResult = sysUser[0].validateResult;
			if(validateResult == 2){
				$('#passWordErrorMsg').addClass("errorShow");
				$('#passWordErrorMsg').html("用户名或密码错误！");
				$('#username').val("");
				$('#password').val("");
				$('#username').focus();
				return false;
			}
			if(validateResult == 3){
				$.dialog.confirm('连接单点登录服务器失败！是否直接登录?', function(){
					loginOnClick(1);
				}, function(){
					this.close();
					return false;
				});
			}
			if(validateResult == 3001){
				$('#passWordErrorMsg').addClass("errorShow");
				$('#passWordErrorMsg').html("登录IP错误！");
				$('#username').focus();
			}
			if(validateResult == 4){
				$('#passWordErrorMsg').addClass("errorShow");
				$('#passWordErrorMsg').html("登录失败！");
				$('#username').focus();
				return false;
			}
			if(validateResult == 0){
				$('#passWordErrorMsg').removeClass("errorShow");
				$('#passWordErrorMsg').html("");
				if(companyFlag == 2){
					window.location.href="${webRoot}/login_mainyc.action";
				}else{
					window.location.href="${webRoot}/login_mainhx.action";
				}
			}
		}
	});
} 

function pkiLogin(){
	try{
		doDataProcess(initParam);
	}catch(e){
		tip("错误信息："+e);
	}
}
//签名结果
var signResult;
var original = "";
// 根据原文和证书产生认证数据包
function doDataProcess(initParam){
	original = '${original}';
	document.getElementById("original_jsp").value = original;
	// 证书版本者主题
	var signSubject = ""; //document.getElementById("rootCADN").value;
	
	// 验证认证原文是否为空
	if(original == ""){
		alert("认证原文不能为空!");
		return false;
	}else{
		if(isChrome){
			p1(original);
		}else{
			// VCTK初始化参数，数据可从网关系统：认证管理->Key类型管理中导出
			//var initParam = "<\?xml version=\"1.0\" encoding=\"gb2312\"\?><authinfo><liblist><lib type=\"CSP\" version=\"1.0\" dllname=\"\" ><algid val=\"SHA1\" sm2_hashalg=\"sm3\"/></lib><lib type=\"SKF\" version=\"1.1\" dllname=\"SERfR01DQUlTLmRsbA==\" ><algid val=\"SHA1\" sm2_hashalg=\"sm3\"/></lib><lib type=\"SKF\" version=\"1.1\" dllname=\"U2h1dHRsZUNzcDExXzMwMDBHTS5kbGw=\" ><algid val=\"SHA1\" sm2_hashalg=\"sm3\"/></lib><lib type=\"SKF\" version=\"1.1\" dllname=\"U0tGQVBJLmRsbA==\" ><algid val=\"SHA1\" sm2_hashalg=\"sm3\"/></lib></liblist></authinfo>";
			// 调用网关工具脚本中的detachSignStr方法进行签名，返回签名结果
			// 参数说明：initParam：vctk控件初始化参数，authContent：认证原文，signSubject：证书版本者主题
			signResult = detachSignStr(initParam,original,signSubject);
			if(signResult){
				// 设置原文及签名结果
				document.getElementById("original_jsp").value = original;
				document.getElementById("signed_data").value = signResult;
				
				// 页面提交，请求后台认证
				validateUserByPKI();
			}else{
				return false;
			}
		}
	}
}

function sr(data,error){
	if (error){
		tip(error);
		return;
	}
	if(data){
		// 设置原文及签名结果
		document.getElementById("original_jsp").value = original;
		document.getElementById("signed_data").value = data;
		
		// 页面提交，请求后台认证
		validateUserByPKI();
	}else{
		tip("签名结果为空");
	}
}

function p1(ori){
	sign(ori,initParam,'AttachSignStr',sr);
}

function validateUserByPKI(){
	var posturl = '${webRoot}/login_validateUserByPKI.action';
	$.ajax({
		type:	"Post",					
		url:posturl,
		data:$('#loginForm').serialize(),
		success:function(result){
			var arr = result.split(",");
			if(arr[0]=="1"){
				if(companyFlag == 2){
					window.location.href="${webRoot}/login_mainyc.action";
				}else{
					window.location.href="${webRoot}/login_mainhx.action";
				}
			}else{
				tip("登录失败,错误描述："+arr[2]);
			}
		}
	});
}
</script>
</body>
</html>
