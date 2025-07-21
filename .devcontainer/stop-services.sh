#!/bin/bash

# Stop the support services
echo "Stopping development support services..."

# Navigate to devcontainer directory
cd "$(dirname "$0")"

# Stop services
docker-compose -f docker-compose.services.yml down

echo "✅ Support services stopped successfully!"