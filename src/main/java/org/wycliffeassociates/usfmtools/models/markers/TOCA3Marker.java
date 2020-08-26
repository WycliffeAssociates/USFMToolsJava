package org.wycliffeassociates.usfmtools.models.markers;

/** 
 Tag for alternative language book abbreviation
*/
public class TOCA3Marker extends Marker
{
	public String AltBookAbbreviation;
	@Override
	public String getIdentifier()
	{
		return "toca3";
	}
	@Override
	public String PreProcess(String input)
	{
		AltBookAbbreviation = input.trim();
		return "";
	}
}
