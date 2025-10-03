# Blog Service Documentation

📘 **BLOG-SERVICE-KIRAN**  
A simple and secure Spring Boot application for managing blog posts. It supports public viewing of blogs and authenticated CRUD operations using form login, Google OAuth2, and GitHub OAuth2. Built with love using Spring Boot, H2 database, and enhanced with Swagger for API documentation. Tested via Postman.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
  - [Public Access Endpoints](#public-access-endpoints)
  - [Authenticated Access Endpoints](#authenticated-access-endpoints)
  - [User Registration Endpoint](#user-registration-endpoint)
- [Authentication](#authentication)
  - [OAuth2 Setup](#oauth2-setup)
  - [Form Login](#form-login)
- [Swagger Documentation](#swagger-documentation)
- [Testing with Postman](#testing-with-postman)
  - [Setup Postman](#setup-postman)
  - [Example Requests](#example-requests)
- [Troubleshooting](#troubleshooting)
- [H2 Database Management](#h2-database-management)
- [Contributing](#contributing)
- [License](#license)

## Overview
This project is a microservice designed to manage blog posts with a focus on security and scalability. It allows anonymous users to view blogs while requiring authentication for creating, updating, or deleting posts. Authentication is handled via form login and OAuth2 (Google and GitHub), with user registration supported via API.

## Features
🚀 **Public Blog Viewing**: Anyone can view blogs without logging in.  
🔐 **Authentication**:  
- Form-based login (`/login`).  
- OAuth2 login via Google and GitHub.  
✍️ **Blog Management**: Create, update, delete blogs (requires login).  
🧠 **User Registration**: Manual registration via API.  
🗃️ **Database**: H2 in-memory (no setup needed).  
🧪 **Unit Testing**: With Mockito for services and controllers.  
📜 **Logging & Error Handling**: SLF4J + global exception handler.  
📋 **Swagger**: Integrated for API documentation.

## Tech Stack
- **Java**: 21  
- **Spring Boot**: Core framework.  
- **Spring Security**: With OAuth2 support.  
- **Spring Data JPA**: For database operations.  
- **H2 Database**: In-memory database.  
- **Maven**: Build tool.  
- **Postman**: For API testing.  
- **Swagger**: For API documentation.

## Prerequisites
- **Java**: JDK 21 or higher.  
- **Maven**: For building the project.  
- **Postman**: For API testing.  
- **OAuth2 Credentials**: Google Client ID and Secret, GitHub Client ID and Secret.  
- **IDE**: IntelliJ IDEA or Eclipse (recommended).

## Setup Instructions
1. **Clone the Repository**:
   - `git clone https://github.com/Saikiran1126/blog-service-kiran.git`
   - `cd blog-service-kiran`
2. **Configure Environment**:
   - Set OAuth2 environment variables (see [OAuth2 Setup](#oauth2-setup)).
   - Ensure `application.properties` is in `src/main/resources` with:
     - `spring.datasource.url=jdbc:h2:mem:blogdb`
     - `spring.datasource.username=sa`
     - `spring.datasource.password=`
     - `spring.h2.console.enabled=true`
3. **Install Dependencies**:
   - Run `mvn clean install` to download dependencies.

## Running the Application
1. **Set Environment Variables**:
   - Export OAuth2 credentials:
     ```bash
     export GOOGLE_CLIENT_ID=your-google-client-id
     export GOOGLE_CLIENT_SECRET=your-google-client-secret
     export GITHUB_CLIENT_ID=your-github-client-id
     export GITHUB_CLIENT_SECRET=your-github-client-secret
