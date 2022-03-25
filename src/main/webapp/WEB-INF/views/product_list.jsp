<%@include file="include/navbar.jsp" %>


  <div class="container"><br/>
  <div class="card">
    <div class="container-fliud">
        
        
         <c:forEach items="${listProduct}" var="p">
      <div class="row">
        <div class="col-md-3">
          <div class="ibox">
            <div class="ibox-content product-box">
              <div class="">
                <img src="https://hc.com.vn/i/ecommerce/media/GS.008256_FEATURE_88774.jpg" alt="">
              </div>
              <div class="product-desc">
                <a href="#" class="product-name">${p.name}</a>
                <small class="text-muted">${p.price}</small>
                <div class="m-t text-right">

                  <a href="#" class="btn btn-xs btn-outline btn-primary">View <i class="fa fa-long-arrow-right"></i> </a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
         </c:forEach>
        
    </div>
    <div class="row">
                <div class="col-lg-12">
                    <nav aria-label="Page navigation">
                        <ul class="pagination job-pagination justify-content-center mt-5 mb-5">
                            <li class="page-item disabled">
                                <a class="page-link" href="#" tabindex="-1" aria-disabled="true">
                                    <i class="mdi mdi-chevron-double-left f-15"></i>
                                </a>
                            </li>
                            <li class="page-item active"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item"><a class="page-link" href="#">4</a></li>
                            <li class="page-item">
                                <a class="page-link" href="#">
                                    <i class="mdi mdi-chevron-double-right f-15"></i>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
  </div>


<%@include file="include/footer.jsp" %>