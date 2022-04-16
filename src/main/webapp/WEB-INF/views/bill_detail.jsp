<%@include file="include/navbar.jsp" %>


<div class="container-fluid">
    <c:forEach items="${billDetail}" var="b">
<div class="container">
  <!-- Title -->
  <!-- Main content -->
  <div class="">
    <div class="col-lg-12">
      <!-- Details -->
      <div class="card mb-4">
        <div class="card-body">
          <div class="mb-3 d-flex justify-content-between">
            <div>
                <span class="me-3"><span style="font-weight: bold">Order Date:</span> ${b.date}</span>
                <span class="me-3"><span style="font-weight: bold">Order ID:</span> #${b.bill_id}</span>
                <span class="me-3"><span style="font-weight: bold">Pay Type:</span> ${b.pay_type}</span>
                <span class="me-3"><span style="font-weight: bold">Order Status:</span> ${b.status}</span>
            </div>
            
          </div>
          <table class="table table-borderless">
            <tbody style="border: 1px solid black">
                  <tr>
                      <th style="font-weight: bold">Name</th>
                      <th style="text-align: center; font-weight: bold">Quantity</th>
                      <th style="text-align: right; font-weight: bold">Total Price</th>
                  </tr>
                <c:forEach items="${b.billProduct}" var="bp">
              <tr>
                <td>
                  <div class="d-flex mb-2">
                    
                    <div class="flex-lg-grow-1 ms-3">
                      <h6 class="mb-0" style="font-size: 15px">${bp.product_name}</h6><span>Price: ${bp.price}</span>
                    </div>
                  </div>
                </td>   
                <td style="text-align: center">${bp.quantity}</td>
                <td class="text-end">$${bp.price * bp.quantity}</td>
              </tr>
                </c:forEach>
            </tbody>
            <tfoot>
              <tr class="fw-bold" style="border-bottom: 1px solid greenyellow">
                <td colspan="2">TOTAL</td>
                <td class="text-end">$${b.total}</td>
              </tr>
            </tfoot>
          </table>
        </div>
      </div>
      <!-- Payment -->
      <div class="card mb-4">
        <div class="card-body">
          <div class="row">
            <div class="col-lg-6">
              <h3 class="h6">Payment Method</h3>
              <p>${b.pay_type}<br>
              Total: $${b.total} <span class="" style="text-align: left;<c:if test="${b.pay_status.equals('Unpaid')}"> color:red</c:if> <c:if test="${b.pay_status.equals('Paid')}">color:green</c:if>">${b.pay_status}</span></p>
            </div>
            <div class="col-lg-6">
              <h3 class="h6">Billing address</h3>
              <address>
                <strong>${b.fname}</strong><br>
                ${b.address}<br>
                ${b.country}<br>
              </address>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
</div>
    </c:forEach>
  </div>

<%@include file="include/footer.jsp" %>