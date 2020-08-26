package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/** 
 Table Row Marker
*/
public class TRMarker extends Marker
{
	@Override
	public String getIdentifier()
	{
		return "tr";
	}

	@Override
	public ArrayList<java.lang.Class> getAllowedContents()
	{
		return new ArrayList<java.lang.Class>(Arrays.asList(TCMarker.class, THMarker.class, TCRMarker.class, THRMarker.class));
	}
}
