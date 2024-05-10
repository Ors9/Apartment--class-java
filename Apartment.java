
/**
 * Represents an Apartment object with attributes such as number of rooms, area, price, tenant, and rental dates.
 * 
 * If any attribute is set to an invalid value (0 or negative), default values are used.
 * 
 * The rental start date must precede the end date. If not, the end date is set to one year after the start date.
 * 
 * Tenant replacement is permitted only if the new tenant is younger, the new price is at least the current price, 
 * and there are 90 or fewer days left from the current rental start date to the current rental end date.
 *
 * @author Or Saban
 * @version 19/10/2023
 */
public class Apartment
{
    private int _noOfRooms; //number of rooms (integer bigger than 0).
    private double _area; // area of house (number bigger than 0).
    private double _price; //price of rent (number bigger than 0).
    private Person _tenant; // tenant of the house.
    private Date _rentalStartDate; // date start rent.
    private Date _rentalEndDate; // date end rent.
    private static final int INVALID = 0;
    private static final int DEFFAULT_ROOM_NUMBERS =3;
    private static final int DEFFAULT_AREA = 80;
    private static final int DEFFAULT_PRICE = 5000;
    //date end rent ( must be after the  date of start of the rent).
    /**
     *  Apartment Constructor
     * @param  noOfRooms the number of rooms the apartment has
     * @param  area the apartment's area
     * @param  price monthly price to rent the apartment
     * @param  p the person renting the apartment
     * @param  startDay starting day of apartment rental
     * @param  startMonth starting month of apartment rental
     * @param  startYear starting year of apartment rental
     * @param  endDay last day of apartment rental
     * @param  endMonth last month of apartment rental
     * @param  endYear last year of apartment rental
     */
    public Apartment(int noOfRooms,double area,double price,Person tenant,
    int rentalStartDay,int rentalStartMonth,int rentalStartYear,
    int rentalEndDay,int rentalEndMonth,int rentalEndYear)
    {
        if(noOfRooms <= INVALID){ //invalid value.
            _noOfRooms = DEFFAULT_ROOM_NUMBERS; //deffault
        }
        else
        {
            _noOfRooms = noOfRooms; //valid.
        }
        if(area <= INVALID){ // invalid value
            _area = DEFFAULT_AREA; //deffault
        }
        else
        {
            _area = area; //valid
        }
        if(price <= INVALID){ //invalid
            _price = DEFFAULT_PRICE; //deffault
        }
        else
        {
            _price = price; //valid
        }
        _tenant = new Person(tenant);  //Person class will handle problems.
        _rentalStartDate = new Date(rentalStartDay,rentalStartMonth,rentalStartYear); //date class will handle problems.
        _rentalEndDate = new Date(rentalEndDay,rentalEndMonth,rentalEndYear); //create object with date rules.
        if(_rentalEndDate.before(_rentalStartDate) || _rentalEndDate.equals(_rentalStartDate))
        { //invalid if before or equals.
            _rentalEndDate.setYear(_rentalStartDate.getYear()+1);
            //equals or before set 1year after start.
            //we dont get in if after and the create of the object will stay same as inilize.
        }
    }

    /**
     * Copy Constructor
     * @param  other the apartment to be copied
     */
    public Apartment(Apartment other){
        _noOfRooms = other._noOfRooms; //copy 
        _area = other._area;//copy
        _price = other._price;//copy
        _tenant = new Person(other._tenant); //avoid aliasing create new and copy.
        _rentalStartDate = new Date(other._rentalStartDate); //avoid aliasing create new and copy.
        _rentalEndDate = new Date(other._rentalEndDate); //avoid aliasing create new and copy.
    }

    /**
     * Gets the number of rooms the apartment has
     * @return    the number of rooms the apartment has
     */
    public  int getNoOfRooms(){
        return _noOfRooms;    
    }

    /**
     * Gets the area of the apartment
     * @return    the area of the apartment
     */
    public double getArea(){
        return _area;   
    }

    /**
     * Gets the price of the apartment
     * @return    the price of the apartment
     */
    public double getPrice(){
        return _price;    
    }

    /**
     * Gets the tenant renting the apartment
     * @return the tenant renting the apartment
     */
    public Person getTenant(){
        return new Person(_tenant); //copy new for avoid aliasing. 
    }

    /**
     * Gets the rental start date
     * @return  the rental start date
     */
    public Date getRentalStartDate(){
        return new Date(_rentalStartDate); //copy new for avoid aliasing.   
    }

    /**
     * Gets the rental end date
     * @return    the rental end date
     */
    public Date getRentalEndDate(){
        return new Date(_rentalEndDate);  //copy new for avoid aliasing.    
    }

    /**
     * Sets the apartment's number of rooms (only if the new value is positive)
     * @param noOfRooms new number of rooms
     */
    public void setNoOfRooms(int num){
        if(num > INVALID){
            _noOfRooms = num; //if valid change,if invalid dont do anything.
        }
    }

    /**
     * Sets the apartment's area (only if the new value is positive)
     * @param area apartment's new area
     */
    public void setArea(double area){
        if(area > INVALID){
            _area = area;  //if valid change,if invalid dont do anything.  
        }
    }

    /**
     * Sets the apartment's price (only if the new value is positive)
     * @param  price apartment's new price
     */
    public void setPrice(double price){
        if(price > INVALID){
            _price = price; //if valid change,if invalid dont do anything.
        }
    }

    /**
     * Sets the apartment's tenant
     * @param  tenant apartment's new tenant
     */
    public void setTenant(Person p){
        _tenant = new Person(p); // create new object and copy to avoid aliasing.
    }

    /**
     * Sets the apartment's rental start date
     * (only if the new rental start date is before the current rental end date)
     * @param  rentalStartDate apartment's new rental start date
     */
    public void setRentalStartDate(Date d){
        //if the date of d before the end of the rent meaning is valid.
        if(d.before(_rentalEndDate)){
            _rentalStartDate = new Date(d);// create new object and copy to avoid aliasing.
        }
        //else meaning is equals or after(invalid dont change).
    }

    /**
     *  Sets the apartment's rental end date 
     *  (only if the new rental end date is after the current rental start date)
     * @param  rentalEndDate apartment's new rental end date
     */
    public void setRentalEndDate(Date d){
        //if the d date is after the start meaning is valid.
        if(d.after(_rentalStartDate)){
            _rentalEndDate = new Date(d); // create new object and copy to avoid aliasing. 
        }
        //if its not after its equals or before then invalid ,dont change.
    }

    /**
     * Returns a String that represents this Apartment
     * @return   a String that represents this Apartment
     */
    public String toString(){
        //apartmentString- build the  String that represents this Apartment
        String apartmentString ="Number of rooms: "+_noOfRooms+"\n"+"Area: "+_area+"\n"+
            "Price: "+_price+" NIS"+"\n"+"Tenant name: "+_tenant.getName()+"\n"+
            "Rental start date: "+_rentalStartDate.toString()+"\n"+"Rental end date: "+
            _rentalEndDate.toString();
        return apartmentString; //Returns a String that represents this Apartment
    }

    /**
     * Checks if the current and other apartments are 
     * equal by all the apartment attributes
     *
     * @param other an apartment object
     * @return true if the two objects are equal. Otherwise, returns false
     */
    public boolean equals(Apartment other){
        //if one of them is not equals immidiatly return false,else we arrive to the end
        // and return true because all are equals.
        if(other == null){ // check other is not null.
            return false;
        }
        if(_noOfRooms != other._noOfRooms ){
            return false; 
        }
        if(_area != other._area){
            return false;    
        }
        if(_price != other._price){
            return false;     
        }
        if(!_tenant.equals(other._tenant)){ //using Person class equals.
            return false;    
        }
        if(!_rentalStartDate.equals(other._rentalStartDate)){ //using Date class equals.
            return false;   
        }
        if(!_rentalEndDate.equals(other._rentalEndDate)){ //using Date class equals.
            return false;     
        } 
        return true;
    }

    /**
     * Extends the rental period by additional years(only if the years value is positive)
     * @param  years  the number of years to extend the lease
     */
    public void extendRentalPeriod(int years){
        if(years > INVALID){ //if the years are not negative.
            _rentalEndDate = new Date(_rentalEndDate.addYearsToDate(years));
            //create new date with the value for avoid aliasing,call 
            //addYearsToDate method from Date class it will handle edge cases of dates.
        }
        //else negative dont change.
    }

    /**
     * Computes the number of days left between a 
     * given date and the end of rental date.
     * If the given date comes after the end of rental date, returns -1.
     * @param  d a date object
     * @return the number of days left between a given date and the end of rental date
     */
    public int daysLeft(Date d){
        if(d.after(_rentalEndDate)){//if the date of d is after the end of the rental return-1.
            return -1;
        }
        return d.difference(_rentalEndDate); //using difference method in Date class.
        //if the date of d is before or equals the end of the rental return the days left.
    }

    /**
     *  Checks if the apartment's tenant can be replaced and update
     *  apartment attributes accordingly ( if the tenant is younger
     *  , the rental end date ends up to 90 days after the new
     *  rental start date and the new price is at least the current rental's price)
     *  @param  startRent a new rental start date
     *  @param  p a new tenant
     *  @param price a new price
     *  @return   true if the tenant has been changed
     */
    public boolean changeTenant(Date startDate, Person p, double price){
        if(p.compareTo(_tenant) != -1){
            return false; //if the new person older or equals than this then its invalid    
        }
        if(_price > price){
            return false; //if the new price smaller than the current price return false.
        }
        if(startDate.after(_rentalEndDate) || startDate.equals(_rentalEndDate)){
            return false;
            //if the new date is after the end of this date or equals return false.
        }
        if( startDate.difference(_rentalEndDate) > 90){
            return false; //if there more than 90 days left to the correct tenant then false.    
        }
        _price = price;
        _tenant = new Person(p); //copy new for avoid aliasing.
        _rentalStartDate = new Date(startDate); //copy new avoid aliasing.
        _rentalEndDate = new Date(_rentalStartDate.addYearsToDate(1));
        //the end date is the same as the starting date except add 1 year also take care 
        //to edge cases in addYearsToDate method in date class.
        return true;
        //if the new person younger and the price is equal or bigger than the current and
        //there equals to less than 90 days left than return true.
    }
}
