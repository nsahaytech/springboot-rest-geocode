# springboot-rest-geocode


### Application Details:


1.  A Retail Manager (using a RESTful client e.g. Chrome's Postman), can post a JSON payload to a shops endpoint containing details of the shop they want to add. Application internally utilizes Google Maps Geocoding API to retrieve the longitude and latitude for the provided shopAddress. Shop address entered by retail manager should contain

*   shopName
*   shopAddress.number
*   shopAddress.postCode 

2.  A Customer (using a RESTful client e.g. Chrome's Postman), can get a JSON payload from the shops endpoint containing details of the shop nearest to them. The server side API requires the latitude and longitude values to provide list of near by shop details. Shop detail consists of


*   shopName
*   shopAddress.number
*   shopAddress.postCode
*   shopLongitude
*   shopLatitude


### Application Setup:


1.  Checkout project from git repository. 
		https://github.com/nsahaytech/springboot-rest-geocode.git
2.  Build the project. 
		./gradlew clean build
3.  Run application as SpringBoot
		java -jar <application jar>
4.  For API details invoke swagger ui url of the running application
		http://localhost:8080/swagger-ui.html
5.  Sample request data for the API are available in /src/test/resources/sample_request/request.json
6.  JUnit test report is available in 
7.  JUnit code coverage is available in /src/test/resources/coverage
8.  In order to rerun code coverage for JUnit tests, following command need to be executed
		./gradlew clean build jacocoTestReport



### Integration:

Google GeoCoding Map Service (https://developers.google.com/maps/documentation/geocoding/intro) for retrieving location info based on address/latitude and longitude
*   https://maps.googleapis.com/maps/api/geocode/json?sensor=false&address=
*   https://maps.googleapis.com/maps/api/geocode/json?sensor=false&latlng=
