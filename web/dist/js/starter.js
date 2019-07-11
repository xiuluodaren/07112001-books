$(function(){
    var menu_lis = $(".menu_li");
    menu_lis.each(function(){
        $(this).click(function(){
            menu_lis.removeClass("active");
            $(this).addClass("active");
            $("#main_content").attr("src",$(this).attr("alt"));
        });
    });

    $("#submitbutton").click(function() {
        var oldPassword = $("#oldPassword").val()
        var newPassword = $("#newPassword").val()
        var confirmPassword = $("#confirmPassword").val()

        if (newPassword == null ||newPassword != confirmPassword)
        {
            alert("两次输入密码不一致");
            return;
        }

        var url = "/login?method=modifyPassword&oldPassword=" + oldPassword +"&newPassword="+newPassword;

        $.ajax({
            url:url,
            type:"post",
            contentType: "application/json;charset=UTF-8",
            success:function(data){
                if(data.success == true){
                    alert("成功");
                    $("#oldPassword").val("")
                    $("#newPassword").val("")
                    $("#confirmPassword").val("")
                    $("#modify").modal('hide');
                }else{
                    alert("失败");
                }
            }
        })

    })

});

function signOut(){
    $.ajax({
       url:'/login?method=logout',
       type:'get',
       success:function(data){
         window.location.href = '/';
       }
    });
}
