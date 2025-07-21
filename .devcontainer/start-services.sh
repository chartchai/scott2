#!/bin/bash

# Start only the support services (MongoDB) for local development
echo "Starting development support services..."

# Navigate to devcontainer directory
cd "$(dirname "$0")"

# Start only MongoDB service
docker-compose -f docker-compose.services.yml up -d

echo "✅ Support services started successfully!"
echo ""
echo "📋 Available services:"
echo "   - MongoDB: localhost:27017"
echo "     - Database: app_db" 
echo "     - Username: app_user"
echo "     - Password: app_password"
echo ""
echo "🚀 You can now run your Spring Boot application from IntelliJ"
echo "   The app will connect to MongoDB running in the container"
echo ""
echo "🛑 To stop services: docker-compose -f docker-compose.services.yml down"