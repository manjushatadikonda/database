package Java1.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class database {
	WebDriver driver=null;
	@Test
	public void test1() throws ClassNotFoundException, SQLException, InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		   driver = new ChromeDriver();
		   driver.get("https://mybreakbucket.s3.amazonaws.com/Departments.html");
		   Thread.sleep(3000);
		   Select s=new Select(driver.findElement(By.xpath("//select[@name='department']")));
		   Thread.sleep(3000);
		   List<WebElement> list=s.getOptions();
		   List<String> list2=new ArrayList<String>();
		   for(int i=0;i<list.size();i++)
		   {
		   System.out.println(list.get(i).getText());
		   list2.add(list.get(i).getText());
		   }
		// TODO Auto-generated method stub
		//Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"		
        String dbUrl = "jdbc:oracle:thin:@localhost:1521/orcl";					

		//Database Username		
		String username = "hr";	
        
		//Database Password		
		String password = "hr";				

		//Query to Execute		
		//String query = "select *  from employees;";	
        
 	    //Load mysql jdbc driver		
   	  //  Class.forName("oracle.jdbc.driver.oracledriver");			
   
   		//Create Connection to DB		
    	Connection con = DriverManager.getConnection(dbUrl,username,password);
  
  		//Create Statement Object		
	   Statement stmt = con.createStatement();					

			// Execute the SQL Query. Store results in ResultSet		
 		ResultSet rs= stmt.executeQuery("SELECT DISTINCT DEPARTMENT_NAME FROM HR.DEPARTMENTS");							
 List<String> list1=new ArrayList<String>();
 		// While Loop to iterate through all data and print results		
		while (rs.next()){
	        		String deptName = rs.getString(1);								        
                    					                               
                    System.out.println(deptName);	
                    list1.add(deptName);
            }		
			 // closing DB Connection	
		for(int i=0;i<list1.size();i++)
		{
		Assert.assertTrue(list2.contains(list1.get(i)));
		}
			con.close();			
}
}
