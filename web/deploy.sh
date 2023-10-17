#!/usr/bin/env bash

# deploy script for the web front-end

# This file is responsible for preprocessing all TypeScript files, making sure
# all dependencies are up-to-date, and copying all necessary files into the
# web deploy directory.

# This is the resource folder where maven expects to find our files
TARGETFOLDER=../backend/src/main/resources

# This is the folder that we used with the Spark.staticFileLocation command
WEBFOLDERNAME=build

# step 1: make sure we have someplace to put everything.  We will delete the
#         old folder tree, and then make it from scratch
echo "deleting $TARGETFOLDER and creating an empty $TARGETFOLDER/$WEBFOLDERNAME"
rm -rf $TARGETFOLDER
mkdir $TARGETFOLDER
mkdir $WEBFOLDERNAME $TARGETFOLDER/$WEBFOLDERNAME

# there are many more steps to be done.  For now, we will just copy an HTML file
echo "$WEBFOLDERNAME to $TARGETFOLDER"
cp -r $WEBFOLDERNAME $TARGETFOLDER

# step 2: update our npm dependencies
#echo "updating npm dependencies"
#npm update

# step 3: copy javascript and other files from src folder
echo "Copying source files to $TARGETFOLDER/$WEBFOLDERNAME"
cp todo.js $TARGETFOLDER/$WEBFOLDERNAME
cp -r src $TARGETFOLDER/$WEBFOLDERNAME/src

# step 4: copy css files
echo "copying todo.css and app.css to $TARGETFOLDER/$WEBFOLDERNAME"
cp todo.css app.css $TARGETFOLDER/$WEBFOLDERNAME

# step 5: compile TypeScript files
echo "Compiling typescript files"
node_modules/typescript/bin/tsc app.ts --lib "es2015","dom" --target es5 --strict --outFile $TARGETFOLDER/$WEBFOLDERNAME/app.js
# step 7: set up Jasmine
node_modules/typescript/bin/tsc apptest.ts --strict --outFile $TARGETFOLDER/$WEBFOLDERNAME/apptest.js
cp spec_runner.html $TARGETFOLDER/$WEBFOLDERNAME
cp node_modules/jasmine-core/lib/jasmine-core/*.css $TARGETFOLDER/$WEBFOLDERNAME
cp node_modules/jasmine-core/lib/jasmine-core/*.js $TARGETFOLDER/$WEBFOLDERNAME

