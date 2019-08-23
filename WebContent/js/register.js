function test(obj){
	//对电子邮件的验证
	var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if(!myreg.test(obj))
	{
	alert('请输入有效的邮箱！');
	return false;
	}
	}
	//手机号验证
	function validatemobile(mobile) 
	{ 
	if(mobile.length==0) 
	{ 
	alert('手机号码不能为空！');
	
	} 
	if(mobile.length!=11) 
	{ 
	alert('请输入有效的手机号码，需是11位！');
	
	} 

	var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
	if(!myreg.test(mobile)) 
	{ 
	alert('请输入有效的手机号码！'); 
	return false; 
	} 
	} 
	

	//检验两次输入的密码是否相同
	function check(){ 
	with(document.all){ 
		var input1 = $("#psw1").val();
		var input2 = $("#psw2").val();
	
	if(input1!=input2) 
	{ 
	alert("密码不一致") 
	input1.value = ""; 
	input2.value = ""; 
	} 
	else {
	
	}
	}
	} 

$(document).ready(function(){
		
		$(".btnReg").click(function(){
			var email = $("#email").val();
			var phone = $("#phone").val();
			var name = $("#name").val();
			var pwd =  $("#psw1").val();
			test(email);
			validatemobile(phone);
			check();
			var json = '{"email":"'+email+'","username":"'+name+'","password":"'+pwd+'","phone":"'+phone+'"}'
			if(email.length==0||phone.length==0||pwd.length==0||name.length==0){
				alert("输入不能为空！");
				return false;
			}else{
				$.ajax({
					url:'register',
				        	data:{'registe':json},
				        	type:"post",
				        	dataType:"json",
				        	success:function(data){
				        		alert("注册成功");
				        	},
				        	error:function(data){		        		
				        	}
				});
			}
			
		})		
	})