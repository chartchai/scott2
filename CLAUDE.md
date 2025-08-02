# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Architecture

This is a full-stack reactive application with a separate frontend and backend structure:

- **Backend**: Spring Boot 3.5.3 application using Java 21, located in `backend/`
  - **Reactive Stack**: Uses Spring WebFlux with reactive MongoDB
  - **Security**: JWT-based authentication with Spring Security
  - **GraphQL**: Exposed at `/graphql` with GraphiQL at `/graphiql`
  - **REST API**: Also exposed at `/api` endpoints
  - **Main application class**: `camt.scott2.backend.BackendApplication`
  - **Package structure**: `camt.scott2.backend` with controller, dao, entity, repository, resolver, and security packages

- **Frontend**: Nuxt 3.12.4 application with Vue 3, located in `frontend/`
  - **UI Library**: Nuxt UI components
  - **API Integration**: Configured to connect to backend via runtime config
  - **Development**: Hot reload enabled with devtools

- **Database**: MongoDB 7.0 with authentication
  - **Reactive**: Uses reactive MongoDB drivers
  - **Authentication**: Configured with app_user/app_password credentials

## Development Commands

### Backend (Spring Boot)
```bash
cd backend
mvn spring-boot:run              # Start development server
mvn test                         # Run tests (no test files currently exist)
mvn clean package               # Build JAR
mvn clean install               # Full build with dependencies
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

### Full Stack Development
```bash
# Complete development environment
docker-compose -f docker-compose.dev.yml up

# Services only (for IDE development)
cd .devcontainer
./start-services.sh             # Start MongoDB only
./stop-services.sh              # Stop services
```

## Service URLs
- **Frontend**: http://localhost:3000
- **Backend REST API**: http://localhost:8080/api
- **GraphiQL Interface**: http://localhost:8080/graphiql
- **MongoDB**: localhost:27017

## Architecture Details

### Backend Architecture
- **Reactive Programming**: Built on Spring WebFlux for non-blocking I/O
- **Data Layer**: Reactive MongoDB with repository pattern and DAO implementations
- **Security Layer**: JWT tokens with reactive security configuration
- **API Layer**: Both REST controllers and GraphQL resolvers
- **Entity Models**: Person, Mail, XlsxInfo with MongoDB document mapping

### Frontend Architecture
- **Framework**: Nuxt 3 with Vue 3 composition API
- **Styling**: Nuxt UI components with TailwindCSS
- **Configuration**: Runtime config for API base URL flexibility

### Development Environment
- **Dev Containers**: Full VS Code dev container support with all dependencies
- **IntelliJ Support**: Optimized for IntelliJ IDEA with Spring Boot tooling
- **Hot Reload**: Both frontend and backend support hot reloading during development

## Configuration

### Backend Configuration (`application.yml`)
- **MongoDB**: Host configurable via `SPRING_DATA_MONGODB_HOST` environment variable
- **Logging**: Debug level for application and MongoDB drivers
- **GraphQL**: GraphiQL interface enabled for development

### Frontend Configuration (`nuxt.config.ts`)
- **API Base**: Configurable via `API_BASE_URL` environment variable
- **Default**: Points to `http://localhost:8080/api` for local development

### Database Configuration
- **Database**: `app_db`
- **Credentials**: `app_user` / `app_password`
- **Admin**: `admin` / `password` (for container setup)
- **Initialization**: Automated via `.devcontainer/init-mongo.js`

## Key Files for Development
- `backend/pom.xml`: Maven dependencies (Spring Boot 3.5.3, WebFlux, Security, GraphQL)
- `backend/src/main/resources/application.yml`: Spring configuration
- `backend/src/main/resources/graphql/schema.graphqls`: GraphQL schema
- `frontend/nuxt.config.ts`: Nuxt configuration and modules
- `docker-compose.dev.yml`: Full development environment
- `.devcontainer/`: Development container and service scripts