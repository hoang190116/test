<%@include file="include/navbar.jsp" %>



<div class="container">
    <br />
  <h3>Hot product</h3>
  <div class="card">
    <div class="container-fliud">
      <div class="row">
        <div class="text-right mb-3">
          <a href="view2/1" type="button" class="btn btn-light"> More <i class="fa fa-arrow-right" aria-hidden="true"></i></a>
        </div>
        <c:forEach items="${hotList}" var="p">
        <div class="col-md-3">
          <div class="ibox">
            <div class="ibox-content product-box">
              <div class="">
                <img style="height: 140px; width: 200px" src="" alt="">
              </div>
              <div class="product-desc">
                  <form action="productView?id=${p.product_id}" method="post">
                <a type= "submit" href="productView?id=${p.product_id}" class="product-name">${p.name}</a>
                <small class="text-muted">$${p.price}</small>
                <div class="m-t text-right">
                  <a type= "submit" href="productView?id=${p.product_id}" class="btn btn-xs btn-outline btn-primary">View <i class="fa fa-long-arrow-right"></i> </a>
                </div>
                  </form>
              </div>
            </div>
          </div>
        </div>
          </c:forEach>
      </div>
    </div>
  </div>


  <div class="container">
  <h3>Product</h3>
  <div class="card">
    <div class="container-fliud">
      <div class="row">
        <div class="text-right mb-3">
          <a href="view/1" type="button" class=" btn btn-light"> More <i class="fa fa-arrow-right" aria-hidden="true"></i></a>
        </div>
          <c:forEach items="${listProduct}" var="p">
        <div class="col-md-3">
          <div class="ibox">
            <div class="ibox-content product-box">
              <div class="">
                <img style="height: 140px; width: 200px" src="" alt="">
              </div>
              <div class="product-desc">
                <a type="submit" href="productView?id=${p.product_id}" class="product-name">${p.name}</a>
                <small class="text-muted">$${p.price}</small>
                <div class="m-t text-right">
                  <a type= "submit" href="productView?id=${p.product_id}" class="btn btn-xs btn-outline btn-primary">View <i class="fa fa-long-arrow-right"></i> </a>
                </div>
              </div>
            </div>
          </div>
        </div>
          </c:forEach>
      </div>
    </div>
  </div>
<%@include file="include/footer.jsp" %>
