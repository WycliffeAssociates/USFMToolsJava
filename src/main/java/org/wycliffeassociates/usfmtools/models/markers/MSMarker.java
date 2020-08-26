package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/** 
 Major heading marker
*/
public class MSMarker extends Marker
{
	public int Weight = 1;
	public String Heading;
	@Override
	public String getIdentifier()
	{
		return "ms";
	}
	@Override
	public String PreProcess(String input)
	{
		Heading = tangible.StringHelper.trimStart(input);
		return "";
	}
	@Override
	public ArrayList<java.lang.Class> getAllowedContents()
	{
		return new ArrayList<java.lang.Class>(Arrays.asList(MRMarker.class));
	}
}
