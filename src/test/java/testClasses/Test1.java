package testClasses;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/*import org.json.JSONArray;
import org.json.JSONML;
import org.json.JSONObject;
import org.json.JSONTokener;*/
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Test1 {

				static public void main( String [] args )
		        {
		                System.out.println( "In the JVMs static main" );
		                main( 5, 6, 7 );    //Calling overloaded static main method
		                Test1 t = new Test1();
		                String [] message  = { "Subhash", "Loves", "Programming" };
		                t.main(5);
		                t.main( 6, message );
		        }

		        public static void main( int ... args )
		        {
		                System.out.println( "In the static main called by JVM's main" );
		                for( int val : args )
		                {
		                        System.out.println( val );
		                }
		        }

		        public void main( int x )
		        {
		                System.out.println( "1: In the overloaded  non-static main with int with value " + x );
		        }

		        public void main( int x, String [] args )
		        {
		                System.out.println( "2: In the overloaded  non-static main with int with value " + x );
		                for ( String val : args )
		                {
		                        System.out.println( val );
		                }
		        }
		}