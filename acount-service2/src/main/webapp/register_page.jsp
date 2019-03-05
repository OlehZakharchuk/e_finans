<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html"-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Register page</title>
    <link href="css/homeStyle.css" rel="stylesheet" type="text/css" media="all">
</head>

<body>
<div class="login-page">
    <div class="form">
        <form action="register" class="login-form" method="post">
            <input type="text" name="firstname"  placeholder="your first name" required />
            <input type="text" name="lastname"  placeholder="your last name"  required/>
            <input type="text" name="inname" placeholder="login" required/>
            <input type="password" name="inpass" placeholder="password" required/>
            <input type="text" name="inemail" placeholder="email address" required/>
            <input type="submit" value="Register" />
            <p class="message">Already registered? <a href="home.jsp">Sign In</a></p>

        </form>


    </div>
</div>
</body>
</html>
