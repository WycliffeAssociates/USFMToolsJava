package org.wycliffeassociates.usfmtools.models.markers;



import java.util.*;

/** 
 List Entry Marker
*/
public class LIMarker extends Marker
{
	public int Depth = 1;
	@Override
	public String getIdentifier()
	{
		return "li";
	}
	@Override
	public String PreProcess(String input)
	{
		return input.trim();
	}
	@Override
	public ArrayList<java.lang.Class> getAllowedContents()
	{
		return new ArrayList<java.lang.Class>(Arrays.asList(VMarker.class, TextBlock.class));
	}
}
