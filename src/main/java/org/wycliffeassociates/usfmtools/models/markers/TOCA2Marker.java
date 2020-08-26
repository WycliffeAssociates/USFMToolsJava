package org.wycliffeassociates.usfmtools.models.markers;




/** 
 Tag for the Alternative language short table of contents
*/
public class TOCA2Marker extends Marker
{
	public String AltShortTableOfContentsText;
	@Override
	public String getIdentifier()
	{
		return "toca2";
	}
	@Override
	public String PreProcess(String input)
	{
		AltShortTableOfContentsText = input.trim();
		return "";
	}
}
