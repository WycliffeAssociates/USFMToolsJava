package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/** 
 Right-aligned poetic line
*/
public class QRMarker extends Marker
{
	@Override
	public String getIdentifier()
	{
		return "qr";
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
