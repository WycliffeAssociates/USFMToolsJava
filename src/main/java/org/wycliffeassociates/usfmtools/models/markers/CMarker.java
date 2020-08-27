package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 Chapter marker
*/
public class CMarker extends Marker
{
	private static Pattern regex = Pattern.compile(" *(\\d*) *(.*)");
	public int Number;
	public final String getPublishedChapterMarker()
	{
		ArrayList<CPMarker> childCharacterMarker = this.<CPMarker>GetChildMarkers(CPMarker.class);
		if (!childCharacterMarker.isEmpty())
		{
			return childCharacterMarker.get(0).PublishedChapterMarker;
		}
		else
		{
			return String.valueOf(Number);
		}
	}
	public final String getCustomChapterLabel()
	{
		ArrayList<CLMarker> childChapLabelMarker = this.<CLMarker>GetChildMarkers(CLMarker.class);
		if (!childChapLabelMarker.isEmpty())
		{
			return childChapLabelMarker.get(0).Label;
		}
		else
		{
			return getPublishedChapterMarker();
		}

	}
	@Override
	public String getIdentifier()
	{
		return "c";
	}
	@Override
	public String PreProcess(String input)
	{
		Matcher match = regex.matcher(input);
		if (match.find())
		{
			if (tangible.StringHelper.isNullOrWhiteSpace(match.group(1)))
			{
				Number = 0;
			}
			else
			{
				Number = Integer.parseInt(match.group(1));
			}
			if (tangible.StringHelper.isNullOrWhiteSpace(match.group(2)))
			{
				return "";
			}
			return tangible.StringHelper.trimEnd(match.group(2));
		}
		return "";
	}
	@Override
	public ArrayList<java.lang.Class> getAllowedContents()
	{
		return new ArrayList<java.lang.Class>(Arrays.asList(MMarker.class, MSMarker.class, SMarker.class, BMarker.class, DMarker.class, VMarker.class, PMarker.class, PCMarker.class, CDMarker.class, CPMarker.class, DMarker.class, CLMarker.class, QMarker.class, QSMarker.class, QSEndMarker.class, QAMarker.class, QMarker.class, NBMarker.class, RMarker.class, LIMarker.class, TableBlock.class, MMarker.class, MIMarker.class, PIMarker.class, CAMarker.class, CAEndMarker.class, SPMarker.class, TextBlock.class));
	}
}
