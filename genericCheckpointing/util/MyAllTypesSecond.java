package genericCheckpointing.util;

public class MyAllTypesSecond extends SerializableObject
{
	private double myDoubleT;
	private float myFloatT;
	private short myShortT;
	private char myCharT;
	private double myOtherDoubleT;


	public MyAllTypesSecond()
	{
		this.myDoubleT = 0;
		this.myOtherDoubleT = 0;
		this.myShortT = 0;
		this.myCharT = 'a';
		this.myFloatT = 0;
	}

	public MyAllTypesSecond(double value1,double value2, short value3, char value4,float value5)
	{
		this.myDoubleT = value1;
		this.myOtherDoubleT = value2;
		this.myShortT = value3;
		this.myCharT = value4;
		this.myFloatT = value5;
	}

	public void setmyDoubleT(double value)
	{
		this.myDoubleT = value;
	}

	public void setmyOtherDoubleT(double value)
	{
		this.myOtherDoubleT = value;
	}

	public void setmyShortT(short value)
	{
		this.myShortT = value;
	}

	public void setmyCharT(char value)
	{
		this.myCharT = value;
	}

	public void setmyFloatT(float value)
	{
		this.myFloatT = value;
	}

	public double getmyDoubleT()
	{
		return this.myDoubleT;
	}

	public double getmyOtherDoubleT()
	{
		return this.myOtherDoubleT;
	}

	public short getmyShortT()
	{
		return this.myShortT;
	}

	public char getmyCharT()
	{
		return this.myCharT;
	}

	public float getmyFloatT()
	{
		return this.myFloatT;
	}

	public String toString()
	{
		String output = "Type : MyAllTypesSecond \n";
		output += "myDoubleT = " + this.myDoubleT + "\n";
		output += "myOtherDoubleT = " + this.myOtherDoubleT + "\n";
		output += "myShortT = " + this.myShortT + "\n";
		output += "myCharT = " + this.myCharT + "\n";
		output += "myFloatT = " + this.myFloatT + "\n";
		return output;
	}

	public boolean equals(Object obj)
	{

		MyAllTypesSecond tempObj = (MyAllTypesSecond) obj;
		return (this.myDoubleT == tempObj.myDoubleT ) && (this.myFloatT == tempObj.myFloatT ) && (this.myShortT == tempObj.myShortT) && (this.myCharT == tempObj.myCharT) && (this.myOtherDoubleT == tempObj.myOtherDoubleT);

	}
}
