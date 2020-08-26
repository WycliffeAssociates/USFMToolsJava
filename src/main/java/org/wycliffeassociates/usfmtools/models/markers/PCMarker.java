package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/** 
 Centered paragraph
*/
public class PCMarker extends Marker
{
	@Override
	public String getIdentifier()
	{
		return "pc";
	}
	@Override
	public String PreProcess(String input)
	{
		return tangible.StringHelper.trimStart(input);
	}
	@Override
	public ArrayList<java.lang.Class> getAllowedContents()
	{
		return new ArrayList<java.lang.Class>(Arrays.asList(VMarker.class, BMarker.class, SPMarker.class, TextBlock.class, FMarker.class, FEndMarker.class, LIMarker.class, QMarker.class));
	}
}
