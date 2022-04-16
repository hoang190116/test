<%@include file="includes/nav.jsp" %>
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
            <form:form action="${pageContext.request.contextPath }/adm/user/saveAccount" method="POST" modelAttribute="a">
                <form:hidden path="id"/>
                <h6 class="heading-small text-muted mb-4">User information</h6>
                <h5 class="btn-success">${success}</h5>
                <h5 class="btn-danger" id="errorNotify">${error}</h5>
                <div>
                    <div class="row justify-content-md-center">
                        <div class="row justify-content-md-center">
                        <div class="col-lg-6">
                            <div class="input-group input-group-outline">
                                <label class="input-group" for="input-nameproduct"> Full Name</label>
                                <form:input path="fname" required="a" class="form-control rounded" maxlength="150" minlength="10"/>
                            </div>
                        </div>
                        </div>
                        <div class="row justify-content-md-center">
                        <div class="col-lg-6">
                            <div class="input-group input-group-outline">
                                <label class="input-group" for="input-nameproduct">Username</label>
                                <form:input path="uname" id="writeName" required="a" class="form-control rounded" maxlength="100" minlength="8"/>
                            </div><span id="result2" style="color: green;" class=""></span>
                        <span id="result" style="color: red" class=""></span>
                        </div>
                        </div>
                        <div class="row justify-content-md-center">
                        <div class="col-lg-6">
                            <div class="input-group input-group-outline">
                                <label class="input-group" for="input-nameproduct"> Password</label>
                                <form:input type="password" path="pass" id="writePass" placeholder="****************" class="form-control rounded" maxlength="100"/>
                            </div><span id="result3" style="color: red" class=""></span>
                        <span id="result4" style="color: green;" class=""></span>
                        </div>
                        </div>
                        
                       
                        <div class="row justify-content-md-center">
                        <div class="col-lg-6">
                            <div class="input-group input-group-outline">
                                <label class="input-group" for="input-nameproduct">Email</label>
                                <form:input path="email" type="email" required="a" class="form-control rounded" maxlength="100" minlength="10"/>
                            </div>
                        </div>
                        </div>
                        <div class="row justify-content-md-center">
                        <div class="col-lg-6">
                            <div class="input-group input-group-outline">
                                <label class="input-group" for="input-nameproduct">Phone</label>
                                <form:input type="number" path="phone" required="a" class="form-control rounded" maxlength="20" minlength="10"/>
                            </div>
                        </div>
                        </div>
                        <div class="row justify-content-md-center">
                        <div class="col-lg-6">
                            <div class="input-group input-group-outline ">
                                <label for="exampleDatepicker1" class="input-group">Date of Birth </label>
                                <form:input path="date_of_bird" type="date" required="a" class="form-control rounded"/>
                            </div>
                        </div>
                        </div>
                        <div class="row justify-content-md-center">
                        <div class="col-lg-6">
                            <div class="input-group input-group-outline">
                                <label class="input-group" for="inputState">Role</label>
                                <form:select path="role" required="" class="form-control rounded  form-select">
                                    <form:option value="user">User</form:option>
                                    <form:option value="staff">Staff</form:option>
                                     <form:option value="admin">Admin</form:option>
                                </form:select>
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
    <script>
                            $(document).ready(function(){
                                var pass = true;
                                var username = true;
                                $('#writePass').change(function(){
                                   var name = $('#writePass').val();
                                   if(name){
                                        $.ajax({
                                            type:'POST',
                                            data:{writePass:name},
                                            url:'${pageContext.request.contextPath }/passwordCheck',
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
                                                    console.log(pass);
                                                    allowButton(pass, username);
                                                }
                                            }
                                        });
                                    }else{
                                        pass = true;
                                        console.log(pass);
                                        $('#result3').html("");
                                        $('#result4').html("");
                                        allowButton(pass, username);
                                    }
                                });
                                $('#writeName').change(function(){
                                   var name = $('#writeName').val();
                                   if(name){
                                       if(name === "${username}"){
                                           $('#result2').html("");
                                           $('#result').html("");
                                           username = true;
                                           allowButton(pass, username);
                                        }else{
                                            $.ajax({
                                            type:'POST',
                                            data:{writeName:name},
                                            url:'${pageContext.request.contextPath }/usernameCheck',
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
                                        }
                                    }else{
                                        username = false;
                                        deniButton();
                                    }
                                }); 
                            });
                            
                allowButton(true,true);
                function deniButton(){
                
                //            var cartArray = listCart();
                   var output="";
                //            for(var i in cartArray){
                    output += "<button type = 'button' id='deniButton' class='btn btn-success' style='margin-right: 5px'> Save </button>"
                    +"<a href='${pageContext.request.contextPath }/adm/user' class='btn btn-secondary'>"
                        +"Back"
                    +"</a>";
                //            }
                    $("#button-submit").html(output);
                };
                function allowButton(pass, username){
                //            var cartArray = listCart();
                if(pass === true && username === true){
                    var output="";
                //            for(var i in cartArray){
                    output += "<button type = 'submit' class='btn btn-success' style='margin-right: 5px'> Save </button>"
                    +"<a href='${pageContext.request.contextPath }/adm/user' class='btn btn-secondary'>"
                        +"Back"
                    +"</a>";
                //            }
                    $("#button-submit").html(output);
                }else{
                    deniButton();
                }
                };
                $("#button-submit").on("click", "#deniButton",function(event){
                    alert(`There are error or empty at the Blank!!!`);
                });
    </script>
</div>


<%@include file="includes/footer.jsp" %>