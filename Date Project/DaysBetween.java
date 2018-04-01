//----------------------------------------------------------------------
// DaysBetween.java          by Dale/Joyce/Weems               Chapter 1
//
// Asks the user to enter two "modern" dates and then reports 
// the number of days between the two dates.
//----------------------------------------------------------------------


/* Nathaniel Cartwright
   This code allows the user to endlessly compare two dates 
   to find the number of days in between them. 
   if the user wishes to stop they can exit the program by typing N when asked if they want to continue.
   the program also terminates if they enter a invalid month integer. valid integers are 1-12
*/
import java.util.Scanner;

public class DaysBetween
{
  public static void main(String[] args)
  {
   while (true)                                                // allows the program to loop endlessly while the user is correctly operating the program
   {  
    Scanner conIn = new Scanner(System.in);
    int day, month, year;
		
    System.out.println("Enter two 'modern' dates: month day year");
    System.out.println("For example, January 12, 1954, would be: 1 12 1954");
    System.out.println();
    System.out.println("Modern dates occur after " + Date.MINYEAR + ".");
    System.out.println();
		
    System.out.println("Enter the first date:");      
    if(conIn.hasNextInt()==false){                             // checks to see if the user did not enter a valid integer for a month (1-12)
    System.out.println("Illegal input was entered");           // if they did enter a invalid integer the program says that an illegal input was entered and terminates the program.
    
    return;
    }
    month = conIn.nextInt();
	 day = conIn.nextInt();
    year = conIn.nextInt();
    Date date1 = new Date(month, day, year);
		
    System.out.println("Enter the second date:");
    month = conIn.nextInt();
    day = conIn.nextInt();
    year = conIn.nextInt();
    Date date2 = new Date(month, day, year);
		
    if ((date1.getYear() <= Date.MINYEAR) 
	    || 
		 (date2.getYear() <= Date.MINYEAR))
      System.out.println("You entered a 'pre-modern' date.");
    else
    {
      System.out.printf("The number of days between");
      System.out.print(date1);
      System.out.print(" and ");
      System.out.print(date2);
      System.out.print(" is ");
      System.out.println(Math.abs(date1.lilian() - date2.lilian()));
      System.out.print(" do you wish to continue? type Y for yes and N for no\n");           // promts the user on how to proceed with the program.
      if (conIn.next().equals("Y")){                                                         // if the user wishes to continue they can type Y if not they can type N or any other key
      continue;
      
      }
      else 
      System.out.println("terminating the program. thank you");
      break;
    }
  }
 }
}