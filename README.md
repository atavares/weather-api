# Project Spring Boot Weather REST API

This is a projetc Java (1.8)/ Maven / Spring Boot (version 2.5.2) application that can be used for get temperature to city sent

## How to Run 

This application is packaged as a jar which has Tomcat embedded. No Tomcat installation is necessary. You run it using the ```java -jar``` command.

* Clone this repository 
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by of method:
```
	java -jar target/weather-api-1.0.jar
```

Once the application runs you should see something like this

```
2021-07-14 14:23:27.135  INFO 19387 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8090 (http)
2021-07-14 14:23:27.320  INFO 19387 --- [           main] com.khoubyari.example.Application        : Started WeatherApiApplication in 4.948 seconds (JVM running for 5.5)
```

## About the Service

The service is a query temperature to City sent in Metaweather Service. 
 
Here is what this application demonstrates: 

### Retrieve a temperature the city of Rio de Janeiro

```
http://localhost:8080/temperature/Rio de Janeiro

Response: HTTP 200
Content: {
			"celsius": xx.xx,
			"farenheit": xx.xx
		 }
```

### To view Swagger 2 API docs

Run the server and browse to http://localhost:8080/swagger-ui.html

# About the definition the request of  service

The definition of request is based in parameter city defined with annotation @PathVariable.
With that parameter consumer the URI https://www.metaweather.com/api/location?query= gives it back woeid and finally consumer https://www.metaweather.com/api/location/codeWoeid gives it back the temperature.

# About the definition the response of  service

The definition of response uses the object TemperatureDTO with attributes: celsius, farenheit and expose only the necessary to client the API.

# About the test end to end

The project have automated test, you run it using the command: mvn verify

# Questions and Comments: allan.tavares@msn.com


