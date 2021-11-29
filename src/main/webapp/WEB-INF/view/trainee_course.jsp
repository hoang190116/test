<%@include file="includes/client_header.jsp" %>
<%@include file="includes/client_navbar.jsp" %>

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-3"> <h4>Topic list from course ${name}</h4></div>
    <div class="row">
     <!--  1 course -->
     <c:if test="${listTopic != null}">
     <c:forEach items="${listTopic}" var="to" varStatus="status">
     <div class="col-md-4" style="margin-bottom: 25px">
        <div class="card">
            <div class="text-center"> <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS4YhCWEDc4h0SfI5zT42VxRRpFQeSAZT3OYw&usqp=CAU" width="250"> </div>
            <div class="text-center">

                <h5>${to.topic_name}</h5><span class="text-success">Trainer name: <c:if test="${to.u_fullname == null}">None</c:if> ${to.u_fullname}</span>

            </div>
            <p style="border-top:1px solid #ccc; padding-left: 15px">Descripsion: ${to.topic_des}</p>
        </div>
    </div>
     </c:forEach>
     </c:if>
</div>
</div>
