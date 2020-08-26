package org.wycliffeassociates.usfmtools.models.markers;



import java.util.*;

/** 
 A flush left margin marker
*/
public class MMarker extends Marker
{
	@Override
	public String getIdentifier()
	{
		return "m";
	}
	@Override
	public ArrayList<java.lang.Class> getAllowedContents()
	{
		return new ArrayList<java.lang.Class>(Arrays.asList(VMarker.class, TextBlock.class, BKMarker.class, BKEndMarker.class, BDMarker.class, BDEndMarker.class, ITMarker.class, ITEndMarker.class, SCMarker.class, SCEndMarker.class, BDITMarker.class, BDITEndMarker.class, NDMarker.class, NDEndMarker.class, NOMarker.class, NOEndMarker.class, SUPMarker.class, SUPEndMarker.class));
	}
}
