<%@include file="include/navbar.jsp" %>


  <div class="container"><br/>
  <div class="card">
    <div class="container-fliud">
        
        
         
      <div class="row">
          <c:forEach items="${productList}" var="p">
        <div class="col-md-3">
          <div class="ibox">
            <div class="ibox-content product-box">
              <div class="">
                <img style="height: 140px; width: 200px" src="${pageContext.request.contextPath }/img/1/${p.photos[0]}" alt="">
              </div>
              <div class="product-desc">
                <a href="${pageContext.request.contextPath }/productView?id=${p.product_id}" class="product-name">${p.name}</a>
                <small class="text-muted">$${p.price}</small>
                <div class="m-t text-right">
                  <a href="${pageContext.request.contextPath }/productView?id=${p.product_id}" class="btn btn-xs btn-outline btn-primary">View <i class="fa fa-long-arrow-right"></i> </a>
                </div>
              </div>
            </div>
          </div>
        </div>
                </c:forEach>
      </div>
         
        
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
                            <c:forEach var="i" begin="1" end="${countP}">
                                <li class="page-item <c:if test="${currentP == i}">active</c:if>"><form method="get" action="${pageContext.request.contextPath }/searchProduct/${i}"><button class="page-link" name="search" value="${searchName}" type="submit">${i}</button></form></li>
                            </c:forEach>
                            <li class="page-item">
                                <a class="page-link" href="#">
                                    <i class="mdi mdi-chevron-double-right f-15">...</i>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
  </div>
  </div>


<%@include file="include/footer.jsp" %>