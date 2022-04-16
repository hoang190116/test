<%@include file="includes/nav.jsp" %>
<nav class="navbar navbar-main navbar-expand-lg px-0 mx-4 shadow-none border-radius-xl" id="navbarBlur" navbar-scroll="true">
    <div class="container-fluid py-1 px-3">
        <!-- <nav aria-label="breadcrumb">
            <ol class="breadcrumb bg-transparent mb-0 pb-0 pt-1 px-0 me-sm-6 me-5">
                <li class="breadcrumb-item text-sm">Pages</li>
                <li class="breadcrumb-item text-sm text-dark active" aria-current="page">Order </li>
            </ol>
            <h6 class="font-weight-bolder mb-0">Order list</h6>
        </nav> -->
    </div>
</nav>
<div class="container-fluid pt-3">
    <div class="card mb-4">
        <div class="card-header">
            <div class="row align-items-center">
                <div class="col-9">
                    <h4 class="font-weight-bolder "> Orders </h4>
                </div>
                <div class="col-3 text-end">
                <form class="d-flex input-group w-auto" method="GET" action="${pageContext.request.contextPath }/adm/searchOrder">
                    <input type="search" name="search" class="form-control rounded" placeholder="Search By ID" aria-label="Search" aria-describedby="search-addon" />
                    <button type="submit" class="input-group-text border-0" id="search-addon">
                        <i class="fas fa-search"></i>
                    </button>
                </form>
                </div>
            </div>
        </div>
        <div class="card-body">
            <div class="card-body px-0 pt-0 pb-2">
                <div class="table-responsive p-0">
                    <table class="table align-items-center mb-0">
                        <thead>
                            <tr>
                                <th class="text-center  ">Order ID</th>
                                <th class="text-center ">Date</th>
                                <th class="text-center ">Customer</th>
                                <th class="text-center ">Payment status</th>
                                <th class="text-center ">Order status</th>
                                <th class="text-center">Payment method</th>
                                <th class="text-center">Total</th>
                                <th class="text-center ">Action</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${billList}" var="b">
                            <tr>
                                <td class="align-middle text-center text-sm">
                                    <span class=" text-xs font-weight-bold">${b.bill_id}</span>
                                </td>
                                <td class="align-middle text-center text-sm">
                                    <p class="text-xs font-weight-bold mb-0">${b.date}</p>
                                </td>
                                <td class="align-middle text-center">
                                    <span class=" text-xs font-weight-bold">${b.fname}</span>
                                </td>
                                <td class="align-middle text-center">
                                    <span class="badge " style="<c:if test="${b.pay_status.equals('Unpaid')}"> color:red</c:if> <c:if test="${b.pay_status.equals('Paid')}">color:green</c:if>">${b.pay_status}</span>
                                </td>
                                <td class="align-middle text-center text-sm">
                                    <span class=" badge bg-success" >${b.status}</span>
                                    <!-- <span class=" text-xs font-weight-bold badge badge-sm bg-gradient-danger" >Unfulfilled</span> -->
                                </td>
                                <td class="align-middle text-center">
                                    <span class=" text-xs font-weight-bold">${b.pay_type}</span>
                                </td>
                                <td class="align-middle text-center">
                                    <span class=" text-xs font-weight-bold">${b.total}$</span>
                                </td>
                                <td class="align-middle text-center text-sm">
                                    <a href="${pageContext.request.contextPath }/adm/order/orderDetail?id=${b.bill_id}" class="badge badge-light" data-toggle="tooltip" data-original-title="Edit product">
                                    <i class="fas fa-eye"></i> view
                                    </a>
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <h3 style="text-align: center">${noBill}</h3>
                </div>
            </div>
        </div>

    </div>
</div>

<%@include file="includes/footer.jsp" %>