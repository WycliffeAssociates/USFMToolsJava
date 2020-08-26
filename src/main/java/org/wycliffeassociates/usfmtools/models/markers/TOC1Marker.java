package org.wycliffeassociates.usfmtools.models.markers;




/** 
 A tag for the long table of contents
*/
public class TOC1Marker extends Marker
{
	public String LongTableOfContentsText;
	@Override
	public String getIdentifier()
	{
		return "toc1";
	}
	@Override
	public String PreProcess(String input)
	{
		LongTableOfContentsText = input.trim();
		return "";
	}
}
