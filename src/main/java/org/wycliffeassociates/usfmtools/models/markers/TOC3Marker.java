package org.wycliffeassociates.usfmtools.models.markers;

/** 
 Tag for book abbreviation
*/
public class TOC3Marker extends Marker
{
	public String BookAbbreviation;
	@Override
	public String getIdentifier()
	{
		return "toc3";
	}
	@Override
	public String PreProcess(String input)
	{
		BookAbbreviation = input.trim();
		return "";
	}
}
