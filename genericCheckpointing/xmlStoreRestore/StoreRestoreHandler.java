package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.XMLSerialization;
import genericCheckpointing.util.XMLDeserialization;
import genericCheckpointing.util.SerStrategy;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StoreRestoreHandler implements InvocationHandler
{
	private String filename;
	private File file = null;
	private BufferedWriter bw;
	private Scanner sc;
	private String readLine = "";

	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable
	{
		Object result = null;
		if(m.getName().equals("writeObj"))
		{
			if(args[2].equals("XML"))
			{
				serializeData((SerializableObject) args[0], new XMLSerialization(this));
			}
		}
		else if(m.getName().equals("readObj"))
		{
			if(args[0].equals("XML"))
			{
				XMLDeserialization xmldes = new XMLDeserialization(this);
				deserializeData(xmldes);
				return xmldes.getObject();
			}
		}
	    
        	return result;
	}

	public void openFileToWrite(String filename)
	{
		if(this.file == null)
		{
			this.file = new File(filename);
			try{
				if (!this.file.exists())
				{
					this.file.createNewFile();
				}  
				FileWriter fw = new FileWriter(this.file);
				this.bw = new BufferedWriter(fw);
			}
			catch(IOException e)
			{
				System.exit(-1);
			}
		}
	}


	public void openFileToRead(String filename)
	{
		file = new File(filename);
		try {
			sc = new Scanner(file);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void closeFile()
	{
    		try
		{
			if(this.bw != null)
			{
				this.bw.close();
			}
		}
		catch(IOException e)
		{
    		}
	}

	public void serializeData(SerializableObject sObject, SerStrategy serStrategy)
	{
		serStrategy.processInput(sObject);
		//System.out.println(content);
		//this.writeToFile(result);
  	}

	public void deserializeData(SerStrategy serStrategy)
	{
		serStrategy.processInput(null);
		//String result = readFromFile();
		//System.out.println(content);
		//this.writeToFile(result);
  	}


	public void writeToFile(String s1)
	{
		try
		{
			bw.write(s1);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public String readFromFile()
	{
		//sc = new Scanner(file);
		readLine = "";
		while (sc.hasNextLine())
		{
			String s1 = sc.nextLine();
			readLine += s1 + "\n";
			if(s1.equals("</DPSerialization>"))
			{
				break;
			}
		}
		return readLine;
		
	}

}
