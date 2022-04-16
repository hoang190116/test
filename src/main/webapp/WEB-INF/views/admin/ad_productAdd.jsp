<%@include file="includes/nav.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
    input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}
input[type=number] {
  -moz-appearance: textfield;
}
</style>
<nav class="navbar navbar-main navbar-expand-lg px-0 mx-4 shadow-none border-radius-xl" id="navbarBlur" navbar-scroll="true">
    <div class="container-fluid py-1 px-3">
        <!-- <nav aria-label="breadcrumb">
            <ol class="breadcrumb bg-transparent mb-0 pb-0 pt-1 px-0 me-sm-6 me-5">
                <li class="breadcrumb-item text-sm">Pages</li>
                <li class="breadcrumb-item text-sm text-dark active" aria-current="page">User </li>
            </ol>
            <h6 class="font-weight-bolder mb-0">User add</h6>
        </nav>
    </div> -->
</nav>

<div class="card-body p-0">
    <div class="card mb-4">
        <div class="card-body">
            <form:form action="addnewProduct" method="POST" modelAttribute="p" enctype="multipart/form-data">
                <h6 class="heading-small text-muted mb-4">Product </h6>
                <ul class="nav nav-tabs nav-justified " role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" data-bs-toggle="" href="#main">Main content</a>
                    </li>
                </ul>
                <h5 class="btn-success">${success}</h5>
                <!-- Tab panes -->
                <div class="tab-content">
                    <div id="main" class="container tab-pane active"><br>
                        <div class="row">
                            <div class="col-lg-2"></div>
                            <div class="col-lg-8">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="input-group input-group-outline" style="margin-bottom: 10px">
                                            <label class="input-group" for="input-nameproduct"> Name Product</label>
                                            <form:input path="name" id="writeName" required="a" class="form-control rounded" maxlength="100" minlength="10"/>
                                            
                                        </div>
                                            <div id="result2" style="color: green; margin-bottom: 25px"></div>
                                            <div class="" id="result" style="color: red; margin-bottom: 25px"></div>
                                    </div>
                                    <div class="col-lg-6" style="margin-bottom: 10px">
                                        <div class="input-group input-group-outline">
                                            <label class="input-group" for="inputState">Price</label>
                                            <form:input type="number" class="form-control" style="border-top-left-radius: 5px; border-bottom-left-radius: 5px" path="price" required="a" maxlength="19" />
                                            <div class="input-group-append" style="background-color: gainsboro; border-top-right-radius: 5px; border-bottom-right-radius: 5px">
                                                <span class="input-group-text">$</span>
                                              </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6" style="margin-bottom: 10px">
                                        <div class="input-group input-group-outline">
                                            <label class="input-group" for="inputState">Release Date</label>
                                            <form:input path="releaseDate" class="form-control rounded  " type="date" required="a" value="" defaultValue="${date}" />
                                        </div>
                                    </div>
                                    <div class="col-lg-6" style="margin-bottom: 10px">
                                        <div class="input-group input-group-outline">
                                            <label class="input-group" for="input-nameproduct">Type</label>
                                            <form:select path="genre" required="" class="form-control rounded  form-select">
                                                <c:forEach items="${listGenre}" var="g">
                                                    <form:option value="${g.genre}">${g.genre}</form:option>
                                                </c:forEach>
                                            </form:select>
                                        </div>
                                    </div>
                               
                                   
                                    <div class="col-lg-12">
                                        <div class=" input-group input-group-outline mb-3">
                                            <label for="exampleFormControlTextarea1" class="input-group">Description (Optional)</label>
                                            <form:textarea class="form-control rounded" rows="5" placeholder="Description" path="detail" required="a" maxlength="150"/>
                                        </div>
                                    </div>
                            <div class="col-lg-8">
                                    <div class="form-group">
                                        <div class="">
                                            <p class="font-weight-bold text-success">"Ctrl + Left Click on Img" for multiple upload</p>
                                            <input type="button" class="btn btn-primary" value="Upload Image" onclick="document.getElementById('fileControl').click();" />
                                            <input type="button" class="btn btn-danger" value="Cancel Imgae" id="canCelImg"/>
                                            <input type="file" name="fileImage" id="fileControl" style="display:none;" accept="image/png, image/jpeg" multiple="multiple"/>
                                            <div id="myImg">
                                            </div>
                                        </div>
                                    </div>
                            </div>
                            <div class="col-lg-1"></div>


                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            
            <hr class="horizontal dark my-4">
            <div class="row">
                <div class="col-lg-2"></div>
                <div class="col-lg-3" id="button-submit">
                    
                </div>
            </div>
            </form:form>
        </div>
    </div>
    
    <script type="text/javascript">             
                //=====================Check IMG
                const input = document.querySelector('#fileControl');
                // Listen for files selection
                input.addEventListener('change', (e) => {
                    // Retrieve all files
                    const files = input.files;

                    // Check files count
                    if (files.length > 4) {
                        alert(`Only 4 files are allowed to upload.`);
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
                //========================Cancel Img
                $("#canCelImg").click(function(){
                    $("#fileControl").val("");
                    rmvImg();
                });
                function imageIsLoaded(e) {
                  $('#myImg').append('<img height="200px" width="200px" style="margin-top:15px; margin-right:20px; box-shadow: 0 4px 9px 4px rgba(0,0,0,0.20); border-radius: 15px" src=' + e.target.result + '>');
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
            <script>
                //Check Unique name
                $(document).ready(function(){
                                $('#writeName').change(function(){
                                   var name = $('#writeName').val();
                                   if(name){
                                        $.ajax({
                                            type:'POST',
                                            data:{writeName:name},
                                            url:'pnameCheck',
                                            success:function(result){
                                                if(result == 1){
                                                    $('#result2').html("");
                                                    $('#result').html("This Product Name Already Existed!!!");
                                                    deniButton();
                                                }else{
                                                    $('#result').html("");
                                                    $('#result2').html("Available Product Name!");
                                                    allowButton();
                                                }
                                            }
                                        });
                                    }else{
                                        deniButton();
                                    }
                                });  
                            });
                
                //Button submit deni or allow
                deniButton(); 
                function deniButton(){
                //            var cartArray = listCart();
                   var output="";
                //            for(var i in cartArray){
                    output += "<button type = 'button' id='deniButton' class='btn btn-success' style='margin-right: 5px'> Save </button>"
                    +"<a href='${pageContext.request.contextPath }/adm/product/1' class='btn btn-secondary'>"
                        +"Back"
                    +"</a>";
                //            }
                    $("#button-submit").html(output);
                };
                function allowButton(){
                //            var cartArray = listCart();
                    var output="";
                //            for(var i in cartArray){
                    output += "<button type = 'submit' class='btn btn-success' style='margin-right: 5px'> Save </button>"
                    +"<a href='${pageContext.request.contextPath }/adm/product/1' class='btn btn-secondary'>"
                        +"Back"
                    +"</a>";
                //            }
                    $("#button-submit").html(output);
                };
                $("#button-submit").on("click", "#deniButton",function(event){
                    alert(`There are error or empty at the Blank!!!`);
                });
            </script>

</div>


<%@include file="includes/footer.jsp" %>