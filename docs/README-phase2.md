# Routes

* Get routes

    1. .get('/messages/:id', (request, response)) - Returns a specific message's id, title, message, and likes
    1. .get('/messages', (request, response)) - Returns array of all messages
    1. .get('/messages/:id/comments', (request, response)) - Returns array of all comments on specific message post
    1. .get('/messages/:id/comments/:commentId', (request, response)) - Returns a comment's id, message, and likes
    1. .get('/users', (request, response)) - Returns array of all users
    1. .get('/users/:id', (request, response)) - Returns a specific user's id, name, email, gender identity, sexual orientation, and note

* Post routes

    1. .post('/messages', (request, response)) - Posts a new message with title and message passed in through the body
    1. .post('/messages/:id', (request, response)) - Posts a new comment to a message with comment message being passed in through the body 
    1. .post('/users', (request, response)) - Adds a new user to the database. Name, email, GI, SO, and note are passed in through the body

* Put routes

    1. .put('/messages/:id', (request, response)) - Edit the number of likes on a message, likes passed in through body
    1. .put('/messages/:id/comments/:commentId', (request, response)) - Edit the number of likes on a comment (?), likes passed in through body
    1. .put('/users/:id', (request, response)) - Edit the details of a user. Name, email, GI, SO, and note are passed in through the body

* Delete routes

    1. .delete('/messages/:id', (request, response)) - Delete a specific message
    1. .delete('/messages/:id/comments/:commentId', (request, response)) - Delete a specific comment
    1. .delete('/users/:id', (request, response)) - Delete a specific user

# Backlog Items

* Admin

    1. Remove unnecessary table funcionality

* Web

    1. Auto-refresh when liking and deleting posts
    1. Unit tests need to be updated
    1. CSS needs to be added for UI
    1. Implement unliking messages

* Mobile

    1. Unit tests need to be updated
    1. Implement unliking messages

* Backend

    1. Needs to connect to database

