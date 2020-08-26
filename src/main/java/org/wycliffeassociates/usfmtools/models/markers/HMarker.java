package org.wycliffeassociates.usfmtools.models.markers;




/** 
 Running header marker
*/
public class HMarker extends Marker
{
	public String HeaderText;
	@Override
	public String getIdentifier()
	{
		return "h";
	}

	@Override
	public String PreProcess(String input)
	{
		HeaderText = input.trim();
		return "";
	}

}
