package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/** 
 Translator's addition
*/
public class ADDMarker extends Marker
{
	@Override
	public String getIdentifier()
	{
		return "add";
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
