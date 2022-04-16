<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title></title>
  
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>


    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet" />
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
  <!-- MDB -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.11.0/mdb.min.css" rel="stylesheet"/> 
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet" />
  
    
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
  <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
  
  <style>
      <%@include file="/WEB-INF/views/css/style.css" %>
  </style>
</head>

<body>
  <div class="m-4">
    <nav class="navbar navbar-expand-sm  navbar-light bg-info rounded-5">
        <div class="container">
            <a href="${pageContext.request.contextPath }/index" class="navbar-brand">G3 SHOP</a>
            <div id="navbarCollapse" class="collapse navbar-collapse">
<!--                <ul class="nav navbar-nav">
                    <li class="nav-item">
                        <a href="" class="nav-link">Home</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Product</a>
                        <div class="dropdown-menu">
                            <a href="#" class="dropdown-item">Inbox</a>
                            <a href="#" class="dropdown-item">Drafts</a>
                            <a href="#" class="dropdown-item">Sent Items</a>
                            <div class="dropdown-divider"></div>
                            <a href="#" class="dropdown-item">Trash</a>
                        </div>
                    </li>
                </ul>-->
      <!-- Search FORM -->
      <form class="container input-group w-auto" method="GET" action="${pageContext.request.contextPath }/searchProduct">
        <input type="search" name="search" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
        <button class="input-group-text border-0" id="search-addon" type="submit">
          <i class="fas fa-search"></i>
        </button>
      </form>
      
         <div class="d-flex align-items-center">
      <c:if test="${login == false}">
          <a href="${pageContext.request.contextPath }/loginForm">
        <button type="button" class="btn btn-light px-3 me-2">
          Login
        </button>
          </a>
          <a href="${pageContext.request.contextPath }/registerForm">
        <button type="button" class="btn btn-primary me-3">
          Sign up for free
        </button>
          </a>
      </c:if>
      <c:if test="${login == true}">
          <!-- CART AND Icon -->
          <a class="text-reset me-3" href="${pageContext.request.contextPath }/myCart">
              <span id="numberCart">
        <span class="badge badge-pill bg-danger">${numberCart}</span>
              </span>
        <i class="fas fa-shopping-cart"></i>
        </a>
          <!--  USER AREA -->
                <ul class="nav navbar-nav ms-auto">
                    <li class="nav-item dropdown">
                      
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"> <i class="fas fa-user"></i></a>
                        <div class="dropdown-menu dropdown-menu-end">
                            <a href="${pageContext.request.contextPath }/myProfile" class="dropdown-item">Profile</a>
                            <div class="dropdown-divider"></div>
                            <a href="${pageContext.request.contextPath }/logout" class="dropdown-item">Logout</a>
                        </div>
                    </li>
                </ul>
      </c:if>
            </div>
        </div>
    </nav>    
</div>
        
        <!-- end navbar -->