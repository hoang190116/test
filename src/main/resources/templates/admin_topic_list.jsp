<%@include file="includes/admin_header.jsp" %>
<%@include file="includes/admin_navbar.jsp" %>
<div class="container-fluid">

  <!-- DataTales Example -->
  <div class="card shadow mb-4">
    <div class="card-header py-3">
     <form action="" >
      <h6 class="m-0 font-weight-bold text-primary">Topic Detail
          &nbsp;&nbsp;<a href="addTopic" class="btn btn-primary">
          Add Topic
        </a>
        
      </form>
      <!-- Topbar Search -->
      <form class="float-right py-3 d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search"
            method="GET" action="searchTopic">
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
            <th>ID</th>
            <th>Topic Name</th>
            <th>Course Name</th>
            <th>Trainer</th>
            <th>Description</th> 
            <th width="10%">Edit </th>
            <th width="10%">Delete </th>
          </tr>
        </thead>
        <tbody>

            <c:forEach items="${listTopic}" var="to" varStatus="status">
          <tr>
           <td>${to.topic_id}</td>
           <td>${to.topic_name}</td>
           <td>${to.c_name}</td>
           <td>${to.u_fullname}</td>
           <td>${to.topic_des}</td>
           <td>
             <form action="editTopic?id=${to.topic_id}" method="post">
               <button  type="submit" class="btn btn-success">Edit</button>       
             </form>
           </td>
           <td>
            <form action="deleteTopic?id=${to.topic_id}" method="post">
               <button  type="submit" class="btn btn-danger">Delete</button>       
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