# Student Tracker

Student Tracker is a simple web-application that uses HTML, Javascript, CSS & Bootstrap along with Java SpringBoot and a PostgreSQL database. The app is hosted on Render. 

This is a dynamic webpage used for assignment purposes. 

## Web-application Usage

The Student Tracker app can be accessed via the homepage link from Render below. As Render auto-sleeps during periods of inactivity, please give the webpage some time to load.

[Render Webapp Link](https://student-tracker-y79x.onrender.com/users/view)

## Features

Users are able to:
* Add new students to the student tracker and see all students on display
* Edit attributes of existing students 
* Delete existing students via student UID
* View a visual representation of each student in the form of colored rectangles by UID
* See all students and their attributes in a table on the homepage
* Go back to homepage via back button

Student Attributes:
* Name, Email, Password
* Height, Weight, Haircolor
* GPA, UID

## Feature Bugs

Deleting Students
* Display page is set to automatically refresh with student deleted but may need the user to manually refresh on PC.

Viewing and Deleting Students by UID
* Only valid UIDS that exist in the database can be viewed and deleted, users who enter a non-existing UID will have the page return in error.

Home Page
* Updating existing students will change the display order and have the newest updated student appear at the bottom of the table.

Updating Students
* When pulling up the update student form, the password becomes blank but can be updated. 

## Initial Design and UI Mockup

I first approached the project by designed a UI mockup for how I envisioned the workflow would go. The different pages are for different features navigated from the homepage and each with a different endpoint. 





