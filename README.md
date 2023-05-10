# Spring Basic Login and Registration Server + Thymeleaf + Bootstrap


## Brief Project Description

This project is a full-stack application that includes a login and registration API developed with Spring Boot, as well as a frontend implemented with Thymeleaf and Bootstrap. The API utilizes JWT (JSON Web Tokens) for authentication and a local MySQL database for persisting user data. The project is written in Java 17.

## Background | Problem to Solve

Login and registration of users are fundamental functionalities in any backend system. Therefore, it is important to be able to configure these functions with any database and have a system that distinguishes between registered users and users who have not yet signed up.

## Results and Analysis
<video controls>
  <source src="media/front.mp4" type="video/mp4">
  Tu navegador no soporta la etiqueta de video.
</video>

The project successfully implements the login and registration endpoints using local authentication strategies. Users' data is securely stored in a local MySQL database. The API provides a middleware that protects routes and allows access only to verified users. The frontend, built with Thymeleaf and Bootstrap, provides a user-friendly interface for interacting with the API.

## Adopted Solution

The chosen solution includes Spring Boot as the framework for building the API, Thymeleaf for server-side rendering, and Bootstrap for responsive and appealing UI design. JWT is used for authentication, providing a secure and stateless approach. The user data is stored in a local MySQL database, ensuring persistence and reliability.

## Installation

To use this project and start working with it, follow these steps:

1. Clone the repository to your local machine.
2. Install Java 17 and set up your development environment.
3. Configure the MySQL database connection properties in the application.yml file.
4. Build the project using maven.
5. Run the application and access the API endpoints.
6. Open the frontend in a web browser to interact with the login and registration forms.



## Code Quality

The code in this project has been tested for quality using [CodeFactor](https://www.codefactor.io/). CodeFactor is a platform that analyzes code repositories and provides insights into code quality, style, and potential issues.

![CodeFactor](/media/codefactor.png)

The above image shows the CodeFactor rating for this project. It represents the overall code quality score, with higher scores indicating better code quality. The code has been reviewed and analyzed by CodeFactor, helping to identify any potential issues and ensure good coding practices.




## API Endpoints

### AuthController

#### Register Endpoint

- **URL:** `/api/v1/auth/register`
- **Method:** POST
- **Request Body:**
  - Example:
    ```json
    {
      "name": "example_user",
      "password": "password123"
    }
    ```
- **Success Response:**
  - Code: 200
  - Body:
    ```json
    {
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJleGFtcGxlX3VzZXIiLCJpYXQiOjE2MjIxNTUwMzgsImV4cCI6MTYyMjE1NzYzOH0.GvdqsQZ2A9fBKwFt6TRCf7_IZyt3lK2M8ymRVGL7G80",
      "user": {
        "username": "example_user"
      }
    }
    ```
- **Error Response:**
  - Code: 409 (Conflict)
  - Body:
    ```json
    {
      "message": "This Username is Already Taken"
    }
    ```
  - Code: 400 (Bad Request)
  - Body:
    ```json
    {
      "message": "The password cannot be empty"
    }
    ```

#### Login Endpoint

- **URL:** `/api/v1/auth/login`
- **Method:** POST
- **Request Body:**
  - Example:
    ```json
    {
      "name": "example_user",
      "password": "password123"
    }
    ```
- **Success Response:**
  - Code: 200
  - Body:
    ```json
    {
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJleGFtcGxlX3VzZXIiLCJpYXQiOjE2MjIxNTUwMzgsImV4cCI6MTYyMjE1NzYzOH0.GvdqsQZ2A9fBKwFt6TRCf7_IZyt3lK2M8ymRVGL7G80",
      "user": {
        "username": "example_user"
      }
    }
    ```
- **Error Response:**
  - Code: 401 (Unauthorized)
  - Body:
    ```json
    {
      "message": "Unauthorized"
    }
    ```

### FrontController

#### Home Endpoint

- **URL:** `/`
- **Method:** GET
- **Response:**
  - Renders the `index.html` template with the user's username.

#### Login Endpoint

- **URL:** `/login`
- **Method:** GET
- **Query Parameters:**
  - `error` (optional): Displays an error message if the value is "badcredentials".
- **Response:**
  - Renders the `login.html` template.

#### Register Endpoint

- **URL:** `/register`
- **Method:** GET
- **Query Parameters:**
  - `error` (optional): Displays an error message if the value is "alreadytaken".
- **Response:**
  - Renders the `register.html` template.

#### Authenticate Endpoint

- **URL:** `/authenticate`
- **Method:** POST
- **Request Body:**
  - Example:
    ```json
    {
      "name": "example_user",
      "password": "password123


## Contact Information 

For any inquiries or further information, please feel free to contact the project author:

- Name: Pau Sansa
- Email: pausansa59@gmail.com

## License
This project is licensed under the MIT License - see the LICENSE file for details.