import org.junit.*;
import java.lang.reflect.*;
/**
 * This class tests the ParkingLot and LFHSTest
 * 
 * @author Mr. Aronson
 */
public class ParkingLotTest extends junit.framework.TestCase
{
    private String className = "ParkingLot";
    private  boolean failed = false;
    private  Object t1, t2, t3;
    private  Class<?> c, c2;
    private Object[] cArgs = {70};
    private Object[] cArgs2 = {};
    private Constructor constructor, constructor2;

    public static void main(String args[])
    {
        ParkingLotTest p = new ParkingLotTest();
        p.test();
    }
    @Test
    public void test() {
        //********** ParkingLot Class Test **************************************
        // Instantiate a new ParkingLot object
        System.out.println("Now testing your ParkingLot class: \n");
        try
        {
            c = Class.forName(className);
        }
        catch (NoClassDefFoundError e)
        {
            failure("Epic Failure: missing ParkingLot class");
        }
        catch (ClassNotFoundException e)
        {
            failure("Epic Failure: missing ParkingLot class");
        }
        catch(Exception e) {
            failure(e.toString());}

        System.out.println("Testing instance variables and constructor");
        if(!failed)
        {
            Field[] fields = c.getDeclaredFields();
            if(fields.length == 0)
                failure("ParkingLot has no instance variables");
        }
        if (!failed)
        {
            try {
                Field field = c.getDeclaredField("numCars");
                String temp = field.toString();
                if (!temp.contains("int"))
                    failure("numCars instance variable needs to be an int");
                else if (!temp.contains("private"))
                    failure("numCars instance variable needs to be private");
            } catch (NoSuchFieldException e) {
                failure("numCars instance variable is missing");
            }
        }
        if (!failed) {
            try {
                Field field = c.getDeclaredField("MAX_CARS");
                String temp = field.toString();

                if (!temp.contains("final"))
                    failure("MAX_CARS needs to be final since it is a constant");
                else if (!temp.contains("int"))
                    failure("MAX_CARS needs to be an int");
                else if (!temp.contains("public"))
                    failure("MAX_CARS should be public");
            } catch (NoSuchFieldException e) {
                failure("MAX_CARS instance variable is missing");
            }
            catch (Exception e)
            {
                failure(e.toString());
            }
        }

        if (!failed) {
            try
            {
                Constructor defaultConstructor = c.getConstructor();
                Object tempObj = defaultConstructor.newInstance();
                Field field = c.getDeclaredField("numCars");
                field.setAccessible(true);
                int val = (int)field.getInt(tempObj);
                if ( val != 0)
                    failure("default constructor setting numCars to " + val + " but should be 0");
            }
            catch (NoSuchMethodException e)
            {
                failure("missing default constructor ParkingLot()");
            }
            catch (Exception e)
            {
                failure(e.toString());
            }
        }
        if (!failed) {
            try {
                Field field = c.getDeclaredField("MAX_CARS");
                Constructor defaultConstructor = c.getConstructor();
                Object tempObj = defaultConstructor.newInstance();

                field.setAccessible(true);
                int val = (int)field.getInt(tempObj);
                if (val != 2500)
                    failure("MAX_CARS is not 2500");

            } catch (NoSuchFieldException e) {
                failure("MAX_CARS instance variable is missing");
            }
            catch (Exception e)
            {
                failure(e.toString());
            }
        }
        if (!failed) {
            try
            {
                constructor = c.getConstructor(new Class[] {int.class});
                t1 = constructor.newInstance(cArgs);
                Field field = c.getDeclaredField("numCars");
                field.setAccessible(true);
                int val = (int)field.getInt(t1);
                if (val != (int)cArgs[0])
                    failure("constructor with parameter setting numCars to " + val + " but should be " + cArgs[0]);
            }
            catch (NoSuchMethodException e)
            {
                failure("missing constructor ParkingLot(int theNumCars)");
            }

            catch (Exception e)
            {
                failure(e.toString());
            }
        }

        if(!failed)
            System.out.println("Passed instance variable and constructor parameter test\n");

        if(!failed)
        {
            System.out.println("Now testing getNumCars method");
            try
            {
                Method m = c.getDeclaredMethod("getNumCars");
                int tempNum = (int)m.invoke(t1);
                if(tempNum != 70)
                    failure("The numCars is " + tempNum +  " and should be " + cArgs[0]);
            } catch (NoSuchMethodException e)
            {
                failure("missing getNumCars() method");
            }
            catch(Exception e) {failure(e.toString());}
        }
        if(!failed)
            System.out.println("Passed getNumCars method test\n");


        if(!failed)
        {
            System.out.println("Now testing setNumCars method");
            try
            {
                t2 = constructor.newInstance(cArgs);
                Method m = c.getDeclaredMethod("setNumCars", int.class);
                m.invoke(t2, 90);
                m = c.getDeclaredMethod("getNumCars");
                Object tempNum = m.invoke(t2);
            }
            catch (NoSuchMethodException e)
            {
                failure("missing setNumCars(int theNum) method");
            }
            catch(Exception e) {failure(e.toString());}
        }

        if(!failed)
        {
            try {
                Method m = c.getDeclaredMethod("getNumCars");
                int tempNum = (int)m.invoke(t2);
                if (tempNum != 90)
                    failure("setNumCars is " + tempNum + " but should be 90");
            }catch(Exception e) {failure(e.toString());}
        }

        if(!failed)
            System.out.println("Passed setNumCars method test\n");

        if(!failed)
        {
            System.out.println("Now testing if setNumCars error checks");
            try
            {
                Object obj = constructor.newInstance(cArgs);
                Method m = c.getDeclaredMethod("setNumCars", int.class);
                m.invoke(obj, 80000);
                m = c.getDeclaredMethod("getNumCars");
                int tempNum = (int)m.invoke(obj);

                if (tempNum != 70)
                    failure("setNumCars does not error check for the MAX_CARS");
            }
            catch (NoSuchMethodException e)
            {
                failure("missing setNumCars() method");
            }
            catch(Exception e) 
            {
                failure(e.toString());
            }
        }
        if(!failed)
        {

            try
            {
                Method m = c.getDeclaredMethod("setNumCars", int.class);
                m.invoke(t2, -3);
                m = c.getDeclaredMethod("getNumCars");
                int tempNum = (int)m.invoke(t2);

                if (tempNum < 0)
                    failure("setNumCars does not error check for negative number of cars");
            }
            catch (NoSuchMethodException e)
            {
                failure("missing setNumCars() method");
            }
            catch(Exception e) 
            {
                failure(e.toString());
            }
        }
        if (!failed)
            System.out.println("Passed if setNumCars error checks\n");
        // Test for proper toString() method format
        if(!failed)
        {
            System.out.println("Now testing toString method");
            String objectToString = t1.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(t1));
            if(t1.toString().equals(objectToString))
                failure("missing toString method");
        }

        if(!failed)
        {
            if(!t1.toString().contains("Number of cars is "+cArgs[0]))
                failure("" + t1.toString() + " is an invalid toString format.  Should be for \"Number of cars is "+cArgs[0] +"\"");
        }

        if(!failed)
            System.out.println("Passed toString method test\n");

        testEquals();
        if(!failed)
        {
            System.out.println("Congratulations, your ParkingLot class works correctly \n");
            System.out.println("****************************************************\n");
        }

        //********** LFHS Class Test **************************************
        // Instantiate a new LHFS object
        if (!failed)
        {
            System.out.println("Now testing your LFHS class: \n");
        }

        if (!failed)
        {
            try
            {
                c2 = Class.forName("LFHS");
                constructor2 = c2.getConstructor(new Class[] {});
                t3 = constructor2.newInstance(cArgs2);

            }
            catch (NoClassDefFoundError e)
            {
                failure("Epic Failure: missing LFHS class");
            }
            catch (ClassNotFoundException e)
            {
                failure("Epic Failure: missing LFHS class");
            }
            catch (Exception e) { failure(e.toString());}
        }

        if(!failed)
        {
            System.out.println("Now testing fillLot method");
            try
            {
                Method m = c2.getDeclaredMethod("fillLot");
                Object tempPL = m.invoke(t3);
                Method m2 = c.getDeclaredMethod("getNumCars");
                int tempNum = (int)m2.invoke(tempPL);

                if(tempNum != 400)

                    failure("The number of cars is " + tempNum + " but should be 400");
            }
            catch (NoSuchMethodException e)
            {
                failure("missing fillLot method");
            }
            catch (Exception e) { failure(e.toString());}
        }
        if (!failed)
            System.out.println("Passed fillLot method\n");

        if(!failed)
        {
            System.out.println("Congratulations, your LHFS class works correctly \n");
            System.out.println("****************************************************\n");
        }

        if(!failed)
            System.out.println("Yay! You have successfully completed the ParkingLot Project!");
        else
            System.out.println("\nBummer.  Try again.");
    }

    private void testEquals() {
        if(!failed)
        {
            System.out.println("Testing equals method");
            try
            {
                Constructor constructor = c.getConstructor(new Class[] {int.class});
                Object temp1 = constructor.newInstance(new Object[] {cArgs[0]});
                Object temp2 = constructor.newInstance(new Object[] {(int)cArgs[0]+10});
                Object temp3 = constructor.newInstance(new Object[] {cArgs[0]});
                if(!t1.equals(temp1))
                    failure("\n" + t1.toString() + "\nshould equal\n" + temp1.toString() + "\n(maybe missing equals method?)");
                else if (t1.equals(temp2))
                    failure("\n" + t1.toString() + "\nshould not equal\n" + temp2.toString() );

            }
            catch (Exception e)
            {
                failure(e.toString());
            }
        }
        if (!failed) 
            System.out.println("Passed  equals method test\n");
    }

    private void failure(String str)
    {
        System.out.println("*** Failed: " + str);
        failed = true;
        fail(str);
    }

}
