📘 BLOG-SERVICE-KIRAN
A simple and secure Spring Boot application for managing blog posts. It supports public viewing of blogs and authenticated CRUD operations using form login, Google OAuth2, and GitHub OAuth2. Built with love using Spring Boot, H2 database, and tested via Postman.

🚀 Features

📝 Public Blog Viewing: Anyone can view blogs without logging in.
🔐 Authentication:

Form-based login (/login)
OAuth2 login via Google and GitHub


✍️ Blog Management:

Create, update, delete blogs (requires login)


🧠 User Registration: Manual registration via API
🗃️ Database: H2 in-memory (no setup needed)
🧪 Unit Testing: With Mockito for services and controllers
📜 Logging & Error Handling: SLF4J + global exception handler

⚙️ Tech Stack

Java 21
Spring Boot
Spring Security + OAuth2
Spring Data JPA
H2 Database
Maven
Postman (for testing)

🛠️ How to Run Locally
📦 Steps

Clone the repo
git clone https://github.com/Saikiran1126/blog-service-kiran.git
cd blog-service-kiran

Build the project
mvn clean install

Run the app
mvn spring-boot:run

Access the app

Base URL: http://localhost:9090
H2 Console: http://localhost:9090/h2-console
Login Page: http://localhost:9090/login

🔐 OAuth2 Setup

Make sure to set these environment variables before running:
export GOOGLE_CLIENT_ID=your-google-client-id
export GOOGLE_CLIENT_SECRET=your-google-client-secret

📮 API Endpoints
🌍 Public Access

GET /api/blogs → Get all blogs
GET /api/blogs/{id} → Get blog by ID

🔐 Authenticated Access

POST /api/blogs/add → Add new blog
PUT /api/blogs/update/{id} → Update blog
DELETE /api/blogs/delete/{id} → Delete blog

👤 User Registration

POST /api/register → Register new user

🧪 Sample Response
[
  {
    "id": 1,
    "title": "OAuth2 in Depth",
    "content": "A detailed walkthrough of implementing OAuth2 in Spring Security."
  },
  {
    "id": 2,
    "title": "Messaging with RabbitMQ",
    "content": "How I implemented async messaging in my app using RabbitMQ."
  }
]

🧰 Developer Notes

Port: 9090
Database: H2 (auto-created at runtime)
Login options: Form login, Google, GitHub
Tested using Postman
