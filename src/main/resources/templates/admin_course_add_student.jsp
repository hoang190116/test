<%@include file="includes/admin_header.jsp" %>
<%@include file="includes/admin_navbar.jsp" %>
<div class="container-fluid">

  <!-- DataTales Example -->
  <div class="card shadow mb-4">
    <h4 class="m-0 font-weight-bold text-primary text-center">Add Student to Course </h4>
    <div class="card-header py-3">
      <!--   	<form action="admin_course_add.php" > -->
      <form>
      <h6 class="m-0 font-weight-bold text-primary">Course name: ${name}
      </form>
        <!-- Topbar Search -->
        <form class="float-right py-3 d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search"
            method="GET" action="searchStudent">
        <div class="input-group">
          <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2"
                 name="search">
          <div class="input-group-append">
              <button class="btn btn-primary" type="submit">
                Search
              </button>
          </div>
        </div>
      </form>
      </h6>

    </div>


    <div class="card-body">
      <div>

      </div>
      <div class="table-responsive">


        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
          <thead>
            <tr>
              <th> ID </th>
              <th>Full Name </th>
              <th>Date of Bird</th>
              <th>Detail</th>
              <th width="13%" >Add to Course</th>
            </tr>
          </thead>
          <tbody>

           <c:forEach items="${listUser}" var="u" varStatus="status">
               <c:if test="${u.u_fullname != null}">
            <tr>
             <td>${u.u_id}</td>
             <td>${u.u_fullname}</td>
             <td>${u.u_date_of_bird}</td>
             <td>${u.u_detail}</td>
             <td>
              <form action="addStu?id=${u.u_id}" method="post" style="text-align: center">
                <button type="submit" class="btn btn btn-success"> Add</button>
              </form>
            </td>
          </tr>
               </c:if>
          </c:forEach>
        </tbody>
      </table>

    </div>
  </div>
</div>

</div>
<!-- /.container-fluid -->

<div class="container-fluid">

  <!-- DataTales Example -->
  <div class="card shadow mb-4">
    <div class="card-header py-3">
      <!--   <form action="admin_course_add.php" > -->
        <h6 class="m-0 font-weight-bold text-primary">List Student in Course 

        <!-- Topbar Search -->
        <form class="float-right py-3 d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search"
            method="GET" action="searchCourseStudent">
        <div class="input-group">
          <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2"
                 name="search">
          <div class="input-group-append">
              <button class="btn btn-primary" type="submit">
                Search
              </button>
          </div>
        </div>
      </form>
      </h6>

    </div>


    <div class="card-body">
      <div>

      </div>
      <div class="table-responsive">


        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
          <thead>
            <tr>
              <th> ID </th>
              <th>Full Name </th>
              <th>Date of Bird</th>
              <th>Detail</th>
              <th width="15%" >Remove from class</th>
            </tr>
          </thead>
          <tbody>

           <c:forEach items="${listUserCourse}" var="u" varStatus="status">
            <tr>
             <td>${u.u_id}</td>
             <td>${u.u_fullname}</td>
             <td>${u.u_date_of_bird}</td>
             <td>${u.u_detail}</td>
             <td>
              <form action="deleteStu?id=${u.u_id}" method="post">
                <button type="submit" class="btn btn btn-success">Remove</button>
              </form>
            </td>
          </tr>
          </c:forEach>
        </tbody>
      </table>

    </div>
  </div>
</div>

</div>
<!-- /.container-fluid -->

<%@include file="includes/admin_scripts.jsp" %>
<%@include file="includes/admin_footer.jsp" %>
