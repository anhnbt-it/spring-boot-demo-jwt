$(document).ready(function () {
    console.log("Get Blogs...")
    // $.ajax({
    //     url: "http://localhost:8080/api/v1/blogs"
    // }).then(function(data, status, jqxhr) {
    //     console.log(data);
    //     console.log(jqxhr);
    // });
    $("#loginForm").submit(function (event) {
        $.ajax({
            method: "POST",
            contentType: "application/json",
            url: "http://localhost:8080/api/login",
            dataType: "json",
            data: JSON.stringify({username: $("#username").val(), password: $("#password").val()})
        }).done(function (data, status) {
            console.log(data);
            console.log("status: " + status);
        });
        event.preventDefault();
    });
});
