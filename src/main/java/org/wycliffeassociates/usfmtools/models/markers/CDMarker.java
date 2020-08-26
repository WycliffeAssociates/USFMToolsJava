package org.wycliffeassociates.usfmtools.models.markers;




/** 
 Chapter description marker
*/
public class CDMarker extends Marker
{
	public String Description;
	@Override
	public String getIdentifier()
	{
		return "cd";
	}
	@Override
	public String PreProcess(String input)
	{
		Description = input;
		return "";
	}
}
