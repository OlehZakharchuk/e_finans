<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login page</title>
    <link href="css/mainStyle.css" rel="stylesheet" type="text/css" media="all">
</head>

<body>
<div class="login-page">
    <div class="form">
        <form action="login" method="post" >
            <input type="text" name="inname"  placeholder="login"/>
            <input type="password" name="inpass" placeholder="password" />
            <input type="submit" value="Login" />
            <p class="message">Not registered? <a href="register_page.jsp">Create an account</a></p>
        </form>
    </div>
</div>
</body>
</html>
