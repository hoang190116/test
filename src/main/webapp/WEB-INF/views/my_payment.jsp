<%@include file="include/navbar.jsp" %>
<style>
    .btnAdd{
        background-color: #00ccff;
        border: none;
        color: white;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        cursor: pointer;
        padding: 5px 25px;
        box-shadow: 0 4px 15px 6px rgba(0,0,0,0.03);
        border-radius: 10px;
    }
    .btnAdd:hover {
        box-shadow: 0 5px 5px 0 rgba(0,0,0,0.10),0 5px 5px 0 rgba(0,0,0,0.19);
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
                <div class="card-header">Your payment<button class="btnAdd" style="float:right;"><a href="addPayment" style="color:white">Add</a></button></div>
                <div class="col-lg-9">
                    <div class="row" style="width: 140%">
                        <c:forEach items="${myPayment}" var="pay">
                        <div class="col-lg-4 col-md-6" style="width:33%;">
                            <div class="list-grid-item mt-4">
                                <div class="grid-item-content p-2">
                                    <div class="grid-list-desc text-center mt-4">
                                        <h5 class="mb-1"><a href="#" class="text-dark">${pay.bank_name}</a></h5>
                                        <p class="text-muted f-14 mb-0" style="text-align: left;width: 200px">Card Name: ${pay.fname}</p>
                                        <p class="f-14 mb-0" style="text-align: left;width: 200px">Card number: *** ${pay.card_number}</p>
                                        <p class="text-muted f-14 mb-0" style="text-align: left;width: 200px">Email: ${pay.email}</p>
                                        <p class="text-muted f-14 mb-0" style="text-align: left; width: 200px">Address: ${pay.address}</p>
                                        <p class="text-muted f-14 mb-0" style="text-align: left;width: 200px">Country: ${pay.country}</p>
                                    </div>
                                </div>
                                <hr class="mt-0 mb-0">
                                <div class="apply-button text-center mt-2 mb-2">
                                    <a href="deletePayment?id=${pay.id}" class="btn btn-custom btn-sm">Delete</a>
                                </div>
                            </div>
                        </div>
                        </c:forEach>

                        
                    </div>
                </div>
            </div>


        </div>
    </div>
</div>
</div>
</div>


<%@include file="include/footer.jsp" %>