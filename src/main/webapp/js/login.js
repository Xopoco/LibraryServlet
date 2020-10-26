$(document).ready(function(){
    $("#button").click(function(){
        var data = {};
        data = {"login":$("#loginLog").val(), "password":$("#passwordLog").val()};

        $.ajax
        ({
            type: "POST",
            data: data,
            url: 'LoginServlet',
            success:function(serverData)
            {
                if(serverData.result == "1"){
                    window.location.assign("index.jsp");
                } else {
                    $("#auth-info").css({"background-color":serverData.backgroundColor, "height": "50px", "color":"white"});
                    $("#auth-info").html(serverData.serverInfo);
                }
            },
            error: function(e)
            {
                $("#auth-info").css({"background-color":"#CC6666", "height": "50px", "color":"white"});
                $("#auth-info").html("Error");
            }
        });
    });
});
