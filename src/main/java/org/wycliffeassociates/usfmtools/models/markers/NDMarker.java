package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/** 
 Name of God (name of Deity)
*/
public class NDMarker extends Marker
{
	@Override
	public String getIdentifier()
	{
		return "nd";
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
