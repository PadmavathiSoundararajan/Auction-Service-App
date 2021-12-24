# Auction-Service-App

Projects:
Bidding-Service-Cqrs-Kafka :
•	demonstrates placing bids, updating bids scenarios.
•	CQRS pattern is implemented using AXON framework using MongoDB as Event store and Kafka as Event bus. Command-Service deals with command part and query-service deals with query part.
 product-discovery-service-elk :
•	demonstrates creating products, deleting products and getting product specific to a product Id
•	Also, ELk stack is implemented in this
AuctionServiceWeb:
	Front end UI is developed using Angular.
Build and Deploy:
Requirements:
•	Docker
Process:
•	Build each project separately using "mvn clean install" command
•	go to path where this exists - product-discovery-service-elk and do "docker-compose build" and then do docker-compose up
•	go to path where this exists - Bidding-Service-Cqrs-Kafka and do "docker-compose build" and then do docker-compose up
•	All the services would spin up and once its all started, use the below urls in postman client and call the REST API's
o	(POST Method) Add Product - "http://localhost:8095/e-auction/api/v1/seller/add-product" .Note the product Id and use it subsequent requests
o	(GET MEthod) Get Product By Product Id - "http://localhost:8095/e-auction/api/v1/seller/getProduct/{productId}
o	(DELETE Method) Delete Product - "http://localhost:8095/e-auction/api/v1/seller/delete/{productId}
o	(POST Method) Add Bid - "http://localhost:8099/e-auction/api/v1/buyer/add-Bid"
o	(GET Method) Get Bid By Product Id - "http://localhost:9096/e-auction/api/v1/buyer/getBid/{productId}"
o	(PUT MEthod) Update Bid AMount - "https://localhost:8099/e-auction/api/v1/buyer/update/bid-amount/{productId}/{buyerEmailld}/{newBidAmount}"
ADD Product Json Example : { "bidEndDate": "25-12-2021", "category": "ORNAMENT", "detailedDescription": "Painting is an art.", "productName": "Abstract Art", "seller": { "address": "XYZ", "city": "mumbai", "email": "xxxx@gmail.com", "firstName": "YYYYYY", "lastName": "ZZZZZZZZZ", "phone": "1234507775", "pin": "770786", "state": "Maharashtra" }, "shortDescription": "Paint", "startingPrice": 55 }
ADD Bid Json Example: { "firstName": "AAAA", "lastName": "BBBBB", "address": "Example Addrs", "city": "mumbai", "state": "Maharashtra", "email": "dddddd@gmail.com", "pin": "6000168", "phone": "1234508988", "productId" : "61c4aa0b03c8e54d64484627", "bidAmount" : "666" }

