<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login page</title>
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js">
    <style><%@include file="/WEB-INF/views/css/login.css"%></style>
</head>
<body class="">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-sm-10 col-md-12 col-lg-11 col-xl-10">
                <div class="card d-flex mx-auto my-5">
                    <div class="row">
                        <div class="col-md-5 col-sm-12 col-xs-12 c1 p-5">
                            <img src="https://inbienquangcao.vn/wp-content/uploads/2020/12/Nhu%CC%9B%CC%83ng-tho%CC%82ng-%C4%91ie%CC%A3%CC%82p-sa%CC%82u-sa%CC%86%CC%81c-phi%CC%81a-sau-logo-FPT.png" width="120" height="210" alt="Teacher">
                        </div>
                        <div class="col-md-7 col-sm-12 col-xs-12 c2 px-5 pt-5">
                            <form:form action="user" method="post" modelAttribute="user"  class="px-5 pb-5">
                                <div class="d-flex">
                                    <h3 class="font-weight-bold">Log in</h3>
                                </div> 
                                <div class="form-group">
                                    <label for="usr">Name:</label>
                                    <form:input path="u_name" type="text" class="form-control" id="usr"/>
                                </div>
                                <div class="form-group">
                                    <label for="pwd">Password:</label>
                                    <form:input path="u_pass" type="password" class="form-control" id="pwd"/>
                                </div>
                                <input type="submit" value="Login" class="text text-weight-bold bt"/>
                                <div class="d-flex justify-content-center links">
                                    <a href="index">Back Home</a>
                                </div>
                                <c:if test="${mes != null}">
                                    <div class="d-flex justify-content-center links">
                                        <p>${mes}</p>
                                    </div>
                                </c:if>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
