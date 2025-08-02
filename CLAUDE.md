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
mvn spring-boot:run              # Start development server (with security)
SPRING_PROFILES_ACTIVE=local mvn spring-boot:run  # Start with security bypass
mvn test                         # Run all tests
mvn test -Dtest=*SecurityTest    # Run security-specific tests
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

## Team Workflows

### Pull Request Requirements
- All PRs must include unit tests for new functionality
- Backend changes require integration tests with MongoDB
- Frontend changes must include component tests
- All CI checks must pass (build, test, security scan)

### Code Review Guidelines
- Review reactive streams for proper error handling and backpressure
- Ensure GraphQL resolvers include proper authentication checks
- Verify MongoDB queries use reactive operators correctly
- Check that JWT tokens are validated in security-sensitive endpoints

### Branch Strategy
- `main`: Production-ready code
- `develop`: Integration branch for features
- `feature/*`: New features, merge to develop
- `hotfix/*`: Critical fixes, merge directly to main

### Deployment Process
- Staging: Auto-deploy from `develop` branch
- Production: Manual deployment from `main` with approval
- Database migrations run automatically via Spring Boot
- Frontend builds are deployed to CDN after backend deployment

## Project-Specific Patterns

### Error Handling
- Use `Mono.error()` and `Flux.error()` for reactive error propagation
- Global exception handler in `ReactiveGlobalExceptionHandler`
- GraphQL errors should return proper error codes in schema
- Frontend errors display via Nuxt UI toast notifications

### Security Patterns
- All `/api` endpoints require JWT authentication except `/api/auth/login`
- GraphQL mutations require ROLE_USER minimum
- Use `@PreAuthorize` annotations on service methods
- JWT tokens expire after 24 hours, refresh tokens after 7 days
- **Local Development**: Security bypass available with `local` profile
  - Set `SPRING_PROFILES_ACTIVE=local` to disable authentication
  - `LocalSecurityConfig` permits all endpoints when local profile is active
  - `ReactiveSecurityConfig` uses `@Profile("!local")` for production security

### Database Patterns
- All entities extend `BaseEntity` with `id`, `createdAt`, `updatedAt`
- Use reactive repositories for all MongoDB operations
- Implement DAO pattern for complex queries
- Database queries should include pagination for list operations

### API Design
- REST endpoints follow `/api/v1/{resource}` pattern
- GraphQL schema uses Pascal case for types, camel case for fields
- All mutations return success/error status with descriptive messages
- Use DTOs for API responses, never expose entities directly

### Frontend Patterns
- Components in `components/` use PascalCase naming
- Pages use kebab-case file names
- Use Nuxt UI components instead of custom styling
- API calls go through `$fetch` with error handling
- Form validation uses Zod schemas

### Testing Patterns
- **Test Location**: All tests MUST be placed in `src/test/java/` directory, never in `src/main/java/`
- **Backend**: Use `@WebFluxTest` for controller tests
- **MongoDB**: Use `@DataMongoTest` with reactive test containers
- **Frontend**: Components tested with Vue Test Utils
- **Integration Tests**: Use Docker containers for real MongoDB
- **Security Tests**: Use `@ActiveProfiles` for profile-specific testing (local vs production)
- **Test Configuration**: Create separate `@TestConfiguration` classes in test directory
- **Mock Services**: Mock external services, never use real third-party APIs

### Development Standards
- Use Lombok for entity boilerplate reduction
- GraphQL resolvers should be thin, delegate to service layer
- Reactive streams should handle empty results gracefully
- All async operations must include timeout configuration

## Development Tools

### Linting and Formatting
```bash
# Backend code formatting
mvn spring-javaformat:apply     # Format Java code
mvn checkstyle:check           # Check code style

# Frontend linting  
npm run lint                   # ESLint + Prettier
npm run lint:fix              # Auto-fix linting issues
```

### Testing Commands
```bash
# Backend testing
mvn test -Dtest=*ControllerTest    # Controller tests only
mvn test -Dtest=*IntegrationTest   # Integration tests only
mvn verify                         # Full test suite with coverage

# Frontend testing
npm run test                   # Unit tests
npm run test:e2e              # End-to-end tests
npm run test:coverage         # Coverage report
```

### Environment Setup Requirements
- Java 21 required (use SDKMAN for version management)
- Node.js 20+ required (use nvm for version management)
- Docker required for MongoDB and integration tests
- IntelliJ IDEA plugins: Spring Boot, GraphQL, Vue.js

## Multi-Agent Configurations

### Backend Agent (Spring WebFlux Specialist)
**Purpose**: Handles Spring Boot reactive backend development, MongoDB operations, and API design.

**Specialized Knowledge**:
- Spring WebFlux reactive programming patterns
- Reactive MongoDB operations with proper error handling
- JWT security implementation and validation
- GraphQL schema design and resolver implementation
- DAO pattern with reactive repositories

**Primary Tasks**:
- Implement reactive controllers and services
- Design GraphQL schemas and resolvers
- Configure Spring Security for JWT authentication
- Optimize MongoDB queries and handle reactive streams
- Implement proper error handling with Mono.error() and Flux.error()

**File Focus**: `backend/src/main/java/camt/scott2/backend/**/*`

### Frontend Agent (Nuxt/Vue Specialist)
**Purpose**: Handles Nuxt.js frontend development, Vue.js components, and UI/UX implementation.

**Specialized Knowledge**:
- Nuxt 3 with Vue 3 composition API
- Nuxt UI component library usage
- TailwindCSS styling patterns
- Reactive data fetching with $fetch
- Form validation and state management

**Primary Tasks**:
- Create Vue components following PascalCase naming
- Implement pages with kebab-case file naming
- Configure API calls with proper error handling
- Design responsive UI with Nuxt UI components
- Implement form validation with Zod schemas

**File Focus**: `frontend/**/*` (pages, components, assets, nuxt.config.ts)

### Testing Agent (QA Specialist)
**Purpose**: Implements comprehensive testing strategies for both backend and frontend.

**Specialized Knowledge**:
- Spring WebFlux testing with @WebFluxTest
- Reactive MongoDB testing with @DataMongoTest
- Vue component testing with Vue Test Utils
- Integration testing with Docker containers
- Test data management and mocking strategies

**Primary Tasks**:
- Write controller tests for Spring WebFlux endpoints
- Create integration tests with reactive MongoDB
- Implement Vue component unit tests
- Set up end-to-end testing scenarios
- Configure test containers for isolated testing
- **IMPORTANT**: Place ALL tests in `src/test/java/` directory, never in `src/main/java/`
- Create profile-specific security tests using `@ActiveProfiles("local")`

**File Focus**: `backend/src/test/**/*`, `frontend/test/**/*`, test configurations

### Security Agent (Security Review Specialist)
**Purpose**: Focuses on security implementation, JWT handling, and vulnerability assessment.

**Specialized Knowledge**:
- Spring Security reactive configuration
- JWT token generation, validation, and refresh
- GraphQL security with authentication checks
- MongoDB security patterns
- Frontend authentication state management

**Primary Tasks**:
- Review and implement JWT security flows
- Configure Spring Security for reactive endpoints
- Implement @PreAuthorize annotations
- Audit GraphQL resolvers for authentication
- Review frontend authentication handling

**File Focus**: `backend/src/main/java/camt/scott2/backend/security/**/*`, authentication-related components

### DevOps Agent (Infrastructure Specialist)
**Purpose**: Manages Docker configurations, deployment processes, and development environment setup.

**Specialized Knowledge**:
- Docker and Docker Compose configurations
- Development container setup
- MongoDB container management
- Environment variable configuration
- CI/CD pipeline setup

**Primary Tasks**:
- Optimize Docker configurations for development
- Manage environment-specific configurations
- Setup and maintain development containers
- Configure MongoDB initialization scripts
- Implement deployment automation

**File Focus**: `docker-compose.dev.yml`, `.devcontainer/**/*`, Dockerfile configurations

## Agent Usage Guidelines

### When to Use Each Agent

**Backend Agent**: 
- Adding new API endpoints or GraphQL mutations
- Implementing reactive business logic
- Database operations and MongoDB queries
- Spring Security configuration

**Frontend Agent**:
- Creating new pages or components
- Styling and UI improvements
- Frontend state management
- API integration and error handling

**Testing Agent**:
- Adding test coverage for new features
- Debugging test failures
- Setting up testing infrastructure
- Performance testing

**Security Agent**:
- Authentication and authorization features
- Security vulnerability reviews
- JWT token management
- Access control implementation

**DevOps Agent**:
- Environment setup issues
- Docker configuration changes
- Deployment troubleshooting
- Development workflow improvements

### Multi-Agent Collaboration Examples

**New Feature Development**:
1. Backend Agent implements API endpoints
2. Frontend Agent creates UI components
3. Testing Agent adds comprehensive tests
4. Security Agent reviews authentication
5. DevOps Agent updates deployment config

**Bug Fixes**:
1. Testing Agent reproduces the issue with tests
2. Backend/Frontend Agent implements the fix
3. Security Agent reviews security implications
4. DevOps Agent verifies deployment impact
