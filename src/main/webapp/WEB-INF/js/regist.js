/**
 * Created by wuxingx on 2017-1-24.
 */

$(function(){
    //获取form表单里面class为.required的input(子类选择器) 并遍历所有符合条件的input
    $("form input.required").each(function() {
        //this代表符合条件的input
        //input的父元素  在input元素的后面添加内容:为append
        $(this).parent().append("<b class='high'> * </b>");
    });
    //获取form表单里面所有的input元素,设置失去焦点事件
    $("form input").blur(function(){
        //this代表form下符合条件的input
        //设置变量$parent,赋值为input的父元素
        var $parent = $(this).parent();
        //查找父元素(<td>)的class中有.formtips的元素并删除它   (重置提示信息)
        $parent.find(".formtips").remove();
        //如果input是id为username的元素
        if($(this).is("#username")) {
            //符合上面if条件的input的value为""(空)
            if($(this).val() == "") {
                //父元素(<td>)添加append:
                $("#usernamelabel").append("<span class='formtips onError'>用户名不能为空</span>");
            }else if(!/^[0-9a-zA-Z]{6,16}$/.test($(this).val())) {
                $("#usernamelabel").append("<span class='formtips onError'>用户名由6-16个字母、数字组成,不得包含特殊字符</span>");
            }else {
                $.post("/talkr/UserServlet/checkName",{"username":$(this).val()},function (data) {
                    //说明用户名不存在
                    if(data != "0") {
                        //父元素添加append:
                        $("#usernamelabel").append("<span class='formtips onError'>用户名已存在</span>");
                    }else {
                        $("#usernamelabel").append("<span class='formtips onSuccess'>用户名输入正确</span>");

                    }
                });

            }
        }
        //如果input的id是password
        if($(this).is("#password")) {
            //如果input的value为""
            if($(this).val() == "") {
                //父元素添加
                $("#passwordlabel").append("<span class='formtips onError'>密码不能为空</span>");
            }else if(!/^[0-9a-zA-Z]{6,16}$/.test($(this).val())) {
                $("#passwordlabel").append("<span class='formtips onError'>密码为6-16个字母或数字组成,不得包含特殊字符</span>");
            }else{
                //父元素添加
                $("#passwordlabel").append("<span class='formtips onSuccess'>密码输入正确</span>");
            }
        }
        if($(this).is("#repassword")) {
            if($(this).val() == "") {
                //父元素添加
                $("#repasswordlabel").append("<span class='formtips onError'>密码不能为空</span>");
            }else if($(this).val() != $("#password").val()) {
                $("#repasswordlabel").append("<span class='formtips onError'>两次输入的密码不一致</span>");
            }else{
                //父元素添加
                $("#repasswordlabel").append("<span class='formtips onSuccess'>密码输入正确</span>");
            }
        }

        if($(this).is("#email")) {
            if($(this).val() == "") {
                $("#emaillabel").append("<span class='formtips onError'>邮箱不能为空</span>");
            }else {
                $.post("/talkr/UserServlet/checkEmail",{"email":$(this).val()},function (data) {
                    //说明邮箱存在
                    if(data != "0") {
                        $("#emaillabel").append("<span class='formtips onError'>邮箱已存在</span>");
                    }
                });
            }
        }

        if($(this).is("#nickName")) {
            if ($(this).val() == "") {
                $("#nickNamelabel").append("<span class='formtips onError'>昵称不能为空</span>");
            }else if($(this).val().length < 2 || $(this).val().length > 7) {
                $("#nickNamelabel").append("<span class='formtips onError'>昵称不能小于2个且不能大于7个字符</span>");
            }else {
                $("#nickNamelabel").append("<span class='formtips onSuccess'>昵称输入正确</span>")
            }
        }

        if($(this).is("#checkCode")) {
            if($(this).val() == "") {
                $("#checkCodelabel").append("<span class='formtips onError'>验证码不能为空</span>");
            }
        }


    }).keyup(function(){ //链式编程,blur时间后跟keyup事件
        //这里的this是form下面所有的input
        //触发绑定blur事件的input,triggerhandler触发是只触发绑定的事件,而不会触发浏览器的blur事件
        $(this).triggerHandler("blur");
    }).focus(function(){ //触发focus焦点消失事件
        //触发绑定blur事件的input方法  不会触发浏览器事件
        $(this).triggerHandler("blur");
    });


//表单提交校验
    $("form").submit(function(){
        //获取form里面所有的input元素 触发他们的blur事件
        $("form :input").trigger("blur");
        $("input[type='radio']").trigger("blur");
        // $("form select").trigger("blur");

        //获取所有class含有.onError的元素,他们数组的长度
        var errorLength = $(".onError").length;
        console.log(errorLength)
        //如果有长度就说明有错误信息
        if(errorLength > 0) {
            return false;
        }
    });

});

