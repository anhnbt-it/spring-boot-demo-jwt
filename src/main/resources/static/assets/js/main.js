$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});
$(document).ready(function () {
    $("#loginForm").submit(function (event) {
        let t = $(this);
        t.find("fieldset").attr('disabled', 'disabled');
        t.find("button").html(`<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Loading...`);
        let inputtedUsername = $("#username").val();
        let inputtedPassword = $("#password").val();
        if (inputtedUsername.length === 0 && inputtedPassword.length === 0) {
            t.find("fieldset").removeAttr("disabled");
        } else {
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "http://localhost:8080/api/v1/users/login",
                dataType: "json",
                data: JSON.stringify({username: inputtedUsername, password: inputtedPassword})
            }).done(function (resp, status) {
                if (resp.status !== "OK") {
                    $("#loginErr").html(resp.message).removeClass("d-none alert-success").addClass("d-block alert-danger");
                } else {
                    $("#loginErr").html(resp.message).removeClass("d-none alert-danger").addClass("d-block alert-success");
                    prompt(resp.status, "Bearer " + resp.data);
                }
            }).fail(function(jqXHR, textStatus, errorThrown) {
                // If fail
                console.log(textStatus + ': ' + errorThrown);
            }).always(function (xhr, status) {
                console.log("The request is complete!");
                t.find("fieldset").removeAttr("disabled");
                t.find("button").html('Login');
            });
        }
        event.preventDefault();
    });
});
