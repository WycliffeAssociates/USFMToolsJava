package org.wycliffeassociates.usfmtools.models.markers;




public class REMMarker extends Marker
{
	public String Comment;
	@Override
	public String getIdentifier()
	{
		return "rem";
	}

	@Override
	public String PreProcess(String input)
	{
		Comment = input.trim();
		return "";
	}
}
