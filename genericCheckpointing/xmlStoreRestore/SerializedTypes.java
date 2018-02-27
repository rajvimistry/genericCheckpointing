package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.Type;

public class SerializedTypes
{

	public static String serializeInt(int value, String tagName)
	{
		String temp = "<"+tagName+" xsi:type=\""+"xsd:int"+"\">"+value+"</"+tagName+">";
		return temp;
	}

	public static String serializeFloat(float value, String tagName)
	{
		String temp = "<"+tagName+" xsi:type=\""+"xsd:float"+"\">"+value+"</"+tagName+">";
		return temp;
	}

	public static String serializeBoolean(boolean value, String tagName)
	{
		String temp = "<"+tagName+" xsi:type=\""+"xsd:boolean"+"\">"+value+"</"+tagName+">";
		return temp;
	}

	public static String serializeDouble(double value, String tagName)
	{
		String temp = "<"+tagName+" xsi:type=\""+"xsd:double"+"\">"+value+"</"+tagName+">";
		return temp;
	}

	public static String serializeLong(long value, String tagName)
	{
		String temp = "<"+tagName+" xsi:type=\""+"xsd:long"+"\">"+value+"</"+tagName+">";
		return temp;
	}

	public static String serializeString(String value, String tagName)
	{
		String temp = "<"+tagName+" xsi:type=\""+"xsd:string"+"\">"+value+"</"+tagName+">";
		return temp;
	}

	public static String serializeChar(char value, String tagName)
	{
		String temp = "<"+tagName+" xsi:type=\""+"xsd:char"+"\">"+value+"</"+tagName+">";
		return temp;
	}

	public static String serializeShort(short value, String tagName)
	{
		String temp = "<"+tagName+" xsi:type=\""+"xsd:short"+"\">"+value+"</"+tagName+">";
		return temp;
	}

	/*public static String serializeFields(String fieldName, Type fieldType, Object value)
	{
	    return String.format("    <%s xsi:type=\"xsd:%s\">%s</%s>\n", fieldName, getFieldType(fieldType), value.toString(), fieldName);
	}*/


	public static String getClassName(String s)
	{
		String[] getClassName = s.split("\"");
		return getClassName[1];
	}

	public  static String getFieldName(String s)
	{
		String[] getFieldName = s.split(" ");
		String[] getFieldNameTemp = getFieldName[0].split("<");
		//System.out.println("Here: "+getFieldNameTemp[0]+"Here: "+getFieldNameTemp[1]);
		return getFieldNameTemp[1];
	}

	public  static String getFieldType(String s)
	{
		String[] getFieldType = s.split("\"");
		//System.out.println("Here: "+getFieldType[1]);
		String[] getFieldTypeTemp = getFieldType[1].split("xsd:");
		return getFieldTypeTemp[1];
	}

	public  static String getValue(String s)
	{
		String[] getValue = s.split(">");
		String[] getValueTemp = getValue[1].split("<");
		//System.out.println(getValueTemp[0]);
		//System.out.println("Here: 1 "+ getValueTemp[1]);
		return getValueTemp[0];
	}

}
