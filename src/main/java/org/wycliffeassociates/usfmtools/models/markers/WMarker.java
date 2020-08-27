package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 Wordlist / Glossary / Dictionary Entry Marker
*/
public class WMarker extends Marker
{
	public String Term;
	public HashMap<String, String> Attributes;
	private static Pattern wordAttrPattern = Pattern.compile("([\\w]+)=?\"?([\\w,:.]*)\"?");
	@Override
	public String getIdentifier()
	{
		return "w";
	}

	@Override
	public String PreProcess(String input)
	{
		input = input.trim();
		Attributes = new HashMap<String, String>();

		String[] wordEntry = input.split("[|]", -1);
		Term = wordEntry[0];

		if (wordEntry.length > 1)
		{

			String[] wordAttr = wordEntry[1].split("[ ]", -1);
			for (String attr : wordAttr)
			{
				Matcher attrMatch = wordAttrPattern.matcher(attr);
				attrMatch.find();
				if (attrMatch.group(2).length() == 0)
				{
					Attributes.put("lemma", attrMatch.group(1));
				}
				else
				{
					Attributes.put(attrMatch.group(1), attrMatch.group(2));
				}

			}

		}

		return "";
	}

}
