package org.wycliffeassociates.usfmtools.models.markers;

/** 
 Tag for the short table of contents
*/
public class TOC2Marker extends Marker
{
	public String ShortTableOfContentsText;
	@Override
	public String getIdentifier()
	{
		return "toc2";
	}
	@Override
	public String PreProcess(String input)
	{
		ShortTableOfContentsText = input.trim();
		return "";
	}
}
