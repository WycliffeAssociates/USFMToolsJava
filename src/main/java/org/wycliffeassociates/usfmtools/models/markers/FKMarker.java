package org.wycliffeassociates.usfmtools.models.markers;

/** 
 Footnote keyword Marker
*/
public class FKMarker extends Marker
{
	@Override
	public String getIdentifier()
	{
		return "fk";
	}
	public String FootNoteKeyword;


	@Override
	public String PreProcess(String input)
	{
		FootNoteKeyword = input.trim();
		return "";
	}
}
