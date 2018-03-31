//----------------------------------------------------------------------
// Date.java             by Dale/Joyce/Weems                   Chapter 1
//
// Supports date objects having year, month, and day attributes.
//----------------------------------------------------------------------
/*Nathaniel Cartright
  
  this class constructs a date that will be intiallized later.
  an array is set up to allow months to be returned as strings. 


*/
public class Date
{
  protected int year;
  protected int month;
  protected int day;
  public static final int MINYEAR = 1583;
  
  protected String[] monthstr= {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}; //; creates an array for months to correspond user input.

  
  // Constructor
  public Date(int newMonth, int newDay, int newYear)
  {
    month = newMonth;
    day = newDay;
    year = newYear;
   }

  // Observers
  public int getYear()
  {
    return year;
  }

  public int getMonth()
  {
    return month;
  }

  public int getDay()
  {
    return day;
  }

  public int lilian()
  {
    // Returns the Lilian Day Number of this date.
    // Precondition: This Date is a valid date after 10/14/1582.
    //
    // Computes the number of days between 1/1/0 and this date as if no calendar
    // reforms took place, then subtracts 578,100 so that October 15, 1582 is day 1. 
    
    final int subDays = 578100;  // number of calculated days from 1/1/0 to 10/14/1582

    int numDays = 0;

    // Add days in years.
    numDays = year * 365;

    // Add days in the months.
    if (month <= 2) 
      numDays = numDays + (month - 1) * 31;
    else 
      numDays = numDays + ((month - 1) * 31) - ((4 * (month-1) + 27) / 10);

    // Add days in the days.
    numDays = numDays + day;

    // Take care of leap years.
    numDays = numDays + (year / 4) - (year / 100) + (year / 400);

    // Handle special case of leap year but not yet leap day.
    if (month < 3) 
    {
        if ((year % 4) == 0)   numDays = numDays - 1;
        if ((year % 100) == 0) numDays = numDays + 1;
        if ((year % 400) == 0) numDays = numDays - 1;
      }

    // Subtract extra days up to 10/14/1582.
    numDays = numDays - subDays;
    return numDays;
  }

  public String toString()
  // Returns this date as a String.
  // this allows the user to see the date with the month actually spelled out. for example 1,12,1994 is actually january,12,1994
  {
    return(this.monthstr[month-1] + "," + this.day + "," + this.year);  // checks to see what index the month is in the str array.
  }

}
 