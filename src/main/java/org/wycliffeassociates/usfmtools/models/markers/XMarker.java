package org.wycliffeassociates.usfmtools.models.markers;



import java.util.*;

/** 
 Cross reference element
*/
public class XMarker extends Marker
{
	@Override
	public String getIdentifier()
	{
		return "x";
	}
	public String CrossRefCaller;

	@Override
	public String PreProcess(String input)
	{
		CrossRefCaller = input.trim();
		return "";
	}
	@Override
	public ArrayList<java.lang.Class> getAllowedContents()
	{
		return new ArrayList<java.lang.Class>(Arrays.asList(XOMarker.class, XTMarker.class, XQMarker.class, TextBlock.class));
	}
}
