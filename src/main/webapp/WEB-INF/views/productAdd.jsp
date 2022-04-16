<%-- 
    Document   : productAdd
    Created on : Mar 23, 2022, 3:35:39 PM
    Author     : Admin
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .ab > textarea{height: 200px}
            .ab > select{width: 100px}
        </style>
        <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    </head>
    <body>
        <form:form action="uploadFile" method="POST" modelAttribute="p" enctype="multipart/form-data">
            <div>
                <form:hidden path="product_id"/>
                <div>
                    <div>
                        <label > Product Name </label>
                        <form:input path="name" id="writeName" required="a" maxlength="50"/>
                         <span id="result2" style="color: green"></span>
                         <span class="" id="result" style="color: red"></span>
                    </div>
                    <div>
                        <label > Product Price </label>
                         <form:input type="number" path="price" required="a" />$
                    </div>
                    <div>
                        <label>Release Date</label>
                        <form:input path="releaseDate" type="date" required="a" value="" defaultValue="${date}" />
                    </div>
                    <div class="ab">
                        <label>Detail</label>
                        <form:textarea placeholder="Description" path="detail" required="a" maxlength="150"/>
                    </div>
                    <div class="ab">
                        <label> Genre </label>
                        <form:select path="genre" required="">
                            <c:forEach items="${listGenre}" var="g">
                                <form:option value="${g.genre}">${g.genre}</form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                    <div>
                        <input type="file" name="fileImage" id="fileControl" accept="image/png, image/jpeg" multiple="multiple" />
                        <div id="myImg">
                        </div>
                        <script>//About check Unique Product Name
                            $(document).ready(function(){
                                $('#writeName').change(function(){
                                   var name = $('#writeName').val();
                                   if(name != null){
                                        $.ajax({
                                            type:'POST',
                                            data:{writeName:name},
                                            url:'pnameCheck',
                                            success:function(result){
                                                console.log(result);
                                                if(result == 1){
                                                    $('#result2').html("");
                                                    $('#result').html("This Name Already Existed!!!");
                                                    deniButton();
                                                }else{
                                                    $('#result').html("");
                                                    $('#result2').html("Available Name!");
                                                    allowButton()
                                                }
                                            }
                                        });
                                    }
                                });  
                            });
                        </script>
                        <!-- ##################################################################  -->
                        <script>//About IMG          
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
                            //Remove img
                            function rmvImg(){
                                $("#myImg > img").remove();
                            }
                            //--------------------------------Display Single IMG
            //                function preview_image(event) {
            //                  var reader = new FileReader();
            //                  reader.onload = function(){
            //                    var output = document.getElementById('thumbnaila');
            //                    output.src = reader.result;
            //                  }
            //                  reader.readAsDataURL(event.target.files[0]);
            //                }
                        </script>
                    </div>
                </div>
                <div id="button-submit">
                    <!--<button type = "submit" > Save </button>-->
                </div>
                    <script>
                        deniButton(); 
                        function deniButton(){
                //            var cartArray = listCart();
                            var output="";
                //            for(var i in cartArray){
                                output += "<button type = 'button' class='deniButton'  > Save </button>";
                //            }
                            $("#button-submit").html(output);
                        };
                        function allowButton(){
                //            var cartArray = listCart();
                            var output="";
                //            for(var i in cartArray){
                                output += "<button type = 'submit' > Save </button>";
                //            }
                            $("#button-submit").html(output);
                        };
                        $("#button-submit").on("click", ".deniButton",function(event){
                            alert(`There are error or empty at the Blank!!!`);
                        });
                    </script>
            </div>
        </form:form>
    </body>
</html>
