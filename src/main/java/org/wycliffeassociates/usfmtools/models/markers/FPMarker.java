package org.wycliffeassociates.usfmtools.models.markers;



import java.util.*;

/** 
 Footer Paragraph Marker
*/
public class FPMarker extends Marker
{
	@Override
	public String getIdentifier()
	{
		return "fp";
	}
	@Override
	public ArrayList<java.lang.Class> getAllowedContents()
	{
		return new ArrayList<java.lang.Class>(Arrays.asList(TextBlock.class));
	}
}
