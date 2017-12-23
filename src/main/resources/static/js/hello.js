$.postJSON = function(url, data, callback, error) {
    return jQuery.ajax({
        'type': 'POST',
        'url': url,
        'contentType': 'application/json',
        'data': JSON.stringify(data),
        'dataType': 'json',
        'success': callback,
        'error': error
    });
};

$.put = function(url, data, callback, error){
    return $.ajax({
        url: url,
        type: 'PUT',
        success: callback,
        error: error,
        data: JSON.stringify(data),
        contentType: 'application/json'
    });
};

$(document).ready(function(){
    $.ajax({
        url: "http://rest-service.guides.spring.io/greeting"
    }).then(function(data) {
        $('.greeting-id').append(data.id);
        $('.greeting-content').append(data.content);
    });
    
    
    $('#send-item').click(function(){
        var name = $('#name').val();
        var price = $('#price').val();
        
        $.postJSON("http://localhost:8888/item", 
        {
            name: name,
            price: price
        }, function(data){
            $('#item-status').text("item added! id: "+data.id);
        }, function(){
            $('#item-status').text("item not added!");
        }); 
    });
    
    $('#create-order').click(function(){
        
        $.postJSON("http://localhost:8888/order", 
        {}, function(data){
            
            $('#order-status').text("id: "+data.id);
        }, function(){
            $('#order-status').text("order not created!");
        }); 
    });
    
    $("#add-item-order").click(function(){
        var itemId = $('#item-id').val();
        var orderId = $('#order-id').val();
        var quantity = $('#quantity').val();
        
        var url = "http://localhost:8888/order/"+orderId
        + "/addItem/"+itemId+"/quantity/"+quantity;
        $.put(url, {}, function(data){
            //todo: implement on server side some reasonable response
            $('#item-order-status').text("item added to the order!");
        }, function(){
            $('#item-order-status').text("item not added to the order!"); 
        })
    });
    
    $("#update-item").click(function(){
        var itemId = $('#update-item-id').val();
        var updatedPrice = $('#updated-item-price').val();
        
        
        var url = "http://localhost:8888/item/update/"+itemId;
        $.put(url, updatedPrice, function(data){
            $('#update-item-status').text("item price updated!");
        }, function(){
            $('#update-item-status').text("item price not updated!"); 
        })
    });

    $("#pay").click(function(){
        var orderId = $('#pay-order-id').val();
        var payment = $('#payment').val();
        
        
        var url = "http://localhost:8888/order/"+orderId+"/pay";
        $.put(url, payment, function(data){
            $('#payment-status').text("payment succeeds!");
        }, function(){
            $('#payment-status').text("payment fails!"); 
        })
    });
    
    
    
    
});