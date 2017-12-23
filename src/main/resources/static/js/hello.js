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
        }, function(){
                $('#item-status').text("item added!");
        }, function(){
            $('#item-status').text("item not added!");
        }); 
    });
});