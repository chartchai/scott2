# Full Stack Application

A multi-repo structure with Spring Boot backend and Nuxt frontend, including MongoDB database.

## Architecture

- **Backend**: Spring Boot 3.3.2 with Java 21
- **Frontend**: Nuxt 3.12.4 with Vue 3
- **Database**: MongoDB 7.0
- **Development**: VS Code Dev Containers

## Quick Start

### Option 1: Dev Container (Recommended)
1. Open in VS Code
2. Command Palette → "Dev Containers: Reopen in Container"
3. Services will start automatically

### Option 2: Docker Compose
```bash
docker-compose -f docker-compose.dev.yml up
```

### Option 3: Local Development
1. Start MongoDB:
   ```bash
   docker run -d -p 27017:27017 mongo:7.0
   ```

2. Start Backend:
   ```bash
   cd backend
   mvn spring-boot:run
   ```

3. Start Frontend:
   ```bash
   cd frontend
   npm install
   npm run dev
   ```

## Services

- **Frontend**: http://localhost:3000
- **Backend API**: http://localhost:8080/api
- **MongoDB**: localhost:27017

## Development

The dev container includes:
- Java 21 + Maven
- Node.js 20 + npm
- VS Code extensions for Java and Vue development
- Port forwarding for all services