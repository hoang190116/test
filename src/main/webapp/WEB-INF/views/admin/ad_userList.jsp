<%@include file="includes/nav.jsp" %>
<nav class="navbar navbar-main navbar-expand-lg px-0 mx-4 shadow-none border-radius-xl" id="navbarBlur" navbar-scroll="true">
    <div class="container-fluid py-1 px-3">
        <!-- <nav aria-label="breadcrumb">
            <ol class="breadcrumb bg-transparent mb-0 pb-0 pt-1 px-0 me-sm-6 me-5">
                <li class="breadcrumb-item text-sm">Pages</li>
                <li class="breadcrumb-item text-sm text-dark active" aria-current="page">User </li>
            </ol>
            <h6 class="font-weight-bolder mb-0">User list</h6>
        </nav> -->
    </div>
</nav>
<div class="card mb-4">
    <div class="card-header">
        <div class="row align-items-center">
            <div class="col-9">
                <a href="${pageContext.request.contextPath }/adm/addUser" class=" font-weight-bolder btn btn-success ">
                    Add User
                </a>
            </div>
            <div class="col-3 text-end">
                <form class="d-flex input-group w-auto" method="GET" action="${pageContext.request.contextPath }/adm/searchUser">
                    <input type="search" name="search" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
                    <button type="submit" class="input-group-text border-0" id="search-addon">
                        <i class="fas fa-search"></i>
                    </button>
                </form>
            </div>
            <hr class="horizontal dark my-4">
            <div class="col-12">
            <div class="card-body px-0 pt-0 pb-2">
                <div class="table-responsive p-0">
                    <table class="table align-items-center mb-0">
                        <thead>
                            <tr>
                                <th class="text-center  text-xxs font-weight-bolder opacity-7">Id</th>
                                <th class="text-center  text-xxs font-weight-bolder opacity-7">Full Name</th>
                                <th class="text-center  text-xxs font-weight-bolder opacity-7">Username</th>
                                <th class="text-center  text-xxs font-weight-bolder opacity-7">Password</th>
                                <th class="text-center text-xxs font-weight-bolder opacity-7">Email</th>
                                <th class="text-center text-xxs font-weight-bolder opacity-7">Phone</th>
                                <th class="text-center  text-xxs font-weight-bolder opacity-7">Date Of Birth</th>
                                <th class="text-center  text-xxs font-weight-bolder opacity-7">Role</th>
                                <th class="text-center  text-xxs font-weight-bolder opacity-7">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${accountList}" var="a">
                            <tr>
                                <td class="align-middle text-center text-sm">
                                    <span class=" text-xs font-weight-bold">${a.id}</span>
                                </td>
                                <td class="align-middle text-center text-sm">
                                    <p class="text-xs font-weight-bold mb-0">${a.fname}</p>
                                </td>
                                <td class="align-middle text-center text-sm">
                                    <span class=" text-xs font-weight-bold">${a.uname}</span>
                                </td>
                                <td class="align-middle text-center">
                                    <span class=" text-xs font-weight-bold">${a.pass}</span>
                                </td>
                                <td class="align-middle text-center">
                                    <span class=" text-xs font-weight-bold"><c:if test="${a.email == null}">Null</c:if>${a.email}</span>
                                </td>
                                <td class="align-middle text-center">
                                    <span class=" text-xs font-weight-bold">0+ ${a.phone}</span>
                                </td>
                                <td class="align-middle text-center">
                                    <span class=" text-xs font-weight-bold"><c:if test="${a.date_of_bird == null}">Null</c:if>${a.date_of_bird}</span>
                                </td>
                                
                                <td class="align-middle text-center">
                                    <span class=" text-xs font-weight-bold">${a.role}</span>
                                </td>
                                <td class="align-middle text-center text-sm">
                                <a href="${pageContext.request.contextPath }/adm/user/editAccount?id=${a.id}" class="badge badge-success " data-toggle="tooltip" data-original-title="Edit product">
                                Edit
                            </a>
                            <a href="${pageContext.request.contextPath }/adm/user/deleteAccount?id=${a.id}" class="badge badge-danger " data-toggle="tooltip" data-original-title="Delete Product">
                             
                                Delete
                            </a>
                                </td>
                            </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                            <li class="page-item">
                                <a class="page-link" href="<c:if test="${currentP > 1}">${pageContext.request.contextPath }/adm/user/${currentP - 1}</c:if>" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                    <span class="sr-only">Previous</span>
                                </a>
                            </li>
                            <c:forEach var="i" begin="1" end="${countP}">
                            <li class="page-item <c:if test="${currentP == i}">active</c:if>"><a class="page-link" href="${pageContext.request.contextPath }/adm/user/${i}">${i}</a></li>
                            </c:forEach>
                            <li class="page-item">
                                <a class="page-link" href="<c:if test="${currentP < countP}">${pageContext.request.contextPath }/adm/user/${currentP + 1}</c:if>" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </li>
                        </ul>
                    </nav>

                </div>
            </div>
            </div>
        </div>
    </div>
</div>
<div class="card-body p-0">
    <!-- Billing history table-->

</div>

</div>
</div>
<%@include file="includes/footer.jsp" %>