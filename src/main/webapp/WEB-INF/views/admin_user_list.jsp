<%@include file="includes/admin_header.jsp" %>
<%@include file="includes/admin_navbar.jsp" %>
<div class="container-fluid">

  <!-- DataTales Example -->
  <div class="card shadow mb-4">
      <p class="abc">ABC</p>
    <div class="card-header py-3">
        
     <form>
      <h6 class="m-0 font-weight-bold text-primary">Users Detail
        <a href="addUser" class="btn btn-primary" name="add_user" >
          Add User
        </a>
        
      </form>
      <!-- Topbar Search -->
      <form class="float-right py-3 d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search"
            method="GET" action="search">
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
            <th width= 15%> Full Name </th>
            <th>Username </th>
            <th>Password</th>
            <th>Phone</th> 
            <th>Address</th>
            <th>Detail</th> 
            <th>Date of bird</th>
            <c:if test="${check == null}">
            <th>Role</th> 
            </c:if>
            <th width="6%">Edit </th>
            <th width="6%">Delete </th>
          </tr>
        </thead>
        <tbody>

         <c:forEach items="${listUser}" var="u" varStatus="status">
          <tr>
           
           <td>${u.u_id}</td>
           <td>${u.u_fullname}</td>
           <td>${u.u_name}</td>
           <td>${u.u_pass}</td>
           <td>${u.u_phone}</td>
           <td>${u.u_address}</td>
           <td>${u.u_detail}</td>
           <td>${u.u_date_of_bird}</td>
           <c:if test="${check == null}">
           <td>${u.role_name}</td>
           </c:if>
           
           <td>
             <form action="editUser?id=${u.u_id}" method="post">
                 <button  type="submit" class="btn btn-success">Edit</button>       
             </form>
           </td>
           <td>
            <form action="deleteUser?id=${u.u_id}" method="post">
              <button type="submit" class="btn btn-danger"> Delete</button>
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

