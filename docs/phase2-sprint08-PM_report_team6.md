# Phase 2 Sprint 8 - PM Report Template
Use this form to provide your project manager report. 

## Team Information

### Team Information:

* Number: 6
* Name: MargaritaVillians
* Mentor: <Ala_Chua, anc224@lehigh.edu>
* Weekly live & synchronous meeting:
    * without mentor: 1
    * with mentor: 1

### Team Roles:

* Project Manger: <Ashley, ash320@lehigh.edu>
    * Has this changed from last week (if so, why)?
        * Yes It has changed because Sprint 8 started a new phase (phase2), and the PM switched with each new phase
* Backend developer: <Kamil, kag624@lehigh.edu>
* Admin developer: <Nick, nmm224@lehigh.edu>
* Web developer: <Sarah, scz225@lehigh.edu>
* Mobile developer: <Aiden, apa225@lehigh.edu>

### Essential links for this project:

* Team's Dokku URL(s)
    * [Dokku] (https://team-margaritavillians.dokku.cse.lehigh.edu)
* Team's software repo (bitbucket)
    * [Git Repo](https://bitbucket.org/cse216-fa23-kag624/cse216-2023fa-team-6/src/master/)
* Team's Trello board
    * [Trello] (https://trello.com/b/NYiEMqdI/phase-2)


## General questions

1. Did the PM for this week submit this report (If not, why not?)? 
    * Yes I, Ashley, the PM is submitting this report. 
2. Has the team been gathering for a weekly, in-person meeting(s)? If not, why not?
    * Yes we me at our usual meeting time, Thursday 5-6PM. All members were present for this in-person meeting
3. Summarize how well the team met the requirements of this sprint.
    * Our team completely met the requirements of this sprint. All of the artifacts were properly created and reviewed by each other, and all of the work was evenly and clearly divided. I, Ashley, did the new user stories and their tests. Sarah did the mock web and mobile drawings and the new state machine drawings. Nick did the updated unit tests and the updated entity relationship diagram of the database tables and feilds. Finally, Kamil did the updated list of routes, their purposes, and format of passed objects and the list of all backlog items. 
    * Everyone also got familiar with their new roles. They looked at all of the current code and learned from the member who previously had that role how to run and work with the code as a whole. Everyone also began the implementation of next sprint's requirement. This was mainly done during class time, but questions were also answered over slack throughout the week as issues arose. 
4. Report on each member's progress (sprint and phase activity completion) – "what is the status?"
    * The status for the sprint is complete, as all of the artifacts were created and implementation was tentatively started. I know everyone completed their work because all artifacts were uploaded to the repository, put onto the Phase 2 README.md doc, and reviewed by another member to ensure quality work. However, the phase requirements are not complete as of now. As implementation week begins we begin to make all of the changes including adding a comment section (with editable comments), removing the ability for users to delete and edit old posts, running web front-end from the remote Dokku-backend, and, most importantly, implementing OAuth account verification to create secure accounts for users. These are being overcome by starting early on first understanding what needs to be done to complete these tasks, understanding the code that is currently in each branch, and finally implementing the new elements to the branch without losing any current functionality. Each team member is currently on track to complete these requirements by the next deadline (Nov 7), but this could very likly change as more challenges arise. 


5. Summary of "code review" during which each team member discussed and showed their progress – "how did you confirm the status?"
    * Through the sprint I checked in on each member and their branch on-demand during in person group meetings and on Sunday before we filmed our video to ensure everything was satisfactory. Because this was a design week, not much coding was done. The code reviews this week were mainly each member showing that they fully (or mostly) understood the code that was written by the previous member in that role, and it was ensured they knew how to run their branch correctly. The only outliers in this were Sarah and Kamil in web front-end and backend. The angular framework used by Nick previously, was not working on Sarah's laptop (and had multiple issues overall), so Sarah had decided to rework the frontend with REACT, as she has previous experience with it. This decision was made just a few days ago, so no code review with the new framework has been done (it was done with the angular framework which she showed she could run on Nick's laptop). Kamil is having a lot of issues with the dokku-backend as it was not fully functional as of last sprint, but everything that is there is able to be explained. 

6. What did you do to encourage the team to be working on phase activities "sooner rather than later"?
    * Firstly I created the new trello board and added a card for each of the artifacts that needed to be done/updated very early in the week. I let everyone know this was done, and by the end of our group meeting on Thursday we had equally divided the work (each chosing two cards to do). Then, I checked in on Saturday asking how far everone had gotten to serve as a reminder of the tasks that needed to be done. I ensured everyone was done with their artifacts by midday sunday to ensure filming would go smoothly. 

7. What did you do to encourage the team to help one another?
    * I made sure to keep communication open throughout the week to ensure that if anyone had any questions they could ask them. If someone had a question I would help as much as possible and encourage those who knew more about the topic than I did to hop in and help where they could as well. 
    * I also ensured that each person met with the person who previously had their role to explain everything and ensure a smooth transition of responsibilities. 

8. How well is the team communicating?
    * The group communication overall is very strong; everyone communicates openly and frequently. We discuss every aspect of the project such as where each member is progress wise, what anyone is confused about, and if someone thinks they will take a bit longer to do their work because of outside factors. However, we do not only talk about buisness, we are also talking as friends within the group. We talk about other classes we are in, our pets, and much more. We also joke quite a bit and the atmosphere of our meetings is very light and makes it okay to ask questions and make mistakes. This has definatly helped our productivity overall. 

9. Discuss expectations the team has set for one another, if any. Please highlight any changes from last week.
    * We have set expectations that we will communicate frequently, ask for help if it is needed, help others when you are able, and attend all meetings. It is also expected that everyone gets their work done to the best of their ability and not wait till the last minute so that help can be gotten if needed. 

10. If anything was especially challenging or unclear, please make sure this is [1] itemized, [2] briefly described, [3] its status reported (resolved or unresolved), and [4] includes critical steps taken to find resolution.
    [1] Challenge: Mobile Branch continuously being added into other branches
        * Status: resolved
        * Despription: There was some issues where changes made in the mobile branch kept adding to all of the other branches. We are completely unsure why this occured, but whenever someone would try to commit a change in their own branch, they would need to continuously add files that had been changed in the mobile folder. Even if the mobile folder was deleted from that branch, it would show back up after some time. 
        * Critical steps taken to find solution: First, we ensured that this was a repository-wide issue (and not just an issue one person was having). Then Sarah, who was working in the mobile branch, deleted her local copy of the repository and recloned it. This seemed to have solved that issue. 
    [2] Challenge: Comment Section
        * Status: resolved
        * Despription: There was some confusion and debate on how the comments would be implemented.
        * Critical steps taken to find solution: We spoke with our mentor who explained the basics of the comment section, and we all came to the conclusion that the best way to implement it would be creating a new database for comments and assigning each a specific id to link it to a specific post.
    


## Project Management
Self-evaluation of PM performance

0. When did your team meet with your mentor, and for how long?
    * We met with our mentor on Thursday Oct 26 for about 40 minutes.
1. Describe your use of Trello.  Did you have too much detail?  Too little?  Just enough? Did you implement policies around its use (if so, what were they?)?
    * My trello board so far only has the artifacts that had to be done listed. I believe it was just the right amount of detail added and I even added a place for each person to claim a card (everyone was responsible for completing two cards). The only policy there was regarding the trello was that once a card had been claimed no one else should be moving or adding anything to it except me or that person. 
2. How did you conduct team meetings?  How did your team interact outside of these meetings?
    * I conducted team meetings very casually. We would begin by talking about any exams we had coming up or anything else happening outside of the class. We then begin working and we go around to each team member and have them give an explaination of where they are in the sprint and what they are having trouble with. The rest of the time is spent working on the individual branches and helping each other when needed. The next meeting time is then confirmed before the meeting ends. Outside of these meetings we regularly communicate via the slack channel and in person about the project and everyday things. 
3. What techniques (daily check-ins/scrums, team programming, timelines, Trello use, slack use, group design exercises) did you use to mitigate risk? Highlight any changes from last week.
    * Trello was used to track all of the things that needed to get done and to officially assign each member to a task. Slack was used to provide reminders on deadlines, ask quick questions, and to confirm meetings. Team programming is also often used to fix bugs and implement complex elements when help is needed. 
4. Describe any difficulties you faced in managing the interactions among your teammates. Were there any team issues that arose? If not, what do you believe is keeping thins so constructive?
    * There have been no difficulties managing interactions among teammates, nor have there been any issues that have arose. I believe this is due to our good and open communication. We are all open to ask questions and make mistakes which has lead to everyone fully communicating any issues they have. 
5. What is your biggest concern as you think ahead to the next sprint?

6. Describe the most significant obstacle or difficulty your team faced.
    * The most significant obstacle our team faced during this sprint was making the switch to doing a different role. Not only was is difficult to interpret code that someone else had begun, but everyone had become very attached to their section of the project and had a hard time reliquishing it to someone else. This was an example of how our thorough communication avoided any arguments or large disagreements. 
7. What might you suggest the team or the next PM "start", "stop", or "continue" doing in the next sprint?
    * Start: As a PM I think next sprint I should start doing daily checkins to keep completely up to date on where everyone is on the project and ensure help can be given as quickly as possible if needed. 
    * Stop: We should stop waiting until we are filming to meet and ensure everything is working. More than once we have met with the intention of filming, but ended up spending around an hour fixing last minute issues that were had. For example, this week we had issues adding our artifacts to the README.md and it took a while to get that all figured out before we were able to actually film as we had planned. 
    * Continue: As a group we should definatly continue with the strong communication we have. It has made a great working environment and I believe it is what is propelling our work forward. 

8. How well did you estimate time during the early part of the sprint? How did your time estimates change as the sprint progressed?
    * I feel I estimated the time this sprint would take relatively well. Having done a design week before, we all had an idea of what to do and how long it would take. Although, the time estimate for smooth transitions into new roles was lengthened when Sarah made the decission to change the frontend framework and doing so will take a significant amount of time that was previously unaccounted for. 
9. What aspects of the project would cause concern for your customer right now, if any?
    * The main thing that would concern me is that the front end is being completely reworked and the dokku-backend is not connecting to any outside entities yet. 