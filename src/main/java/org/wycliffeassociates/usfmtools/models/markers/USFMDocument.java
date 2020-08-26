package org.wycliffeassociates.usfmtools.models.markers;



import java.util.*;

public class USFMDocument extends Marker
{
	public USFMDocument()
	{
		Contents = new ArrayList<Marker>();
	}

	@Override
	public String getIdentifier()
	{
		return "";
	}

	@Override
	public ArrayList<java.lang.Class> getAllowedContents()
	{
		return new ArrayList<java.lang.Class>(Arrays.asList(HMarker.class, IDEMarker.class, IDMarker.class, IBMarker.class, IQMarker.class, ILIMarker.class, IOTMarker.class, IOMarker.class, STSMarker.class, USFMMarker.class, TOC1Marker.class, TOC2Marker.class, TOC3Marker.class, TOCA1Marker.class, TOCA2Marker.class, TOCA3Marker.class, ISMarker.class, MTMarker.class, IMTMarker.class, IPMarker.class, IPIMarker.class, IMMarker.class, IMIMarker.class, IPQMarker.class, IMQMarker.class, IPRMarker.class, CLMarker.class, CMarker.class));
	}
	public final void Insert(Marker input)
	{
		if (!TryInsert(input))
		{
			// Since this is the root then add them anyway
			Contents.add(input);
		}

	}
	public final void Insert(USFMDocument document)
	{
		InsertMultiple(document.Contents);
	}
	public final void InsertMultiple(java.lang.Iterable<Marker> input)
	{
		for (Marker i : input)
		{
			Insert(i);
		}

	}
}
