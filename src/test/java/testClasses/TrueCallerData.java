package testClasses;

public class TrueCallerData 
{
	private String profileTag;
	private String mobileNumber;
	
	public TrueCallerData(String mobileNumber, String profileTag)
	{
		this.profileTag = profileTag;
		this.mobileNumber = mobileNumber;
	}
	
	public void setMobileNumber(String mobileNumber)
	{
		this.mobileNumber = mobileNumber;
	}
	
	public String getMobileNumber()
	{
		return mobileNumber;
	}
	
	public void setProfileTag(String profileTag)
	{
		this.profileTag = profileTag;
	}
	
	public String getProfileTag()
	{
		return profileTag;
	}

}
