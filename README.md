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

**Note:** Shop name uniquely identifies a shop

### Application Setup:


1.  Checkout project from git repository. 
		https://github.com/nsahaytech/springboot-rest-geocode.git
2.  Build the project which also runs the junit tests and executes pmd, findbug over the source folder excuding test files
		./gradlew clean build
3.  Run application as SpringBoot
		java -jar build/libs/springboot-rest-geocode-0.1.0.jar
4.  For API details invoke swagger ui url of the running application
		http://localhost:8080/swagger-ui.html
5.  In order to rerun code coverage for JUnit tests, following command need to be executed
		./gradlew clean build jacocoTestReport
6.  JUnit code coverage report is generated in 
		/build/reports/coverage
7.  JUnit Test reports are generated in 
		/build/reports/tests/test
8.  PMD report is generated in 
		/build/reports/pmd
9.  FindBugs report is generated in
		/build/reports/findbugs

### API Details (Sample Requests)

1.  Save Shop Details
    
    **Curl Request**
	curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{
	  "address": {
	    "shopAddress.number": 123,
	    "shopAddress.postCode": 560100
	  },
	  "shopName": "test2"
	}' 'http://localhost:8080/shop'

    **Request URL** 
    	http://localhost:8080/shop

    **Request Headers**
	{
	  "Accept": "application/json"
	}

    **Response Body**
	
	For Create: No Content

	For Update:	
	{
	  "shopLongtitude": "77.6544856",
	  "shopLatitude": "12.8498481",
	  "address": {
	    "shopAddress.number": 123,
	    "shopAddress.postCode": 560100
	  },
	  "shopName": "test2"
	}

    **Response Code**

	For Create: 201

	For Update: 200

2.  Find Nearby Shops

    **Curl Request**
	curl -X GET --header 'Accept: application/json' 'http://localhost:8080/shop?customerLatitude=12.8498481&customerLongitude=77.6544856'

    **Request URL** 
    	http://localhost:8080/shop?customerLatitude=12.8498481&customerLongitude=77.6544856

    **Request Headers**
	{
	  "Accept": "application/json"
	}

    **Response Body**
	
	[
	  {
	    "shopLongtitude": "77.6544856",
	    "shopLatitude": "12.8498481",
	    "address": {
	      "shopAddress.number": 123,
	      "shopAddress.postCode": 560100
	    },
	    "shopName": "test2"
	  },
	  {
	    "shopLongtitude": "77.6544856",
	    "shopLatitude": "12.8498481",
	    "address": {
	      "shopAddress.number": 123,
	      "shopAddress.postCode": 560100
	    },
	    "shopName": "test"
	  }
	]

    **Response Code**

	200

        

### Integration:

Google GeoCoding Map Service (https://developers.google.com/maps/documentation/geocoding/intro) for retrieving location info based on address/latitude and longitude
*   https://maps.googleapis.com/maps/api/geocode/json?sensor=false&address=
*   https://maps.googleapis.com/maps/api/geocode/json?sensor=false&latlng=

### Checklist:

*   API to save shop details. This API should accept shopName, shopNumber and Shop Postal Code in JSON format. Internally google geo code api 	 is utilized to fetch the latitude and longitude for the postal code provided and saves the Shop details with latitude and longitude details in InMemory repository
*   API to retrieve nearby shops. This API accepts latitude and longitude as request params and return list of near by shops for the provided inputs
*   Project should be available in github with required ReadMe file providing instructions on how to build and run the app.
*   Adequate unit test cases for important classes should be available.
*   Unit test cases should run during the build process
*   Unit tests should have predefined and agreed code coverage
*   Build process should also run static code analysis tool including PMD, FindBugs
*   Java and Spring best practises to be followed 
