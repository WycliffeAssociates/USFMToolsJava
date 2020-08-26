package org.wycliffeassociates.usfmtools.models.markers;



import java.util.*;

/** 
 Centered poetic line
*/
public class QCMarker extends Marker
{
	@Override
	public String getIdentifier()
	{
		return "qc";
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
