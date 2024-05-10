
/**
 * This class represents a Date object.
 * Provides functionalities for handling dates including validation
 * , comparison, and manipulation.
 *
 * @author Or saban
 * @version 19/10/2023 
 */
public class Date
{
    private int _day; // Represents day between 1-31
    private int _month; // Represents month 1-12
    private int _year; // Represents year with 4 digits
    private static final int LAST_FEB_LEAP = 29; // Last day of February in leap year
    private static final int LAST_FEB_NOT_LEAP = 28; // Last day of February not in leap year
    private static final int LAST_DAY_NORMAL = 30; // Last day of normal month
    private static final int LAST_DAY_NORMAL_BIG = 31; // Last day of normal month
    private static final int FEB_MONTH = 2; // February month
    private static final int LAST_MONTH = 12; // Last month of the year
    private static final int INVALID_MIN_YEAR = 1000; // Year must contain 4 digits
    private static final int INVALID_MAX_YEAR = 9999; // Year must contain 4 digits
    private static final Date DEFAULT_DATE = new Date(1, 1, 2000);

    /**
     * Date constructor - If the given date is valid, creates a new Date object;
     * otherwise, creates the date 01/01/2000.
     *
     * @param day   The day in the month (1-31)
     * @param month The month in the year (1-12)
     * @param year  The year (4 digits)
     */
    public Date(int day, int month,int year)
    {
        if(validDateValue(day,month,year) == true){ //if valid date.
            _day = day;
            _month = month;
            _year = year;
        }
        else //otherwise invalid date return deffault date 01/01/2000.
        {
            _day = DEFAULT_DATE._day;
            _month = DEFAULT_DATE._month;
            _year = DEFAULT_DATE._year;
        }
    }

    /**
     * Copy constructor
     * @param  other  the date to be copied.
     */
    public Date (Date other){
        _day = other._day; 
        _month = other._month;
        _year = other._year;
        // will contain the value of the other Date object.
    }

    /**
     * Private method that checks if the date is valid.
     *
     * @param day   The day in the month
     * @param month The month in the year
     * @param year  The year
     * @return True if the date is valid; otherwise, false
     */
    private boolean validDateValue(int day , int month , int year){
        if(year <INVALID_MIN_YEAR || year > INVALID_MAX_YEAR){ // years must contain 4 digits.
            return false;
        }
        if(month < 1 || month > LAST_MONTH){ //if month is invalid.
            return false;
        }
        switch(month){ //switch between days in months (28/29/30/31).
            case 1: //January.
            case 3: // March.
            case 5: //May.
            case 7: //July.
            case 10: //October.
            case LAST_MONTH: //December.
                return (day >=1 && day<= LAST_DAY_NORMAL_BIG);  //check if valid or invalid day
            case 4: // April.
            case 6: //June
            case 8: //August.
            case 9: //September.
            case 11: //November.
                return (day >=1 && day<= LAST_DAY_NORMAL);  //check if valid or invalid day                   

            case FEB_MONTH: //February.
                if(isLeapYear(year)) //check if leap year
                {
                    return (day >=1 && day<= LAST_FEB_LEAP); //check if valid or invalid day                    
                }
                else //otherwise not leap year.
                {
                    return (day >=1 && day<= LAST_FEB_NOT_LEAP); //check if valid or invalid day   
                }
        }
        return false; 
    }

    /**
     *  Gets the day
     *  @return the day of this date.
     */
    public int getDay(){ 
        return _day;
    }

    /**
     *  Gets the month
     *  @return the month of this date.
     */
    public int getMonth(){ 
        return _month;
    }

    /**
     *  Gets the year
     *  @return the year of this date.
     */
    public int getYear(){
        return _year;
    }

    /**
     *  Sets the day (only if date remains valid)
     *  @param dayToSet  the new day value
     */
    public void setDay(int dayToSet){
        if(validDateValue(dayToSet,_month,_year)){ // if valid change otherwise nothing.
            _day = dayToSet;
        }

    }

    /**
     *  Sets the month (only if date remains valid)
     *  @param monthToSet  the new month value
     */
    public void setMonth(int monthToSet){
        if(validDateValue(_day,monthToSet,_year)){
            _month =monthToSet;
        }
    }

    /**
     *  Sets the year (only if date remains valid)
     *  @param yearToSet  the new year value
     */
    public void setYear(int yearToSet){
        if(validDateValue(_day,_month,yearToSet)){
            _year = yearToSet;
        }
    }

    /**
     * Checks if two dates are the same
     * @param other  the date to compare this date to
     * @return true if the dates are the same
     */
    public boolean equals (Date other){
        if(calculateDate(_day,_month,_year) == calculateDate(other._day,other._month,other._year)){
            return true; //if the counting days equals they are equals date.
        }
        return false;
    }

    /**
     * Checks if this date comes before another date
     * @param other  date to compare this date to
     * @return true if this date is before the other date
     */
    public boolean before (Date other){
        if(calculateDate(_day,_month,_year) < calculateDate(other._day,other._month,other._year)){
            return true;
            //if the days is smaller meaning this date come before other date.
        }
        return false;    
    }

    /**
     * Checks if this date comes after another date
     * @param other  date to compare this date to
     * @return true if this date is after the other date
     */
    public boolean after (Date other){
        return other.before(this); //call to before method on other with this.
    }

    /**
     * Calculates the difference in days between two dates
     * @param  other  the date to calculate the difference between
     * @return
    the number of days between the dates (non negative value)       
     */
    public int difference (Date other){
        return Math.abs(calculateDate(_day,_month,_year) - calculateDate(other._day,other._month,other._year));
        //absulute value of the difference of the days from starting count and the other one.
    }

    /**
     * Returns a String that represents this date.
     * Overrides:
     * toString in class java.lang.Object
     * @return a String that represents this date in the following format: day (2 digits) 
     * / month(2 digits) / year (4 digits) for example: 02/03/1998
     */
    public String toString(){
        String temp =""; //building the date string.
        if(_day < 10){ //if miss 0 add.
            temp +="0"+_day+"/";    
        }
        else //not miss 0.
        {
            temp +=_day+"/";
        }
        if(_month < 10){ //if miss 0 add.
            temp +="0"+_month+"/";    
        }
        else //not miss 0.
        {
            temp+=_month+"/";
        }
        temp += _year; // add the year.
        return temp;
    }

    /**
     * Returns a String that represents this date.
     * Overrides:
     * toString in class java.lang.Object
     * @return a String that represents this date in the following format: day (2 digits) 
     * / month(2 digits) / year (4 digits) for example: 02/03/1998
     */
    public Date addYearsToDate(int num){ //integer bigger than 0.
        if(!isLeapYear(_year) && _day == LAST_FEB_NOT_LEAP && _month == FEB_MONTH && isLeapYear(_year+num)){
            return new Date(_day+1,_month,_year+num);
            //if we are not in leap year (28days) and after adding we in leap year
            // so adding 1day  and year, to 29.2.year+num.
        }
        else if(isLeapYear(_year) && _day == LAST_FEB_LEAP && _month == FEB_MONTH && !isLeapYear(_year+num)){
            return new Date(_day-1,_month,_year+num);
            //if we are in leap year and last day in feb and after adding
            // we will not in leap year we want to decrease day to 28.2.year+num.
        }
        //else we are going from leap to leap or from regular to regular adding just year+num.
        return new Date(_day,_month,_year+num);
    }

    /**
     * private method that given to help the students.
     * computes the day number since the beginning of the Christian counting of years
     */
    private int calculateDate ( int day, int month, int year)
    {
        if (month < 3) {
            year--;
            month = month + 12;
        }
        return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 + (day - 62);
    } 

    /**
     * private method that given to help the students.
     * checks if the year is a leap year
     */
    private boolean isLeapYear (int y)
    {
        return (y%4==0 && y%100!=0) || (y%400==0) ? true : false;
    } 
}
