<%@include file="include/navbar.jsp" %>

<style>.button {
  border: none;
  color: white;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  transition-duration: 0.4s;
  cursor: pointer;
  height: 28px;width: 28px;border-radius: 50%;
}
.button1 {
  background-color: #00ccff; 
  color: white; 
}

.button1:hover {
  background-color: #80e5ff;
  color: white;
  box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
}
.mar-left{
    margin-left: 8px
}
.mar-right{
    margin-right: 8px
}
</style>

<div class="container">
    <div class="row">
        <div class="col-md-2">
        </div>
        <div class="col-md-8">
            <div class="container pb-5 mb-2">
                <!-- Alert-->
                <div class="alert alert-muted alert-dismissible fade show text-left  mb-30">
                </div>
                <!-- Cart Item-->
                <c:if test="${cartList.isEmpty()}"><h3 style="margin-bottom: 100px">No Product Here !!</h3></c:if>
                <c:if test="${!cartList.isEmpty()}">
                <%--<c:forEach items="${cartList}" var="p">--%>
                <div id="show-cart" >
                <div class="cart-item d-md-flex justify-content-between"><span class="remove-item"><i class="fa fa-times"></i></span>
                    <div class="px-3 my-3">
                        <a class="cart-item-product" href="">
                            <div class="cart-item-product-thumb"><img src="" alt="Product"></div>
                            <div class="cart-item-product-info">
                                <h4 class="cart-item-product-title"></h4><span><strong>Type:</strong></span><span><strong>Price:</strong></span>
                            </div>
                        </a>
                    </div>
                    <div class="px-3 my-3 text-center">
                        <div class="cart-item-label">Quantity</div>
                        <div class="count-input">
                            
                        </div>
                    </div>
                    <div class="px-3 my-3 text-center">
                        <div class="cart-item-label">Subtotal</div><span class="text-xl font-weight-medium "></span>
                    </div>
                </div>
                </div>
                
                <!-- Coupon + Subtotal-->
                <div class="" style="float: right;">
                    <div class="py-2"><span class="d-inline-block align-middle text-sm text-muted font-weight-medium text-uppercase mr-2">Subtotal:</span><span style="font-weight: bold; color: red" id="total-cart" class="d-inline-block align-middle text-xl font-weight-medium text-success"></span></div>
                </div><br /><br />
                <!-- Buttons-->
                <hr class="my-2">
                <div class="row pt-3 pb-5 mb-2">
                    <div class="col-sm-6 mb-3"><a class="btn btn-style-1 btn-primary btn-block" href="${pageContext.request.contextPath }/Cashcheckout"><i class="fe-icon-credit-card"></i>&nbsp;Cash Payment</a></div>
                    <div class="col-sm-6 mb-3"><a class="btn btn-style-1 btn-primary btn-block" href="${pageContext.request.contextPath }/checkout"><i class="fe-icon-credit-card"></i>&nbsp;Card Payment</a></div>
                </div>
                
                    <script>
                        var  cart = [];
                        var numberCart = ${numberCart};
                        var Item = function(p_id, name, price, quantity, genre, photos, c_id){
                            this.name = name;
                            this.price = price;
                            this.product_id = p_id;
                            this.quantity = quantity;
                            this.genre = genre;
                            this.photos = photos;
                            this.c_id = c_id;
                        };
                        
                        <c:forEach items="${cartList}" var="p">
                            var item = new Item(${p.product_id}, "${p.name}", ${p.price}, ${p.quantity}, "${p.genre}", "${p.photos[0]}", ${p.id});
                            cart.push(item);
                        </c:forEach>
                            
                        displayCart();
//                        $(".add-to-cart").click(function(event){
//                             event.preventDefault();
//                             var id = Number($(this).attr("data-id"));
//                             var name = $(this).attr("data-name");
//                             var price = Number($(this).attr("data-price"));
//                             var quantity = Number($(this).attr("data-quantity"));
//                             console.log(id, name, price, quantity);
//                             addItem(id, name, price, 1);
//                             displayCart();
//                         });
//                         var cart2 = [];
                         function plusItem(id, name, price, quantity){
                             for(var i in cart){
                                 if(cart[i].product_id == id){
                                     if(cart[i].quantity >= 9){
                                         alert("Max Number is 9!");
                                         return;
                                     }else{
                                        var idP = cart[i].product_id;
                                        var idC = cart[i].c_id;
                                        $.ajax({
                                            data:{idP:idP, idC:idC},
                                            type:"POST",
                                            url:"${pageContext.request.contextPath }/plusButton",
                                            success:function(data){

                                            }
                                        });
                                        cart[i].quantity+= quantity;
                                        numberCart = numberCart + 1;
                                        return;
                                    }
                                 }
                             }
                         };
                         function displayCart(){
                             var cartArray = cart;
                             var output="";
                             for(var i in cartArray){
                                 output += "<div class='cart-item d-md-flex justify-content-between'><span class='remove-item delete-item' data-id='"+cartArray[i].product_id+"'><i class='fa fa-times'></i></span>"
                                            +"<div class='px-3 my-3' style='width:50%'>"
                                                +"<a class='cart-item-product' href='${pageContext.request.contextPath }/productView?id="+cartArray[i].product_id+"'>"
                                                    +"<div class='cart-item-product-thumb'><img src='${pageContext.request.contextPath }/img/1/"+cartArray[i].photos+"' alt='Product'></div>"
                                                    +"<div class='cart-item-product-info'>"
                                                        +"<h4 class='cart-item-product-title'>"+cartArray[i].name+"</h4><span><strong>Type:</strong> "+cartArray[i].genre+"</span><span><strong>Price:</strong> "+cartArray[i].price+"$</span>"
                                                    +"</div>"
                                                +"</a>"
                                            +"</div>"
                                            +"<div class='px-3 my-3 text-center' style='width:40%'>"
                                                +"<div class='cart-item-label'>Quantity</div>"
                                                +"<div class='count-input'>"
                                        +" <button class='plus-item button button1 mar-right' data-id='"+cartArray[i].product_id+"'>+</button>"
                                                    +cartArray[i].quantity
                                        +" <button class='subtract-item button button1 mar-left' data-id='"+cartArray[i].product_id+"'>-</button>"
                                                +"</div>"
                                            +"</div>"
                                            +"<div class='px-3 my-3 text-center' style='width:10%'>"
                                                +"<div class='cart-item-label'>Subtotal</div><span class='text-xl font-weight-medium text-success'>"+cartArray[i].quantity * cartArray[i].price+"$</span>"
                                            +"</div></div>";
                             }
                             $("#show-cart").html(output);
                             $("#total-cart").html("&nbsp; &nbsp; "+totalCart()+"$");
                         };
                         <!-- on-click for when no item in cart-->
                         $("#show-cart").on("click", ".delete-item",function(event){
                             var id = Number($(this).attr("data-id"));
                             remove(id);
                             $("#numberCart").html("<span class='badge badge-pill bg-danger'>"+numberCart+"</span>");
                             displayCart();
                         });

                         function totalCart(){
                             var total = 0;
                             for(var i in cart){
                                 total = total + (cart[i].price * cart[i].quantity);
                             }
                             return total.toFixed(2);
                         }
                         function minusS(id){
                             for(var i in cart){
                                 if(cart[i].product_id === id){
                                     cart[i].quantity --;
                                     numberCart = numberCart - 1;
                                     if(cart[i].quantity === 0){
                                        var idP = cart[i].product_id;
                                        var idC = cart[i].c_id;
                                        $.ajax({
                                            data:{idP:idP, idC:idC},
                                            type:"POST",
                                            url:"${pageContext.request.contextPath }/rmButton",
                                            success:function(data){

                                            }
                                        });
                                         cart.splice(i, 1);
                                     }else{
                                        var idP = cart[i].product_id;
                                        var idC = cart[i].c_id;
                                        $.ajax({
                                            data:{idP:idP, idC:idC},
                                            type:"POST",
                                            url:"${pageContext.request.contextPath }/minusButton",
                                            success:function(data){

                                            }
                                        });
                                     }
                                     break;
                                 }
                             }
                         }
                         function remove(id){
                             for(var i in cart){
                                 if(cart[i].product_id === id){
                                    var idP = cart[i].product_id;
                                    var idC = cart[i].c_id;
                                    $.ajax({
                                        data:{idP:idP, idC:idC},
                                        type:"POST",
                                        url:"${pageContext.request.contextPath }/rmButton",
                                        success:function(data){
                                            
                                        }
                                    });
                                     numberCart = numberCart - cart[i].quantity;
                                     cart.splice(i, 1);
                                     break;
                                 }
                             }
                         }
                         $("#show-cart").on("click", ".subtract-item", function(event){
                             var id = Number($(this).attr("data-id"));
                             minusS(id);
                             $("#numberCart").html("<span class='badge badge-pill bg-danger'>"+numberCart+"</span>");
                             displayCart();
                         });
                         $("#show-cart").on("click", ".plus-item", function(event){
                             var id = Number($(this).attr("data-id"));
                             plusItem(id, null, 0, 1);
                             $("#numberCart").html("<span class='badge badge-pill bg-danger'>"+numberCart+"</span>");
                             displayCart();
                         });
                    </script>
                <%--</c:forEach>--%>
                </c:if>

            </div>
        </div>

    </div>
    <div class="col-md-2">

    </div>
</div>
</div>


<%@include file="include/footer.jsp" %>