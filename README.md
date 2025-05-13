# WebDev Intern
A simple fullstack web application for managing and viewing student scores.  
Built with Java Spring Boot (backend), MySQL (database), and HTML/CSS/JS (frontend).

### Live Demo
- **Frontend**: [https://marvelous-custard-c5dfdf.netlify.app](https://marvelous-custard-c5dfdf.netlify.app)
- **Backend (Public IP)**: `http://34.87.113.216:8080`


### API Documentation

You can view the API documentation for the backend at the following link:

- [Backend API Documentation (Springdoc OpenAPI)](http://34.87.113.216:8080/swagger-ui/index.html)

This will give you detailed information about the available API endpoints, request parameters, and response structure.


## Backend Setup (Spring Boot + MySQL)
### Using Docker Compose

Create a folder (e.g., `webapp_intern/`) and add these files:

### `docker-compose.yml`

```yaml
services:
  webdev-intern:
    image: brookvita3/webdev-intern:0.0.2
    container_name: webdev-intern
    ports:
      - "8080:8080"
    networks:
      - app-networks
    env_file:
      - .env
    environment:
      - TZ=Asia/Ho_Chi_Minh
    restart: on-failure
    depends_on:
      - mysql

  mysql:
    image: mysql:8.0.41
    container_name: mysql
    expose:
      - "3306"
    networks:
      - app-networks
    environment:
      - MYSQL_ROOT_PASSWORD=root
      - MYSQL_DATABASE=webdev_intern
    volumes:
      - mysql_data:/var/lib/mysql

networks:
  app-networks:
    driver: bridge

volumes:
  mysql_data:
```
### `.env`
```dotenv
SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/webdev_intern
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=root
```
### Run with Docker
```commandline
docker compose up -d
```
Your backend will be available at http://your-vm-ip:8080.


## Frontend Setup
### Folder Structure
Just HTML/CSS/JS:

````text
webdev-intern-fe/
├── index.html
├── styles.css
├── script.js
└── _redirects
````
### Deploy to Netlify
Drag-drop webdev-intern folder to https://app.netlify.com/drop
> Use `_redirects` to rewrite frontend API calls and ensure HTTPS compatibility when the backend is only available via HTTP.

### Features
- Search student scores
- View top 10 students in group A including (math, physics, chemistry)
- Simple API integration with async fetch
- Seamless frontend-backend communication

### Technologies
- Backend: Java 21, Spring Boot, JPA, MySQL
- Frontend: HTML, CSS, JavaScript (Vanilla)
- Deploy: Docker, Docker Compose, Netlify, Google Cloud VM