<%@include file="includes/nav.jsp" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-main navbar-expand-lg px-0 mx-4 shadow-none border-radius-xl" id="navbarBlur" navbar-scroll="true">
    <div class="container-fluid py-1 px-3">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb bg-transparent mb-0 pb-0 pt-1 px-0 me-sm-6 me-5">
                <li class="breadcrumb-item text-sm ">Pages</li>
                <li class="breadcrumb-item text-sm " aria-current="page">Order Detail</li>
            </ol>
        </nav>
    </div>
</nav>

<div class="container-fluid pt-3">
    <div class="card mb-4">
        <div class="card-body ">
            <form:form action="${pageContext.request.contextPath }/emp/saveOrder" method="POST" modelAttribute="b">
                <form:hidden path="bill_id" />
                <h6 class="heading-small text-muted mb-4">Order ID: ${b.bill_id}</h6>
                <div>
                    <div class="row">
                        <div class="col-lg-7">
                            <div class="card">
                                <div class="card-header pb-0">
                                    <table class="table align-items-center mb-0">
                                        <thead>
                                            <tr>
                                                <th class="text-center  ">Product Name</th>
                                                <th class="text-center">Price</th>
                                                <th class="text-center">Amount</th>
                                                <th class="text-center">Total</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${b.billProduct}" var="bp">
                                            <tr>
                                                <td class="align-middle text-center text-sm">
                                                    <span class="y text-xs ">${bp.product_name}</span>
                                                </td>
                                                <td class="align-middle text-center text-sm">
                                                    <span class="y text-xs ">${bp.price}$</span>
                                                </td>
                                                <td class="align-middle text-center">
                                                    <span class=" text-xs ">${bp.quantity}</span>
                                                </td>
                                                <td class="align-middle text-center text-sm">
                                                    <span class=" text-xs ">${bp.quantity * bp.price}</span>
                                                </td>
                                            </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- total payment -->
                                <div class="card-header pb-0">
                                    <table class="table align-items-center mb-0">
                                        <tfoot>
                                            <tr class="fw-bold">
                                                <td colspan="2">TOTAL</td>
                                                <td class="text-end">${b.total}$</td>
                                            </tr>
                                        </tfoot>
                                    </table>

                                </div>
                                <div class="card-body px-0 pb-2">

                                </div>
                            </div>
                        </div>
                        <div class="col-lg-5">
                            <div class="card h-100">
                                <div class="card-header pb-0">
                                    <h6>Customer</h6>
                                    <!-- <p class="text-sm">
                                        <i class="fa fa-arrow-up text-success" aria-hidden="true"></i>
                                        <span class="font-weight-bold">24%</span> this month
                                    </p> -->
                                </div>
                                <div class="card-body p-3">
                                    <div class="timeline timeline-one-side">
                                        <div class="timeline-block mb-3">
                                            <div class="timeline-content">
                                                <h6 class=" mb-0">${b.fname}</h6>
                                            </div>
                                        </div>
                                        <hr class="horizontal dark my-2">
                                        <div class="timeline-block mb-3">
                                            <div class="timeline-content">
                                                <h6 class=" mb-0">Contact info</h6>
                                                <p class=" text-xs mt-1 mb-0">Email: ${b.email}</p>
                                            </div>
                                        </div>
                                        <hr class="horizontal dark my-2">
                                        <div class="timeline-block mb-3">
                                            <div class="timeline-content">
                                                <h6 class=" mb-0">Address</h6>
                                                <p class=" text-xs mt-1 mb-0">${b.country}</p>
                                                <p class=" text-xs mt-1 mb-0">${b.address}</p>
                                            </div>
                                        </div>
                                        <hr class="horizontal dark my-2">
                                        <div class="timeline-block mb-3">
                                            <div class="timeline-content">
                                                <h6 class=" font-weight-bold mb-0">Order Date</h6>
                                                <p class=" text-xs mt-1 mb-0">${b.dateString}</p>
                                            </div>
                                        </div>
                                        <div class="timeline-block mb-3">

                                            <div class="timeline-content">
                                                <h6 class=" mb-0">Payment Status</h6>
                                                <h3 class="badge text-wrap text-left" style="color: red;">Attention: If you save order to "Finish", 
                                                    the "Payment Status" will automatically convert to "Paid"</h3>
                                                <form:radiobutton class="text-xs mt-1 mb-0" id="flexCheckChecked" value="Unpaid" path="pay_status" />
                                                <label class="text-xs mt-1 mb-0 " for="flexCheckChecked" style="margin-right: 20px">
                                                    Unpaid
                                                  </label>
                                                <form:radiobutton class="text-xs mt-1 mb-0" id="flexCheckChecked" value="Paid" path="pay_status" />
                                                <label class="text-xs mt-1 mb-0 " for="flexCheckChecked">
                                                    Paid
                                                  </label>
                                            </div>
                                        </div>
                                        <div class="timeline-block">
                                            <div class="timeline-content">
                                                <h6 class=" mb-0">Order Status</h6>
                                                <h3 class="badge text-wrap text-left" style="color: red;">Attention: If you save order to "Finish", 
                                                    the "Payment Status" will automatically convert to "Paid"</h3>
                                                <form:radiobutton class="text-xs mt-1 mb-0" path="status" value="Waiting" />
                                                <label class="text-xs mt-1 mb-0 " for="flexCheckChecked" style="margin-right: 20px">
                                                    Waiting
                                                  </label>
                                                <form:radiobutton class="text-xs mt-1 mb-0" id="flexCheckChecked" value="In processing" path="status" />
                                                <label class="text-xs mt-1 mb-0 " for="flexCheckChecked" style="margin-right: 20px">
                                                    In processing
                                                  </label><br />
                                                <form:radiobutton class="text-xs mt-1 mb-0" id="flexCheckChecked" value="Delivering" path="status" />
                                                <label class="text-xs mt-1 mb-0 " for="flexCheckChecked" style="margin-right: 20px">
                                                    Delivering
                                                  </label>
                                                <form:radiobutton class="text-xs mt-1 mb-0" id="flexCheckChecked" value="Finish" path="status" />
                                                <label class="text-xs mt-1 mb-0 " for="flexCheckChecked">
                                                    Finish
                                                  </label>
                                            </div>
                                        </div>
                                        <div class="timeline-block">
                                            <div class="timeline-content">
                                                <h6 class="  mb-0">Payment Method </h6>
                                                <p class=" text-xs mt-1 mb-0">Type:  ${b.pay_type}</p>
                                                <c:if test="${cash == false}"><p class=" text-xs mt-1 mb-0">Card:  *** *** ${b.card_number}</p></c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="text-right" style="margin-top: 15px">
                        <button type="submit" class="btn btn-success "> Save </button>
                </div>


            </form:form>

        </div>
    </div>
</div>
<%@include file="includes/footer.jsp" %>