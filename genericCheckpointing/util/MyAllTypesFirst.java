package genericCheckpointing.util;

public class MyAllTypesFirst extends SerializableObject
{

	private int myInt;
	private int myOtherInt;
	private long myLong;
	private boolean myBool;
	private String myString;

	public MyAllTypesFirst()
	{
		this.myInt = 0;
		this.myOtherInt = 0;
		this.myLong = 0;
		this.myBool = false;
		this.myString = "";
	}

	public MyAllTypesFirst(int value1,int value2, long value3, boolean value4,String value5)
	{
		this.myInt = value1;
		this.myOtherInt = value2;
		this.myLong = value3;
		this.myBool = value4;
		this.myString = value5;
	}

	public void setmyInt(int value)
	{
		this.myInt = value;
	}

	public void setmyOtherInt(int value)
	{
		this.myOtherInt = value;
	}

	public void setmyLong(long value)
	{
		this.myLong = value;
	}

	public void setmyBool(boolean value)
	{
		this.myBool = value;
	}

	public void setmyString(String value)
	{
		this.myString = value;
	}

	public int getmyInt()
	{
		return this.myInt;
	}

	public int getmyOtherInt()
	{
		return this.myOtherInt;
	}

	public long getmyLong()
	{
		return this.myLong;
	}

	public boolean getmyBool()
	{
		return this.myBool;
	}

	public String getmyString()
	{
		return this.myString;
	}

	public String toString()
	{
		String output = "Type : MyAllTypesFirst \n";
		output += "myInt = " + this.myInt + "\n";
		output += "myLong = " + this.myLong + "\n";
		output += "myString = " + this.myString + "\n";
		output += "myBool = " + this.myBool + "\n";
		output += "myOtherInt = " + this.myOtherInt + "\n";
		return output;
	}

	public boolean equals(Object obj)
	{
		MyAllTypesFirst tempObj = (MyAllTypesFirst) obj;
		return (this.myInt == tempObj.myInt ) && (this.myLong == tempObj.myLong ) && (this.myBool == tempObj.myBool) && (this.myString.equals(tempObj.myString)) && (this.myOtherInt == tempObj.myOtherInt);

	}
	
}
