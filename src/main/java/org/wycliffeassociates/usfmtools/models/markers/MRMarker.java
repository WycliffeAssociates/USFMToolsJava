package org.wycliffeassociates.usfmtools.models.markers;



import java.util.*;

/** 
 Major section reference marker
*/
public class MRMarker extends Marker
{
	public int Weight = 1;
	public String SectionReference;
	@Override
	public String getIdentifier()
	{
		return "mr";
	}
	@Override
	public String PreProcess(String input)
	{
		SectionReference = tangible.StringHelper.trimStart(input);
		return "";
	}
	@Override
	public ArrayList<java.lang.Class> getAllowedContents()
	{
		return new ArrayList<java.lang.Class>(Arrays.asList(FMarker.class, FEndMarker.class));
	}
}
