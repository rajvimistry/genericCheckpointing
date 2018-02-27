package genericCheckpointing.util;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import genericCheckpointing.xmlStoreRestore.SerializedTypes;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

public class XMLSerialization implements SerStrategy {

	private StoreRestoreHandler storeHandler;
	
	public XMLSerialization(StoreRestoreHandler handler)
	{
		this.storeHandler = handler;
	}
     public void processInput(SerializableObject sObject) {

	Class<?> cls = sObject.getClass();
	String XMLString = "<DPSerialization>\n<complexType xsi:type=\""+cls.getName()+"\">";
	//Field field = null;
	//String fieldName = field.getName();
	for(Field field:cls.getDeclaredFields()){
	try{ String fieldName = field.getName();
	if (field.getType() == int.class)
	{
		String methodName = "get" + fieldName;
		Method getterMethod = cls.getMethod(methodName);
		Object invokeRet = getterMethod.invoke(sObject);
		int value = (int)invokeRet;
		if(value<10){XMLString += "";}
		else{
		XMLString += "\n"+SerializedTypes.serializeInt(value,fieldName);}
		//XMLString += ""+SerializedTypes.serializeFields(field.getName(), field.getType(), invokeRet) +"\n";
	}

	else if (field.getType() == float.class)
	{
		String methodName = "get" + fieldName;
		Method getterMethod = cls.getMethod(methodName);
		Object invokeRet = getterMethod.invoke(sObject);
		float value = (float)invokeRet;
		XMLString += "\n"+SerializedTypes.serializeFloat(value,fieldName);
		//XMLString += ""+SerializedTypes.serializeFields(field.getName(), field.getType(), invokeRet) +"\n";
	}

	else if (field.getType() == double.class)
	{
		String methodName = "get" + fieldName;
		Method getterMethod = cls.getMethod(methodName);
		Object invokeRet = getterMethod.invoke(sObject);
		double value = (double)invokeRet;
		if(value<10){XMLString+="";}
		else{
		XMLString += "\n"+SerializedTypes.serializeDouble(value,fieldName);}
		//XMLString += ""+SerializedTypes.serializeFields(field.getName(), field.getType(), invokeRet) +"\n";
	}

	else if (field.getType() == boolean.class)
	{
		String methodName = "get" + fieldName;
		Method getterMethod = cls.getMethod(methodName);
		Object invokeRet = getterMethod.invoke(sObject);
		boolean value = (boolean)invokeRet;
		XMLString += "\n"+SerializedTypes.serializeBoolean(value,fieldName);
		//XMLString += ""+SerializedTypes.serializeFields(field.getName(), field.getType(), invokeRet) +"\n";
	}

	else if (field.getType() == short.class)
	{
		String methodName = "get" + fieldName;
		Method getterMethod = cls.getMethod(methodName);
		Object invokeRet = getterMethod.invoke(sObject);
		short value = (short)invokeRet;
		XMLString += "\n"+SerializedTypes.serializeShort(value,fieldName);
		//XMLString += ""+SerializedTypes.serializeFields(field.getName(), field.getType(), invokeRet) +"\n";
	}

	else if (field.getType() == long.class)
	{
		String methodName = "get" + fieldName;
		Method getterMethod = cls.getMethod(methodName);
		Object invokeRet = getterMethod.invoke(sObject);
		long value = (long)invokeRet;
		if(value<10){XMLString += "";}
		else{
		XMLString += "\n"+SerializedTypes.serializeLong(value,fieldName);}
		//XMLString += ""+SerializedTypes.serializeFields(field.getName(), field.getType(), invokeRet) +"\n";
	}

	else if (field.getType() == char.class)
	{
		String methodName = "get" + fieldName;
		Method getterMethod = cls.getMethod(methodName);
		Object invokeRet = getterMethod.invoke(sObject);
		char value = (char)invokeRet;
		XMLString += "\n"+SerializedTypes.serializeChar(value,fieldName);
		//XMLString += ""+SerializedTypes.serializeFields(field.getName(), field.getType(), invokeRet) +"\n";
	}

	else if (field.getType() == String.class)
	{
		String methodName = "get" + fieldName;
		Method getterMethod = cls.getMethod(methodName);
		Object invokeRet = getterMethod.invoke(sObject);
		String value = (String)invokeRet;
		XMLString += "\n"+SerializedTypes.serializeString(value,fieldName);
		//XMLString += ""+SerializedTypes.serializeFields(field.getName(), field.getType(), invokeRet) +"\n";
	}
	}
	catch(IllegalAccessException error){
        // Ignore
        System.out.println(error);
      }catch(NoSuchMethodException error){
        // Ignore
        System.out.println(error);
      }catch(InvocationTargetException error){
        // Ignore
        System.out.println(error);
      }

  }
	XMLString += "\n</complexType>\n</DPSerialization>\n";
	//System.out.println(XMLString);
	this.storeHandler.writeToFile(XMLString);
	//return XMLString;
}
}

