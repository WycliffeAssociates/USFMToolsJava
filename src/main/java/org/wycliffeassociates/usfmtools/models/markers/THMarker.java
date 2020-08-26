package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/** 
 Table Header Marker
*/
public class THMarker extends Marker
{
	public int ColumnPosition = 1;
	@Override
	public String getIdentifier()
	{
		return "th";
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
