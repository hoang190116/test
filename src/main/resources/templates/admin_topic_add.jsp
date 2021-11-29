<%@include file="includes/admin_header.jsp" %>
<%@include file="includes/admin_navbar.jsp" %>

<form:form action="saveTopic" method="post" modelAttribute="to">
<div class="container-fluid ">
<form:hidden path="topic_id"/>
  <!-- DataTales Example -->
  <div class="card shadow mb-4">

    <div class="card-header py-3">
    	<h6 class="m-0 font-weight-bold text-primary text-center">Add/Edit Topic </h6>
    </div>
    <div class="">
      <div class="col "> 
       <div class="card text-dark">
        <div class="card-body ">
         <div class="form-group">
           <label > Topic Name </label>
           <form:input class="form-control" placeholder="Enter Name" path="topic_name" required="a"/>
         </div>
               	<div class="form-group ab">
                <label> Courses </label>
                <form:select path="c_id" class="form-control">
                    <form:option value="">None</form:option>
                    <c:forEach items="${listCourse}" var="c">
                        <form:option value="${c.c_id}">${c.c_id}</form:option>
                    </c:forEach>
                </form:select>
                </div> 
         
                <div class="form-group ab">
                  <label> Trainer</label>
                  <form:select path="u_id" class="form-control">
                    <form:option value="">None</form:option>
                    <c:forEach items="${listUser}" var="u">
                        <form:option value="${u.u_id}">${u.u_id}</form:option>
                    </c:forEach>
                </form:select>
                </div>
                <div class="form-group">
                  <label>Description</label>
                  <form:textarea class="form-control ab" placeholder="Enter Description" path="topic_des" required="a"/>
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
      <!-- /.container-fluid -->
</form:form>

<%@include file="includes/admin_scripts.jsp" %>
<%@include file="includes/admin_footer.jsp" %>
