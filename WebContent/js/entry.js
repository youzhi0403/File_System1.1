
$(document).ready(function(){
	 $('#error').click(function(){
	        var user = $('input[name=user]');
	        var pwd = $('input[name=pwd]');

	        if(user.val() == ''|| pwd.val() == '')
	        {
	            $('[name=user]').focus();
	           alert("<strong>用户名或密码不能为空<strong>");
	            return false;

	        }
	        else{
	        $.ajax({
	        	url:'login',
	        	data:{
	        		'account':user.val(),
	        		'password':pwd.val()
	        	},
	        	type:"post",
	        	dataType:"json",
	        	success:function(data){
	        		 if(data.code == '200'){
                         window.location.href = "beta1.0.html?userId="+data.userId+"&username="+data.username;
                        }
                      else if (data.code == '201') {
                          alert(data.data);
                      }
	        	},
	        	error:function(data){
	        		 alert("出错：" + data.data);
	        	}
	        });	
	        }
	        });
	
	
});
