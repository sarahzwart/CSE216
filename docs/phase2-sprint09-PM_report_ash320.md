# Phase 2 Sprint 9 - PM Report Template
Use this form to provide your project manager report. 

## Team Information [10 points total]

### Team Information:

* Number: 6
* Name: MargaritaVillians
* Mentor: <Ala, anc224@lehigh.edu>
* Weekly live & synchronous meeting:
    * without mentor: Tuesday (in class), Thursday (in class), Monday (zoom), Tuesday (in person)
    * with mentor: Saturday (zoom)

### Team Roles:

* Project Manger: <Ashley, ash320@lehigh.edu>
    * Has this changed from last week (if so, why)?
        * no it has not changed
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

Screenshot of Trello board:
(should match/support list of backlog and tech debt items in subheadings below)


## General questions [15 points total]

1. Did the PM for this week submit this report (If not, why not?)? 
    * yes I (Ashley) am submitting this week's PM report. I will be turning it in a day late, but I recieved an extension to do so. 
2. Has the team been gathering for a weekly, in-person meeting(s)? If not, why not?
    * Yes we have all been gathering for multiple in-person meetings and multiple remote meetings. 
3. Summarize how well the team met the requirements of this sprint.
    * Everyone worked as much and as quickly as they could to complete what they could of this sprint. There was a lot of collaboration and peer coding where we sat together and worked out bugs and any confusions we had were discussed and cleared up. It is clear by all of the commits and time we spent working together syncronously and asyncronously that everyone had completed all that they could within the week we were given. 
    * The app itself is starting to come together. While OAuth account setup and verification is not set up as of now, everything else is functional. User specific posts, comments, upvotes, and downvotes are all fully functional on the web front end and backend. Web front end also is all set up to accomodate verified accounts once backend is able to implement it into the backend. Mobile needs a bit more time to fully implement all of these changes, but he is very confident he can get it done by the end of the next sprint. 

4. Report on each member's progress (sprint and phase activity completion) – "what is the status?"
    * **Admin:** Admin, Nick, is fully complete with all of the requirements. I know that it is completed to a satusfactory level because I have seen the new datatables that were created and he demoed for me the full functionality of it. I also witnessed him helping others with their code when he could and advising on test cases. 
    * **Backend:** Backend, Kamil, was not able to fully complete his requirements, however, this is reasonable. Backend had by far the most requirements in this sprint. Not only did he have to implement comments, user ids, upvotes, and downvotes, he also had to teach himself the innerworkings of OAuth and successfully implement it in a way where only lehigh accounts are able to use it. OAuth took quite a while to learn, so it was decided that he would focus on successfuly implementing everything else, which was all done very well. He is confident he will be able to fully implement OAuth users by the end of Sprint 10. 
    * **Web:** Web, Sarah, was not able to fully complete his requirements because backend was not OAuth-friendly yet. However, everything else is implemented very well and she has everything set up in such a way that she will easily be able to incorperate OAuth accounts into the frontend when it is ready. 
    * **Mobile:** Mobile, Aiden, was not able to fully complete his requirements for this sprint. This was partially because backend was not fully functional, but also due to te fact that Flutter is a very new coding language for him and a lot of time had to be spent learning the language itself to figure out how to implement what needed to be done. He now has a far better grasp on the syntax and organization and is confident he will get everything done by the end of Sprint 10. He still needs to implement likes and dislikes on the front-end, implement comments, and integrate OAuth users into the app as a whole. 
    * **PM:** PM, I, was able to complete all of my requirements. I consistantly asked for updates, helped explain topics, and helped fix errors almost daily. I also created a thorough trello board for everyone to keep track of their tasks and stay organized. I also det up a collaborative google account for our team and set it up to host OAuth. 


5. Summary of "code review" during which each team member discussed and showed their progress – "how did you confirm the status?"
    * I confirmed everyone's progress multiple times throughout the week by approaching them and asking them to show me what they have, what they need to do, and what they need help with. This was done in person during those meetings and over zoom when we connected that way. It was done as a team so everyone could give their input on any issues that were being faced or see anything in their code that may clash with what they are seeing. These were done "on-demand", but constant updates were given as milestones were reached. 

6. What did you do to encourage the team to be working on phase activities "sooner rather than later"?
    * I had the Trello board set up early and went over everyone's expectations and requirements with them so they knew what they had to do. I also constantly asked for updates throughout the week as reminders and encouragement. 

7. What did you do to encourage the team to help one another?
    * Once again, I had everyone be open and transparent with where they were with their requirements and what they were struggling with. Our group dynamic is one in which we are all free to fail and ask questions. We are all learning a lot, so we are all confused and completely okay with each other not being perfect. 

8. How well is the team communicating?
    *  The team's communication is our greatest strength. Everyone participates in active and constructive communication about their work and other's. Everyone is free to speak their minds and make mistakes without judgement. Also, we do not just talk about the project. We all talked about exams we had last week and helped each other study. We also talk about anything fun happening in our personal lives (like what we did for halloween). This has helped us bond and strengthened our collaboration on the project as a whole. 

9. Discuss expectations the team has set for one another, if any. Please highlight any changes from last week.
    * Expectations have not changed since last sprint. Everyone is still expected to appear at meetings unless they are really unable to do so and to complete their work to the best of their abilities while communicating their progress. 

10. If anything was especially challenging or unclear, please make sure this is [1] itemized, [2] briefly described, [3] its status reported (resolved or unresolved), and [4] includes critical steps taken to find resolution.
    1. Challenge: OAuth Understanding
        * Status: resolved
        * Description: We were unsure how OAuth was to be implemented on the front end (ie. how it should be displayed)
        * Critical steps taken to find resolution: We spoke with our Mentor and she explained that it should just be a button that takes you to  OAuth and then it will send you back to the dokku but as an authorized user. 
    2. Challenge: Branch Compatibility
        * Status: Resolved
        * Description: Front end was having issues not being able to send information to the database. We assumed it was an issue on their end or backend, but after further examination it turned out there was a slight change with the table in the database itself so the right things were not being sent to exactly the right place.
        * Critical steps taken to find resolution: We looked at every branch to try to find the issue and eventually found that the main table's name was changed, thus messing up it's connection to the backend end rendering the frontends unable to make changes. Once this was found it was decided that every little change made to a databse was to be communicated immediately to avoid this confusion in the future. 

11. What might you suggest the team or the next PM "start", "stop", or "continue" doing in the next sprint?
    * Start: We should start fully communicating all changes being made to aspects in a branch that directly affects another branch's work. 

    * Stop: We should stop trying to get everything done at once and setting unachievable expectations for ourselves. 

    * Continue: As a group we should definatly continue with the strong communication we have. It has made a great working environment and I believe it is what is propelling our work forward.

## Role reporting [75 points total, 15 points each (teams of 4 get 15 free points)]
Report-out on each role, from the PM perspective.
You may seek input where appropriate, but this is primarily a PM related activity.

### Back-end

1. Overall evaluation of back-end development (how was the process? was Trello used appropriately? how were tasks created? how was completion of tasks verified?)
    * Kamil carried out his requirements as backend very well considering all that was asked. He had by far the most requirements for this sprint and what he was asked to do was very difficult. He made the very smart decision to focus on everything except OAuth for this sprint, and that choice paid off. He got everything else done and it all worked perfectly on his end. He is now fully ready to implement OAuth as he also understand it much better than he did before. He made good use of the Trello board to keep track of his progress and kept the team updated with all new functionality that was ready when it was ready. 
2. List your back-end's REST API endpoints
    * get(“/messages”) //Gets all messages
    * get(“/messages/<insert message id>”) //Gets a specific message
    * post(“/messages”) //Adds a new message
    * put(“/messages/<insert message id>”) //Likes or dislikes depending on what’s passed into the body
    * delete(“/messages/<insert message id>”) //Delete a specific message
    * get(“/comments/<insert message id>”) //Gets all comments from a specified message
    * get(“/comments”) //Gets all comments from all messages
    * put(“/comments/<insert comment id>”) //Edits a comment
    * post(“/comments”) //Adds a new comment to a specific post

3. Assess the quality of the back-end code
    * The backend code that is there is functional and easy to understand. 
4. Describe the code review process you employed for the back-end
    * I met with Kamil individually during a group meeting and had him walk me through the code and explain any issues that came up and what is still needed to be fixed. 
5. What was the biggest issue that came up in code review of the back-end server?
    * The biggest issue that came up in the code review was that the back-end did not implement Google OAuth. 
6. Is the back-end code appropriately organized into files / classes / packages?
    * All backend code is appropriately organized in a way where its easy to follow and keeps everything in order. 
7. Are the dependencies in the `pom.xml` file appropriate? Were there any unexpected dependencies added to the program?
    * All of the dependencies within the pom.xml are appropriate. 
8. Evaluate the quality of the unit tests for the back-end
    * The tests for backend are manual using curl commands to test the functionality. While it is not the most efficient way to test, it gets the job done and is a reasonable test method to accomodate the lack of time Kamil faced with all that was asked of him. Although the Curl commands did not work in the video we filmed initially, it was later discovered it was simply an issue with Kamil's laptop at the time and a new video of the curl commands working was uploading separetly. All this being said, the tests were done very well. 
9. Describe any technical debt you see in the back-end
    * There are some unnesicary routes still available in backend such as delete("/messages/:id") which are not needed as a functionality anymore. 

### Admin

1. Overall evaluation of admin app development (how was the process? was Trello used appropriately? how were tasks created? how was completion of tasks verified?)
    * Nick very successfully completed the development of the admin app. He get everything that affected others done first, and worked at a steady pace to ensure everything was complete on time. Tasks were checked off of the checklist on trello as they were completed, and a progress report was given at each meeting. 
2. Describe the tables created by the admin app
    * There was the original table for the posts, a table for the user's information, and a table for comments. The tables were linked together by including sections in the message table and comments table for the user's id to show who made the post, and a section in the comments table for the message id to coordinate what post that comment is for. 
3. Assess the quality of the admin code
    * Nick's code is easy to follow and successfully completes all of the requirements assigned to Admin. 
4. Describe the code review process you employed for the admin app
    * I met with Nick individually during a group meeting and had him walk me through the code and explain any issues that came up. 
5. What was the biggest issue that came up in code review of the admin app?
    * The biggest issue that arose was the large size of the database.java file. It could maybe be organized a bit more clearly in order to fully differentiate the methods intended for each table from one another. 
6. Is the admin app code appropriately organized into files / classes / packages?
    * The code in the admin branch is very well organized into appropriately names folders, making it easy to follow, and it is simple to run due to it's use of packages. 
7. Are the dependencies in the `pom.xml` file appropriate? Were there any unexpected dependencies added to the program?
    * All of the dependencies within the pom.xml are appropriate. 
8. Evaluate the quality of the unit tests for the admin app
    * Admin's unit tests are done very efficiently. The unit tests are all confinded within one program, organized by general topic, and are all very concise. 
9. Describe any technical debt you see in the admin app
    * The database could use some cleaning up, possibly even deleting duplicate methods intended for different tables to all use the same method with a new parameter indicating which table is being altered.

### Web

1. Overall evaluation of Web development (how was the process? was Trello used appropriately? how were tasks created? how was completion of tasks verified?)
    * Sarah made very steady progress throughout the week with the web development. She kept her trello card updated as task were completed and gave a progress report each meeting. She had many issues, but asked for help during each meeting and was eventually able to successfully complete everything she was able to with the backend not implementing OAuth.
2. Describe the different models and other templates used to provide the web front-end's user interface
    * Web front end implemented Angular during the last phase, however, Sarah could not get it to work on her laptop (and had past experience with React), so she eventually decided to change the branch to use REACT to create the web front end's interface. 
3. Assess the quality of the Web front-end code
    * The web front-end works almost perfectly with everything it has implemented. Everything works as expected except the upvotes and downvotes, which require a reload of the window in order to see the change in votes. The code itself is well organized and efficiently written. I especially like how the whole thing is already set up in a way that is ready to easily accomodate OAuth users.
4. Describe the code review process you employed for the Web front-end
    * I met with Sarah individually during a group meeting and had her walk me through the code and explain any issues that came up. 
5. What was the biggest issue that came up in code review of the Web front-end?
    * The biggest issue is that the webpage must be reloaded in order to see any changes that were made in real time on the webpage. 
6. Is the Web front-end code appropriately organized into files / classes / packages?
    * Web front-end was nicely organized into approriate files and folders making it efficient and easy to follow and find code fragments. 
7. Are the dependencies in the `package.json` file appropriate? Were there any unexpected dependencies added to the program?
    * All of the dependencies within the package.json are appropriate. 
8. Evaluate the quality of the unit tests for the Web front-end
    * The web front end used both automated and manual tests. The maunal tests involved Sarah going through the webpage and showing the functionality of everything, while the automated tests were best react unit tests ensuring that the basic components worked as they were expected. Both worked very well in ensuring and displaying the successfulness of the code. 
9. Describe any technical debt you see in the Web front-end
    * There are hard-coded accounts that were created to test the user settings while OAuth was not set up. These will need to be removed once real accounts can be created. 

### Mobile

1. Overall evaluation of Mobile development (how was the process? was Trello used appropriately? how were tasks created? how was completion of tasks verified?)
    * Aiden worked at a good pace to try to get everything completed. Although Trello was not updated as things were completed, he gave updates in our meetings on his progress and showed the current functionality. He did, however, use the tasks outlined on the trello board as a guide for what needed to be done. 
2. Describe the activities that comprise the Mobile app
    * Mobile app has the ability to add a post, view older posts, and like posts that are stored and sent directly to the database.
3. Assess the quality of the Mobile code
    * What is written within the mobile code is very easy to follow and concise. However, there is a lot of backlog that must be completed in the next sprint. A comment button is present, but has no functionality. There is still only a like button, but no dislike button (and the like button has no indication of having been clicked when it is). Finally, there is no user functionality within the app itself, but there is some parts of the code that is accomodating to users (like passing a user id with the message itself). 
4. Describe the code review process you employed for the Mobile front-end
    * I met with Aiden individually during a group meeting and had him walk me through the code and explain any issues that came up.
5. What was the biggest issue that came up in code review of the Mobile front-end?
    * The biggest issue that came up was the large ampunt of backlog mobile had accumulated. However, this was explained by Aiden not being very familiar with Flutter, and having a lot of trouble finding correct syntax to implement the changes and fix the errors he as getting. 
6. Is the Mobile front-end code appropriately organized into files / classes / packages?
    * The Mobile front-end is appropriately organized into folders. There are a lot of files that are included so it is a bit difficult to find where a specific code is if you are unfamiliar with it, however, it is definatly possible to find code by following appropriate folder and file names. 
7. Are the dependencies in the `pubspec.yaml` (or build.gradle) file appropriate? Were there any unexpected dependencies added to the program?
    * All of the dependencies within the pubspec.yaml are appropriate. 
8. Evaluate the quality of the unit tests for the Mobile front-end here
    * The mobile front-end used both automated and manual tests to show and test functionality. The manual tests involved Aiden going through the app and demoing each functionality. The automated tests are concise and tests  actual results against expected results automatically. 
9. Describe any technical debt you see in the Mobile front-end here
    * There are a lot of different folders for different uses that we are simply not utilizing and could be deleted or condensed (a lot of the files are very similar). 

### Project Management
Self-evaluation of PM performance

1. When did your team meet with your mentor, and for how long?
    * We met with our mentor on Saturday at 5PM for around 30 minutes. We were forced to move our typical meeting time (Thursdays at 5PM) because three of our five members had a common hour exam at that time. 
2. Describe your use of Trello.  Did you have too much detail?  Too little?  Just enough? Did you implement policies around its use (if so, what were they?)?
    * I made each person their own card with a checklist of their individual requirements on it. I believe I put just the right amount of detail into it, and there were no real policies surrounding it's use; the only rule was that you should not touch a card that is meant for someone else. 
3. How did you conduct team meetings?  How did your team interact outside of these meetings?
    * I conducted team meetings very casually. I made sure we spoke about other things outside of the project during at least one of our meetings. However, I did ask everyone for a progress report during each meeting and we all participated in helping to figure out any issues or bugs anyone came across. Other than that they was used as a group work time where everyone did their own thing, helped each other, and coordinated their codes with others. 
4. What techniques (daily check-ins/scrums, team programming, timelines, Trello use, group design exercises) did you use to mitigate risk? Highlight any changes from last week.
    * Trello was used to track all of the things that needed to get done for each role. Slack was used to provide reminders on deadlines, ask quick questions, and to confirm/organize meetings. Team programming is also used to fix bugs and implement complex elements when help is needed. There were no big changes from last week. 
5. Describe any difficulties you faced in managing the interactions among your teammates. Were there any team issues that arose? If not, what do you believe is keeping things so constructive?
    * There were no difficulties I faced when managing intereactions among teammates, and I believe this is due to our open and relaxed group dynamics. We are all open to each other's ideas and questions so there are seldom issues. 
6. Describe the most significant obstacle or difficulty your team faced.
    * The most significant obstacle we faced was coordinating all of the changes being made on all side of the project and ensuring that it could all work cohesively together. When a small change was made to the database or some other aspect, all cooralating side would need to make the same change to ensure it would work together. 
7. What is your biggest concern as you think ahead to the next phase of the project? To the next sprint?
    * The biggest concern for the next sprint is ensuring that we get all of the backlog items we have accumulated completed (most importantly implementing OAuth). Thinking ahead to the next sprint our biggest concern is finalizing who will be assigned to which role, and starting to understand the code that aleady exists for that role. 
8. How well did you estimate time during the early part of the phase?  How did your time estimates change as the phase progressed?
    * I estimated time pretty well in the early part of the phase, however, I did underestimated how much time OAuth would take to fully understand and implement. As this sprint began, I realized we would be having a lot of issues and it would take very long to do. 
9. What aspects of the project would cause concern for your customer right now, if any?
    * The biggest concern I would have is that there is currently no way to safely use the app as a Google OAuth user. 