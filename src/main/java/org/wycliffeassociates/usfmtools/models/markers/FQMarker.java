package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/** 
 Footnote translations
*/
public class FQMarker extends Marker
{
	@Override
	public String getIdentifier()
	{
		return "fq";
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
