package org.wycliffeassociates.usfmtools.models.markers;



import java.util.*;

/** 
 Poetry Selah Marker (I know weird but it is in the spec)
*/
public class QSMarker extends Marker
{
	public String Text;
	@Override
	public String getIdentifier()
	{
		return "qs";
	}
	@Override
	public String PreProcess(String input)
	{
		return input.trim();
	}
	@Override
	public ArrayList<java.lang.Class> getAllowedContents()
	{
		return new ArrayList<java.lang.Class>(Arrays.asList(TextBlock.class));
	}
}
