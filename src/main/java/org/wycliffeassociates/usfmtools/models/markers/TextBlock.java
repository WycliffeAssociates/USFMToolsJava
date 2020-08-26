package org.wycliffeassociates.usfmtools.models.markers;




/** 
 A "marker" for a text block. This exists so that we can handle 
*/
public class TextBlock extends Marker
{
	public TextBlock(String text)
	{
		Text = text;
	}
	public String Text;
	@Override
	public String getIdentifier()
	{
		return "";
	}
}
