package org.wycliffeassociates.usfmtools.models.markers;

/** 
 Marker to indicate the acrostic letter within a poetic line
*/
public class QACMarker extends Marker
{
	public String AcrosticLetter;
	@Override
	public String getIdentifier()
	{
		return "qac";
	}
	@Override
	public String PreProcess(String input)
	{
		AcrosticLetter = input.trim();
		return "";
	}
}
