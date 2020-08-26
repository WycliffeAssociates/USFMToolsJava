package org.wycliffeassociates.usfmtools.models.markers;

/** 
 Acrostic heading for poetry
*/
public class QAMarker extends Marker
{
	/** 
	 Heading for the poetry
	*/
	public String Heading;
	@Override
	public String getIdentifier()
	{
		return "qa";
	}
	@Override
	public String PreProcess(String input)
	{
		Heading = input.trim();
		return "";
	}
}
