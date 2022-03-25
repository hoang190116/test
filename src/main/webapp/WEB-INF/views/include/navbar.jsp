<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title></title>
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
  <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
  <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <!------ Include the above in your HEAD tag ---------->

  <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet" />
  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet" />
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
  <!-- MDB -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.11.0/mdb.min.css" rel="stylesheet"/>
  
  
  
  <style>
      <%@include file="/WEB-INF/views/css/style.css" %>
  </style>
</head>

<body>

  <div class="navbar navbar-expand-md navbar-light bg-info ">
    <div class="container">
      <a class="navbar-brand" href="index">
        <img src="https://lockstepgroup.com/wp-content/uploads/2017/03/icon-solutions-server-compute.png" class="me-2" height="20" loading="lazy" />
        <small>G3 SHOP</small>
      </a>
      <!-- search -->
      <form class="container input-group w-auto" method="GET" action="searchProduct">
        <input name="search" type="search" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
        <button type="submit" class="input-group-text border-0" id="search-addon">
          <i class="fas fa-search"></i>
        </button>
      </form>
      
      
      <ul class="navbar-nav ">
        <!-- Cart -->
        <li class="nav-item me-3 me-lg-0">
          <a class="nav-link" href="myCart">
            <span><i class="fas fa-shopping-cart"></i></span>
          </a>
        </li>
      </ul>
      <br>
      <ul class="navbar-nav  ">
        <!-- user -->
        <li class="nav-item me-3 me-lg-0 dropdown">
            <c:if test="${login == null}">
            <a class="nav-link d-flex align-items-center" href="login" id="navbarDropdownMenuLink" role="button" data-mdb-toggle="dropdown" aria-expanded="false">
            <i class="fas fa-user"></i>
            </a>
            </c:if>
            <c:if test="${login != null}">
          <a class="nav-link dropdown d-flex align-items-center" href="profile" id="navbarDropdownMenuLink" role="button" data-mdb-toggle="dropdown" aria-expanded="false">
            <i class="fas fa-user"></i>
          </a>
            
          <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
            <li>
              <a class="dropdown-item" href="profile">My profile</a>
            </li>
            <li>
              <a class="dropdown-item" href="#">Logout</a>
            </li>
          </ul>
          </c:if>
        </li>
      </ul>
    </div>
  </div>

  <!-- end navbar -->