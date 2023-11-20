# Phase 2' Sprint 10 - PM Report Template
Use this form to provide your project manager report for Phase 2' (Prime).

## Team Information [10 points total]

### Team Information:

* Number: Team 6
* Name: Ashley Hirst
* Mentor: <Ala, anc224@lehigh.edu>
* Weekly live & synchronous meeting:
    * without mentor: Saturday (Zoom) Tuesday (Class)
    * with mentor: Wednesday (Zoom)

### Team Roles:

* Project Manger: <Ashley, ash320@lehigh.edu>
    * Has this changed from last week (if so, why)?
        * PM has not changed from last week
* Backend developer: <Kamil, kag624@lehigh.edu>
* Admin developer: <Nick, nmm224@lehigh.edu>
* Web developer: <Sarah, scz225@lehigh.edu>
* Mobile developer: <Aiden, apa225@lehigh.edu>

### Essential links for this project:

* Team's Dokku URL(s)
    * [MargaritaVillians Dokku](team-margaritavillians.dokku.cse.lehigh.edu)
* Team's software repo (bitbucket)
    * [Git Repo](https://bitbucket.org/cse216-fa23-kag624/cse216-2023fa-team-6/src/master/)
* Team's Trello board
    * [Trello](https://trello.com/b/NYiEMqdI/phase-2)


## Beginning of Phase 2' [20 points total]
Report out on the Phase 2 backlog and any technical debt accrued during Phase 2.

1. What required Phase 2 functionality was not implemented and why? 
    * Admin-cli
            1. Missing functionality 1: List of users blocked from using the site
                * Why: It was not an official requirement of the previous sprint. 
    * Backend
            1. Missing functionality 1: OAuth User functionality
                * Why: There was not enough time to correctly implement this along with comments, downvotes, and generally adapting the site to handle users and regulate what functionalities they had. 
    * Web FE
            1. Missing functionality 1: OAuth User Functionality
                * Why: OAuth was not implemented in the backend, making frontend unable to do so as well, as it was connected to the backend's functionality. 
    * Mobile FE
            1. Missing functionality 1: OAuth Functionality
                * Why: OAuth was not implemented in the backend, making frontend unable to do so as well, as it was connected to the backend's functionality. 
            2. Missing functionality 2: Usable comment section
                * Why: Many errors and bugs were appearing and a lot of research on flutter itself was needed to fix these issues. When they were dealt with, there was not enough time to add this functionality in a way that worked. 
            3. Missing functionality 3: User recognition and differentiation
                * Why: He did not know of a good way to implenent users without OAuth being functional, and once he had learned how Sarah had done it, it was too late to be able to fully implement the feature. 
            4. Missing functionality 4: Downvoting capabilities
                * Why: Many errors and bugs were appearing and a lot of research on flutter itself was needed to fix these issues. When they were dealt with, there was not enough time to add this functionality in a way that worked.

2. What technical debt did the team accrue during Phase 2?
    * Admin-cli
            1. Tech debt item 1: The database could use some cleaning up, possibly even deleting duplicate methods intended for different tables to all use the same method with a new parameter indicating which table is being altered.
        * Backend
            1. Tech debt item 1: There are some unnesicary routes still available in backend such as delete("/messages/:id") which are not needed as a functionality anymore.
        * Web FE
            1. Tech debt item 1: There are hard-coded accounts that were created to test the user settings while OAuth was not set up. These will need to be removed once real accounts can be created.
        * Mobile FE
            1. Tech debt item 1: There are a lot of different folders for different uses that we are simply not utilizing and could be deleted or condensed (a lot of the files are very similar).

## End of Phase 2' [20 points total]
Report out on the Phase 2' backlog as it stands at the conclusion of Phase 2'.

1. What required Phase 2 functionality still has not been implemented or is not operating properly and why?

    *  Mobile FE
            1. Missing functionality 1: OAuth Functionality
                * Why: Many errors kept occuring when trying to implement this, so time had run out before an answer was found. 
            3. Missing functionality 3: User profile editing, recognition, and differentiation
                * Why: Testing with the user's was not possible without OAuth working because a session key had to be passed to the backend.
            4. Missing functionality 4: Downvoting capabilities
                * Why: Many errors and bugs were appearing and a lot of research on flutter itself was needed to fix these issues. When they were dealt with, there was not enough time to add this functionality in a way that worked. However, it is ready to be implemented, a button just needs to be added to allow users to do so. 
        * Web FE
            1. Missing functionality 1: User Profile editing
                * Why: After getting everything else functional, it was discovered that the changes that appeared to be made to user profiles never saved the changes, and it was unclear why. 
        * Admin
            1. NONE
        * Backend
            1. NONE

2. What technical debt remains?
        * Admin-cli
            1. Tech debt item 1: The names of the rows and databases need to be changed in the creation of the tables inorder to match how it is in the backend. 
        * Backend
            1. Tech debt item 1: There are some unnesicary routes still available in backend such as delete("/messages/:id") which are not needed as a functionality anymore.
            1. Tech debt item 2: The database platform used (elephant SQL) needs to be switched over to one that all members have direct access to.
        * Web FE
            1. Tech debt item 1: Removing unneeded files and refactoring how the code is organized
        * Mobile FE
            1. Tech debt item 1: There are a lot of different folders for different uses that we are simply not utilizing and could be deleted or condensed (a lot of the files are very similar).

3. If there was any remaining Phase 2 functionality that needed to be implemented in Phase 2', what did the PM do to assist in the effort of getting this functionality implemented and operating properly?
    * I made sure to keep a list of what needed to get done for each person and I helped whenever I was able to fix errors and mitigate confusion. I also strongly encouraged other members to meet on their own even if we all couldn't make it to the specific meeting time to work things out together and coordinate routes. 

4. Describe how the team worked together in Phase 2'. Were all members engaged? Was the work started early in the week or was there significant procrastination?
    * The team worked together very well in Phase 2'. There was a lot of collaboration between everyone. There was even an extra meeting on Saturday where compatitibility between the branches was ensured and all questions were answered. Although there was a day or two of a break taken betweem Phase 2 and Phase 2' to decompress and take some time away from the code, I believe this helped everyone to produce better results quicker thus making the break a very efficient use of their time. 

5. What might you suggest the team or the next PM "start", "stop", or "continue" doing in the next Phase?
    * Start: We should start encouraging more breaks to be taken. Sometimes you just need to step away from the work for a day or so to be able to come back with a clear mind and solve the issues that you were having. This was demonstrated by everyone this week, and it will definatly help us again in the future.

    * Stop: We should stop using the ElephantSQL that only one person has direct access to, rather we should start using the one made that everyone has access to. 

    * Continue: As a group we should definatly continue with the strong communication we have. It has made a great working environment.


## Role reporting [50 points total]
Report-out on each team members' activity, from the PM perspective (you may seek input where appropriate, but this is primarily a PM related activity).
**In general, when answering the below you should highlight any changes from last week.**

### Back-end
What did the back-end developer do during Phase 2'?
1. Overall evaluation of back-end development (how was the process? was Trello used appropriately? how were tasks created? how was completion of tasks verified?)
    * Kamil did a wonderful job as backend. Everything was implemented and communicated to every other member very well. The trello board was used appropriately (checking off tasks as they were completed and keeping the team updated regularly as well). He also helped every other member implement the new features added to the backend.
2. List your back-end's REST API endpoints
    * get(“/messages?sessionKey=<insert session key>”)
    * get(“/messages/<insert message id>/?sessionKey=<insert session key>”)
    * post(“/messages?sessionKey=<insert session key>”)
    * put(“/messages/<insert message id>/?sessionKey=<insert session key>”)
    * delete(“/messages/<insert message id>/?sessionKey=<insert session key>”)
    * get(“/comments/<insert message id>/?sessionKey=<insert session key>”)
    * get(“/comments?sessionKey=<insert session key>”)
    * put(“/comments/<insert comment id>/?sessionKey=<insert session key>”)
    * post(“/comments?sessionKey=<insert session key>”)
    * post(“/users”)
    * get(“/users/<insert user id>/?sessionKey=<insert session key>”)
    * put(“/users?sessionKey=<insert session key>”)
    * get(“/likes?sessionKey=<insert session key>”)
3. Assess the quality of the back-end code
    * The backend code is easy to follow, well organized, and works seamlessly with the other branches and the new features implemented. 
4. Describe the code review process you employed for the back-end
    * I met with Kamil individually during a group meeting and had him walk me through the code and explain any issues that came up and what is still needed to be fixed.
5. What was the biggest issue that came up in code review of the back-end server?
    * The database that we have been using is only visable to Kamil, however, we have plans to switch the database to one we all have access to in the next sprint. 
6. Is the back-end code appropriately organized into files / classes / packages?
    * All backend code is appropriately organized in a way where its easy to follow and keeps everything in order.
7. Are the dependencies in the `pom.xml` file appropriate? Were there any unexpected dependencies added to the program?
    * All of the dependencies within the pom.xml are appropriate and needed.
8. Evaluate the quality of the unit tests for the back-end
    * The tests for backend are manual using curl commands to test the functionality. While it is not the most efficient way to test, it gets the job done and is a reasonable test method to accomodate the lack of time Kamil faced with all that was asked of him. They are used to show all backend features are functional.
9. Describe any technical debt you see in the back-end
    * There are still some not needed routes in the backend that need to be deleted. Also the database needs to switched to the one that we all have access to. 

### Admin
What did the admin front-end developer do during Phase 2'?
1. Overall evaluation of admin app development (how was the process? was Trello used appropriately? how were tasks created? how was completion of tasks verified?)
    * Nick very successfully completed the development of the admin app. He get everything done early and efficiently. Tasks were checked off of the checklist on trello as they were completed, and a progress report was given at each meeting.
2. Describe the tables created by the admin app
    * There was the original table for the posts, a table for the user's information, and a table for comments. The tables were linked together by including sections in the message table and comments table for the user's id to show who made the post, and a section in the comments table for the message id to coordinate what post that comment is for. The only difference from the last sprint is that a section was added to the user table to allow admin to block accounts from using the app.
    * List of Tables and their inputs:
        * tblData - id: SERIAL, title: varchar, message: varchar, uId: int, likes: int
        * commentData - id: SERIAL, content: varchar, mId: varchar, uId: varchar
        * likeData - id: SERIAL, mId: int, uId: int, like: int
        * userData - id: SERIAL, name: varchar, email: varchar, GI: varchar, SO: varchar, note: varchar
3. Assess the quality of the admin code
    * Nick's code is easy to follow and successfully completes all of the requirements assigned to Admin.
4. Describe the code review process you employed for the admin app
    * I met with Nick individually during a group meeting and had him walk me through the code and explain any issues that came up.
5. What was the biggest issue that came up in code review of the admin app?
    * The biggest issue that came up in the code review was that the tables are created with slightly different names than are used in the backend and frontend, creating some issues for everyone whenever a new table was created. 
6. Is the admin app code appropriately organized into files / classes / packages?
    * The code in the admin branch is very well organized into appropriately names folders, making it easy to follow, and it is simple to run due to it's use of packages.
7. Are the dependencies in the `pom.xml` file appropriate? Were there any unexpected dependencies added to the program?
    * All of the dependencies within the pom.xml are appropriate and needed.
8. Evaluate the quality of the unit tests for the admin app
    * Admin's unit tests are done very efficiently. The unit tests are all confinded within one program, organized by general topic, test every functionality, and are all very concise.
9. Describe any technical debt you see in the admin app
    * The names of the rows and databases need to be changed in the creation of the tables inorder to match how it is in the backend. 

### Web
What did the web front-end developer do during Phase 2'?
1. Overall evaluation of Web development (how was the process? was Trello used appropriately? how were tasks created? how was completion of tasks verified?)
    * Sarah did a great job developing the web frontend. She worked very hard and it paid off. She asked a lot of questions, made sure she knew what she had to do, and work for hours to achieve it. All the while she was checking off the todo list I had created on the trello board and keeping the rest of us updated on her progress.
2. Describe the different models and other templates used to provide the web front-end's user interface
    * REACT was used as a framework to created the frontend. 
3. Assess the quality of the Web front-end code
    * The web-frontend works very well. Not only is it very aesthetic, but it is also very functional. The only missing functionalities that were not able to be added successfully were the editing of user profiles and the need to refresh the page everytime something is changed in order to visualize that change, but, other than that, it works as expected. Overall, it is very high quality. 
4. Describe the code review process you employed for the Web front-end
    * I met with Sarah individually during a group meeting and had her walk me through the code and explain any issues that came up.
5. What was the biggest issue that came up in code review of the Web front-end?
    * The biggest issue was the lack of ability to make changes to the user profile on the webpage. Much time was spent getting OAuth to be implemented correctly, so there was not enough time to fix this error. 
6. Is the Web front-end code appropriately organized into files / classes / packages?
    * Web front-end was nicely organized into approriate folders making it efficient and easy to follow and find needed files. However, the code within the files themselves could be better organized.
7. Are the dependencies in the `package.json` file appropriate? Were there any unexpected dependencies added to the program?
    * All of the dependencies within the package.json are appropriate and needed.
8. Evaluate the quality of the unit tests for the Web front-end 
    * The web front end used both automated and manual tests. The maunal tests involved Sarah going through the webpage and showing the functionality of everything, while the automated tests were basic REACT unit tests ensuring that the basic components worked as they were expected. Both worked very well in ensuring and displaying the successfulness of the code.
9. Describe any technical debt you see in the Web front-end
    * The organization of the code within the files themselves could be reorganized to be a bit more logically organized. There are also some unneded files within the web folder that should be deleted.

### Mobile
What did the mobile front-end developer do during Phase 2'?
1. Overall evaluation of Mobile development (how was the process? was Trello used appropriately? how were tasks created? how was completion of tasks verified?)
    * Aiden faced the most issues with mobile out of any of our members. He worked for hours and hours, but was unable to finish the needed functionalities due to all of the errors that were encountered. However, everything was communicated with the group very well and what was done was checked off on the trello board as it occured. While the functionality of the Mobile branch is not exactly what was expected, it is clear he did everything he could. 
2. Describe the activities that comprise the Mobile app
    * Mobile app has the ability to add a post, view older posts, comment on posts, edit those comments, and like posts that are stored and sent directly to the database.
3. Assess the quality of the Mobile code
    * Mobile made good progress from last sprint, many functionalities are working, and the app has a very pleasing layout. However, there are still a few functionalities that need to be implemented, and, because of this, it is not very viable to judge all implementation done. Although, the code itself is easy to follow (especially when explained), so I can see that the code added is looking correct.
4. Describe the code review process you employed for the Mobile front-end
    * I met with Aiden individually during a group meeting and had him walk me through the code and explain any issues that came up.
5. What was the biggest issue that came up in code review of the Mobile front-end?
    * The biggest issue that came up in the code review was not that OAuth was not working, but that there was no way to test any functionality without it working, so any new functionalities added were unclear if they worked appropriately.
6. Is the Mobile front-end code appropriately organized into files / classes / packages?
    * The Mobile front-end is appropriately organized into folders. There are a lot of files that are included so it is a bit difficult to find where a specific code is if you are unfamiliar with it, however, it is definatly possible to find code by following appropriate folder and file names.
7. Are the dependencies in the `pubspec.yaml` (or build.gradle) file appropriate? Were there any unexpected dependencies added to the program?
    * All of the dependencies within the pubspec.yaml are appropriate and needed.
8. Evaluate the quality of the unit tests for the Mobile front-end here
    * The mobile frontend mainly used manual tests which consisted of Aiden going through the app and demoing each functionality. 
9. Describe any technical debt you see in the Mobile front-end here
    * There are a lot of different folders for different uses that we are simply not utilizing and could be deleted or condensed (a lot of the files are very similar).

### Project Management
Self-evaluation of PM performance
1. When did your team meet with your mentor, and for how long?
    * We met with our mentor on Wednesday at 5:00 pm for around 45 minutes. 
2. Describe your use of Trello.  Did you have too much detail?  Too little?  Just enough? Did you implement policies around its use (if so, what were they?)?
    * The trello for this sprint was not any different from last sprint's trello board. All of the basic rules remaind: do not touch anyone else's board, check things off as they are done, etc. The exact same requirements were on everyone's cards (except for admin who had to also implement a way to block people's accounts from the website), and the only things that were left on everyone's cards were the backlog items left over from last sprint's requirements. 
3. How did you conduct team meetings?  How did your team interact outside of these meetings?
    * Meetings were not any different from the previous week's. The meetings were still conducted in a very casual manor in which we reported on progress, issues, and questions we had. Once everything was properly addressed, then everyone did their own thing, asking for help when needed until the meeting was over. 
4. What techniques (daily check-ins/scrums, team programming, timelines, Trello use, group design exercises) did you use to mitigate risk? Highlight any changes from last week.
    * There were no changes from last week. I had asked regular updates from everyone whenever we met and made sure to help anyone who needed it. 
5. Describe any difficulties you faced in managing the interactions among your teammates. Were there any team issues that arose? If not, what do you believe is keeping things so constructive?
    * There were no difficulties I faced when managing intereactions among teammates, and I believe this is due to our open and relaxed group dynamics. Mistakes were made, but everyone was very understanding as it was very clear everyone was trying their best. 
6. Describe the most significant obstacle or difficulty your team faced.
    * The biggest obstacle we faced was getting OAuth correctly connected from google, to backend, and then to frontend. A lot of coordination between the members was needed to figure it out.
7. What is your biggest concern as you think ahead to the next phase of the project? To the next sprint? What steps can the team take to reduce your concern?
    * The biggest concern for the next phase of the project is covering the backlog that has accumulated within the mobile branch. Looking forward to the next sprint, the issue would be conceptualizing the new backend implementation and depicting it. 
8. How well did you estimate time during the early part of the phase?  How did your time estimates change as the phase progressed?
    * The time estimate for this sprint was different based on the branch. We had underestimated how long mobile would take (a lot of errors arose), but all other branches took around the same time we had anticipated them to. 
9. What aspects of the project would cause concern for your customer right now, if any?
    * The missing account functionality of the mobile would be the largest concern for me if I were the customer.