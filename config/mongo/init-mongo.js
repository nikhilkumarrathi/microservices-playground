db.createUser({
    user: "admin",
    pwd: "nimda",
    roles: [
        {
            role: "readWrite",
            db: "admin"
        }
    ]
})