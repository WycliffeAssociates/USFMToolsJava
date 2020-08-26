package org.wycliffeassociates.usfmtools.models.markers;




public class IDMarker extends Marker
{
	public String TextIdentifier;
	@Override
	public String getIdentifier()
	{
		return "id";
	}
	@Override
	public String PreProcess(String input)
	{
		TextIdentifier = input.trim();
		return "";
	}
}
