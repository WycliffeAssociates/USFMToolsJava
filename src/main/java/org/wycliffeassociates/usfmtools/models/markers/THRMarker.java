package org.wycliffeassociates.usfmtools.models.markers;



import java.util.*;

/** 
 Table Header Marker (Right-Aligned)
*/
public class THRMarker extends Marker
{
	public int ColumnPosition = 1;
	@Override
	public String getIdentifier()
	{
		return "thr";
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
