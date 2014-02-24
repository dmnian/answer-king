(1) Read the Code

(2) Compile the Code
mvn clean package

(3) Run the Main Method
java -jar target/answer-king-0.0.1-SNAPSHOT.war
or
mvn exec:java -Dexec.mainClass="answer.king.config.AppConfig"

(4) Create Burger
Using a REST client
URL: http://localhost:8888/item/
Method: POST
Headers: Content-Type: application/json
Content: {"name":"burger", "price":"1.99"}

i.e. --> curl http://localhost:8888/item/ -H "Content-Type: application/json" -d '{"name":"burger", "price":"1.99"}'

(5) Create Chips
Using a REST client
URL: http://localhost:8888/item/
Method: POST
Headers: Content-Type: application/json
Content: {"name":"chips", "price":"0.99"}

i.e. --> curl http://localhost:8888/item/ -H "Content-Type: application/json" -d '{"name":"chips", "price":"0.99"}'

(6) List Items
Using a REST client
URL: http://localhost:8888/item/
Method: GET
Headers: Content-Type: application/json
Content: <None>

i.e. --> curl http://localhost:8888/item/ -H "Content-Type: application/json"

(7) Create Order
Using a REST client
URL: http://localhost:8888/order/
Method: POST
Headers: Content-Type: application/json
Content: <None>

i.e. --> curl -XPOST http://localhost:8888/order/ -H "Content-Type: application/json"

(8) Add Burger to Order
Using a REST client
URL: http://localhost:8888/order/1/addItem/1
Method: PUT
Headers: Content-Type: application/json
Content: <None>

i.e. --> curl -XPUT http://localhost:8888/order/1/addItem/1 -H "Content-Type: application/json"

(9) Add Chips to Order
Using a REST client
URL: http://localhost:8888/order/1/addItem/2
Method: PUT
Headers: Content-Type: application/json
Content: <None>

i.e. --> curl -XPUT http://localhost:8888/order/1/addItem/2 -H "Content-Type: application/json"

(10) Pay for Order
Using a REST client
URL: http://localhost:8888/order/1/pay
Method: PUT
Headers: Content-Type: application/json
Content: "50.00"

i.e. --> curl -XPUT http://localhost:8888/order/1/pay -H "Content-Type: application/json" -d '"50.00"'

(11) Write Tests for the ItemController and ItemService for the saving and creating of items for the case above for creating a burger item

(12) It is possible that somebody could try and create an Item with invalid data (price and name), amend the code base so that this cannot happen, supporting unit tests will need to be written as part of the task

(13) Write Tests for the OrderController and OrderService for the payment of an order

(14) When an Order is paid the boolean paid flag in the Order model class is not updated, amend the code base so that this flag gets updated correctly, supporting unit tests will need to be written as part of the task

(15) It is possible that somebody could try and pay for an Order with insufficient funds, amend the code base so that this cannot happen, supporting unit tests will need to be written as part of the task

(16) Amend the codebase so that Receipts are persisted as part of a successful payment, that can be later retrieved, supporting unit tests will need to be written as part of the task

(17) Amend the codebase so that it is possible to change the price of an item, supporting unit tests will need to be written as part of the task

(18) When calculating the change for a historic receipt, after an item has had a price change, the receipt will show an incorrect amount for the change, introduce a new model type called LineItem. This LineItem will replace Item within an Order, so when an item is added to an order from the OrderController and passed to the OrderService, a LineItem will be created, this should have a reference to the Item, its Current Price and a quantity (which will be of 1). Amend the code base to reflect this, unit tests will need to be written as part of the task

(19) As a LineItem can now have a quantity, amend the codebase so that a quantity value can be given on the OrderController when adding an Item, also make sure that when the Order is persisted that there can not be duplicate LineItems for the same Item, so if a duplicate Item is added to an order, the quantity for the already existing lineitem is increased. supporting unit tests will need to be written as part of the task

(20) Write a clientside webpage using jQuery and Twitter bootstrap demonstrating the usage of the AnswerKing REST interface. KEEP IT SIMPLE!






