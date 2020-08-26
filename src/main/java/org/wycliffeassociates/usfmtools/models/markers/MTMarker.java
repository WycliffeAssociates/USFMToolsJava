package org.wycliffeassociates.usfmtools.models.markers;




/** 
 Major title marker
*/
public class MTMarker extends Marker
{
	public int Weight = 1;
	public String Title;
	@Override
	public String getIdentifier()
	{
		return "mt";
	}
	@Override
	public String PreProcess(String input)
	{
		Title = input.trim();
		return "";
	}
}
