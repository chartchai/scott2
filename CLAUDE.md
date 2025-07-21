# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Architecture

This is a full-stack application with a separate frontend and backend structure:

- **Backend**: Spring Boot 3.3.2 application using Java 21, located in `backend/`
  - Uses MongoDB for data persistence
  - REST API exposed at `/api` endpoints
  - Main application class: `camt.scott2.backend.BackendApplication`
  - Controllers in `camt.scott2.backend.controller` package

- **Frontend**: Nuxt 3.12.4 application with Vue 3, located in `frontend/`
  - Uses Nuxt UI components library
  - Configured to connect to backend API via runtime config
  - Pages in `frontend/pages/`, components in `frontend/components/`

- **Database**: MongoDB 7.0 with authentication configured

## Development Commands

### Backend (Spring Boot)
```bash
cd backend
mvn spring-boot:run              # Start development server
mvn test                         # Run tests
mvn clean package               # Build JAR
```

### Frontend (Nuxt)
```bash
cd frontend
npm install                     # Install dependencies
npm run dev                     # Start development server
npm run build                   # Build for production
npm run generate               # Generate static site
npm run preview                # Preview built app
```

### Docker Development
```bash
# Full stack with Docker Compose
docker-compose -f docker-compose.dev.yml up

# Just MongoDB
docker run -d -p 27017:27017 mongo:7.0
```

## Service URLs
- Frontend: http://localhost:3000
- Backend API: http://localhost:8080/api
- MongoDB: localhost:27017

## Configuration Details

### Backend Configuration
- MongoDB connection configured in `application.yml`
- Uses environment variables for database host in containerized environments
- Debug logging enabled for `camt.scott2.backend` and MongoDB

### Frontend Configuration
- API base URL configured via `API_BASE_URL` environment variable
- Defaults to `http://localhost:8080/api` for local development
- Nuxt UI module included for components

### Database Setup
- Default database: `app_db`
- Authentication: username `app_user`, password `app_password`
- Container setup includes initialization script at `.devcontainer/init-mongo.js`

## Key Files
- `backend/pom.xml`: Maven dependencies and build configuration
- `frontend/package.json`: Node.js dependencies and scripts
- `frontend/nuxt.config.ts`: Nuxt configuration and modules
- `backend/src/main/resources/application.yml`: Spring Boot configuration
- `docker-compose.dev.yml`: Development environment setup