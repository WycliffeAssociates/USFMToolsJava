package org.wycliffeassociates.usfmtools.models.markers;




/** 
 Introduction major title 
*/
public class IMTMarker extends Marker
{
	public int Weight = 1;
	public String IntroTitle;
	@Override
	public String getIdentifier()
	{
		return "imt";
	}
	@Override
	public String PreProcess(String input)
	{
		IntroTitle = input.trim();
		return "";
	}
}
