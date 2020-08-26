package org.wycliffeassociates.usfmtools.models.markers;




/** 
 Alternate chapter number
*/
public class CAMarker extends Marker
{
	public String AltChapterNumber;

	@Override
	public String getIdentifier()
	{
		return "ca";
	}
	@Override
	public String PreProcess(String input)
	{
		AltChapterNumber = input.trim();
		return "";
	}
}
