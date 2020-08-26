package org.wycliffeassociates.usfmtools.models.markers;




public class UnknownMarker extends Marker
{
	public String ParsedIdentifier;
	public String ParsedValue;

	@Override
	public String getIdentifier()
	{
		return "";
	}
	@Override
	public String PreProcess(String input)
	{
		ParsedValue = input;
		return "";
	}
}
