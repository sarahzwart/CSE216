# Phase 1 Sprint 6 - PM Report Template
Use this form to provide your project manager report for Phase 1 Sprint 6.

<!-- PM: When editing this template for your submission, you may remove this section -->
## Instructions
Be as thorough and complete as possible, while being brief/concise. Please give detailed answers.

Submit one report per team. This should be submitted by the designated PM, except in approved circumstances. The report should be created as a markdown file (and converted to pdf if required).

In addition to uploading to coursesite, version control this in the `master` branch under the `docs` folder.

## Team Information [10 points total]

### Team Information:

* Number: 6
* Name: Kamil Gjondla
* Mentor: <Ala Chu, anc224@lehigh.edu>
* Weekly live & synchronous meeting:
    * without mentor: Monday October 16th 9PM, 
    * without mentor: Tuesday October 17th 6:30PM
    * with mentor: Friday October 13th 3:30PM

### Team Roles:

* Project Manger: <Kamil Gjondla, kag624@lehigh.edu>
    * Has this changed from last week (if so, why)?
    No
* Backend developer: <Aiden Astle, apa225@lehigh.edu>
* Admin developer: <Ashley Hirst, ash320@lehigh.edu>
* Web developer: <Nick Madaio, nmm224@lehigh.edu>
* Mobile developer: <Sarah Zwart, scz225@lehigh.edu>

### Essential links for this project:

* Team's Dokku URL(s)
    * <team-margaritavillians.dokku.cse.lehigh.edu>
* Team's software repo (bitbucket)
    * <https://bitbucket.org/cse216-fa23-kag624/cse216-2023fa-team-6/src/master/>
* Team's Trello board
    * <https://trello.com/w/team609992452>


## General questions [15 points total]

1. Did the PM for this week submit this report (If not, why not?)? 

    Yes

2. Has the team been gathering for a weekly, in-person meeting(s)? If not, why not?

    Yes, we usually have our weekly meeting with Ala. Along with this we met several times to work together on this phase.

3. Summarize how well the team met the requirements of this sprint.
    <!-- PM: When editing this template for your submission, you may remove this guidance -->
    * Individually, each member worked very well to get their section of the sprint finished. We had some trouble with this sprint, however, so some of the functionality was not fully achieved. Each member started working on their sections early and worked consistently. No one member did particularly bad in their section. We all worked very well, but some of the issues we were having were too much for us to fix at the moment.

4. Report on each member's progress (sprint and phase activity completion) – "what is the status?"
    * Admin finished all the work that was expected of them in this sprint. 
    * Backend seems to have finished all the work that was expected of them, but the design is not fully set in stone. So, more work may be done next sprint.
    * Web did not fully complete everything expected. Everything is implemented, but there are still bugs within the implementation. For example, the like counter displays "undefined" instead of the like count. This will need to all be resolved by the end of the next sprint.
    * Mobile, like web, did not get everything implemented correctly. They implemented everything needed, but there are also bugs that are hindering the functionality. This will also need to be resolved by the end of the next sprint.

5. Summary of "code review" during which each team member discussed and showed their progress – "how did you confirm the status?"
    <!-- PM: When editing this template for your submission, you may remove this guidance -->
    * Code reviews for all components were done during extra meeting times throughout the sprint. When people needed help we would meet either on zoom or in person to resolve the problems. During these times, we did code reviews. Along with this, we did code reviews before making and submitting our video on the Tuesday it was due.

6. What did you do to encourage the team to be working on phase activities "sooner rather than later"?

    I did not need to do a lot to get everyone to work on their sections early. Most people did that on their own since there was a lot of work to be done. All I did was create the Trello board and encourage everyone to ask for help if they needed it.

7. What did you do to encourage the team to help one another?

    I kept telling my group that we could meet anytime they need help with any problems they had with their code. Becaues of this, we met several times during this sprint to work on the functionalities needed of us.

8. How well is the team communicating?
    <!-- PM: When editing this template for your submission, you may remove this guidance -->
    * Our discussions are almost entirely business discussions. They mostly consist of all of us trying to help troubleshoot each others' problems. All of our members communicated well with each other. We had no problems with people not communicating.

9. Discuss expectations the team has set for one another, if any. Please highlight any changes from last week.
    <!-- PM: When editing this template for your submission, you may remove this guidance -->
    * Our team set our expectations according to the requirements of each branch of the project. Most of the expectations were met, but some were not. Last week, we set expectations to start earlier on our work and we achieved that this week.

10. If anything was especially challenging or unclear, please make sure this is [1] itemized, [2] briefly described, [3] its status reported (resolved or unresolved), and [4] includes critical steps taken to find resolution.
    <!-- PM: When editing this template for your submission, you may remove this guidance -->
    * Challenge: Learning react
        * Status: resolved
        * Description: Our web programmer this week had a lot of trouble learning react due to the tutorial being out of date. They eventually got everything to work but I know they spent a long time trying to troubleshoot everything to make it work.
        * Critical steps taken to find resolution: Redoing the tutorial and spending a lot of time troubleshooting with the team
    * Challenge: Connecting mobile to backend
        * Status: unresolved
        * Description: Our mobile developer had a lot of issues getting the mobile app to connect and communicate with the backend. They had to deal with a lot of bugs that were occuring when trying to communicate with the backend. This is still unresolved, but the mobile developer is working on this regularly to fix it.
        * Critical steps taken to find resolution: Meeting with the rest of the team regularly and regularly working on the issue in their own time
    * Challenge: Implementing likes on web
        * Status: unresolved (mostly)
        * Description: Our web developer had issues implementing likes on the front end. At the moment, they got the like button to work, sending a put request to the backend. However, the like count displayed on the front end is not working and none of us can see why.
        * Critical steps taken to find resolution: We met regularly to help resolve the issues. Along with this, the web developer worked regularly on their own to try to fix the problem.

11. What might you suggest the team or the next PM "start", "stop", or "continue" doing in the next sprint?
    <!-- PM: When editing this template for your submission, you may remove this guidance -->
    * I suggest the next PM does more code reviews in the future. 
    * I suggest the team does less solo coding and works together a little more to allow for the individual components to work together better more fluidly.
    * I suggest the team continues to communicate how we are now, continues to ask to meet to resolve issues we are having, and continues to regularly work on the project instead of putting it off.


## Role reporting [75 points total, 15 points each (teams of 4 get 15 free points)]
Report-out on each role, from the PM perspective.
You may seek input where appropriate, but this is primarily a PM related activity.

### Back-end

1. The backend did well on this sprint. Everything that was expected of the backend was achieved this sprint. There was some confusion with the implementation of likes, but this was all resolved easily. Trello was not used extensively, just to check off the requirements in the Phase 1 rubric.
2. The REST api endpoints include:
    * .get('/messages', (request, response))
    * .get('/messages/:id', (request, response))
    * .post('/messages', (request, response))
    * .put('/messages/:id', (request, response))
    * .delete('/messages/:id', (request, response))
3. The backend code is implemented well. I see no issues with the implementation.
4. For the code review, I mostly just asked the backend developer to walk me through the code they wrote and try to explain how it worked. This allowed for me to get a better idea of how everything fits together.
5. The biggiest issue that came up in the code review was that there were two put endpoints that were identical for liking and unliking a post. This was resolved by getting rid of one of them and redesigning the like functionality.
6. The backend code is organized well. There is a markdown file in the backend folder that explains how to start and stop the server. 
7. The dependencies in the pom.xml are appropriate. The postgresql dependency allows for communication with the sql database. The gson dependency allows for manipulation of json files. The spark dependency allows of the creation of HTTP endpoints.
8. The unit tests of the backend are well implemented. The curl command was used to test the functionality of the spark endpoints.
9. The main technical debt I see from the back-end is the lack of connection between the backend and the web. Some of this was resolved, but changes the the implementation of likes caused the emergence of technical debt.

### Admin

1. Overall, the admin did very well on this sprint. All of the work required was finished very early and without any problems. Again, trello was not used extensively, just to track the requirements in the rubric.
2. The way admin is implemented, a table named "tblData" can be created, dropped, or updated. The rows of this table include the message id, the message title, the message itself, and the number of likes. 
3. The admin code is high quality. It is very similar to that of the tutorials, but with the requirements of this sprint added to it. 
4. Code reviews for admin were similar to that of backend. When the team met, I asked the admin developer to walk me through their code and show me their implementations of the requirements of this sprint.
5. No issues came up for admin during this sprint.
6. The admin app code is appropriately organized, as it is identical to the origranization of the tutorial files.
7. The dependencies in pom.xml were approporiate. The only two dependencies are junit, used for testing, and postgresql, used for accessing the sql database.
8. The unit tests for admin were implemented well. All the methods implemented in admin were tested to make sure they work. Along with this, the character limit was tested.
9. I do not see any technical debt that has arisen in the implementation of the sprint this week. All of the code written is very similar to that of the tutorial, so there is not a lot technical debt.

### Web

1. Web did very well developing their section of the project. The web developer did the react tutorials to learn how to do their section. Unfortunately, they ran into several bugs which were not all resolved. Like the other components, trello was used to track the completion of the sprint requirements. 
2. The front end is modeled after the tutorials we did for front end, including the react tutorial. However, the css was not created (atleast not properly), so it is mostly an HTML page.
3. The web front end code is mostly good. The implementation seems right, but there are some bugs that we are still working through.
4. Just like the other components, the code review for web was done during our meetings. I ran through the code of the web branch and had the web developer run me through it.
5. The biggest issue with the code review was the organization of the files. Since the implementation was based on all the tutorials, there are a lot of files and lots of code that is not being used. These were being used in the tutorials, but are now just crowding the web branch.
6. The web branch is not organized well, as all the files from the tutorials are still in the branch, so it is hard to navigate. However, the added implementation is organized well.
7. The package.json is as to be expected, most dependencies being for react. 
8. The unit tests are well implemented, testing each feature of the web requirements in this sprint.
9. There is a decent amount of technical debt from this sprint. First, as I mentioned before, the file structure is not ideal. In the future, we will need to go through the file structure and refactor it. Along with this, the web's like functionalities are not all properly working.

### Mobile

1. Overall, I think mobile did a good job trying to implement the requirements in this sprint. There were a lot of problems the mobile developer ran into during the sprint. Because of this, some of the implementation was not fully finished. Hopefully, the mobile developer will be able to fix this all by the end of the next sprint. Like the other components, trello was used to track the implementations of the mobile requirements in this sprint.
2. The mobile app involves a continuous scrolling feature that lets you scroll through the messages currently stored in the database. Along with this, the mobile app allows for the liking of messages. Finally, the mobile app allows for users to add messages.
3. The mobile code is implemented pretty well. The implementation is similar to that of the flutter tutorial, but with the extra functionality required.
4. Like all the other components, we did code reviews during our meetings where the mobile developer walked me through their code.
5. The biggest issue that came up in the code reviews was the implementation of the HTTP requests. The mobile developer had several problems with these and with connecting the mobile app to the backend. 
6. The mobile front end file structure is implemented well. It is easy to navigate through the file structure.
7. The dependencies in pubspec.yaml is to be expected. The dependencies include the http and uuid. Along with this, the dependencies include things needed from the flutter tutorials.
8. The unit tests are high quality. They run through all the functionalities that are expected from the mobile front end.
9. At the moment, there is technical debt in the mobile front end, as some of the functionality is not fully implemented correctly.

### Project Management
Self-evaluation of PM performance

1. My team met with our mentor on Friday at 3:30PM. We only met for about 15 minutes, as most of the team was trying to leave campus to go home for the weekend.
2. I think I used Trello pretty effectively. I created a card for each component's functionalities. With that, I added a checklist to each of these cards which highlighted the requirements for the sprint.
3. For our team meetings, we all met either on zoom or in person. The meetings were pretty casual, mostly just involving us helping each other with problems we were having with implementing the requirements of the sprint.
4. I did not use many techniques to mitigate risk. I mostly trusted that each person would do their work, as their grade is on the line. The only thing I actively did to mitigate risk was regularly provide times for meetings if anyone was having trouble with their implementations.
5. I did not run into any difficulties with the members of my team. I believe that having trust in my team members was key to them doing their work.
6. The most significant obstable my team faced was connecting the front ends to the backend. A lot of coordination is needed in order to match the http routes properly.
7. My biggest concern for the future sprints/phases is that we did not even get the implementation for this sprint completely correct. I don't know how we will properly implement future requirements when we don't have this sprint's requirements properly implemented.
8. I believe we estimated the time well when we first started the sprint. However, as we worked through the sprint, we ended up in a time crunch, as a lot of the implementations were not working. 
9. Our current project would cause many concerns for the customer at the moment. Because the mobile and web components are not implemented entirely correctly, there are aspects of the apps that the customer would not be happy with.