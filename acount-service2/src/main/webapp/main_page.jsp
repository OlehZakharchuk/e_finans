<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login page</title>
    <link href="css/homeStyle.css" rel="stylesheet" type="text/css" media="all">
</head>

<body>
<div class="welcomeStyle">
    <p>Welcome: ${nameOfLogedUser}</p>
    <p>Login: ${loginOfLogedUser}</p>
</div>
<footer>x
<div class="form">

    <form action="deleteaccount" class="login-form" method="post" >
    <input type="text" name="inname"  placeholder="login"/>
    <input type="password" name="inpass" placeholder="password" />
    <input type="submit" value="delete" />
    <p class="message">Do you want delete account? </p>
    </form>
</div>
</footer>
</body>
</html>
