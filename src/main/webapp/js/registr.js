$('input#Check').change(function () {
    if ($('input#Check').is(':checked')) {
        $('button#regisButton').prop( "disabled", false );
    } else {
        $('button#regisButton').prop( "disabled", true );
    }
});

(function() {
    'use strict';
    window.addEventListener('load', function() {
    var forms = document.getElementsByClassName('needs-validation');
    var validation = Array.prototype.filter.call(forms, function(form) {
      form.addEventListener('submit', function(event) {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }
        form.classList.add('was-validated');
      }, false);
    });
  }, false);
})();

$(document).ready(function(){
    $("#regisButton").click(function(){
        var data = {};
        data = {"login":$("#loginReg").val(), "password":$("#passwordReg").val(), "first_name":$("#firstNameReg").val(), "last_name":$("#lastNameReg").val(), "email":$("#emailReg").val(), "telephone":$("#telephoneReg").val()};

        $.ajax
        ({
            type: "POST",
            data: data,
            url: 'RegisServlet',
            success:function(serverData)
            {
                if(serverData.result == "1"){
                    window.location.assign("index.jsp");
                } else {
                    $("#registr-info").css({"background-color":serverData.backgroundColor, "height": "50px", "color":"white"});
                    $("#registr-info").html(serverData.serverInfo);
                }
            },
            error: function(e)
            {
                $("#registr-info").css({"background-color":"#CC6666", "height": "50px", "color":"white"});
                $("#registr-info").html("Error");
            }
        });
    });
});
