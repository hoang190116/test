<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title></title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
 <!-- Font Awesome -->
<link
  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
  rel="stylesheet"
/>
<!-- Google Fonts -->
<link
  href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
  rel="stylesheet"
/>
<!-- MDB -->
<link
  href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.11.0/mdb.min.css"
  rel="stylesheet"
/>
<script
  type="text/javascript"
  src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.11.0/mdb.min.js"
></script>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
<section class="vh-100" style="background-color: #eee;">
  <div class="container h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-lg-12 col-xl-11">
        <div class="card text-black" style="border-radius: 25px;">
          <div class="card-body p-md-5">
            <div class="row justify-content-center">
              <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Register</p>
                <form:form class="mx-1 mx-md-4" action="${pageContext.request.contextPath }/register" method="post" modelAttribute="account">
                    
                    <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <form:input path="fname" type="text" id="form3Example2c" class="form-control" required="a" minlength="8" maxlength="40"/>
                      <label class="form-label" for="form3Example2c">Full name</label>
                    </div>
                  </div>
                    
                    <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <form:input path="uname" id="writeName" type="text" class="form-control" required="a" minlength="8" maxlength="40"/>
                      <label class="form-label" for="form3Example1c">User name</label>
                    </div>
                  </div>
                      
                  <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <form:input path="pass" type="password" id="writePass" class="form-control" required="a" minlength="8"/>
                      <label class="form-label" for="form3Example4c">Password</label>
                    </div>
                  </div>
<!--                  <div class="form-check d-flex justify-content mb-5">
                    <input
                      class="form-check-input me-2"
                      type="checkbox"
                      name="remember"
                      value=""
                      id="form2Example3c"
                    />
                    <label class="form-check-label" for="form2Example3">
                      Remember me &nbsp; &nbsp;<a href="#!">Forgot password?</a>
                    </label>
                  </div>-->

                  <span id="result2" style="color: green;" class=""></span>
                  <span id="result" style="color: red" class=""></span>
                  <span id="result3" style="color: red" class=""></span>
                  <span id="result4" style="color: green;" class=""></span>
                  <div id="button-submit" class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                    <!--<button type = "submit" > Save </button>-->
                  </div>
                  <div class="form-check d-flex justify-content-center mb-5">
                    <label class="form-check-label" for="form2Example3">
                      Back To <a href="${pageContext.request.contextPath }/loginForm">Login Form</a><br>
                      <a href="${pageContext.request.contextPath }/index" style="padding-left: 0px">Go Back To Index</a>
                    </label>
                  </div>

                    <script>//About check Unique User Name
                            $(document).ready(function(){
                                var pass = false;
                                var username = false;
                                $('#writePass').change(function(){
                                   var name = $('#writePass').val();
                                   if(name){
                                        $.ajax({
                                            type:'POST',
                                            data:{writePass:name},
                                            url:'passwordCheck',
                                            success:function(result){
                                                console.log(result);
                                                if(result == 1){
                                                    $('#result4').html("");
                                                    $('#result3').html("<span class='d-flex flex-row align-items-center mb-4'>Password must be atleast 8 character, and should contain special character, upercase and lowercase chacter, and number </span>");
                                                    deniButton();
                                                    pass = false;
                                                }else{
                                                    $('#result3').html("");
                                                    $('#result4').html("<span class='d-flex flex-row align-items-center mb-4'>Available Password!</span>");
                                                    pass = true;
                                                        allowButton(pass, username);
                                                }
                                            }
                                        });
                                    }else{
                                        deniButton();
                                        pass = false;
                                    }
                                });
                                $('#writeName').change(function(){
                                   var name = $('#writeName').val();
                                   if(name){
                                        $.ajax({
                                            type:'POST',
                                            data:{writeName:name},
                                            url:'usernameCheck',
                                            success:function(result){
                                                console.log(result);
                                                if(result == 1){
                                                    $('#result2').html("");
                                                    $('#result').html("<span class='d-flex flex-row align-items-center mb-4'>This User Name Already Existed!!!</span>");
                                                    deniButton();
                                                    username = false;
                                                }else{
                                                    $('#result').html("");
                                                    $('#result2').html("<span class='d-flex flex-row align-items-center mb-4'>Available User Name!</span>");
                                                    username = true;
                                                        allowButton(pass, username);
                                                }
                                            }
                                        });
                                    }else{
                                        username = false;
                                        deniButton();
                                    }
                                }); 
                            });
                            
                        deniButton(); 
                        function deniButton(){
                //            var cartArray = listCart();
                            var output="";
                //            for(var i in cartArray){
                                output += "<button type = 'button' class='deniButton btn btn-primary btn-block btn-lg'  > Sign In </button>";
                //            }
                            $("#button-submit").html(output);
                        };
                        function allowButton(pass, username){
                //            var cartArray = listCart();
                        if(pass === true && username === true){
                            var output="";
                //            for(var i in cartArray){
                                output += "<button type = 'submit' class='btn btn-primary btn-block btn-lg'> Sign In </button>";
                //            }
                            $("#button-submit").html(output);
                        }else{
                            deniButton();
                        }
                        };
                        $("#button-submit").on("click", ".deniButton",function(event){
                            alert(`There are error or empty at the Blank!!!`);
                        });
                        </script>
                </form:form>
                        

              </div>
              <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">

                <img src="https://images.macrumors.com/t/tA_RAlO-sKIrvULzR2aaVJ5Tyd4=/1920x/article-new/2019/02/MR-Future-Products-2020-2.png" class="img-fluid" alt="Sample image">

              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
</section>
</body>
</html>