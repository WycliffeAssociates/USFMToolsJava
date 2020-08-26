package org.wycliffeassociates.usfmtools.models.markers;




/** 
 Encoding marker
*/
public class IDEMarker extends Marker
{
	public String Encoding;
	@Override
	public String getIdentifier()
	{
		return "ide";
	}

	@Override
	public String PreProcess(String input)
	{
		Encoding = input.trim();
		return "";
	}
}
