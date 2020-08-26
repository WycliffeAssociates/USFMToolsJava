package org.wycliffeassociates.usfmtools.models.markers;

/** 
 Footnote origin reference
*/
public class FRMarker extends Marker
{
	@Override
	public String getIdentifier()
	{
		return "fr";
	}
	public String VerseReference;


	@Override
	public String PreProcess(String input)
	{
		VerseReference = input.trim();
		return "";
	}
}
