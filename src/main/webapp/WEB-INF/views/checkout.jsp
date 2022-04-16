<%@include file="include/navbar.jsp" %>


    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
      
      .flipswitch {
  border: 1px solid red
}

.flipswitch:after {
  border: 1px solid red
}

.flipswitch:after {
  border: 1px solid red
}

.flipswitch:checked:after {
  border: 1px solid red
}

input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}
input[type=number] {
  -moz-appearance: textfield;
}
    </style>

  <body class="bg-light">
    <div class="container">
  <div class="py-5 text-center">
  </div>

  <div class="row">
    <div class="col-md-4 order-md-2 mb-4">
      <h4 class="d-flex justify-content-between align-items-center mb-3">
        <span class="text-muted">Your cart</span>
      </h4>
      <ul class="list-group mb-3">
          <span id="show-cart">
        <li class="list-group-item d-flex justify-content-between lh-condensed">
          <div>
            <h6 class="my-0">Product name</h6>
            <small class="text-muted">Brief description</small>
          </div>
          <span class="text-muted">$</span>
        </li>
          </span>
        <li class="list-group-item d-flex justify-content-between ">
          <span class="text-success">Total (USD)</span>
          <strong class="text-success" id="total-price">$</strong>
          <span id="totalHidden"></span>
        </li>
      </ul>
        <script>
            var cart = [];
            var Item = function(name, price, quantity){
                            this.name = name;
                            this.price = price;
                            this.quantity = quantity;
                        };
            <c:forEach items="${cartList}" var="p">
                var item = new Item("${p.name}", ${p.price}, ${p.quantity});
                cart.push(item);
            </c:forEach>
            
            displayCart();
                function displayCart(){
                    var cartArray = cart;
                    var total = 0;
                    var output="";
                    for(var i in cartArray){
                    output += "<li class='list-group-item d-flex justify-content-between lh-condensed'>"
                                +"<div>"
                                  +"<h6 class='my-0'>"+cartArray[i].name+"</h6>"
                                  +"<small class='text-muted'>Quantity: "+cartArray[i].quantity+"</small>"
                                +"</div>"
                                +"<span class='text-muted'>"+cartArray[i].price * cartArray[i].quantity+"$</span>"
                              +"</li>";
                    total = total + cartArray[i].price * cartArray[i].quantity;
                    }
                    $("#show-cart").html(output);
                    $("#total-price").html(total+"$");
                    $("#totalHidden").html("<input type='hidden' value='"+total+"' id='totalInputHidden'>");
                };
        </script>

    </div>
    <div class="col-md-8 order-md-1">
      <h4 class="mb-3">Billing address
<c:if test="${payment == true}">

      <!-- Button trigger modal -->
<button type="button" class="btn btn-info   pull-right" data-toggle="modal" data-target="#centralModalSm">
 Choose saved information
</button>

<!-- Central Modal Small -->

<div class="modal fade" id="centralModalSm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
  aria-hidden="true">

  <!-- Change class .modal-sm to change the size of the modal -->
  <div class="modal-dialog " role="document">

    <div class="modal-content" id="myModal">
      <div class="modal-header" id="myModal">
        <h4 class="modal-title w-100" id="myModalLabel">Your payment card</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
<table class="table table-hover text-nowrap">
  <thead>
    <tr>
      <th scope="col">Bank</th>
      <th scope="col">Fullname</th>
      <th scope="col" style="text-align: center">Select</th>
    </tr>
  </thead>
  <tbody>
      <c:if test="${paymentList.isEmpty()}">
              <tr>
                  <td style="border: none; color: red; text-align: center; font-weight: bold">
                  No saved Information
                  </td>
              </tr>
      </c:if>
      <c:forEach items="${paymentList}" var="pay" varStatus="loopCount">
    <tr>
      <td style="width:40%; vertical-align: middle">
        <span class="">
            <span>${pay.bank_name}</span><br/><span style="font-size: 12px">${pay.card_number}</span>
        </span>
      </td>
      <td style="width:40%; vertical-align: middle">
        <span class="">
          <span>${pay.fname}</span>
        </span>
      </td>
      <td style="width:20%; vertical-align: middle">
        <span>
        <button type="button" class="btn btn-secondary" style="padding: 10px" id="takeCard${loopCount.index}" data-fname="${pay.fname}" data-email="${pay.email}" data-country="${pay.country}" data-address="${pay.address}" data-bank="${pay.bank_name}" data-cardnum="${pay.card_number}" data-YY="${pay.YY}" data-MM="${pay.MM}">Choose</button>
        </span>
      </td>
    </tr>
      </c:forEach>
  </tbody>
</table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
</c:if>
<!-- Central Modal Small -->
      </h4>



      <form class="needs-validation" novalidate>
        <div class="row">
           <div class="mb-3">
          <label for="fullname">Full name </label>
          <span id="fname-input">
          <input type="text" class="form-control" id="fullname" placeholder="Fullname">
          </span>
        </div>
        </div>
        <div class="mb-3">
          <label for="email">Email <span class="text-muted">(Optional)</span></label>
          <span id="email-input">
          <input type="email" class="form-control" id="email" placeholder="you@example.com">
          </span>
        </div>
        <div class="mb-3">
          <label for="country">Country</label>
          <span id="country-input">
          <input class="form-control" id="country" placeholder="Your country/nation" required>
          </span>
        </div>
        <div class="mb-3">
          <label for="address">Address</label>
          <span id="adres-input">
          <input class="form-control" id="address" placeholder="1234 Main St" required>
          </span>
        </div>
        <hr class="mb-4">
        <c:if test="${payment == true}">
        <hr class="mb-4">
        
        <h4 class="mb-3">Payment</h4>

        <div class="d-block my-3" id="radioPay">
          <div class="custom-control custom-radio">
            <input id="credit" name="paymentMethod" type="radio" class="custom-control-input" checked value="Credit card" required>
            <label class="custom-control-label" for="credit">Credit card</label>
          </div>
          <div class="custom-control custom-radio">
            <input id="debit" name="paymentMethod" type="radio" class="custom-control-input" value="Debit card" required>
            <label class="custom-control-label" for="debit">Debit card</label>
          </div>
          <div class="custom-control custom-radio">
            <input id="paypal" name="paymentMethod" type="radio" class="custom-control-input" value="Paypal" required>
            <label class="custom-control-label" for="paypal">PayPal</label>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6 mb-3">
            <label for="cc-name">Name on card</label>
            <span id="nameCard">
            <input class="form-control" placeholder="" id="nameCardInput" required>
            </span>
            <small class="text-muted">Full name as displayed on card</small>
          </div>
          <div class="col-md-6 mb-3">
            <label>Card number</label>
            <span id="numberCard">
            <input type="number" class="form-control" placeholder="" id="numberCardInput" required>
            </span>
          </div>
        </div>
        <div class="row">
          <div class="col-md-3 mb-3" style="margin-right: -20px;">
            <label for="">Expiration</label>
            <span id="mmCard">
            <input type="number" id="mmCardInput" class="form-control" style="max-width: 150px" placeholder="MM" onKeyUp="if(this.value>12){this.value='12';}else if(this.value<1){this.value='1';}"`>
            </span>
          </div>
          <div class="col-sm-1 mb-3">
            <label for=""></label>
            <h2>/</h2>
          </div>
            <div class="col-md-3 mb-3" style="margin-left: -25px; margin-right: -15px">
            <label></label>
            <span id="yyCard">
            <input type="number" id="yyCardInput" class="form-control" style="max-width: 150px" placeholder="YY" onKeyUp="if(this.value>99){this.value='99';}else if(this.value<0){this.value='0';}">
            </span>
          </div>
          <div class="col-md-3 mb-3">
            <label for="cc-cvv">CVV</label>
            <span id="cvvCard">
            <input type="number" class="form-control" id="cvvCardInput" placeholder="" onKeyUp="if(this.value>999){this.value='999';}">
            </span>
          </div>
        </div>
        <div class="custom-control custom-checkbox" id="checkBoxSaveInfor">
          <input type="checkbox" class="custom-control-input" id="save-info" name="save-info">
          <label>Save this information for next time</label><br />
        </div>
        </c:if>
        
        <div class="custom-control custom-checkbox" id="agreeBox">
                <input type="checkbox" id="agreePolicy">
          <label>I have read and accept the <a href="#">Privacy Policy</a></label>
        </div>
        
        <hr class="mb-4">
        <a class="btn btn-primary btn-lg btn-block" id="pay-button">Buy</a>
        
      </form>
    </div>
  </div>
            <script>
                $('#fname-input').on("change", "#fullname",function(){
                    var fullname = $('#fullname').val();
                    if(fullname){
                        $("#fname-input").html("<input value='"+fullname+"' type='text' class='form-control' id='fullname' placeholder='Fullname'>");
                    }else{
                        $("#fname-input").html("<input style='border: 2px solid red' type='text' class='form-control' id='fullname' placeholder='Fullname'>");
                    }
                 });
                 $('#email-input').on("change", "#email",function(){
                    var email = $('#email').val();
                    if(email){
                        $("#email-input").html("<input value='"+email+"' type='email' class='form-control' id='email' placeholder='you@example.com'>");
                    }else{
                        $("#email-input").html("<input style='border: 2px solid red' type='email' class='form-control' id='email' placeholder='you@example.com'>");
                    }
                 });
                 $('#adres-input').on("change", "#address",function(){
                    var adres = $('#address').val();
                    if(adres){
                        $("#adres-input").html("<input value='"+adres+"' class='form-control' id='address' placeholder='123 Main St'>");
                    }else{
                        $("#adres-input").html("<input style='border: 2px solid red' class='form-control' id='address' placeholder='123 Main St'>");
                    }
                 });
                 $('#country-input').on("change", "#country",function(){
                    var country = $('#country').val();
                    if(country){
                        $("#country-input").html("<input value='"+country+"' class='form-control' id='country' placeholder='Your country/nation'>");
                    }else{
                        $("#country-input").html("<input style='border: 2px solid red' class='form-control' id='country' placeholder='Your country/nation'>");
                    }
                 });
                 $('#agreeBox').on("change", "#agreePolicy",function(){
                    var box = $('#agreePolicy:checked').val();
                    if(box){
                        $("#agreeBox").html("<input type='checkbox' class='custom-control-input' id='agreePolicy' name='agreePolicy' checked='checked'>"
                            +"&nbsp<label class='custom-control-label' for='save-info'> I have read and accept the <a href='#'>Privacy Policy</a></label>");
                    }
                 });
                 if(${payment} === true){
                 $('#nameCard').on("change", "#nameCardInput",function(){
                    var fname = $('#nameCardInput').val();
                    if(fname){
                        $("#nameCard").html("<input value='"+fname+"' type='text' class='form-control' placeholder='' id='nameCardInput'>");
                    }else{
                        $("#nameCard").html("<input type='text' class='form-control' style='border: 2px solid red' placeholder='' id='nameCardInput'>");
                    }
                 });
                 $('#numberCard').on("change", "#numberCardInput",function(){
                    var value = $('#numberCardInput').val();
                    if(value){
                        $("#numberCard").html("<input value='"+value+"' type='number' class='form-control' placeholder='' id='numberCardInput' required>");
                    }else{
                        $("#numberCard").html("<input style='border: 2px solid red' type='number' class='form-control' placeholder='' id='numberCardInput' required>");
                    }
                 });
                 $('#mmCard').on("change", "#mmCardInput",function(){
                    var value = $('#mmCardInput').val();
                    if(value){
                        $("#mmCard").html("<input value='"+value+"' type='number' id='mmCardInput' class='form-control' style='max-width: 150px' placeholder='MM' onKeyUp='if(this.value>12){this.value="+''+12+''+";}else if(this.value<1){this.value="+''+1+''+";}'`>");
                    }else{
                        $("#mmCard").html("<input style='border: 2px solid red' type='number' id='mmCardInput' class='form-control' style='max-width: 150px' placeholder='MM' onKeyUp='if(this.value>12){this.value="+''+12+''+";}else if(this.value<1){this.value="+''+1+''+";}'`>");
                    }
                 });
                 $('#yyCard').on("change", "#yyCardInput",function(){
                    var value = $('#yyCardInput').val();
                    if(value){
                        $("#yyCard").html("<input value='"+value+"' type='number' id='yyCardInput' class='form-control' style='max-width: 150px' placeholder='YY' onKeyUp='if(this.value>99){this.value="+''+99+''+";}else if(this.value<0){this.value="+''+0+''+";}'>");
                    }else{
                        $("#yyCard").html("<input style='border: 2px solid red' type='number' id='yyCardInput' class='form-control' style='max-width: 150px' placeholder='YY' onKeyUp='if(this.value>99){this.value="+''+99+''+";}else if(this.value<0){this.value="+''+0+''+";}'>");
                    }
                 });
                 $('#cvvCard').on("change", "#cvvCardInput",function(){
                    var value = $('#cvvCardInput').val();
                    if(value){
                        $("#cvvCard").html("<input value='"+value+"' type='number' class='form-control' id='cvvCardInput' placeholder='' onKeyUp='if(this.value>999){this.value="+''+999+''+";}'>");
                    }else{
                        $("#cvvCard").html("<input style='border: 2px solid red' type='number' class='form-control' id='cvvCardInput' placeholder='' onKeyUp='if(this.value>999){this.value="+''+999+''+";}'>");
                    }
                 });
                }
                $("#pay-button").click(function(event){
                    var fullname = $('#fullname').val();
                    if(!fullname){
                        $("#fname-input").html("<input style='border: 2px solid red' type='text' class='form-control' id='fullname' placeholder='Fullname'>");
                    }
                    var email = $('#email').val();
                    if(!email){
                        $("#email-input").html("<input style='border: 2px solid red' type='email' class='form-control' id='email' placeholder='you@example.com'>");
                    }
                    var adres = $('#address').val();
                    if(!adres){
                        $("#adres-input").html("<input style='border: 2px solid red' class='form-control' id='address' placeholder='123 Main St'>");
                    }
                    var country = $('#country').val();
                    if(!country){
                        $("#country-input").html("<input style='border: 2px solid red' class='form-control' id='country' placeholder='Your country/nation'>");
                    }
                    if(${payment} === true){
                    var nameCard = $('#nameCardInput').val();
                    if(!nameCard){
                        $("#nameCard").html("<input type='text' class='form-control' style='border: 2px solid red' placeholder='' id='nameCardInput'>");
                    }
                    var numberCard = $('#numberCardInput').val();
                    if(!numberCard){
                        $("#numberCard").html("<input style='border: 2px solid red' type='number' class='form-control' placeholder='' id='numberCardInput' required>");
                    }
                    var mmCard = Number($('#mmCardInput').val());
                    if(!mmCard){
                        $("#mmCard").html("<input style='border: 2px solid red' type='number' id='mmCardInput' class='form-control' style='max-width: 150px' placeholder='MM' onKeyUp='if(this.value>12){this.value="+''+12+''+";}else if(this.value<1){this.value="+''+1+''+";}'>");
                    }
                    var yyCard = Number($('#yyCardInput').val());
                    if(!yyCard){
                        $("#yyCard").html("<input style='border: 2px solid red' type='number' id='yyCardInput' class='form-control' style='max-width: 150px' placeholder='YY' onKeyUp='if(this.value>99){this.value="+''+99+''+";}else if(this.value<0){this.value="+''+0+''+";}'>");
                    }
                    var cvvCard = Number($('#cvvCardInput').val());
                    if(!cvvCard){
                        $("#cvvCard").html("<input style='border: 2px solid red' type='number' class='form-control' id='cvvCardInput' placeholder='' onKeyUp='if(this.value>999){this.value="+''+999+''+";}'>");
                    }
                    }
                    if(${payment} === true){
                    if(fullname && email && adres && country && nameCard && numberCard && mmCard && yyCard && cvvCard){
                        var agreebox = $('#agreePolicy:checked').val();
                        if(agreebox){
                            var saveInfo = $('#save-info:checked').val();
                            if(saveInfo){
                                var bankName = document.querySelector('input[name="paymentMethod"]:checked').value;
                                var totalPrice = Number($('#totalInputHidden').val());
                                var idP = Number(${idP});
                                $.ajax({
                                    type:'POST',
                                    data:{fname:fullname, email:email, adres:adres, country:country, 
                                        nameCard:nameCard, numberCard:numberCard, mmCard:mmCard, yyCard:yyCard, 
                                        cvvCard:cvvCard, bankName: bankName, total:totalPrice, idP:idP},
                                    url:'savingpaying',
                                    success:function(result){
                                        alert('Thank for using our services!!');
                                        window.location = "${pageContext.request.contextPath }/myBill/";
                                    }
                                });
                            }else{
                                var bankName = document.querySelector('input[name="paymentMethod"]:checked').value;
                                var totalPrice = Number($('#totalInputHidden').val());
                                var idP = Number(${idP});
                                $.ajax({
                                    type:'POST',
                                    data:{fname:fullname, email:email, adres:adres, country:country, 
                                        nameCard:nameCard, numberCard:numberCard, mmCard:mmCard, yyCard:yyCard, 
                                        cvvCard:cvvCard, bankName: bankName, total:totalPrice, idP:idP},
                                    url:'paying',
                                    success:function(result){
                                        alert('Thank for using our services!!');
                                        window.location = "${pageContext.request.contextPath }/myBill/";
                                    }
                                });
                            }
                        }else{
                            $("#agreeBox").html("<input type='checkbox' class='custom-control-input' id='agreePolicy' name='agreePolicy' style='box-shadow: 0px 0px 5px 1px red;'>"
                            +"&nbsp<label class='custom-control-label' for='save-info' style='color:red'> I have read and accept the <a href='#'>Privacy Policy</a></label>");
                        }
                    }
                    }else if(fullname && email && adres && country){
                        var agreebox = $('#agreePolicy:checked').val();
                        if(agreebox){
                        var totalPrice = Number($('#totalInputHidden').val());
                        var idP = Number(${idP});
                                $.ajax({
                                    type:'POST',
                                    data:{fname:fullname, email:email, adres:adres, country:country, total:totalPrice, idP:idP},
                                    url:'Cashpaying',
                                    success:function(result){
                                    }
                                });
                                alert('Thank for using our services!!');
                                window.location = "${pageContext.request.contextPath }/myBill/";
                        }else{
                            $("#agreeBox").html("<input type='checkbox' class='custom-control-input' id='agreePolicy' name='agreePolicy' style='box-shadow: 0px 0px 5px 1px red;'>"
                            +"&nbsp<label class='custom-control-label' for='save-info' style='color:red'> I have read and accept the <a href='#'>Privacy Policy</a></label>");
                        }
                    }
                    
                });
                
                if(${payment} === true){
                var lengthPayment = ${fn:length(paymentList)};
                for(var i=0; i<lengthPayment; i++){
                    $("#takeCard"+i).click(function(event){
                        var fname = $(this).attr("data-fname");
                        var email = $(this).attr("data-email");
                        var country = $(this).attr("data-country");
                        var address = $(this).attr("data-address");
                        var fname = $(this).attr("data-fname");
                        var bank = $(this).attr("data-bank");
                        var number = $(this).attr("data-cardnum");
                        var YY = $(this).attr("data-YY");
                        var MM = $(this).attr("data-MM");
                        var credit = null, debit = null, paypal = null;
                        if(bank === "Credit card"){
                            credit = "checked";
                        }else if(bank === "Debit card"){
                            debit = "checked";
                        }else if(bank === "Paypal"){
                            paypal = "checked";
                        }
                        $("[data-dismiss=modal]").trigger({ type: "click" });
                        $("#radioPay").html("<div class='custom-control custom-radio'>"
                            +"<input id='credit' name='paymentMethod' type='radio' class='custom-control-input' "+credit+" value='Credit card' required>"
                            +"<label class='custom-control-label' for='credit'>&nbsp;Credit card</label>"
                          +"</div>"
                          +"<div class='custom-control custom-radio'>"
                            +"<input id='debit' name='paymentMethod' type='radio' class='custom-control-input' "+debit+" value='Debit card' required>"
                            +"<label class='custom-control-label' for='debit'>&nbsp;Debit card</label>"
                          +"</div>"
                          +"<div class='custom-control custom-radio'>"
                            +"<input id='paypal' name='paymentMethod' type='radio' class='custom-control-input' "+paypal+" value='Paypal' required>"
                            +"<label class='custom-control-label' for='paypal'>&nbsp;PayPal</label>"
                          +"</div>");

                          $("#nameCard").html("<input value='"+fname+"' type='text' class='form-control' placeholder='' id='nameCardInput' required>");
                          $("#email-input").html("<input value='"+email+"' type='email' class='form-control' id='email' placeholder='you@example.com'>");
                          $("#fname-input").html("<input value='"+fname+"' type='text' class='form-control' id='fullname' placeholder='Fullname'>");
                          $("#adres-input").html("<input value='"+address+"' class='form-control' id='address' placeholder='123 Main St'>");
                          $("#country-input").html("<input value='"+country+"' class='form-control' id='country' placeholder='Your country/nation'>");
                          $("#numberCard").html("<input value='"+number+"' type='number' class='form-control' placeholder='' id='numberCardInput' required>");
                          $("#mmCard").html("<input value='"+MM+"' type='number' id='mmCardInput' class='form-control' style='max-width: 150px' placeholder='MM' onKeyUp='if(this.value>12){this.value="+''+12+''+";}else if(this.value<1){this.value="+''+1+''+";}'`>");
                          $("#yyCard").html("<input value='"+YY+"' type='number' id='yyCardInput' class='form-control' style='max-width: 150px' placeholder='YY' onKeyUp='if(this.value>99){this.value="+''+99+''+";}else if(this.value<0){this.value="+''+0+''+";}'>");
                    });
                }
                }
            </script>
            
</div>
<%@include file="include/footer.jsp" %>