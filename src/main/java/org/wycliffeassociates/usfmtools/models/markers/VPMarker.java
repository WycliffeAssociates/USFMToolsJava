package org.wycliffeassociates.usfmtools.models.markers;

/** 
 Marker for Custom Verse Number
*/
public class VPMarker extends Marker
{
	public String VerseCharacter;
	@Override
	public String getIdentifier()
	{
		return "vp";
	}
	@Override
	public String PreProcess(String input)
	{
		VerseCharacter = input.trim();
		return "";
	}
}
