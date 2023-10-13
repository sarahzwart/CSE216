
# https://www.youtube.com/watch?v=FJDVKeh7RJI 

# Running the Web Branch Using REACT
* To run the current version, use "npm start" in the root directory "web"
    * Don't mess with anything without running "npm start" to determine if it will cause an error
* You will need to change react-router-dom to a v5 build or statements will just not work
* Create a new build file with "npm run build"
* To run the build version WITHOUT backend functionality, use "serve -s build"
* To run the build version WITH backend functionality, use "sh deploy.sh"

# Something Went Wrong and Web Branch is Gone
 * To create a brand spankin' new typescript app in bitbucket, use "npx create-react-app web --template typescript" (The tutorial is kinda dookie 
   I had to find workarounds for stuff that is outdated from the template. You may have already known but I sure didn't)
* Make sure to remove "eslintConfig" from package.json to avoid it giving you a problem
* You will need to change react-router-dom to a v5 build or statements will just not work