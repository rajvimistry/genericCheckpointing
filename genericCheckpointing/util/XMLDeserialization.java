package genericCheckpointing.util;

import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;
import genericCheckpointing.xmlStoreRestore.SerializedTypes;
import java.lang.reflect.Type;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.ClassNotFoundException;
import java.lang.NoSuchMethodException;
import java.lang.reflect.InvocationTargetException;
import java.lang.InstantiationException;
import java.lang.IllegalAccessException;

public class XMLDeserialization implements SerStrategy
{
	private StoreRestoreHandler storeHandler;
	private Object obj = null;
	
	public XMLDeserialization(StoreRestoreHandler handler)
	{
		this.storeHandler = handler;
	}
	
	public void processInput(SerializableObject sObject)
	{

		try
		{

			String result = storeHandler.readFromFile();
			//System.out.println("Ressult: "+result);
			String[] temp = result.split("\n");
//System.out.println("The temp length is " + temp.length);
			String className = SerializedTypes.getClassName(temp[1]);
//System.out.println("The class name is " + className);
			Class<?> myClass = Class.forName(className);
			Constructor<?> cons = myClass.getConstructor();
			obj = cons.newInstance();

			String fieldName = null;
			String fieldType = null;
			String value = null;
			String methodName = null;
			Method setterMethod = null;
			Object tempObject = null;

			for(int i=2;i<(temp.length)-2;i++)
			{
				//System.out.println(temp[i]);
				fieldName = SerializedTypes.getFieldName(temp[i]);
				fieldType = SerializedTypes.getFieldType(temp[i]);
				//System.out.println("fieldName: "+fieldName);
				//System.out.println("fieldtype: "+fieldType);
				value = SerializedTypes.getValue(temp[i]);
				//System.out.println("Value: "+value);
				methodName = "set" + fieldName;

				if(fieldType.equals("int"))
				{
					setterMethod = myClass.getMethod(methodName,int.class);
					tempObject = Integer.parseInt(value);
				}
				else if(fieldType.equals("float"))
				{
					setterMethod = myClass.getMethod(methodName,float.class);
					tempObject = Float.parseFloat(value);
				}
				else if(fieldType.equals("char"))
				{
					setterMethod = myClass.getMethod(methodName,char.class);
					tempObject = value.charAt(0);
				}
				else if(fieldType.equals("long"))
				{
					setterMethod = myClass.getMethod(methodName,long.class);
					tempObject = Long.parseLong(value);
				}
				else if(fieldType.equals("double"))
				{
					setterMethod = myClass.getMethod(methodName,double.class);
					tempObject = Double.parseDouble(value);
				}
				else if(fieldType.equals("boolean"))
				{
					setterMethod = myClass.getMethod(methodName,boolean.class);
					tempObject = Boolean.parseBoolean(value);
				}
				else if(fieldType.equals("short"))
				{
					setterMethod = myClass.getMethod(methodName,short.class);
					tempObject = Short.parseShort(value);
				}
				else if(fieldType.equals("string"))
				{
					setterMethod = myClass.getMethod(methodName,String.class);
					tempObject = value;
				}
				//System.out.println("Object: "+obj+"TempObj: "+tempObject);
				setterMethod.invoke(obj,tempObject);
			}
			

		}
		catch(ClassNotFoundException error)
		{
			System.out.println(error);
			error.printStackTrace(System.out);
		}
		catch(NoSuchMethodException error)
		{
			System.out.println(error);
			error.printStackTrace(System.out);
		}
		catch(InstantiationException error)
		{
			System.out.println(error);
			error.printStackTrace(System.out);
		}
		catch(IllegalAccessException error)
		{
			System.out.println(error);
			error.printStackTrace(System.out);
		}
		catch(InvocationTargetException error)
		{
			System.out.println(error);
			error.printStackTrace(System.out);
		}
	}

	public Object getObject()
	{
		return this.obj;
	}

}
