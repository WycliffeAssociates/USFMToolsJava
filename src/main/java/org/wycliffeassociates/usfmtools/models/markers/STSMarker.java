package org.wycliffeassociates.usfmtools.models.markers;




/** 
 Project text status tracking
*/
public class STSMarker extends Marker
{
	public String StatusText;
	@Override
	public String getIdentifier()
	{
		return "sts";
	}
	@Override
	public String PreProcess(String input)
	{
		StatusText = input.trim();
		return "";
	}
}
