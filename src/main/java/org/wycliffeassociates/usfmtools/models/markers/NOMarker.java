package org.wycliffeassociates.usfmtools.models.markers;



import java.util.*;

/** 
 Normal Text
*/
public class NOMarker extends Marker
{
	@Override
	public String getIdentifier()
	{
		return "no";
	}
	@Override
	public String PreProcess(String input)
	{
		return input.trim();
	}
	@Override
	public ArrayList<java.lang.Class> getAllowedContents()
	{
		return new ArrayList<java.lang.Class>(Arrays.asList(TextBlock.class));
	}
}
