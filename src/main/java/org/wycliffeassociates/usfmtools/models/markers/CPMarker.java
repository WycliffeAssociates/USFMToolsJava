package org.wycliffeassociates.usfmtools.models.markers;




/** 
 Published Chapter Marker (for when it isn't an english number)
*/
public class CPMarker extends Marker
{
	public String PublishedChapterMarker;
	@Override
	public String getIdentifier()
	{
		return "cp";
	}
	@Override
	public String PreProcess(String input)
	{
		PublishedChapterMarker = input.trim();
		return "";
	}
}
