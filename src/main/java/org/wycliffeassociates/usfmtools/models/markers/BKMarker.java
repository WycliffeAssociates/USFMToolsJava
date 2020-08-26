package org.wycliffeassociates.usfmtools.models.markers;

/** 
 Quoted Book Title Marker
*/
public class BKMarker extends Marker
{
	public String BookTitle;
	@Override
	public String getIdentifier()
	{
		return "bk";
	}
	@Override
	public String PreProcess(String input)
	{
		BookTitle = input.trim();
		return "";
	}
}
