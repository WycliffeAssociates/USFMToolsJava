package org.wycliffeassociates.usfmtools.models.markers;



import java.util.*;

/** 
 Footnote alternative translations
*/
public class FQAMarker extends Marker
{
	@Override
	public String getIdentifier()
	{
		return "fqa";
	}

	@Override
	public String PreProcess(String input)
	{
		return tangible.StringHelper.trimStart(input);
	}

	@Override
	public ArrayList<java.lang.Class> getAllowedContents()
	{
		return new ArrayList<java.lang.Class>(Arrays.asList(TextBlock.class, TLMarker.class, TLEndMarker.class, WMarker.class, WEndMarker.class));
	}
}
