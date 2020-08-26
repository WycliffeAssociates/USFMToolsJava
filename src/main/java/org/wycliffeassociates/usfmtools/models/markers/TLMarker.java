package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/** 
 Transliterated (or foreign) word(s)
*/
public class TLMarker extends Marker
{
	@Override
	public String getIdentifier()
	{
		return "tl";
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
