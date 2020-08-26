package org.wycliffeassociates.usfmtools.models.markers;



import java.util.*;

/** 
 Introduction right-aligned paragraph
*/
public class IPRMarker extends Marker
{
	@Override
	public String getIdentifier()
	{
		return "ipr";
	}
	@Override
	public String PreProcess(String input)
	{
		return input.trim();
	}
	@Override
	public ArrayList<java.lang.Class> getAllowedContents()
	{
		return new ArrayList<java.lang.Class>(Arrays.asList(TextBlock.class, BKMarker.class, BKEndMarker.class, BDMarker.class, BDEndMarker.class, ITMarker.class, ITEndMarker.class, SCMarker.class, SCEndMarker.class, BDITMarker.class, BDITEndMarker.class, NDMarker.class, NDEndMarker.class, NOMarker.class, NOEndMarker.class, SUPMarker.class, SUPEndMarker.class));
	}

}
