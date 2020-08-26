package org.wycliffeassociates.usfmtools.models.markers;




/** 
 Introduction outline title
*/
public class IOTMarker extends Marker
{
	public String Title;
	@Override
	public String getIdentifier()
	{
		return "iot";
	}
	@Override
	public String PreProcess(String input)
	{
		Title = input.trim();
		return "";
	}
}
