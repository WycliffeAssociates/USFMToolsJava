package org.wycliffeassociates.usfmtools.models.markers;



import java.util.*;

/** 
 Target reference(s)
*/
public class XTMarker extends Marker
{
	@Override
	public String getIdentifier()
	{
		return "xt";
	}
	@Override
	public String PreProcess(String input)
	{
		return tangible.StringHelper.trimStart(input);
	}
	@Override
	public ArrayList<java.lang.Class> getAllowedContents()
	{
		return new ArrayList<java.lang.Class>(Arrays.asList(TextBlock.class));
	}
}
