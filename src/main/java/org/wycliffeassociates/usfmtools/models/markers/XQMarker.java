package org.wycliffeassociates.usfmtools.models.markers;



import java.util.*;

/** 
 A quotation from the scripture text
*/
public class XQMarker extends Marker
{
	@Override
	public String getIdentifier()
	{
		return "xq";
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
