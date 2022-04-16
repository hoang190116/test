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
                    <form:form action="addnewPayment" method="post" modelAttribute="payment">
                        <!-- Form Group (username)-->
                        <div class="mb-3">
                            <label class="small mb-1" for="fullname">Full Name</label>
                            <form:input class="form-control" id="fullname" type="text" placeholder="Enter your full name" path="fname_card" required="a"/>
                        </div>
                        <!-- Form Row-->
                        <div class="row gx-3 mb-3">
                            <!-- Form Group (first name)-->
                            <div class="col-md-6">
                                <label class="small mb-1" for="bank">Bank</label>
                                <form:select path="bank_name" class="form-select" required="a">
                                    <form:option value="Paypal">Paypal</form:option>
                                    <form:option value="Credit Card">Credit Card</form:option>
                                    <form:option value="Debit Card">Debit Card</form:option>
                                </form:select>
                            </div>
                            <!-- Form Group (last name)-->
                            <div class="col-md-6">
                                <label class="small mb-1" for="cNumber">Card Number</label>
                                <form:input type="number" class="form-control" id="cNumber" placeholder="Number on card" path="card_number" required="a" maxlength="19"/>
                            </div>
                        </div>
                        <div class="row gx-3 mb-3">
                            <!-- Form Group (first name)-->
                            <div class="col-md-6">
                                <label class="small mb-1" for="mm">MM Expiration</label>
                                <form:input class="form-control" id="mm" type="number" placeholder="Account user name" path="MM" required="a" onKeyUp="if(this.value>12){this.value='12';}else if(this.value<1){this.value='1';}"/>
                            </div>
                            <!-- Form Group (last name)-->
                            <div class="col-md-6">
                                <label class="small mb-1" for="yy">YY Expiration</label>
                                <form:input type="number" class="form-control" id="cNumber" placeholder="Number on card" path="YY" required="a" onKeyUp="if(this.value>99){this.value='99';}else if(this.value<0){this.value='0';}"/>
                            </div>
                        </div>
                        <!-- Form Group (email address)-->
                        <div class="mb-3">
                            <label class="small mb-1" for="inputEmailAddress">Email address</label>
                            <form:input class="form-control" id="inputEmailAddress" type="email" placeholder="Enter your email address" path="email" required="a"/>
                        </div>
                        <div class="mb-3">
                            <label class="small mb-1" for="inputCountry">Country</label>
                            <form:input class="form-control" id="inputCountry" type="text" placeholder="Your country" path="country" required="a"/>
                        </div>
                        <div class="mb-3">
                            <label class="small mb-1" for="inputAddress">Address</label>
                            <form:input class="form-control" id="inputAddress" type="text" placeholder="Enter your address" path="address" required="a"/>
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