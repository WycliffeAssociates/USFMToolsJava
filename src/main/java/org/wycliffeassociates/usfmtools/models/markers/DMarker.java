package org.wycliffeassociates.usfmtools.models.markers;



import java.util.*;

/** 
 A descriptive title marker
*/
public class DMarker extends Marker
{
	public String Description;
	@Override
	public String getIdentifier()
	{
		return "d";
	}
	@Override
	public String PreProcess(String input)
	{
		Description = input.trim();
		return "";
	}
	@Override
	public ArrayList<java.lang.Class> getAllowedContents()
	{
		return new ArrayList<java.lang.Class>(Arrays.asList(FMarker.class, FEndMarker.class));
	}
}
