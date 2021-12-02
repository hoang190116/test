<nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">

 <img style="max-height: 70px;" src="https://www.fpt.com.vn/Content/home/images/icon/ic-logo.png">
 <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
  <span class="navbar-toggler-icon"></span>
</button>

<div class="collapse navbar-collapse" id="navbarSupportedContent">
  <ul class="navbar-nav mr-auto">
    <li class="nav-item active">
      <a class="nav-link" href="home">
        <i class="fa fa-home"></i>
        Home
        <span class="sr-only">(current)</span>
      </a>
    </li>
<!--    <li class="nav-item">
      <a class="nav-link" href="#">
        <i class="fa fa-bars">
        </i>
        Courses
      </a>
    </li>-->
  </ul>
  <form class="form-inline my-2 my-lg-0" method="GET" action="searchBar" style="margin-right: 30%">
    <input class="form-control  mr-sm-2" type="text" placeholder="Search" aria-label="Search" name="search">
    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
  </form>
  <ul class="navbar-nav ">
<!--    <li class="nav-item">
      <a class="nav-link" href="#">
        <i class="fa fa-bell">
        </i>
        Ring
      </a>
    </li>-->

    <li class="nav-item">
     <div class="topbar-divider d-none d-sm-block"></div>

     <!-- Nav Item - User Information -->
     <li class="nav-item dropdown no-arrow">
      <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <span class="mr-2 d-none d-lg-inline text-gray-600 small">
          
         User
         
       </span>
     </a>
     <!-- Dropdown - User Information -->
     <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
      <a class="dropdown-item" href="studentProfile" >
        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
        Profile
      </a>
      <a class="dropdown-item" href="logOut" >
        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
        Logout
      </a>
    </div>
  </li>
</ul>
</div>
</nav>
