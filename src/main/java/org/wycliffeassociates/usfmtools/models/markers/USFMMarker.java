package org.wycliffeassociates.usfmtools.models.markers;

/** 
 Marker for USFM version
*/
public class USFMMarker extends Marker
{
	@Override
	public String getIdentifier()
	{
		return "usfm";
	}

	/** 
	 USFM Version
	*/
	private String Version;
	public final String getVersion()
	{
		return Version;
	}
	public final void setVersion(String value)
	{
		Version = value;
	}

	@Override
	public String PreProcess(String input)
	{
		setVersion(input.trim());
		return "";
	}
}
