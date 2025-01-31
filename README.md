# Shape Metrics Calculator

This project is a Spring Boot application that calculates the area and perimeter of various geometric shapes. 
It provides an API endpoint to calculate the metrics for different shapes like circles and triangles.

### Prerequisites

- Java 21 or higher
- Maven 3.6.0 or higher

### Installation

1. **Clone the repository:**
    ```
   git clone https://github.com/your-username/shape-metrics-calculator.git
   cd shape-metrics ```
2. **Build the project using Maven:**
    ```mvn clean install```
3. **Running the Application**
    ```mvn spring-boot:run```


### API Endpoints
Calculate Shape Metrics

Supported types: CIRCLE, SQUARE, TRIANGLE, RECTANGLE

URL: /shape/metrics/calculate

Method: POST

Request Body: 
````
{
    "type": "CIRCLE", // one of [CIRCLE, SQUARE, TRIANGLE, RECTANGLE]
    "context": {
        "radius": 5.0 // for circles
        // or
        "side": 6.5 // for square
        // or 
        "length": 4.0,
        "width": 6.0 // for rectangle
        // or
        "sideA": 3.0,
        "sideB": 4.0,
        "sideC": 5.0 // for triangles
    }
}
````

Response Body:
````
{
  "perimeter": 31.4,
  "area": 78.5
}
````
