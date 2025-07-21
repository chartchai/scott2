# Development Setup Guide

This project is configured for hybrid development where you can run the application from IntelliJ while using containerized support services.

## Quick Start

### Option 1: Using Dev Container (Recommended for full setup)
1. Open the project in VS Code or IntelliJ with dev container support
2. The dev container will automatically start MongoDB and set up the development environment
3. Run the Spring Boot application directly from your IDE

### Option 2: Manual Services Setup
If you prefer to run services manually:

```bash
# Start support services (MongoDB)
cd .devcontainer
./start-services.sh

# Or use docker-compose directly
docker-compose -f docker-compose.services.yml up -d
```

### Option 3: IntelliJ Run Configuration
1. Ensure MongoDB is running (via dev container or manual setup)
2. Create/use the Spring Boot run configuration in IntelliJ
3. Set these environment variables if needed:
   - `SPRING_DATA_MONGODB_HOST=localhost`

## Services Information

### MongoDB
- **Host:** localhost:27017
- **Database:** app_db
- **Username:** app_user  
- **Password:** app_password
- **Admin Username:** admin
- **Admin Password:** password

### Application URLs
- **Backend API:** http://localhost:8080/api
- **GraphiQL:** http://localhost:8080/graphiql
- **Frontend:** http://localhost:3000 (when running Nuxt)

## Development Workflow

1. **Start Services:** Use dev container or run `./start-services.sh`
2. **Run Backend:** Use IntelliJ's Spring Boot run button
3. **Run Frontend:** `cd frontend && npm run dev`
4. **Stop Services:** `./stop-services.sh` or stop dev container

## IntelliJ Configuration

### Recommended Plugins
- Spring Boot
- MongoDB Plugin
- Vue.js
- TailwindCSS

### Run Configuration
- **Main Class:** `camt.scott2.backend.BackendApplication`
- **Active Profiles:** (none needed for local dev)
- **Environment Variables:** 
  - `SPRING_DATA_MONGODB_HOST=localhost` (optional, defaults to localhost)

## Troubleshooting

### MongoDB Connection Issues
1. Verify MongoDB container is running: `docker ps`
2. Check logs: `docker-compose -f .devcontainer/docker-compose.services.yml logs mongodb`
3. Test connection: `mongo mongodb://app_user:app_password@localhost:27017/app_db`

### Port Conflicts
- MongoDB: 27017
- Spring Boot: 8080  
- Nuxt: 3000

Change ports in docker-compose.services.yml if needed.