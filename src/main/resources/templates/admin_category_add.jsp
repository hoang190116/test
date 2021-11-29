<%@include file="includes/admin_header.jsp" %>
<%@include file="includes/admin_navbar.jsp" %>

<form:form action="saveCate" method="post" modelAttribute="cate">
<div class="container-fluid ">
<form:hidden path="category_id"/>
  <!-- DataTales Example -->
  <div class="card shadow mb-4">

    <div class="card-header py-3">
    	<h6 class="m-0 font-weight-bold text-primary text-center">Add/Edit Category </h6>
    </div>
    <div class="">
      <div class=""> 
       <div class="card text-dark">
        <div class="card-body ">
         <div class="form-group">
           <label > Category Name </label>
           <form:input class="form-control" placeholder="Enter Name" path="category_name" required="a"/>
         </div>
         <div class="form-group ab">
          <label>Description</label>
          <form:textarea class="form-control" placeholder="Description" path="category_des"/>
        </div>
      </div>
    </div>
  </div>
  <div class="col "> </div>
</div>

</div>
<div class="float-right">
    <button type = "submit" class = "btn btn-primary" > Save </button>
  </div>
</form:form>


<%@include file="includes/admin_scripts.jsp" %>
<%@include file="includes/admin_footer.jsp" %>
