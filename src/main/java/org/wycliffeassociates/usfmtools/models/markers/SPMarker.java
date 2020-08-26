package org.wycliffeassociates.usfmtools.models.markers;




/** 
 A speaker Marker (Used mostly in Job and Songs of Solomon)
*/
public class SPMarker extends Marker
{
	public String Speaker;
	@Override
	public String getIdentifier()
	{
		return "sp";
	}
	@Override
	public String PreProcess(String input)
	{
		Speaker = input.trim();
		return "";
	}
}
