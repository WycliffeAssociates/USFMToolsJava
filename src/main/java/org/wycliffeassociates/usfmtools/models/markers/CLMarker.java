package org.wycliffeassociates.usfmtools.models.markers;




/** 
 Chapter label marker
*/
public class CLMarker extends Marker
{
	public String Label;
	@Override
	public String getIdentifier()
	{
		return "cl";
	}
	@Override
	public String PreProcess(String input)
	{
		Label = input.trim();
		return "";
	}
}
