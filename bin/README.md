# BlogPost Spring Boot Application

A Spring Boot application providing a RESTful API for managing blog posts, with user authentication (form-based login, registration, and OAuth2 with Google/GitHub). It uses an H2 in-memory database for persistence. Public access is allowed for reading blogs, while creating, updating, or deleting requires authentication. Logging, exception handling, and unit tests are included.

## Features
- **Public Blog Viewing**: Access `/api/blogs` (GET all blogs as JSON) and `/api/blogs/{id}` (GET single blog as JSON) without authentication.
- **Blog CRUD Operations**:
  - Create (`POST /api/blogs/add`), update (`PUT /api/blogs/update/{id}`), and delete (`DELETE /api/blogs/delete/{id}`) blog posts (authenticated users only).
- **User Authentication**:
  - Form-based login via `/login` (handled by Spring Security).
  - User registration via `POST /api/register` (requires manual ID, username, and password).
  - OAuth2 login with Google and GitHub (redirects to `/oauth2/authorization/google` or `/oauth2/authorization/github`).
- **Database**: H2 in-memory database with JPA repositories for blogs and users.
- **Logging**: SLF4J for request/response logging.
- **Exception Handling**: Global handler for errors, returning appropriate HTTP statuses.
- **Testing**: Unit tests for services (BlogService, CustomUserDetailsService) and controllers (BlogController) using Mockito.

## application.properties:
For application properties, see [`src/main/resources/application.properties`](src/main/resources/application.properties).
