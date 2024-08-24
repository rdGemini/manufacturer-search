# Manufacturer Search API

## Overview
The Manufacturer Search API allows buyers to search for manufacturers based on their location, nature of business, and manufacturing processes. This API provides a paginated response of manufacturers matching the specified criteria.

## Features
- Search manufacturers by location, nature of business, and manufacturing process
- Pagination support
- Returns only relevant fields in the response
- Input validation and exception handling
- Security best practices (e.g., HTTPS, rate limiting)

## Prerequisites
- Java 17 or later
- Maven

## Setup and Installation

1. **Clone the Repository**

   ```bash
   git clone https://github.com/your-repository/manufacturer-search.git
   cd manufacturer-search

2. **Build the Project**

    Use Maven to build the project:
    ```bash
    mvn clean install
   
3. **Run the Application**
   
    You can run the application using Maven:
    ```bash
    mvn spring-boot:run
  
  The application will start on port 8080 by default.
  
## API Endpoints

1. **Search Manufacturers**
   
- Endpoint: /api/supplier/query
- Method: POST
- Description: Retrieve a list of manufacturers based on location, nature of business, and manufacturing process.
- Request Parameters:
  - location (String): The location to search in (e.g., "India").
  - natureOfBusiness (String): The nature of business (e.g., "SMALL_SCALE").
  - manufacturingProcess (String): The manufacturing process (e.g., "PRINTING_3D").
  - page (Integer, optional): The page number (default is 0).
  - size (Integer, optional): The page size (default is 10).
- Request Example:
  ```bash
  POST /api/supplier/query?location=India&natureOfBusiness=SMALL_SCALE&manufacturingProcess=PRINTING_3D&page=0&size=10
- Response Example:
  ```bash
  [
    {
        "supplierId": 1,
        "companyName": "ABC Manufacturing",
        "website": "http://abcmanufacturing.com",
        "location": "India",
        "natureOfBusiness": "SMALL_SCALE",
        "manufacturingProcesses": [
            "PRINTING_3D",
            "MOULDING"
        ]
    },
    {
        "supplierId": 2,
        "companyName": "XYZ Industries",
        "website": "http://xyzindustries.com",
        "location": "India",
        "natureOfBusiness": "SMALL_SCALE",
        "manufacturingProcesses": [
            "CASTING"
        ]
    }
  ]

## Testing

Unit tests are provided to ensure the correct behavior of the endpoint. To run the tests, you can use 'mvn test' command.
    

## Security and Best Practices
- Ensure HTTPS is enabled in production environments.
- Implement rate limiting to protect against DDoS attacks.
- Validate and sanitize all inputs to prevent SQL injection and XSS attacks.
- Use authentication mechanisms (e.g., JWT) for securing endpoints.
- Log sensitive operations for auditability.

## Documentation
The API is documented using Swagger. To view the documentation, you need to include Swagger dependencies and access it at /swagger-ui.html.

## Contributing
Feel free to contribute to the project by opening issues or submitting pull requests.

For any questions or support, please contact support@makersharks.com.
