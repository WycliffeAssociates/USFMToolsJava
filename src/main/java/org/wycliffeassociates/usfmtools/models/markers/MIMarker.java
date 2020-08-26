package org.wycliffeassociates.usfmtools.models.markers;



import java.util.*;

/** 
 Indented flush left paragraph
*/
public class MIMarker extends Marker
{
	@Override
	public String getIdentifier()
	{
		return "mi";
	}
	@Override
	public ArrayList<java.lang.Class> getAllowedContents()
	{
		return new ArrayList<java.lang.Class>(Arrays.asList(TextBlock.class, VMarker.class, BKMarker.class, BKEndMarker.class, BDMarker.class, BDEndMarker.class, ITMarker.class, ITEndMarker.class, SCMarker.class, SCEndMarker.class, BDITMarker.class, BDITEndMarker.class, NDMarker.class, NDEndMarker.class, NOMarker.class, NOEndMarker.class, SUPMarker.class, SUPEndMarker.class));
	}
}
