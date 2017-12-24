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
    
    var addItemRow = function(rowId, rowName, rowPrice){
        var row = "<tr><td>"+rowId+"</td><td>"+rowName+"</td><td>"+rowPrice+"</td></tr>";
        
        $('#items-table tbody').append(row);
    };
    
    var addItems = function(items){
        
        $('#items-table tbody').find("tr:gt(0)").remove();
        
        items.forEach(function(item){
            var rowId = item["id"];
            var rowName = item["name"];
            var rowPrice = item["price"];
            
            addItemRow(rowId, rowName, rowPrice);
        });
    };
    
    
    $.get("http://localhost:8888/item", addItems);
    
    $('#send-item').click(function(){
        var name = $('#name').val();
        var price = $('#price').val();
        
        $.postJSON("http://localhost:8888/item", 
        {
            name: name,
            price: price
        }, function(data){
            $('#item-status').text("item added! id: "+data.id);     
            
            $.get("http://localhost:8888/item", addItems);
            
        }, function(){
            $('#item-status').text("item not added!");
        }); 
    });
    
    $("#update-item").click(function(){
        var itemId = $('#update-item-id').val();
        var updatedPrice = $('#updated-item-price').val();
        
        
        var url = "http://localhost:8888/item/update/"+itemId;
        $.put(url, updatedPrice, function(data){
            $('#update-item-status').text("item price updated!");
            
            $.get("http://localhost:8888/item", addItems);
        }, function(){
            $('#update-item-status').text("item price not updated!"); 
        })
    });
    
    
    var addItemOrderRow = function(rowId, rowName, rowPrice, rowQuantity, rowCurrentPrice){
        var row = "<tr><td>"+rowId+"</td><td>"+rowName+"</td><td>"+rowPrice+"</td><td>"+rowQuantity+"</td><td>"+rowCurrentPrice+"</td></tr>";
        
        $('#order-items-table tbody').append(row);
    };
    
    var addOrderItems = function(lineItems){
        
        $('#order-items-table tbody').find("tr:gt(0)").remove();
        
        var summary = 0;
        lineItems.forEach(function(lineItem){
            var rowId = lineItem.id
            var rowName = lineItem.item.name;
            var rowPrice = lineItem.item.price;
            var rowQuantity = lineItem.quantity;
            var rowCurrentPrice = lineItem.currentPrice;
            
            summary += Number(rowCurrentPrice);
            addItemOrderRow(rowId, rowName, rowPrice, rowQuantity, rowCurrentPrice );
        });



        $("#summary").text(summary);
    };
    
    
    // getOrderResult[0]["lineItems"][0]["item"]["name"]
    $('#create-order').click(function(){
        
        $.postJSON("http://localhost:8888/order", 
        {}, function(data){
            
            $('#order-status').text("order created!");
            $('#actual-order-id').val(data.id);
            $('#create-order').hide();
            $('#order-items-table').show();
            
        }, function(){
            $('#order-status').text("order not created!");
        }); 
    });
    
    $("#add-item-order").click(function(){
        var itemId = $('#item-id').val();
        var orderId = $('#actual-order-id').val();
        var quantity = $('#quantity').val();
        
        var url = "http://localhost:8888/order/"+orderId
        + "/addItem/"+itemId+"/quantity/"+quantity;
        $.put(url, {}, function(data){
            //todo: implement on server side some reasonable response
            $('#item-order-status').text("item added to the order!");
            
            $.get("http://localhost:8888/order", function(orders){
            var specificOrder = $.grep(orders, function(e){ return e.id == orderId; })[0];
            
            var orderLineitems = specificOrder.lineItems;
            
            addOrderItems(orderLineitems);
        });
        
        
    }, function(){
        $('#item-order-status').text("item not added to the order!"); 
    })
});

$("#pay").click(function(){
    var orderId = $('#actual-order-id').val();
    var payment = $('#payment').val();
    
    
    var url = "http://localhost:8888/order/"+orderId+"/pay";
    $.put(url, payment, function(data){
        $('#payment-status').text("payment succeeds!");
    }, function(data){
        // $('#payment-status').text("payment fails!"); 
        var errorMessage =  $.parseJSON(data.responseText);
        console.log(errorMessage.message);
        $('#payment-status').text(errorMessage.message); 
    })
});


});