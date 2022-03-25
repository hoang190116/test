<%@include file="includes/navbar.jsp" %>



<div class="container">
    <div class="row">
        <div class="col-md-2">
        </div>
        <div class="col-md-8">
            <div class="container pb-5 mb-2">
                <!-- Alert-->
                <div class="alert alert-muted alert-dismissible fade show text-left  mb-30">

                    <a href="index.php" type="button" class=" btn btn-success"><i class="fa fa-arrow-left" aria-hidden="true"></i> Back </a>
                </div>
                <!-- Cart Item-->
                <div class="cart-item d-md-flex justify-content-between"><span class="remove-item"><i class="fa fa-times"></i></span>
                    <div class="px-3 my-3">
                        <a class="cart-item-product" href="#">
                            <div class="cart-item-product-thumb"><img src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="Product"></div>
                            <div class="cart-item-product-info">
                                <h4 class="cart-item-product-title">Canon EOS M50 Mirrorless Camera</h4><span><strong>Type:</strong> Mirrorless</span><span><strong>Color:</strong> Black</span>
                            </div>
                        </a>
                    </div>
                    <div class="px-3 my-3 text-center">
                        <div class="cart-item-label">Quantity</div>
                        <div class="count-input">
                            <select class="form-control">
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                                <option>6</option>
                            </select>
                        </div>
                    </div>
                    <div class="px-3 my-3 text-center">
                        <div class="cart-item-label">Subtotal</div><span class="text-xl font-weight-medium">$910.00</span>
                    </div>
                    <div class="px-3 my-3 text-center">
                        <div class="cart-item-label">Discount</div><span class="text-xl font-weight-medium">$35.00</span>
                    </div>
                </div>
                <!-- Cart Item-->
                <div class="cart-item d-md-flex justify-content-between"><span class="remove-item"><i class="fa fa-times"></i></span>
                    <div class="px-3 my-3">
                        <a class="cart-item-product" href="#">
                            <div class="cart-item-product-thumb"><img src="https://bootdey.com/img/Content/avatar/avatar2.png" alt="Product"></div>
                            <div class="cart-item-product-info">
                                <h4 class="cart-item-product-title">Apple iPhone X 256 GB Space Gray</h4><span><strong>Memory:</strong> 256GB</span><span><strong>Color:</strong> Space Gray</span>
                            </div>
                        </a>
                    </div>
                    <div class="px-3 my-3 text-center">
                        <div class="cart-item-label">Quantity</div>
                        <div class="count-input">
                            <select class="form-control">
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                                <option>6</option>
                            </select>
                        </div>
                    </div>
                    <div class="px-3 my-3 text-center">
                        <div class="cart-item-label">Subtotal</div><span class="text-xl font-weight-medium">$1,450.00</span>
                    </div>
                    <div class="px-3 my-3 text-center">
                        <div class="cart-item-label">Discount</div><span class="text-xl font-weight-medium">?</span>
                    </div>
                </div>
                <!-- Cart Item-->
                <div class="cart-item d-md-flex justify-content-between"><span class="remove-item"><i class="fa fa-times"></i></span>
                    <div class="px-3 my-3">
                        <a class="cart-item-product" href="#">
                            <div class="cart-item-product-thumb"><img src="https://bootdey.com/img/Content/avatar/avatar3.png" alt="Product"></div>
                            <div class="cart-item-product-info">
                                <h4 class="cart-item-product-title">HP LaserJet Pro Laser Printer</h4><span><strong>Type:</strong> Laser</span><span><strong>Color:</strong> White</span>
                            </div>
                        </a>
                    </div>
                    <div class="px-3 my-3 text-center">
                        <div class="cart-item-label">Quantity</div>
                        <div class="count-input">
                            <select class="form-control">
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                                <option>6</option>
                            </select>
                        </div>
                    </div>
                    <div class="px-3 my-3 text-center">
                        <div class="cart-item-label">Subtotal</div><span class="text-xl font-weight-medium">$188.50</span>
                    </div>
                    <div class="px-3 my-3 text-center">
                        <div class="cart-item-label">Discount</div><span class="text-xl font-weight-medium">?</span>
                    </div>
                </div>
                <!-- Coupon + Subtotal-->
                <div class="d-sm-flex justify-content-between align-items-center text-center text-sm-left">
                    <form class="form-inline py-2">
                        <label class="sr-only">Coupon code</label>
                        <input class="form-control form-control-sm my-2 mr-3" type="text" placeholder="Coupon code" required="">
                        <button class="btn btn-style-1 btn-secondary btn-sm my-2 mx-auto mx-sm-0" type="submit">Apply Coupon</button>
                    </form>
                    <div class="py-2"><span class="d-inline-block align-middle text-sm text-muted font-weight-medium text-uppercase mr-2">Subtotal:</span><span class="d-inline-block align-middle text-xl font-weight-medium">$188.50</span></div>
                </div>
                <!-- Buttons-->
                <hr class="my-2">
                <div class="row pt-3 pb-5 mb-2">
                    <div class="col-sm-6 mb-3"><a class="btn btn-style-1 btn-secondary btn-block" href="#"><i class="fe-icon-refresh-ccw"></i>&nbsp;Update Cart</a></div>
                    <div class="col-sm-6 mb-3"><a class="btn btn-style-1 btn-primary btn-block" href="checkout-address.html"><i class="fe-icon-credit-card"></i>&nbsp;BUY</a></div>
                </div>


            </div>
        </div>

    </div>
    <div class="col-md-2">

    </div>
</div>
</div>


<%@include file="includes/footer.jsp" %>