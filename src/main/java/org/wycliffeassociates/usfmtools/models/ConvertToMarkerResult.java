package org.wycliffeassociates.usfmtools.models;

import org.wycliffeassociates.usfmtools.models.markers.Marker;

/** 
 A holder class to take the place of a tuple
*/
public class ConvertToMarkerResult
{
	public ConvertToMarkerResult(Marker marker, String remainingText)
	{
		this.marker = marker;
		this.remainingText = remainingText;
	}
	public Marker marker;
	public String remainingText;
}
