$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/api/login"
    }).then(function(data, status, jqxhr) {
        console.log(data);
        console.log(jqxhr);
    });

    $.post( "http://localhost:8080/api/login", { username: "admin", password: "admin" })
        .done(function( data ) {
            console.log(data)
        });
});
