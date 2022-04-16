<%@include file="include/navbar.jsp" %>
<style>
    input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}
input[type=number] {
  -moz-appearance: textfield;
}
</style>
<div class="container-xl px-4 mt-4">
    <div class="row">
        <div class="col-xl-4">
            <!-- Profile picture card-->
            <div class="card mb-4 mb-xl-0">
                <div class="card-header">Profile Infomation</div>
                <div class="card-body text-center">
                    <div class="row mb-4">
                        <a href="${pageContext.request.contextPath }/myProfile" class="btn btn-lg btn-info  btn-block p-4 mb-4" type="button"><i class="fas fa-user" aria-hidden="true"></i> Profile</a>
                        <a href="${pageContext.request.contextPath }/myBill" class="btn btn-lg btn-info  btn-block p-4 mb-4" type="button"><i class="fa fa-shopping-cart" aria-hidden="true"></i> My bill</a>
                        <a href="${pageContext.request.contextPath }/myPayment" class="btn btn-lg btn-info  btn-block p-4 mb-4" type="button"><i class="fa fa-credit-card" aria-hidden="true"></i> Payment</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xl-8">
            <!-- Account details card-->
            <div class="card mb-4">
                <div class="card-header">Account Details</div>
                <div class="card-body">
                    <form:form action="saveInfor" method="post" modelAttribute="user">
                        <!-- Form Group (username)-->
                        <div class="mb-3">
                            <label class="small mb-1" for="inputUsername">Full Name</label>
                            <form:input class="form-control" id="inputUsername" type="text" placeholder="Enter your full name" path="fname" required="a"/>
                        </div>
                        <!-- Form Row-->
                        <div class="row gx-3 mb-3">
                            <!-- Form Group (first name)-->
                            <div class="col-md-6">
                                <label class="small mb-1" for="userName">User Name</label>
                                <form:input class="form-control" id="userName" type="text" placeholder="Account user name" path="uname" required="a"/>
                            </div>
                            <!-- Form Group (last name)-->
                            <div class="col-md-6">
                                <label class="small mb-1" for="userPass">Password</label>
                                <form:input type="password" class="form-control" id="userPass" placeholder="**************" path="pass"/>
                            </div>
                        </div>
                        <!-- Form Row        -->
                        <div class="row gx-3 mb-3">
                            <!-- Form Group (organization name)-->
                            <div class="col-md-6">
                                <label class="small mb-1" for="dateOfBird">Date of bird</label>
                                <form:input class="form-control" id="dateOfBird" type="date" path="date_of_bird" required="a"/>
                            </div>
                            <!-- Form Group (location)-->
                            <div class="col-md-6">
                                <label class="small mb-1" for="phoneInput">Phone</label>
                                <form:input class="form-control" id="phoneInput" type="number" maxlength="20" path="phone" required="a"/>
                            </div>
                        </div>
                        <!-- Form Group (email address)-->
                        <div class="mb-3">
                            <label class="small mb-1" for="inputEmailAddress">Email address</label>
                            <form:input class="form-control" id="inputEmailAddress" type="email" placeholder="Enter your email address" path="email" required="a"/>
                        </div>
                        <!-- Save changes button-->
                        <button class="btn btn-info" type="submit">Save changes</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>


<%@include file="include/footer.jsp" %>