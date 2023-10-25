# Phase 1' Sprint 7 - PM Report Template
Use this form to provide your project manager report for Phase 1' (Prime).

<!-- PM: When editing this template for your submission, you may remove this section -->
## Instructions
Be as thorough and complete as possible, while being brief/concise. Please give detailed answers.

Submit one report per team. This should be submitted by the designated PM, except in approved circumstances. The report should be created as a markdown file (and converted to pdf if required).

In addition to uploading to coursesite, version control this in the `master` branch under the `docs` folder.

## Team Information [10 points total]

### Team Information:

* Number: 6
* Name: MargaritaVillians
* Mentor: <Ala Chua, anc224@lehigh.edu>
* Weekly live & synchronous meeting:
    * without mentor: Tuesday October 24th, during class time
    * without mentor: Sunday October 22nd, 9PM
    * with mentor: Thursday October 19th, 5PM

### Team Roles:

* Project Manger: <Kamil Gjondla, kag624@lehigh.edu>
    * Has this changed from last week (if so, why)? No
* Backend developer: <Aiden Astle, apa225@lehigh.edu>
* Admin developer: <Ashley Hirst, ash320@lehigh.edu>
* Web developer: <Nick Madaio, nmm224@lehigh.edu>
* Mobile developer: <Sarah Zwart, scz225@lehigh.edu>

### Essential links for this project:

* Team's Dokku URL(s)
    * team-margaritavillians.dokku.cse.lehigh.edu
* Team's software repo (bitbucket)
    * <https://bitbucket.org/cse216-fa23-kag624/cse216-2023fa-team-6/src/master/>
* Team's Trello board
    * <https://trello.com/b/TdzCpzDz/phase-1>


## Beginning of Phase 1' [20 points total]
Report out on the Phase 1 backlog and any technical debt accrued during Phase 1.

1. What required Phase 1 functionality was not implemented and why? 

    * Mobile
        1. Most functionality, including adding and liking messages
    * Web
        1. Likes, both displaying and adding
        1. File structure
    * Backend
        1. Lack of connectivity with web

2. What technical debt did the team accrue during Phase 1?

    * Mobile
        1. Lots of the functionality was not implemented correctly 
    * Web
        1. Some of the routes were not used correctly
        1. File structure not ideal with implementation of react
    * Backend
        1. Does not connect with web



## End of Phase 1' [20 points total]
Report out on the Phase 1' backlog as it stands at the conclusion of Phase 1'.

1. What required Phase 1 functionality still has not been implemented or is not operating properly and why?

    * Backend
        1. Connect Dokku backend to database

2. What technical debt remains?

    * Web
        1. Unnecessary node modules
        1. No automatic refresh when liking and deleting posts
        1. Unit Tests
    * Backend
        1. Data not being pushed to database
        2. Extra unnecessary functionality for table interactions
    * Admin
        1. Extra unnecessary functionality for table interactions
    * Mobile
        1. Unlike implementation
        1. Unit tests

3. If there was any remaining Phase 1 functionality that needed to be implemented in Phase 1', what did the PM do to assist in the effort of getting this functionality implemented and operating properly?

    * I did not need to do a lot to encourage the implementation of functionality, as everyone wanted to get everything done for a good grade. However, I did help everyone else by encouraging meetings to help each other with implementation.

4. Describe how the team worked together in Phase 1'. Were all members engaged? Was the work started early in the week or was there significant procrastination?

    * Most of the work done in Phase 1' was just finishing what we had not finished in Phase 1, so everone started their work early. Along with this, everyone was working on their components regularly. Anyone that did not have anything they needed to add to their own component helped everyone else out with what they were working on.

5. What might you suggest the team or the next PM "start", "stop", or "continue" doing in the next Phase?

    * Start: Using things like Trello more.
    * Stop: I have nothing I suggest we stop doing.
    * Continue: Encouraging communication and allowing extra meetings for anyone having issues

## Role reporting [50 points total]
Report-out on each team members' activity, from the PM perspective (you may seek input where appropriate, but this is primarily a PM related activity).
**In general, when answering the below you should highlight any changes from last week.**

### Back-end
What did the back-end developer do during Phase 1'?
1. Overall evaluation of back-end development (how was the process? was Trello used appropriately? how were tasks created? how was completion of tasks verified?)

* The backend developer spent a lot of time working with Dokku this sprint in order to get the front ends to work with the backend. Trello was used appropriately. The tasks were tracked based on what was on the rubric.

2. List your back-end's REST API endpoints

* Routes:
    1. get('/')
    1. get('/messages')
    1. get('/messages/:id')
    1. post('/messages')
    1. put('/messages/:id')
    1. delete('/messages/:id')

3. Assess the quality of the back-end code

* The quality of the backend code is pretty good. It is similar to the code used in the tutorials, since that is what we based everything on.

4. Describe the code review process you employed for the back-end

* Anytime a pull request was submitted, everything changed was reviewed with me. Along with this, anytime we had problems with anything in the backend, code reviews were used as a sort of rubber duck debugging.

5. What was the biggest issue that came up in code review of the back-end server?

* The biggest issue that came up is the fact that the backend still uses DataStore from the tutorials instead of the database.

6. Is the back-end code appropriately organized into files / classes / packages?

* The backend files are appropriately organized.

7. Are the dependencies in the `pom.xml` file appropriate? Were there any unexpected dependencies added to the program?

* The dependencies are appropriate in the backend's pom.xml file. 

8. Evaluate the quality of the unit tests for the back-end

* The unit tests could be refined in the future, as curl is currently being used manually to test the functionalities of the backend. In the future, I would like the unit tests to be automated.

9. Describe any technical debt you see in the back-end

* The main technical debt currently in the backend is the lack of connectivity between the dokku server and the database. The database url is configured, but the code needs to be tweeked to use the database.

### Admin
What did the admin front-end developer do during Phase 1'?
1. Overall evaluation of admin app development (how was the process? was Trello used appropriately? how were tasks created? how was completion of tasks verified?)

* The admin's development went very well this phase. Ashley had no problems when developing the admin component. Trello was used effectively, and tasks were created based on the rubric requirements.

2. Describe the tables created by the admin app

* There is one main table created by the admin. This table stores the message id, message title, the message itself, and the number of likes.

3. Assess the quality of the admin code

* The admin code is good quality, as it is very similar to the tutorials for admin. The main differences between the current admin implementation and the implementation in the tutorials are the required functionalities that were added.

4. Describe the code review process you employed for the admin app

* Like backend, code reviews were done with admin whenever pull requests were submitted. Along with this, anytime there were problems implementing requirements, code reviews were done.

5. What was the biggest issue that came up in code review of the admin app?

* There were no issues that came up in the code reviews this sprint.

6. Is the admin app code appropriately organized into files / classes / packages?

* The admin folder structure is organized very well.

7. Are the dependencies in the `pom.xml` file appropriate? Were there any unexpected dependencies added to the program?

* The dependencies in the pom.xml file are appropriate, as the only dependencies that are there are junit and postgresql. 

8. Evaluate the quality of the unit tests for the admin app

* The unit tests for admin are good, as they test all the implementations required by admin.

9. Describe any technical debt you see in the admin app

* The only technical debt I see in the admin app is the implementation of unnecessary table functionality.

### Web
What did the web front-end developer do during Phase 1'?
1. Overall evaluation of Web development (how was the process? was Trello used appropriately? how were tasks created? how was completion of tasks verified?)

* The web developer spent a lot of this sprint getting the functionality from last sprint actually working. As a team, we agreed to switch to angular if this web developer thought it was easier than react. So, this sprint the web developer also switched our web component to angular.

2. Describe the different models and other templates used to provide the web front-end's user interface

* The web front end user interface is very barebones. Since the web developer switched from react to angular, there was not a lot of time to mess with the user interface. Instead, the time was used to make sure the functionality is all working.

3. Assess the quality of the Web front-end code

* The quality of the web front-end code is very good. The only issues I see with it are the lack of css files to customize the user interface. 

4. Describe the code review process you employed for the Web front-end

* Like the other components, code reviews for web were done during pull requests and whenever the web developer was having issues with his implementation.

5. What was the biggest issue that came up in code review of the Web front-end?

* The biggest issue that came up was the implementation of likes. Nick was having issues implementing likes for a while so this was something that came up several times.

6. Is the Web front-end code appropriately organized into files / classes / packages?

* The file organization is much better this sprint than it was last sprint. Since Nick switched to angular, a lot of the unnecessary files have been removed.

7. Are the dependencies in the `package.json` file appropriate? Were there any unexpected dependencies added to the program?

    * The dependencies are appropriate, mostly all being dependencies required by angular.

8. Evaluate the quality of the unit tests for the Web front-end 

* Since the web switched from react to angular, their tests were removed. So, the unit tests are something that need to be added again.

9. Describe any technical debt you see in the Web front-end

* There are a couple things that need to be addressed in the future. As stated before, tests need to be readded, auto refresh needs to be implemented, user interface needs to be improved, etc.

### Mobile
What did the mobile front-end developer do during Phase 1'?
1. Overall evaluation of Mobile development (how was the process? was Trello used appropriately? how were tasks created? how was completion of tasks verified?)

* Mobile did very well this sprint working on the functionality that was not fully implemented last sprint. There were lots of issues that the mobile developer was having last sprint. This sprint they fixed them all.

2. Describe the activities that comprise the Mobile app

* The mobile app allows a user to post a new message, like messages, and scroll through all messages.

3. Assess the quality of the Mobile code

* The mobile code is well implemented, as the mobile developer essentially started from scratch after having issues with the implementation last sprint. So, most of the mobile code is very well implemented.

4. Describe the code review process you employed for the Mobile front-end

* Like all the other components, mobile code reviews were done during pull requests and whenever mobile was having issues with implementation.

5. What was the biggest issue that came up in code review of the Mobile front-end?

* The biggest issue that came up was with the implementation of the get request. During the code review, we realized that the get request in mobile was not getting the message data array, but instead an object that contained the message data array in it. This was easily fixed once the issue was found.

6. Is the Mobile front-end code appropriately organized into files / classes / packages?

* The mobile file structure is well organized.

7. Are the dependencies in the `pubspec.yaml` (or build.gradle) file appropriate? Were there any unexpected dependencies added to the program?

* Most of the dependencies in pubspec.yaml are appropriate with the exception of uuid, as this is not being used.

8. Evaluate the quality of the unit tests for the Mobile front-end here

* The tests are out of date, as they were created for last sprint.

9. Describe any technical debt you see in the Mobile front-end here

* The only technical debt I see with mobile is the implementation of unliking messages and having to fix the tests.

### Project Management
Self-evaluation of PM performance
1. When did your team meet with your mentor, and for how long?

* Our team met with our mentor during our usual Thursday 5PM time. We met for about 45 minutes.

2. Describe your use of Trello.  Did you have too much detail?  Too little?  Just enough? Did you implement policies around its use (if so, what were they?)?

* Trello was not used this sprint as much as it usually is. Since this sprint we focused on fixing the implementations of last sprint, updating the trello board was not really necessary. Only a few things were changed in trello to account for some additional requirements this sprint.

3. How did you conduct team meetings?  How did your team interact outside of these meetings?

* Team meetings were agreed upon by all the team members and were used whenever members were having issues implementing their components. Outside of meetings, team members messaged in slack and in a group chat.

4. What techniques (daily check-ins/scrums, team programming, timelines, Trello use, group design exercises) did you use to mitigate risk? Highlight any changes from last week.

* The only technique I really used to mitigate risk was hosting frequent meetings whenever team members were having issues with their implementations.

5. Describe any difficulties you faced in managing the interactions among your teammates. Were there any team issues that arose? If not, what do you believe is keeping things so constructive?

* No issues arose with the interactions between team members. I believe what is keeping things constructive is the trust between members to get their own work done.

6. Describe the most significant obstacle or difficulty your team faced.

* The biggest difficulty our team faced were individual implementations of our components. Team members had issues with their own implementation, but no members had issues with each other.

7. What is your biggest concern as you think ahead to the next phase of the project? To the next sprint? What steps can the team take to reduce your concern?

* I do not see any major concerns thinking ahead to the next phase. I had concerns in the past that implementing things on our own would be incredibly hard, but this phase has killed those concerns.

8. How well did you estimate time during the early part of the phase?  How did your time estimates change as the phase progressed?

* We estimated the time very well this sprint. Apart from the implementation of backend to use the database, everyone got their components working early.

9. What aspects of the project would cause concern for your customer right now, if any?

* Right now the only concerns a customer would have is the lack of connectivity of the backend to the database, the minimal user interface on the web backend, and the lack of implementation of unliking messages.