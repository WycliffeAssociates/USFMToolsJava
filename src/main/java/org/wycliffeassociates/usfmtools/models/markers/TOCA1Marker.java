package org.wycliffeassociates.usfmtools.models.markers;




/** 
 A tag for the alternative language long table of contents
*/
public class TOCA1Marker extends Marker
{
	public String AltLongTableOfContentsText;
	@Override
	public String getIdentifier()
	{
		return "toca1";
	}
	@Override
	public String PreProcess(String input)
	{
		AltLongTableOfContentsText = input.trim();
		return "";
	}
}
