db = db.getSiblingDB('app_db');

db.createUser({
  user: 'app_user',
  pwd: 'app_password',
  roles: [
    {
      role: 'readWrite',
      db: 'app_db',
    },
  ],
});

db.createCollection('users');
db.users.insert({
  name: 'Sample User',
  email: 'user@example.com',
  createdAt: new Date()
});