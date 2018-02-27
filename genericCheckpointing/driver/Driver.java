package genericCheckpointing.driver;

import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.RestoreI;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.SerializableObject;
import java.lang.Character;

// import the other types used in this file

public class Driver {
    
    public static void main(String[] args) {


	if(args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}"))
	{
		System.out.println("Invalid Arguments");
		System.exit(0);
	}



	String mode = args[0];
	int n = Integer.parseInt(args[1]);
	String filename = args[2];
	int NUM_OF_OBJECTS = n;
	
	SerializableObject[] serialization = new SerializableObject[2 * NUM_OF_OBJECTS];
	SerializableObject[] deserialization = new SerializableObject[2 * NUM_OF_OBJECTS];


	// FIXME: read the value of checkpointFile from the command line
	
	ProxyCreator pc = new ProxyCreator();
	StoreRestoreHandler storeRestoreHandler = new StoreRestoreHandler();
	
	// create an instance of StoreRestoreHandler (which implements
	// the InvocationHandler
	
	// create a proxy
	StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(
								 new Class[] {
								     StoreI.class, RestoreI.class
								 }, 
								 storeRestoreHandler
								 );
	
	if(args[0].equals("serdeser"))
	{
		storeRestoreHandler.openFileToWrite(filename);
		MyAllTypesFirst[] array1 = new MyAllTypesFirst[NUM_OF_OBJECTS];
		MyAllTypesSecond[] array2 = new MyAllTypesSecond[NUM_OF_OBJECTS];
		MyAllTypesFirst myFirst;
		MyAllTypesSecond  mySecond;

		for (int i=0; i<NUM_OF_OBJECTS; i++)
		{
			// FIXME: create these object instances correctly using an explicit value constructor
			// use the index variable of this loop to change the values of the arguments to these constructors
			myFirst = new MyAllTypesFirst(i,i+2,9999*i,(i%3==0),"String "+i);
			mySecond = new MyAllTypesSecond((double) i*9 , (double) i*5, (short) i,Character.forDigit(i,10),(float)i/3);


			// FIXME: store myFirst and mySecond in the data structure
			((StoreI) cpointRef).writeObj(myFirst,1, "XML");
			((StoreI) cpointRef).writeObj(mySecond,1, "XML");

			serialization[2 * i] = myFirst;
			serialization[(2 * i) + 1] = mySecond;

		}storeRestoreHandler.closeFile();

		storeRestoreHandler.openFileToRead(filename);
		SerializableObject myRecordRet;
		for (int j=0; j<2*NUM_OF_OBJECTS; j++)
		{
			myRecordRet = ((RestoreI) cpointRef).readObj("XML");
			//System.out.println("myRecordRet: "+myRecordRet);
			deserialization[j] = myRecordRet;
		}storeRestoreHandler.closeFile();


	}
	else
	{
		storeRestoreHandler.openFileToRead(filename);
		SerializableObject myRecordRet;
		for (int j=0; j<2*NUM_OF_OBJECTS; j++)
		{
			myRecordRet = ((RestoreI) cpointRef).readObj("XML");
			//System.out.println("myRecordRet: "+myRecordRet);
			deserialization[j] = myRecordRet;
		}storeRestoreHandler.closeFile();

	}	
	

	if(args[0].equals("serdeser"))
	{
		int mismatched = 0;
		for(int i =0; i < 2 * NUM_OF_OBJECTS; i ++)
		{
			SerializableObject obj1 = serialization[i];
			SerializableObject obj2 = deserialization[i];
			
			//System.out.println("***Src: "+obj1+"dest: "+obj2+"***");

			if(!obj1.equals(obj2))
			{
				mismatched ++;
				//System.out.println("obj1: "+obj1+"obj2: "+obj2);
			}
		}
		System.out.println("Mismatched Objects are "+mismatched);
	}
	else
	{
		System.out.println("The deserialized objects are ");
		for(int i = 0; i < 2 * NUM_OF_OBJECTS; i ++){
		System.out.println(deserialization[i]);}
	}    
    }
}
