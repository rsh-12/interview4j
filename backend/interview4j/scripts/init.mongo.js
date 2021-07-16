db.createUser({
    user: 'user',
    pwd: 'password',
    roles: [
        {
            role: 'readWrite',
            db: 'interview4jdb',
        },
    ],
});

db = new Mongo().getDB("interview4jdb");

db.createCollection('users', {capped: false});
db.createCollection('sections', {capped: false});
db.createCollection('questions', {capped: false});