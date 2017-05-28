package main;

public class BookingType {
	String bookingType;
	int bookingLength;
	
	/**
	 * Constructor for booking type.
	 * @param bookingType booking type to be set.
	 * @param bookingLength booking length to be set.
	 */
	public BookingType(String bookingType, int bookingLength)
	{
		setBookingType(bookingType);
		setBookingLength(bookingLength);
	}

	/**
	 * Mutator method for booking type.
	 * @param bookingType
	 */
	public void setBookingType(String bookingType)
	{
		this.bookingType = bookingType;
	}
	
	/**
	 * Mutator method for booking length.
	 * @param bookingLength
	 */
	public void setBookingLength(int bookingLength)
	{
		this.bookingLength = bookingLength;
	}
	
	/**
	 * Accessor method for booking type.
	 * @return
	 */
	public String getBookingType()
	{
		return this.bookingType;
	}
	
	/**
	 * Accessor method for booking length.
	 * @return
	 */
	public int getBookingLength()
	{
		return this.bookingLength;
	}
}
