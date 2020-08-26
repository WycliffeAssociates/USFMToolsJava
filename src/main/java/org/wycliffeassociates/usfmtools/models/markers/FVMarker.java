package org.wycliffeassociates.usfmtools.models.markers;




/** 
 Footnote verse number
*/
public class FVMarker extends Marker
{
	public String VerseCharacter;
	@Override
	public String getIdentifier()
	{
		return "fv";
	}
	@Override
	public String PreProcess(String input)
	{
		VerseCharacter = input.trim();
		return "";
	}
}
