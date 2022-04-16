<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container " style="height: 400px; width: 900px">
<div id="carouselExampleDark" class="carousel carousel-dark slide " data-bs-ride="carousel">
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="1" aria-label="Slide 2"></button>
    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="2" aria-label="Slide 3"></button>
  </div>
  <div class="carousel-inner rounded-6 " style="height: 400px;">
      <c:forEach items="${slideBar}" var="p">
    <div class="carousel-item active" data-bs-interval="10000">
      <img src="${pageContext.request.contextPath }/img/1/${p.photos[0]}" style="height: 400px; max-height: 400px" class="d-block w-100" alt="...">
      <div class="carousel-caption d-none d-md-block" style="background-color: black; color: white">
        <h3>${p.name}</h5>
        <h5>${p.price}$</h5>
        <p>${p.detail}</p>
      </div>
    </div>
      </c:forEach>
  </div>
  <button class="carousel-control-prev" style="background-color: gray" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden" >Previous</span>
  </button>
  <button class="carousel-control-next" type="button" style="background-color: gray"  data-bs-target="#carouselExampleDark" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>
</div>