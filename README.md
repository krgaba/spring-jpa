# spring-jpa

Load Data
POST http://localhost:8080/stock-quote/load

Aggregate based on month
GET http://localhost:8080/summarized-data/byMonth/PVTL/2018-06

Aggregate based on day
http://localhost:8080/summarized-data/byDate/PVTL/2018-06-26


To load the data dynamically from the URI, set the dynamic.load property in the application.properties to true.

