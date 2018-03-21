package com.teamb.model;

import org.postgresql.jdbc.PgArray;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class VolunteerizeModel {
    private DatabaseInterface database;
    private Users user;
    private Profile profile;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    /**
     * Constructor for VolunteerizeModel.
     */
    public VolunteerizeModel() {
        database = new DatabaseInterface();
        profile = new Profile();
        user = new Users();
    }

    /**
     * Wraps the given string in single quotes (needed for database queries,
     * specifically strings within queries).
     * @param value - String to be wrapped in single quotes
     *              - eg. John -> 'John'
     * @return - Returns the wrapped string.
     */
    private String wrap(String value) {
        return "'" + value + "'";
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public void login(String username, String password) {
        ResultSet result = database.select("* FROM users u WHERE u.username = " +
                wrap(username) + " AND password = " + wrap(password) + ";");

        try {
            result.next();
            user.setUsername(result.getString("username"));
            user.setProfileID(result.getInt("volunteer_id"));
            user.setIsStaff(result.getBoolean("is_staff"));
            profile = getProfile(result.getInt("volunteer_id"));
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void addUser(Users user) {
        database.insert("users (username, password, is_staff, volunteer_id)\n " +
                "VALUES (" +
                wrap(user.getUsername()) + ", " +
                wrap(user.getPassword()) + ", " +
                user.getIsStaff() + ", " +
                user.getProfileID() +
                ");");
    }

    /**
     * Creates a query which takes the attributes from the given profile and inserts them
     * into the appropriate tables within the database.
     * @param volunteer - Profile with insert information.
     */
    public void addProfile(Profile volunteer) {
        // Insert all relevant volunteer table information.
        database.insert("volunteers (id, first_name, middle_name, last_name, email," +
                " hours_worked, criminal_check, medical_info, availability)\n" +
                "VALUES (" + volunteer.getMemberID() + ", " +
                wrap(volunteer.getFirstName()) + ", " +
                wrap(volunteer.getMiddleName()) + ", " +
                wrap(volunteer.getLastName()) + ", " +
                wrap(volunteer.getEmail()) + ", " +
                volunteer.getHoursWorked() + ", " +
                "'" + volunteer.getCriminalRecordCheck() + "'" + ", " +
                wrap(volunteer.getMedicalInformation()) + ", " +
                // Good men died writing this line.
                // Converts 2D array into "bracket notation"
                // ie. {{1, 2, 3}, {4, 5, 6}}
                wrap(Arrays.deepToString(volunteer.getAvailability()
                        .availabilityArray)).replaceAll("\\]", "}").replaceAll("\\[", "{") +
                ");");

        // Insert all emergency contact information.
        database.insert("emergency_contact (id, first_name, middle_name, last_name," +
                " phone_number, address, postal_code, volunteer_id)\n " +
                "VALUES (" + volunteer.getMemberID() + ", " +
                wrap(volunteer.getEmergencyContactFirst()) + ", " +
                wrap(volunteer.getEmergencyContactMiddle()) + ", " +
                wrap(volunteer.getEmergencyContactLast()) + ", " +
                wrap(volunteer.getEmergencyContactPhoneNumber()) + ", " +
                wrap(volunteer.getEmergencyContactAddress()) + ", " +
                wrap(volunteer.getEmergencyContactPostalCode()) + ", " +
                volunteer.getMemberID() +
                ");");

        // Insert all contact information.
        database.insert("contact_information (prefer_phone, prefer_email, phone_number, address, postal_code, " +
                "volunteer_id)\n " +
                "VALUES (" +
                volunteer.getContactByPhone() + ", " +
                volunteer.getContactByEmail() + ", " +
                volunteer.getPhoneNumber() + ", " +
                wrap(volunteer.getAddress()) + ", " +
                wrap(volunteer.getPostalCode()) + ", " +
                volunteer.getMemberID() +
                ");");
    }

    /**
     * Updates the database entry for the given profile.
     * PROFILE ID MUST MATCH
     * @param volunteer - Profile with update information.
     */
    public void editProfile(Profile volunteer) {
        // Insert all relevant volunteer table information.
        database.update("volunteers SET \n" +
                "first_name = " + wrap(volunteer.getFirstName()) + ", \n" +
                "last_name = " + wrap(volunteer.getLastName()) + ", \n" +
                "email = " + wrap(volunteer.getEmail()) + ", \n" +
                "hours_worked = " + volunteer.getHoursWorked() + ", \n" +
                "criminal_check = " + "'YES'" + " \n" +
                "WHERE id = " + volunteer.getMemberID() + ";");

        // Insert all contact information.
        database.update("contact_information SET \n " +
                "prefer_phone = " + volunteer.getContactByPhone() + ", \n" +
                "prefer_email = " + volunteer.getContactByEmail() + ", \n" +
                "phone_number = " + volunteer.getPhoneNumber() + ", \n" +
                "address = " + wrap(volunteer.getAddress()) + ", \n" +
                "postal_code = " + wrap(volunteer.getPostalCode()) + ", \n" +
                "volunteer_id = " + volunteer.getMemberID() + " \n" +
                "WHERE volunteer_id = " + volunteer.getMemberID() + ";");

        // Insert all emergency contact information.
        database.update("emergency_contact SET \n " +
             //   "id = " + volunteer.getEmergencyContactID() + ", \n" +
                "first_name = " + wrap(volunteer.getEmergencyContactFirst()) + ", \n" +
                "middle_name = " + wrap(volunteer.getEmergencyContactMiddle()) + ", \n" +
                "last_name = " + wrap(volunteer.getEmergencyContactLast()) + ", \n" +
                "phone_number = " + wrap(volunteer.getEmergencyContactPhoneNumber()) + ", \n" +
                "address = " + wrap(volunteer.getEmergencyContactAddress()) + ", \n" +
                "postal_code = " + wrap(volunteer.getEmergencyContactPostalCode()) + "\n " +
                "WHERE volunteer_id = " + volunteer.getMemberID() + ";");
    }

    /**
     * Deletes the all relevant entries for the given profile.
     * @param id - Profile with delete information.
     */
    public void deleteProfile(int id) {
        // TODO - Fix cascade, currently does not delete contact_information.
        database.delete("volunteers WHERE id = " + id + ";");
    }


    /**
     * Retrieves the profile associated with the given ID.
     * @param id - An integer referring to the profile we want.
     * @return Returns the profile associated with the given ID.
     */
    public Profile getProfile(int id) {
        ResultSet volunteer;
        ResultSet contactInformation;
        ResultSet emergencyContact;

        Profile newProfile = new Profile();
        Availability availability = new Availability();

        volunteer = database.select(" * FROM volunteers WHERE id = " + id + ";");
        contactInformation = database.select(" * FROM contact_information WHERE volunteer_id = " + id + ";");
        emergencyContact = database.select(" * FROM emergency_contact WHERE volunteer_id = " + id + ";");

        try {
            volunteer.next();
            contactInformation.next();
            emergencyContact.next();

            PgArray array = (PgArray)volunteer.getArray("availability");

            // So turns out JDBC doesn't facilitate multi-dimensional arrays.
            // And I'm super sick of this, so we're about to get really gross. Sorry everyone.
            // I will explain this though because I'm not a monster.
            // So. When we get an array back from the volunteer ResultSet we cast it to
            // PgArray so we can use the toString function. This gives us a string in the following
            // format. {{f,f,f},{f,t,f},{f,f,f},{f,f,t},{f,f,f},{f,f,f},{f,f,f}}
            // We parse that string and convert all the f's and t's to boolean values and store
            // those in conversionArray. Yeah. I know.
            boolean[][] conversionArray = new boolean[7][3];

            int charIndex = 2;

            for(int i = 0; i < 7; i++) {
                for(int j = 0; j < 3; j++) {
                    if(array.toString().charAt(charIndex) == 't') {
                        conversionArray[i][j] = true;
                    } else {
                        conversionArray[i][j] = false;
                    }
                    charIndex += 2;
                }

                charIndex += 2;
            }

            availability.availabilityArray = conversionArray;
            // Grab the 2D array from the database and cast it as a 2D boolean array and assign that
            // hot mess to the availability object availabilityArray property.
            // availability.availabilityArray = (boolean[][])array.getArray();

            newProfile.setAllBaseInformation(volunteer.getString("first_name"),
                    volunteer.getString("middle_name"),
                    volunteer.getString("last_name"),
                    contactInformation.getString("address"),
                    contactInformation.getString("phone_number"),
                    contactInformation.getString("postal_code"),
                    emergencyContact.getString("phone_number"),
                    emergencyContact.getString("first_name"),
                    emergencyContact.getString("middle_name"),
                    emergencyContact.getString("last_name"),
                    emergencyContact.getInt("id"),
                    emergencyContact.getString("postal_code"),
                    emergencyContact.getString("address"),
                    volunteer.getString("email"),
                    contactInformation.getBoolean("prefer_phone"),
                    contactInformation.getBoolean("prefer_email"),
                    volunteer.getInt("id"),
                    volunteer.getBoolean("criminal_check"),
                    volunteer.getString("medical_info"),
                    volunteer.getInt("hours_worked"),
                    volunteer.getString("photo_path"),
                    availability
            );
        } catch(SQLException exception) {
            exception.printStackTrace();
        }

        return newProfile;
    }

    /**
     * Creates a query which takes the attributes from the given event and inserts them
     * into the appropriate tables within the database.
     * @param newEvent - Event with insert information.
     */
    public void addEvent(Event newEvent) {
        // TODO - Figure out how we're storing locations application-side.

        // Converts int values to string, and if necessary adds a zero so that is will be
        // properly formatted in the SQL query
        String startTime = Integer.toString(newEvent.getStartTime());
        if (newEvent.getStartTime() < 1000)
            startTime = "0" + startTime;

        //adds zero if start tiem is before 1 am (00:30)
        if (newEvent.getStartTime() < 100)
            startTime = "0" + startTime;

        // adds zero if start time is midnight (00:00)
        if (newEvent.getStartTime() < 10)
            startTime = "0" + startTime;

        String endTime = Integer.toString(newEvent.getEndTime());
        if (newEvent.getEndTime() < 1000)
            endTime = "0" + endTime;

        if (newEvent.getEndTime() < 100)
            endTime = "0" + endTime;

        if (newEvent.getEndTime() < 10)
            endTime = "0" + endTime;



        database.insert("events (name, start_time, end_time, description, location_id)\n " +
                "VALUES (" +
                wrap(newEvent.getEventName()) + ", " +
                 "to_timestamp('" + newEvent.getStartDate() +" " + startTime + "', 'DD/MM/YYYY HH24MI'), " +
                "to_timestamp('" + newEvent.getEndDate() +" " + endTime + "', 'DD/MM/YYYY HH24MI'), " +
                wrap(newEvent.getDescription()) + ", " +
                0 +
                ");");
    }

    /**
     * Updates the database entry for the given event.
     * EVENT ID MUST MATCH
     * @param newEvent - Event with update information.
     */
    public void editEvent(Event newEvent) {
        database.update("events SET\n " +
                "name = " + wrap(newEvent.getEventName()) + ", \n" +
                "start_time = " + newEvent.getStartTime() + ", \n" +
                "end_time = " + newEvent.getEndTime() + ", \n" +
                "description = " + wrap(newEvent.getDescription()) + ", \n" +
                "location_id = " + newEvent.getLocation() + " \n");
    }

    /**
     * Deletes the all relevant entries for the given profile.
     * @param newEvent - Event with delete information.
     */
    public void deleteEvent(Event newEvent) {
        database.delete("events WHERE id = " + newEvent.getEventID() + ";");
    }

    /**
     * Deletes the all relevant entries for the given profile.
     * @param id - is the id of the event you would like to delete
     */
    public void deleteEventid(int id) {
        database.delete("events WHERE id = " + id + ";");
    }


    /**
     * Adds event Participants to table in Database
     * @param e - Event that participant will go to.
     * @param p - Profile of participant.
     */
    public void addEventParticipants( Event e, Profile p) {
        database.insert("event_participants (id, volunteer_id, event_id, job_id)\n " +
                "VALUES (DEFAULT, " +
                e.getEventID() + ", " +
                p.getMemberID() + ", " +
                "0);");
    }

    public Profile[] retrieveAllProfiles() {
        Profile[] profileList;
        ResultSet id;

        id = database.select(" id FROM volunteers;");

        int count = 0;

        //counts number of values in result set. // repeated work?
        try{
            ResultSet counter = database.count("volunteers");

            counter.next();
            count = counter.getInt("count");

        } catch(SQLException exception) {
            System.out.println("Count query failed.");
            exception.printStackTrace();
        }

        profileList = new Profile[count];


        try {
            int i = 0;

            while(i < count) {
                id.next();

                profileList[i] = getProfile(id.getInt("id"));
                i++;
            }
        } catch(SQLException exception) {
            exception.printStackTrace();
        }

        return profileList;
    }


    /**
     * Discovers type of data sought, and returns a formatted type for PostgreSQL.
     * @param choice - String with data type of query.
     */
    public String getProfileDataType (String choice) {
        String dataType;

        if (choice.equals("Id"))
            dataType = "v.id";
        else if (choice.equals("First Name"))
            dataType = "v.first_name";
        else if (choice.equals("Last Name"))
            dataType = "v.last_name";
        else if (choice.equals("Availability")) // Place holder while we wait for more options
            dataType = "LIST";  // What else can be searched by?
        else
            dataType = "fail"; // meaning you can't search by this data type
        return dataType;
    }

    /**
     * Discovers type of data sought, and returns a formatted type for PostgreSQL.
     * @param choice - String with data type of query.
     */
    public String getEventDataType (String choice) {

        String dataType;

        if (choice.equals("Id"))
            dataType = "e.id  ";
        else if (choice.equals("Name"))
            dataType = "e.name  ";
        else if (choice.equals("StartTime"))
            dataType = "e.start_time ";
        else if (choice.equals("EndTime"))
            dataType = "e.end_time ";  // What else can be searched by?
        else
            dataType = "fail"; // meaning you can't search by this data type
        return dataType;
    }

    /**
     * Finds the requested profile from the database and returns it.
     * @param query - String with the data that is being looked for.
     * @param dataType - String with data type of query.
     */
    public ResultSet getVolunteerSet(String query, String dataType ) {

        // Generates a result set from the database using the dataType and query
        ResultSet rs = database.select( "v.id, " +
                    "u.type, " +
                    "v.first_name, " +
                    "v.middle_name, " +
                    "v.last_name, " +
                    "v.email, " +
                    "v.hours_worked, " +
                    "v.criminal_check, " +
                    "v.medical_info, " +
                    "v.photo_path, " +
                    "g.name as volunteer_group, " +
                    "c.prefer_phone, " +
                    "c.prefer_email, " +
                    "c.phone_number, " +
                    "c.address, " +
                    "c.postal_code, " +
                    "e.first_name as emergency_contact_first_name, " +
                    "e.middle_name as emergency_contact_middle_name, " +
                    "e.last_name as emergency_contact_last_name, " +
                    "e.id as emergency_contact_id, " +
                    "e.phone_number as emergency_contact_phone_number, " +
                    "e.address as emergency_contact_address, " +
                    "e.postal_code as emergency_contact_postal_code " +
                    "FROM users u, " +
                    "volunteers v, " +
                    "contact_information c, " +
                    "emergency_contact e, " +
                    "volunteer_group g, " +
                    "volunteer_group_members m " +
                    "where u.volunteer_id = v.id " +
                    "and c.volunteer_id = v.idw " +
                    "and c.emergency_contact_id = e.id " +
                    "and m.volunteer_id = v.id " +
                    "and m.group_id = g.id " +
                    "and " + dataType + " = " + query);

        return rs;
    }

    /*

    "v.id, " +
                    "u.type, " +
                    "v.first_name, " +
                    "v.middle_name, " +
                    "v.last_name, " +
                    "v.email, " +
                    "v.hours_worked, " +
                    "v.criminal_check, " +
                    "v.medical_info, " +
                    "v.photo_path, " +
                    "c.prefer_phone, " +
                    "c.prefer_email, " +
                    "c.phone_number, " +
                    "c.address, " +
                    "c.postal_code, " +
                    "e.first_name as emergency_contact_first_name, " +
                    "e.middle_name as emergency_contact_middle_name, " +
                    "e.last_name as emergency_contact_last_name, " +
                    "e.id as emergency_contact_id, " +
                    "e.phone_number as emergency_contact_phone_number, " +
                    "e.address as emergency_contact_address, " +
                    "e.postal_code as emergency_contact_postal_code " +
                    "FROM users u, " +
                    "volunteers v, " +
                    "contact_information c, " +
                    "emergency_contact e, " +
                    "WHERE u.volunteer_id = v.id " +
                    "and c.volunteer_id = v.id " +
                    "and c.emergency_contact_id = e.id " +
                    "and (v.first_name = '" + query + "' OR v.last_name = '" + query +"'");


                    profileToAdd.setAllBaseInformation(rs.getString("first_name"),
                     rs.getString("middle_name"),
                     rs.getString("last_name"),
                     rs.getString("address"),
                     rs.getString("phone_number"),
                     rs.getString("postal_code"),
                     rs.getString("emergency_contact_phone_number"),
                     rs.getString("emergency_contact_first_name"),
                     rs.getString("emergency_contact_middle_name"),
                     rs.getString("emergency_contact_last_name"),
                     rs.getInt("emergency_contact_id"),
                     rs.getString("emergency_contact_adress"),
                     rs.getString("emergency_contact_postal_code"),
                     rs.getString("email"),
                     rs.getBoolean("prefer_phone"),
                     rs.getBoolean("prefer_email"),
                     rs.getInt("id"),
                     rs.getBoolean("criminal_check"),
                     rs.getString("medical_info"),
                     rs.getInt("hours_worked"),
                     rs.getString("photo_path"),
                     null); // availability must be updated

     */

    public ArrayList<Profile> getProfiles() {
        ArrayList<Profile> toReturn = new ArrayList<>();

        try{
            ResultSet rs = database.select( "* " +
                    "FROM volunteers v ");

            while(rs.next()) {
                Profile profileToAdd = new Profile();

                profileToAdd.setAllBaseInformation(rs.getString("first_name"),
                        rs.getString("middle_name"),
                        rs.getString("last_name"),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        0,
                        null,
                       null,
                        rs.getString("email"),
                        true,
                        true,
                        rs.getInt("id"),
                        rs.getBoolean("criminal_check"),
                        rs.getString("medical_info"),
                        rs.getInt("hours_worked"),
                        rs.getString("photo_path"),
                        null);
                toReturn.add(profileToAdd);
            }

        }catch(SQLException exception) {
            System.out.println("Get profiles failed.");

            exception.printStackTrace();
        }


        return toReturn;

    }



    public ArrayList<Profile> searchProfileName(String query) {
        ArrayList<Profile> toReturn = new ArrayList<>();

        try{
            ResultSet rs = database.select( "v.id, " +
                    "v.first_name, " +
                    "v.middle_name, " +
                    "v.last_name, " +
                    "v.email, " +
                    "v.hours_worked, " +
                    "v.criminal_check, " +
                    "v.medical_info, " +
                    "v.photo_path " +
                    "FROM volunteers v " +
                    "WHERE v.first_name = '" + query + "' OR v.last_name = '" + query +"'");

         while(rs.next()) {
             Profile profileToAdd = new Profile();

             profileToAdd.setAllBaseInformation(rs.getString("first_name"),
                     rs.getString("middle_name"),
                     rs.getString("last_name"),
                     null,
                     null,
                     null,
                     null,
                     null,
                     null,
                     null,
                     0,
                     null,
                     null,
                     rs.getString("email"),
                     true,
                     true,
                     rs.getInt("id"),
                     rs.getBoolean("criminal_check"),
                     rs.getString("medical_info"),
                     rs.getInt("hours_worked"),
                     rs.getString("photo_path"),
                     null);

             toReturn.add(profileToAdd);
         }

        }catch(SQLException exception) {
            System.out.println("Search profile query failed.");

            exception.printStackTrace();
        }


        return toReturn;

    }


    /*
    public Profile[] searchProfiles(String query, String dataType ) {

        // Converts desired data type to Postgres terminology
        String dbDataType = getProfileDataType(dataType);
        // Will be the number of results from the query
        int numOfValues = 0;

        // Counts the number of results from the database    //redoing work ?
        try{
            ResultSet rs = database.count( "FROM users u, " +   // can this be just users u?
                            "volunteers v, " +
                            "contact_information c, " +
                            "emergency_contact e, " +
                            "volunteer_group g, " +
                            "volunteer_group_members m " +
                            "where u.volunteer_id = v.id " +
                            "and c.volunteer_id = v.id " +
                            "and c.emergency_contact_id = e.id " +
                            "and m.volunteer_id = v.id " +
                            "and m.group_id = g.id " +
                            "and " + dbDataType + " = '" + query + "'");


            //sets the number of results so we can create an array of necessary length
            numOfValues = rs.getInt("count");

        }catch(SQLException exception) {
            System.out.println("Count query failed.");
            exception.printStackTrace();
        }
        // create array of profiles to hold all results
        Profile [] profilesToReturn = new Profile[numOfValues]; // terrible names
        ResultSet profilesSearched = getVolunteerSet(query,dbDataType); // terrible names

        try {
            // goes through array created and assigns values to each profile
            for(int i = 0; i < numOfValues; i++){
                Event e = new Event();
                profilesToReturn[i].setAllBaseInformation(profilesSearched.getString("first_name"),
                        profilesSearched.getString("middle_name"),
                        profilesSearched.getString("last_name"),
                        profilesSearched.getString("address"),
                        profilesSearched.getString("phone_number"),
                        profilesSearched.getString( "postal_code"),
                        profilesSearched.getString("emergency_contact_phone_number"),
                        profilesSearched.getString("emergency_contact_first_name"),
                        profilesSearched.getString("emergency_contact_middle_name"),
                        profilesSearched.getString("emergency_contact_last_name"),
                        profilesSearched.getInt("emergency_contact_id"),
                        profilesSearched.getString("emergency_contact_address"),
                        profilesSearched.getString("emergency_contact_postal_code"),
                        profilesSearched.getString("email"),
                        profilesSearched.getBoolean("prefer_phone"),
                        profilesSearched.getBoolean("prefer_email"),
                        profilesSearched.getInt("id"),
                        profilesSearched.getBoolean("criminal_check"),
                        profilesSearched.getString("medical_info"),
                        profilesSearched.getInt("hours_worked"),
                        profilesSearched.getString("photo_path"),
                        null  // availability is not clearly defined
                );
                //moves result set to next value
                profilesSearched.next();  // could use while??
            }

        }catch(SQLException exception) {
            System.out.println("Count query failed.");
            exception.printStackTrace();
        }

        return profilesToReturn;

    }

    */

    /**
     * Finds the requested event and returns a set with all instances of it.
     * @param query - String with the data that is being looked for.
     * @param dataType - String with data type of query.
     */
    public ResultSet getEventSet(String query, String dataType ) {
        Event e = new Event();
        ResultSet rs = database.select( "e.id, " +
                "e.name, " +
                "e.start_time, " +
                "e.end_time, " +
                "e.description, " +
                "l.name as location_name, " +
                "l.address, " +
                "l.postal_code " +
                "FROM events e, locations l " +
                "WHERE l.id = e.location.id " +
                "and " + dataType + " = " + query);
        return rs;
    }


    public ArrayList<Event> getUpcomingEvents(){

        ArrayList<Event> eventsToReturn = new ArrayList<>();
        try {
            ResultSet events = database.select("* from events e where e.start_time > now();");
            ResultSet eventTimesDates = database.select ("to_char(e.start_time, 'YYYY:MM:DD') as start_date, " +
                    "to_char(e.start_time, 'HH24MI') as start_time, " +
                    "to_char(e.end_time, 'YYYY:MM:DD') as end_date, " +
                    "to_char(e.end_time, 'HH24MI') as end_time FROM events e where e.start_time > now();");
            while(events.next()){
                Event e = new Event();
                eventTimesDates.next(); // should be in while condition?
                e.setEventID(events.getInt("id"));
                e.setEventName(events.getString("name"));
                e.setLocation(events.getString("location_id"));
                e.setDescription(events.getString("description"));

                e.setStartTime(eventTimesDates.getInt("start_time"));
                e.setStartDate(eventTimesDates.getString("start_date"));
                e.setEndTime(eventTimesDates.getInt("end_time"));
                e.setEndDate(eventTimesDates.getString("end_date"));

                eventsToReturn.add(e);
                System.out.println(eventsToReturn.get(0).getEventName());
            }
        }catch(SQLException exception) {
            System.out.println("get upcoming events failed.");
            exception.printStackTrace();
        }
        return eventsToReturn;
    }


    public Event getEvent(int id){
        Event e = new Event();

        try {
            ResultSet events = database.select("* FROM events e where e.id = '" + id + "';");
            ResultSet eventTimesDates = database.select ("to_char(e.start_time, 'YYYY:MM:DD') as start_date, " +
                    "to_char(e.start_time, 'HH24MI') as start_time, " +
                    "to_char(e.end_time, 'YYYY:MM:DD') as end_date, " +
                    "to_char(e.end_time, 'HH24MI') as end_time FROM events e where e.id = '" + id + "';");

                eventTimesDates.next();
                events.next();
                e.setEventID(events.getInt("id"));
                e.setEventName(events.getString("name"));

                e.setStartTime(eventTimesDates.getInt("start_time"));
                e.setStartDate(eventTimesDates.getString("start_date"));
                e.setEndTime(eventTimesDates.getInt("end_time"));
                e.setEndDate(eventTimesDates.getString("end_date"));

                e.setLocation(events.getString("location_id"));
                e.setDescription(events.getString("description"));

        }catch(SQLException exception) {
            System.out.println("Get event failed.");
            exception.printStackTrace();
        }
        return e;

    }

    public ArrayList<Event> searchEvents(String query, String dataType){
        ArrayList<Event> eventstoReturn = new ArrayList<>();
        String dbDataType = getEventDataType(dataType);

        try {
            ResultSet events = database.select("* FROM events WHERE " + dbDataType + " = '" +query + "';");
            ResultSet eventTimesDates = database.select ("to_char(e.start_time, 'YYYY:MM:DD') as start_date, " +
                    "to_char(e.start_time, 'HH24MI') as start_time, " +
                    "to_char(e.end_time, 'YYYY:MM:DD') as end_date, " +
                    "to_char(e.end_time, 'HH24MI') as end_time FROM events e WHERE " + dbDataType +" = '"+  query + "';");
            while(events.next()) {
                Event e = new Event();
                eventTimesDates.next();
                e.setEventID(events.getInt("id"));
                e.setEventName(events.getString("name"));

                e.setStartTime(eventTimesDates.getInt("start_time"));
                e.setStartDate(eventTimesDates.getString("start_date"));
                e.setEndTime(eventTimesDates.getInt("end_time"));
                e.setEndDate(eventTimesDates.getString("end_date"));

                e.setLocation(events.getString("location_id"));
                e.setDescription(events.getString("description"));
                eventstoReturn.add(e);
            }

        }catch(SQLException exception) {
            System.out.println("Search events failed.");
            exception.printStackTrace();
        }

        return eventstoReturn;
    }


    public ArrayList<Event> getMyEvents(Profile p){
        ArrayList<Event> profileEventsToReturn = new ArrayList<>();

        try {
            ResultSet events = database.select("* FROM events WHERE events.id IN " +
                    "(SELECT ep.event_id FROM  event_participants ep WHERE ep.volunteer_id = '" + p.getMemberID() + "';");
            ResultSet eventTimesDates = database.select ("to_char(e.start_time, 'YYYY:MM:DD') as start_date, " +
                    "to_char(e.start_time, 'HH24MI') as start_time, " +
                    "to_char(e.end_time, 'YYYY:MM:DD') as end_date, " +
                    "to_char(e.end_time, 'HH24MI') as end_time FROM events e where e.id = '" + p.getMemberID() + "';");
            while(events.next()){
                Event e = new Event();
                eventTimesDates.next();  // Should be in while conditional?
                e.setEventID(events.getInt("id"));
                e.setEventName(events.getString("name"));

                e.setStartTime(eventTimesDates.getInt("start_time"));
                e.setStartDate(eventTimesDates.getString("start_date"));
                e.setEndTime(eventTimesDates.getInt("end_time"));
                e.setEndDate(eventTimesDates.getString("end_date"));

                e.setLocation(events.getString("location_id"));
                e.setDescription(events.getString("description"));
                profileEventsToReturn.add(e);
            }
        }catch(SQLException exception) {
            System.out.println("get my events failed.");
            exception.printStackTrace();
        }
        return profileEventsToReturn;

    }

        public static void main(String args[]) {
        VolunteerizeModel model = new VolunteerizeModel();

        Profile[] listOfProfiles = model.retrieveAllProfiles();

        for(int i = 0; i < listOfProfiles.length; i++) {
            System.out.println(listOfProfiles[i].getFirstName());
        }

    }
}
