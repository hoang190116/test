<%@include file="includes/admin_header.jsp" %>
<%@include file="includes/admin_navbar.jsp" %>

<form:form action="saveUser" method="post" modelAttribute="u">
<div class="container-fluid">
  <!-- DataTales Example -->
  <div class="card shadow mb-4">
    <div class="card-header py-3">
      <h6 class="m-0 font-weight-bold text-primary text-center">Add/Edit User </h6>
    </div>
    <div class="row">
      <div class="col"> 
        <div class="card text-dark">
          <div class="card-body ">
              <form:hidden path="u_id"/>
            <div class="form-group">
              <label > Full name </label>
              <form:input class="form-control" placeholder="Enter Full name" path="u_fullname" required="a"/>
            </div>
            <div class="form-group">
              <label> Username </label>
              <form:input class="form-control" placeholder="Enter Username" path="u_name" required="a"/>
            </div>
            <div class="form-group">
              <label>Password</label>
              <form:input type="password" class="form-control" placeholder="Enter Password" path="u_pass" required="a"/>
            </div>
            <div class="form-group">
                <c:if test="${check == null}">
              <label>Role </label><br>
              <div class="form-check form-check-inline">
                <form:radiobutton class="form-check-input" value="Admin" path="role_name" />
                <label class="form-check-label" >Admin</label>
              </div>
              <div class="form-check form-check-inline">
                <form:radiobutton  class="form-check-input" value="Staff" path="role_name" />
                <label class="form-check-label">Staff</label>
              </div>
              <div class="form-check form-check-inline">
                <form:radiobutton  class="form-check-input"  value="Teacher" path="role_name" />
                <label class="form-check-label" >Trainer</label>
              </div>
              <div class="form-check form-check-inline">
                  <form:radiobutton  class="form-check-input" value="Student" path="role_name"/>
                <label class="form-check-label">Trainee</label>
              </div>
                </c:if>
                <c:if test="${check != null}">
                    <div class="form-group">
                        <label> Date of Birth</label>
                        <form:input path="u_date_of_bird"  class="form-control" required="Need Set The Date" type="date" value="" defaultValue="${date}" />
                    </div>
                </c:if>
            </div>
          </div>
        </div>
      </div>
      <div class="col "> 
        <div class="card text-dark">
          <div class="card-body">
              <c:if test="${check == null}">
            <div class="form-group">
              <label> Date of Birth</label>
              <form:input path="u_date_of_bird"  class="form-control" required="Need Set The Date" type="date" value="" defaultValue="${date}" />
            </div>
              </c:if>
            <div class="form-group">
              <label>Detail</label>
              <form:input type="text" class="form-control" placeholder="Enter Detail" path="u_detail" />
            </div>
            <div class="form-group">
              <label> Phone </label>
              <form:input type="number" class="form-control" placeholder="Enter Phone Number" path="u_phone" />
            </div>
            <div class="form-group">
              <label>Address</label>
              <form:input type="text" class="form-control" placeholder="Enter Address" path="u_address" />
            </div>
            

          </div>
        </div>
      </div>
    </div>
  </div>
  <c:if test="${mes != null}"><p style="color: red">${mes}</p></c:if>
  <c:if test="${checkPass != null}"><p style="color: red">${checkPass}</p></c:if>
  <div class="float-right">
    <button type = "submit" class = "btn btn-primary" > Save </button>
  </div>
</div>
<!-- /.container-fluid -->
</form:form>


<%@include file="includes/admin_scripts.jsp" %>
<%@include file="includes/admin_footer.jsp" %>