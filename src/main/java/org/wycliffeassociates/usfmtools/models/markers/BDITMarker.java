package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/** 
 Bold-italic text
*/
public class BDITMarker extends Marker
{
	@Override
	public String getIdentifier()
	{
		return "bdit";
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
