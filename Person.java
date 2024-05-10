
/**
 * Represents a Person object with attributes such as name, date of birth, and ID.
 * Provides methods for accessing and modifying these attributes, as well
 * as for comparing and copying Person objects.
 *
 * @author Or Saban
 * @version 19/10/2023
 */
public class Person
{
    private String _name; // Name of the person
    private String _id; //ID number of the person
    private Date _dateOfBirth; // Date of birth of the person
    private static final String DEFFAULT_NAME = "Someone"; // Default name if not provided
    private static final String DEFFAULT_ID = "000000000"; // Default ID if not provided or invalid
    private static final int ID_LENGTH = 9;// Length of a valid ID number
    /**
     * Constructs a Person object with the given attributes.
     * If name is an empty string, assigns the default name "Someone".
     * If id doesn't contain 9 digits, assigns the default ID "000000000".
     *
     * @param name  The person's name
     * @param day   The day of the person's date of birth
     * @param month The month of the person's date of birth
     * @param year  The year of the person's date of birth
     * @param id    The person's ID
     */
    public Person(String name , int day, int month , int year, String id)
    {
        if(name == "") //if empty get deffault.
        {
            _name = DEFFAULT_NAME;
        }
        else
            _name = name;
        _dateOfBirth = new Date(day,month,year); //create date object.
        if(id.length() != ID_LENGTH) //if id length invalid get deffault
        {
            _id = DEFFAULT_ID;    
        }
        else
        {
            _id = id;
        }
    }

    /**
     * Copy Constructor
     * @param other the person to be copied
     */
    public Person(Person other)
    {
        _name = other._name;
        _id = other._id;
        _dateOfBirth = new Date(other._dateOfBirth); //copy new valid for avoid aliasing.
    }

    /**
     * Gets the person's name
     * @return the person's name
     */
    public String getName(){
        return _name;
    }

    /**
     * Gets the person's id
     * @return person's id
     */
    public String getId(){
        return _id;
    }

    /**
     * Gets the person's date of birth
     * @return person's date of birth
     */
    public Date getDateOfBirth(){
        return _dateOfBirth;
    }

    /**
     * Sets the person's name (only if the string given is not empty)
     * @param name  the person's new name
     */
    public void setName (String name){
        if(name != ""){ //if value is not empty copy.
            _name = name;    
        }
    }

    /**
     * Sets the person's id (only if the id is 9 digits)
     * @param id the person's new id
     */
    public void setId (String id){
        if(id.length() == ID_LENGTH) //if the length is valid then copy.
        {
            _id = id;    
        }
    }

    /**
     * Sets the person's date of birth
     * @param d the person's new date of birth
     */
    public void setDateOfBirth (Date d ){ 
        _dateOfBirth = new Date(d);
        //no need to check if valid because Date class take care of it
    }

    /**
     *Returns a String that represents this Person
     * @return a String that represents this Person.
     */
    public String toString(){
        String personString ="Name: "+_name+"\n"+"ID: "
            +_id+"\n"+"Date of birth: "+_dateOfBirth.toString(); 
        return personString;
    }

    /**
     * Checks if two Person objects are the same
     * @param other another Person object
     * @return true if all the attributes are the same
     */
    public boolean equals(Person other){
        if(other == null){
            return false; // check other is not null.
        }
        if(_name.equals(other._name) && _id.equals(other._id) 
        && _dateOfBirth.equals(other._dateOfBirth))
        {//check if the name equal , id equal , date equals (using date equals method).
            return true;
        }
        return false;
    }

    /**
     *Checks which person is older 
     *@param other another person object
     *@return 1 if this person is older than the other person 
     * , -1 if the other person is older than this person.
     * If both people have the same birth date, return 0.
     */
    public int compareTo(Person other){
        if(_dateOfBirth.before(other._dateOfBirth)){ //if the date before meaning bigger age.
            return 1;
        }
        if(_dateOfBirth.after(other._dateOfBirth)){ //if the date after meaning smaller age
            return -1;
        }
        return 0; //not smaller not bigger mean equals.
    }
}