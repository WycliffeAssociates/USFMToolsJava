package org.wycliffeassociates.usfmtools.models.markers;




/** 
 Introduction section heading
*/
public class ISMarker extends Marker
{
	public int Weight = 1;
	public String Heading;
	@Override
	public String getIdentifier()
	{
		return "is";
	}
	@Override
	public String PreProcess(String input)
	{
		Heading = input.trim();
		return "";
	}
}
