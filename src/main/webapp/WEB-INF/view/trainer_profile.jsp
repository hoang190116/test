<%@include file="includes/client_header.jsp" %>
<%@include file="includes/client_navbar.jsp" %>

<div class="container" style="margin-top: 50px">
	<div class="row justify-content-md-center">
		<div class="col-md-9">
      <div class="card">
        <div class="card-body">
          <div class="row ">
            <div class="col-md-12">
              <h4>Your Profile</h4>
              <hr>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <form:form action="saveProfile" method="post" modelAttribute="u">
                  <form:hidden path="u_id"/>
                <div class="form-group row">
                  <label for="name" class="col-4 col-form-label">Full Name</label> 
                  <div class="col-8">
                    <form:input class="form-control" placeholder="Enter Name" path="u_fullname" required="a"/>
                  </div>
                </div>
                  
                  <div class="form-group row">
                        <label for="lastname" class="col-4 col-form-label">Date of Birth</label>
                        <div class="col-8">
                        <form:input path="u_date_of_bird"  class="form-control" required="Need Set The Date" type="date" value="" defaultValue="${date}" />
                    </div>
                  </div>

                <div class="form-group row">
                  <label for="email" class="col-4 col-form-label">Phone</label> 
                  <div class="col-8">
                    <form:input type="number" class="form-control" placeholder="Enter Phone Number" path="u_phone" />
                  </div>
                </div>
                <div class="form-group row">
                  <label for="website" class="col-4 col-form-label">Address</label> 
                  <div class="col-8">
                    <form:input type="text" class="form-control" placeholder="Enter Address" path="u_address" />
                  </div>
                </div>
                <div class="form-group row">
                  <div class="offset-4 col-8">
                    <button type="submit" class="btn btn-primary" style="float: right">Update My Profile</button>
                  </div>
                </div>
              </form:form>
            </div>
          </div>
          
        </div>
      </div>
    </div>
  </div>
</div>
