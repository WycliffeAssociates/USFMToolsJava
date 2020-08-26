package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/** 
 Embedded text poetic line
*/
public class QMMarker extends Marker
{
	public int Depth = 1;
	@Override
	public String getIdentifier()
	{
		return "qm";
	}
	@Override
	public String PreProcess(String input)
	{
		return tangible.StringHelper.trimStart(input);
	}
	@Override
	public ArrayList<java.lang.Class> getAllowedContents()
	{
		return new ArrayList<java.lang.Class>(Arrays.asList(TextBlock.class, FMarker.class, FEndMarker.class, TLMarker.class, TLEndMarker.class, WMarker.class, WEndMarker.class));
	}
}
