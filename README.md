# URL Shortener Project

## Overview

The URL Shortener project is a web application that allows users to shorten long URLs into compact and manageable links. This project is built using modern technologies and is designed for performance and scalability.

## Features

* Shorten long URLs to a compact form.
* Retrieve the long URL when requested with short URL
* Store URL mappings in a MongoDB database.
* Use Redis for caching to improve performance.

## Technologies Used

1. Java: Backend logic and API development.
2. Spring Boot: Framework for building the application.
3. MongoDB: NoSQL database for storing URL mappings.
4. Redis: Caching layer to speed up URL retrieval.
5. Docker: Containerization for easy deployment.

## Installation and Setup

## Prerequisites

Ensure you have the following installed on your system:

* Java 17

* Docker

## Steps

1. Clone the Repository:
- `git clone [https://github.com/your-username/url-shortener.git](https://github.com/SarvarTulkunov/url-shortener.git)`
- `cd url-shortener`

2. Build the Project:

- ./mvnw clean package

3. Run the Application Locally:

java -jar target/url-shortener.jar

4. Run with Docker:

- Build the Docker image:
  `docker-compose build`

- Start the containers using Docker Compose:
  `docker-compose up`

## Configuration

The application uses the following environment variables for configuration:

 - MONGO_URI: MongoDB connection string (e.g., mongodb://localhost:27017/urlshortener)

 - REDIS_HOST: Redis server host (e.g., localhost)

 - REDIS_PORT: Redis server port (default: 6379)

You can set these variables in a .env file or directly in your Docker Compose configuration.

## API Endpoints

# 1. Shorten URL

**Endpoint:** `POST /api/v1/shorten`

**Request Param:**

`{
  "longUrl": "https://example.com"
}`

**Response:**

`{
  "shortUrl": "http://your-domain/{shortCode}"
}`

# 2. Get Original URL (it can be implemented to redirect to the original URL)

**Endpoint:** `GET /api/v1/{shortCode}`

**Response:** Get Long URL (Or you can change it so that it redirects the user to the original URL)

## Caching with Redis

Redis is used to cache shortened URLs to enhance retrieval speed and reduce database queries. Cached entries expire after a defined TTL (time-to-live), which can be configured in the application properties.

## Contribution

Contributions are welcome! To contribute:

1. Fork the repository.

2. Create a feature branch.

3. Commit your changes.

4. Create a pull request.
