<%@include file="include/navbar.jsp" %>

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
                <div class="card-header">Your order Bill</div>
                <div class="col-lg-9">
                    <div class="row" style="width: 140%">
                        <c:forEach items="${myBilling}" var="b">
                        <div class="col-lg-4 col-md-6" style="width:33%">
                            <div class="list-grid-item mt-4">
                                <div class="grid-item-content p-2">
                                    <div class="grid-list-desc text-center mt-4" style="text-align: left">
                                        <h5 class="mb-1"><a href="#" class="text-dark">Bill ID: ${b.bill_id}</a></h5>
                                        <p class="text-muted f-14 mb-0" style="text-align: left;">Pay Type: ${b.pay_type}</p>
                                        <p class="f-14 mb-0" style="text-align: left;<c:if test="${b.pay_status.equals('Unpaid')}"> color:red</c:if> <c:if test="${b.pay_status.equals('Paid')}">color:green</c:if>">Status: ${b.pay_status}</p>
                                        <p class="text-muted f-14 mb-0" style="text-align: left;">Order Status: ${b.status}</p>
                                        <p class="text-muted f-14 mb-0" style="text-align: left;">Date: ${b.date}</p>
                                
                                    </div>
                                </div>
                                <hr class="mt-0 mb-0">
                                <div class="apply-button text-center mt-2 mb-2">
                                    <a href="${pageContext.request.contextPath }/billDetail?id=${b.bill_id}" class="btn btn-custom btn-sm">View </a>
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