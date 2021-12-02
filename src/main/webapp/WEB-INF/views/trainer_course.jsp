<%@include file="includes/client_header.jsp" %>
<%@include file="includes/client_navbar.jsp" %>
<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-3"> <span> List course from category ${name}</span></div>
    <div class="row">
     <!--  1 course -->
     <c:forEach items="${listTopic}" var="to" varStatus="status">
     <div class="col-md-4" style="margin-bottom: 25px">
        <div class="card">
            <a href="CourseT?id=${to.c_id}" style="text-decoration:none; outline: none">
            <div class="text-center"> <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS4YhCWEDc4h0SfI5zT42VxRRpFQeSAZT3OYw&usqp=CAU" width="250"> </div>
            <div class="text-center">

                <h5>${to.c_name}</h5>

            </div>
            </a>
            <p style="border-top:1px solid #ccc; padding-left: 15px">Descripsion: ${to.c_des}</p>
        </div>
    </div>
     </c:forEach>
</div>
</div>
