package org.wycliffeassociates.usfmtools.models.markers;




/** 
 Alternate verse number
*/
public class VAMarker extends Marker
{
	public String AltVerseNumber;

	@Override
	public String getIdentifier()
	{
		return "va";
	}
	@Override
	public String PreProcess(String input)
	{
		AltVerseNumber = input.trim();
		return "";
	}
}
