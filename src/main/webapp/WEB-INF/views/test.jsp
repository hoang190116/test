<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page session="false" %>
<html>
<head>
<title>Upload File Request Page</title>
<!-- comment -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<!-- comment -->
</head>
<body>
    <form method="POST" action="uploadFile" id="forms" enctype="multipart/form-data">
        <input type="text" id="myuser"/><br/>
        <span class="" id="result">abc</span><br/>
            File to upload: <input type="file" name="fileImage" id="fileControl" accept="image/png, image/jpeg" multiple="multiple" /><br /> 
            <input type="button" id="loadFileXml" value="loadXml" onclick="document.getElementById('file').click();" />
            <input type="file" style="display:none;" id="file" name="file"/>
            <div id="myImg">
                <img id="thumbnaila" alt="Logo" height="200px" width="200px" />
            </div>
<script>//check unique name
    $(document).ready(function(){
      $('#myuser').change(function(){
         var myuser = $('#myuser').val();
         $.ajax({
             type:'POST',
             data:{myuser:myuser},
             url:'unameCheck',
             success:function(result){
                 $('#result').html(result);
             }
         });
      });  
    });
</script>
            <script type="text/javascript">             
                //=====================Check IMG
                const input = document.querySelector('#fileControl');
// Listen for files selection
input.addEventListener('change', (e) => {
    // Retrieve all files
    const files = input.files;

    // Check files count
    if (files.length > 2) {
        alert(`Only 2 files are allowed to upload.`);
        $("#fileControl").val('');
        return;
    }

    // TODO: continue uploading on server
});
                //=======================Displasy IMG
                $(function() {
  $(":file").change(function() {
      rmvImg();
    if (this.files && this.files[0]) {
      for (var i = 0; i < this.files.length; i++) {
        var reader = new FileReader();
        reader.onload = imageIsLoaded;
        reader.readAsDataURL(this.files[i]);
      }
    }
  });
});

function imageIsLoaded(e) {
  $('#myImg').append('<img height="200px" width="200px" src=' + e.target.result + '>');
};
function rmvImg(){
    $("#myImg > img").remove();
}
//-----------------------------------------------------------Display Single IMG
//                function preview_image(event) {
//                  var reader = new FileReader();
//                  reader.onload = function(){
//                    var output = document.getElementById('thumbnaila');
//                    output.src = reader.result;
//                  }
//                  reader.readAsDataURL(event.target.files[0]);
//                }
            </script>
            
		<input type="submit" value="Upload"> Press here to upload the file!
            
    </form>       
<!--Show img from Apache    <form th:opject="${product2}">
        <img src="${product2.logoImgPath}" />
    </form>-->
                 
    <!-- ########################################################################### -->
<div class="container">
  <h2>Modal Example</h2>
  <!-- Trigger the modal with a button -->
  <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal" id="edit_data">Open Modal</button>

  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modal Header</h4>
        </div>
        <div class="modal-body">
          <p>Some text in the modal.</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
</div>
    <script>
        <!-- Display Model -->
        $(document).ready(function(){
           $('#edit_data').on('click', function(){
//               var id = $'{this}.attr('id');//id in tag id=""
                var id = 1;
               $.ajax({
                   data:{id:id},
                   type:"POST",
                   url:"checkAjax",
                   success:function(data){
//                       $('#name').val(data.name);//input type="text" id="name"
//                       $('#insert').val("Update");//input type="submit" id="insert"
//                       $('#myModal').modal('show');//div
                   }
               });
           }) ;
        });
        </script>
    <!-- ########################################################################### -->
        <div id="load">
        <table>
            <c:forEach items="${listProduct}" var="to">
                <tr>
                 <td data-id="${to.getProduct_id()}">${to.product_id}</td>
                 <td data-name="${to.getName()}">${to.getName()}</td>
                 <td data-price="${to.getPrice()}">${to.getPrice()}</td>
                 <td style="padding-left: 15px; padding-right: 15px" id="quantity${to.getProduct_id()}">${to.getQuantity()}</td>
                 <td id="total${to.getProduct_id()}">$${to.totalPrice()}</td>
                 <td><a class="add-to-cart" href="" data-id="${to.getProduct_id()}" data-name="${to.getName()}" data-price="${to.getPrice()}" data-quantity="${to.getQuantity()}">Add</a></td>
              </tr>
             </c:forEach>
        </table>
    </div>
               <button class="clear-cart">Clear</button>
           
           <div>
               <ul id="show-cart">
                   
               </ul>
               <div>Total:$<span id="total-cart"></span></div>
           </div>
    <script>
        var  cart = [];
        var Item = function(id, name, price, quantity){
            this.name = name;
            this.price = price;
            this.product_id = id;
            this.quantity = quantity;
        };
        <c:forEach items="${listProduct}" var="to">
            var item = new Item(${to.product_id}, "${to.name}", ${to.price}, ${to.quantity});
            cart.push(item);
        </c:forEach>
            console.log(cart[0]);
       $(".add-to-cart").click(function(event){
            event.preventDefault();
            var id = Number($(this).attr("data-id"));
            var name = $(this).attr("data-name");
            var price = Number($(this).attr("data-price"));
            var quantity = Number($(this).attr("data-quantity"));
            console.log(id, name, price, quantity);
            addItem(id, name, price, 1);
            displayCart();
        });
        var cart2 = [];
        function addItem(id, name, price, quantity){
            for(var i in cart2){
                if(cart2[i].product_id == id){
                    cart2[i].quantity+= quantity;
                    return;
                }
            }
                var item = new Item(id, name , price, quantity);
                cart2.push(item);
        };
        function displayCart(){
            var cartArray = listCart();
            var output="";
            for(var i in cartArray){
                output += "<li>"+cartArray[i].product_id
                        +" "+cartArray[i].name
                        +" "+cartArray[i].quantity
                        +" <button class='plus-item' data-id='"+cartArray[i].product_id+"'>+</button>"
                        +" <button class='subtract-item' data-id='"+cartArray[i].product_id+"'>-</button>"
                        +" <button class='delete-item' data-id='"+cartArray[i].product_id+"'>Delete</button>"
                        +"</li>";
            }
            $("#show-cart").html(output);
            $("#total-cart").html(totalCart());
        };
        <!-- on-click for when no item in cart-->
        $("#show-cart").on("click", ".delete-item",function(event){
            var id = Number($(this).attr("data-id"));
            remove(id);
            displayCart();
        });
        function listCart(){
            var cartCopy=[];
            for(var i in cart2){
                var item = cart2[i];
                var itemCopy = {};
                for(var p in item){
                    itemCopy[p] = item[p];
                }
                cartCopy.push(itemCopy);
            }
            return cartCopy;
        }
        function totalCart(){
            var total = 0;
            for(var i in cart2){
                total = total + (cart2[i].price * cart2[i].quantity);
            }
            return total.toFixed(2);
        }
        function minusS(id){
            for(var i in cart2){
                if(cart2[i].product_id === id){
                    cart2[i].quantity --;
                    if(cart2[i].quantity === 0){
                        cart2.splice(i, 1);
                    }
                    break;
                }
            }
        }
        function remove(id){
            for(var i in cart2){
                if(cart2[i].product_id === id){
                    cart2.splice(i, 1);
                    break;
                }
            }
        }
        $("#show-cart").on("click", ".subtract-item", function(event){
            var id = Number($(this).attr("data-id"));
            minusS(id);
            displayCart();
        });
        $("#show-cart").on("click", ".plus-item", function(event){
            var id = Number($(this).attr("data-id"));
            addItem(id, null, 0, 1);
            displayCart();
        });
        function minus(id){
//            for (var i in cart){
//            }
        };
    </script>
</body>
</html>