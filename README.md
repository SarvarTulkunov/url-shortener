URL Shortener Project

Overview

The URL Shortener project is a web application that allows users to shorten long URLs into compact and manageable links. This project is built using modern technologies and is designed for performance and scalability.

Features

Shorten long URLs to a compact form.

Redirect shortened URLs to their original destinations.

Store URL mappings in a MongoDB database.

Use Redis for caching to improve performance.

Deployable using Docker for seamless setup and scaling.

Technologies Used

Java: Backend logic and API development.

Spring Boot: Framework for building the application.

MongoDB: NoSQL database for storing URL mappings.

Redis: Caching layer to speed up URL retrieval.

Docker: Containerization for easy deployment.

Installation and Setup

Prerequisites

Ensure you have the following installed on your system:

Java 17

Docker

MongoDB

Redis

Steps

Clone the Repository:

git clone https://github.com/your-username/url-shortener.git
cd url-shortener

Build the Project:

./mvnw clean package

Run the Application Locally:

java -jar target/url-shortener.jar

Run with Docker:

Build the Docker image:

docker build -t url-shortener .

Start the containers using Docker Compose:

docker-compose up

Configuration

The application uses the following environment variables for configuration:

MONGO_URI: MongoDB connection string (e.g., mongodb://localhost:27017/urlshortener)

REDIS_HOST: Redis server host (e.g., localhost)

REDIS_PORT: Redis server port (default: 6379)

You can set these variables in a .env file or directly in your Docker Compose configuration.

API Endpoints

1. Shorten URL

Endpoint: POST /api/v1/shorten

Request Body:

{
  "longUrl": "https://example.com"
}

Response:

{
  "shortUrl": "http://your-domain/{shortCode}"
}

2. Redirect to Original URL

Endpoint: GET /{shortCode}

Response:
Redirects the user to the original URL.

Project Structure

src/main/java: Contains application source code.

src/main/resources: Configuration files and templates.

Dockerfile: Defines how to build the application container.

docker-compose.yml: Simplifies container orchestration.

Caching with Redis

Redis is used to cache shortened URLs to enhance retrieval speed and reduce database queries. Cached entries expire after a defined TTL (time-to-live), which can be configured in the application properties.

Testing

Run the following command to execute the tests:

./mvnw test

Contribution

Contributions are welcome! To contribute:

Fork the repository.

Create a feature branch.

Commit your changes.

Create a pull request.

License

This project is licensed under the MIT License. See the LICENSE file for details.

Contact

For any questions or feedback, feel free to reach out:

Email: your-email@example.com

GitHub: your-username

Thank you for checking out this project! If you find it helpful, please give it a star on GitHub!

