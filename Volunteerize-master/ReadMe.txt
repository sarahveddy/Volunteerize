1. Installation
  NOTE: This must be done on Windows, Linux is having a timezone issue with PostgreSQL Database.
  - Import Volunteerize into IntelliJ IDEA.
  - Create project from existing sources
  - Press Next
  - Press Next
    'The project file 'C:\Users\Matt\Development\Volunteerize\.idea'already exists.
    Would you like to overwrite it?'
  - Press Yes
  - Press Next
  - Make sure postgresql-42.1.1 checkbox is checked.
  - Press Next
  - Make sure Volunteerize folder is checked.
  - Press Next
    The module file 'C:\Users\Matt\Development\Volunteerize\Volunteerize.iml' already exist.
    Would you like to overwrite it?
  - Press Reuse
  - Press Next
  - Press Finish

  Project should now be created!

2. Running the Application
  - In the Project Navigator, navigate to the 'Volunteerize' class.
  - Right click 'Volunteerize' and run it.
  - Username: teamb
    Password: jellybean

    The application will check the credentials against all users in the database.
    (At this point it's only one user so only the above credentials will work.)
    On successful login the application will navigate to an Event Page with a list
    of events pulled from the database. What the application is showcasing is our
    ability to login with valid credentials and to view events in the database.

TROUBLESHOOTING

Project SDK not defined
  - File -> Project Structure -> Project
  - Under the project SDK specify Java 1.8 or JDK 1.8 whichever is available

Complaints about Database
  - Most likely due to PostgreSQL Library
  - File -> Project Structure -> Libraries
  - If nothing is specified in the left column, press the plus button at the top
    left and select Java, navigate to and select 'Volunteerize/lib/postgresql-42.1.1.jar',
    then hit 'Apply'.

NOTE: All code can be found at https://github.com/mmulenga/Volunteerize under the 'Testing'
      branch if the provided zip does not function as expected.
