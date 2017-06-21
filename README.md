# ANSWER KING
Would you like fries with that?

### Note:
- In order to run the project, you must first have a version of Java's JDK8 installed on your machine.

### Tasks
(1) Read the Code

(2) Compile the Code
mvn clean package

(3) Run the Main Method
java -jar target/answer-king-0.3.0-SNAPSHOT.war
or
mvn exec:java -Dexec.mainClass="answer.king.Application"

(4) Browse to http://localhost:8888 and test out the api.

(5) Write Tests for the ItemController and ItemService for the saving and creating of items

(6) It is possible that somebody could try and create an Item with invalid data (price and name), amend the code base so that this cannot happen, supporting unit tests will need to be written as part of the task

(7) Write Tests for the OrderController and OrderService for the payment of an order

(8) When an Order is paid the boolean paid flag in the Order model class is not updated, amend the code base so that this flag gets updated correctly, supporting unit tests will need to be written as part of the task

(9) It is possible that somebody could try and pay for an Order with insufficient funds, amend the code base so that this cannot happen, supporting unit tests will need to be written as part of the task

(10) Amend the codebase so that Receipts are persisted as part of a successful payment, that can be later retrieved, supporting unit tests will need to be written as part of the task

(11) Amend the codebase so that it is possible to change the price of an item, supporting unit tests will need to be written as part of the task

(12) When calculating the change for a historic receipt, after an item has had a price change, the receipt will show an incorrect amount for the change, introduce a new model type called LineItem. This LineItem will replace Item within an Order, so when an item is added to an order from the OrderController and passed to the OrderService, a LineItem will be created, this should have a reference to the Item, its Current Price and a quantity (which will be of 1). Amend the code base to reflect this, unit tests will need to be written as part of the task

(13) As a LineItem can now have a quantity, amend the codebase so that a quantity value can be given on the OrderController when adding an Item, also make sure that when the Order is persisted that there can not be duplicate LineItems for the same Item, so if a duplicate Item is added to an order, the quantity for the already existing lineitem is increased. supporting unit tests will need to be written as part of the task

(14) OPTIONAL : Write a clientside webpage using jQuery and Twitter bootstrap demonstrating the usage of the AnswerKing REST interface. KEEP IT SIMPLE!

(15) Once complete please email an archive of your source code (no build artefacts) or send a link to your own git repository




