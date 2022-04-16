<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="includes/nav.jsp" %>

<nav class="navbar navbar-main navbar-expand-lg px-0 mx-4 shadow-none border-radius-xl" id="navbarBlur" navbar-scroll="true">
    <div class="container-fluid py-1 px-3">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb bg-transparent mb-0 pb-0 pt-1 px-0 me-sm-6 me-5">
                <li class="breadcrumb-item text-sm ">Pages</li>
                <li class="breadcrumb-item text-sm " aria-current="page">Order Processing</li>
            </ol>
        </nav>
    </div>
</nav>
<div class="tab-content bg-transparent">
    <div id="note-full-container" class="note-has-grid row">
        <c:forEach items="${orderList}" var="o">
        <div class="col-md-4 single-note-item all-category">
            <div class="card card-body">
                <span class="side-stick"></span>
                <h5 class="note-title text-truncate w-75 mb-0">Order ID: ${o.bill_id}<i class="point fa fa-circle ml-1 font-10" style='color:#33f8ff'></i></h5>
                <p class="note-date font-12 text-muted">Order Date: ${o.dateString}</p>
                <div class="note-content">
                    <p class="note-inner-content text-muted">Customer Name: ${o.fname}</p>
                </div>
                <div class="note-content">
                    <p class="note-inner-content text-muted">Method: ${o.pay_type}</p>
                </div>
                <div class="note-content">
                    <p class="note-inner-content text-muted badge badge-warning" style="font-size: 15px;">Status: ${o.status}</p>
                </div>
                <div class="note-content">
                    <p class="note-inner-content text-muted">Payment Status: <span style="<c:if test="${o.pay_status.equals('Unpaid')}"> color:red</c:if> <c:if test="${o.pay_status.equals('Paid')}">color:green</c:if>">${o.pay_status}</span></p>
                </div>
                <div class="note-content">
                    <p class="note-inner-content text-muted badge badge-info" style="font-size: 15px;">Total: ${o.total}$</p>
                </div>
                <div class="d-flex align-items-center">
                    <a href="${pageContext.request.contextPath }/emp/updateOrder?id=${o.bill_id}" class="btn btn-light btn-lg btn-block rounded-pill"><i class="fas fa-edit"></i> Update</a>
                </div>
            </div>
        </div>
        </c:forEach>

    </div>
</div>



<%@include file="includes/footer.jsp" %>