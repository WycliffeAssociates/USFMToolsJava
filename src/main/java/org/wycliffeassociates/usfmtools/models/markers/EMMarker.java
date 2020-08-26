package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/** 
 Emphasis text
*/
public class EMMarker extends Marker
{
	@Override
	public String getIdentifier()
	{
		return "em";
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
