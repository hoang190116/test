<%@include file="includes/admin_header.jsp" %>
<%@include file="includes/admin_navbar.jsp" %>

<form:form action="saveCourse" method="post" modelAttribute="c">
<div class="container-fluid ">
<form:hidden path="c_id"/>
  <!-- DataTales Example -->
  <div class="card shadow mb-4">

    <div class="card-header py-3">
    	<h6 class="m-0 font-weight-bold text-primary text-center">Add Course </h6>
    </div>
    <div class="">
      <div class="col "> 
       <div class="card text-dark">
        <div class="card-body ">
         <div class="form-group">
           <label > Course Name </label>
           <form:input class="form-control" placeholder="Enter Name" path="c_name" required="a"/>
         </div>
         <div class="form-group ab">
           <label> Category </label>
           <form:select path="category_id" class="form-control" required="a">
                    <c:forEach items="${listCategory}" var="c">
                        <form:option value="${c.category_id}">${c.category_id}</form:option>
                    </c:forEach>
           </form:select>
        </div>
        <div class="form-group ab">
          <label>Description</label>
          <form:textarea class="form-control" placeholder="Enter Description" path="c_des" />
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
