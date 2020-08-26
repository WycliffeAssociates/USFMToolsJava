package org.wycliffeassociates.usfmtools.models.markers;



import java.util.*;

/** 
 Section marker
*/
public class SMarker extends Marker
{
	public int Weight = 1;
	public String Text;
	@Override
	public String getIdentifier()
	{
		return "s";
	}
	@Override
	public String PreProcess(String input)
	{
		Text = tangible.StringHelper.trimStart(input);
		return "";
	}
	@Override
	public ArrayList<java.lang.Class> getAllowedContents()
	{
		return new ArrayList<java.lang.Class>(Arrays.asList(RMarker.class, FMarker.class, FEndMarker.class));
	}
}
