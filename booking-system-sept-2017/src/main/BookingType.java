package main;

public class BookingType {
	String bookingType;
	int bookingLength;
	
	public BookingType(String bookingType, int bookingLength)
	{
		setBookingType(bookingType);
		setBookingLength(bookingLength);
	}

	public void setBookingType(String bookingType)
	{
		this.bookingType = bookingType;
	}
	
	public void setBookingLength(int bookingLength)
	{
		this.bookingLength = bookingLength;
	}
	
	public String getBookingType()
	{
		return this.bookingType;
	}
	
	public int getBookingLength()
	{
		return this.bookingLength;
	}
}
