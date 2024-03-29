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
    1. .put('/messages/:id/comments/:commentId', (request, response)) - Edit the comment message, comment passed through body
    1. .put('/users/:id', (request, response)) - Edit the details of a user. Name, email, GI, SO, and note are passed in through the body

* Delete routes

    1. .delete('/users/:id', (request, response)) - Delete a specific user (?)

# Backlog Items

* Admin

    1. Refactor the database names

* Web

    1. Editing the User Profile not working as expected
    1. Different user profiles not being shown correctly

* Mobile

    1. Unit tests need to be updated
    1. OAuth profiles not working as expected

* Backend

    1. Switch to database that we all have access to
    1. All posts that users have liked is not shown in its entirety 

# System Diagram

![System Drawing](diagrams/Phase2_System_Drawing.jpg)

# State Machine Diagram from User Perspective

![User State Machine Diagram](diagrams/Phase2_User_State_Diagram.jpg)

# Entity Diagram

![Entity_Diagram](diagrams/Entity_Diagram.JPG)

# User Stories and Tests
https://docs.google.com/document/d/1K8kSes5hbc4sY5MNz98EBYR8Cx7yYY04osTwsXr-eZo/edit?usp=sharing 

# Unit Tests
https://docs.google.com/document/d/12otxU6lLwTojMmRcqqpRKlj5uO6V8lMntIydhbBi8WM/edit

# Mock Mobile

![Mock 1 Mobile](diagrams/phse2Diagrams/mockmobile/1.jpg)

![Mock 2 Mobile](diagrams/phse2Diagrams/mockmobile/2.jpg)

![Mock 3 Mobile](diagrams/phse2Diagrams/mockmobile/3.jpg)

![Mock 4 Mobile](diagrams/phse2Diagrams/mockmobile/4.jpg)

# Mock Web

![Mock Web](diagrams/phse2Diagrams/mockweb/Posts Page.jpg)

![Mock Web](diagrams/phse2Diagrams/mockweb/Profile Page.jpg)

![Mock Web](diagrams/phse2Diagrams/mockweb/LogIN.jpg)

# State Machine Diagram

![State Machine Diagram](diagrams/phse2Diagrams/State Machine Drawing.jpeg)