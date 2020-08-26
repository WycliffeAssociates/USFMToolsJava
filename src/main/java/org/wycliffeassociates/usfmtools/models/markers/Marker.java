package org.wycliffeassociates.usfmtools.models.markers;



import java.util.*;

public abstract class Marker
{
	public Marker()
	{
		Contents = new ArrayList<Marker>();
	}
	public ArrayList<Marker> Contents;
	public abstract String getIdentifier();
	private int Position;
	public final int getPosition()
	{
		return Position;
	}
	public final void setPosition(int value)
	{
		Position = value;
	}
	public ArrayList<java.lang.Class> getAllowedContents()
	{
		return new ArrayList<java.lang.Class>();
	}

	/** 
	 Pre-process the text contents before creating text elements inside of it
	 
	 @param input
	 @return 
	*/
	public String PreProcess(String input)
	{
		return input;
	}

	public boolean TryInsert(Marker input)
	{
		if (!Contents.isEmpty() && Contents.get(Contents.size() - 1).TryInsert(input))
		{
			return true;
		}
		if (getAllowedContents().contains(input.getClass()))
		{
			Contents.add(input);
			return true;
		}
		return false;
	}
	public final ArrayList<java.lang.Class> GetTypesPathToLastMarker()
	{
		ArrayList<java.lang.Class> types = new ArrayList<java.lang.Class>();
		types.add(this.getClass());
		if (!Contents.isEmpty())
		{
			types.addAll(Contents.get(Contents.size() - 1).GetTypesPathToLastMarker());
		}
		return types;
	}

	public final ArrayList<Marker> GetHierarchyToMarker(Marker target)
	{
		ArrayList<Marker> output = new ArrayList<Marker>(Arrays.asList(this));

		if (target == this)
		{
			return output;
		}

		ArrayList<Marker> tmp;
		for (Marker marker : this.Contents)
		{
			tmp = marker.GetHierarchyToMarker(target);
			if (!tmp.isEmpty())
			{
				output.addAll(tmp);
				return output;
			}
		}
		return new ArrayList<Marker>();
	}
	/** 
	 A recursive search for children of a certain type
	 
	 <typeparam name="T"></typeparam>
	 @return 
	*/
	public final <T extends Marker> ArrayList<T> GetChildMarkers(Class<T> clazz)
	{
		ArrayList<T> output = new ArrayList<T>();

		for (Marker i : Contents)
		{
			if (i.getClass().isAssignableFrom(clazz))
			{
				output.add((T)i);
			}
			output.addAll(i.<T>GetChildMarkers(clazz));
		}

		return output;
	}

	public final Marker GetLastDescendent()
	{
		if (Contents.isEmpty())
		{
			return this;
		}

		return Contents.get(Contents.size() - 1).GetLastDescendent();
	}
}
