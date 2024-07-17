package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Wordlist / Glossary / Dictionary Entry Marker
 */
public class WMarker extends Marker {

    public String term;
    public HashMap<String, String> attributes;
    private static Pattern wordAttrPattern = Pattern.compile("([\\w-]+)=?\"?([\\w,:.]*)\"?", Pattern.DOTALL);

    @Override
    public String getIdentifier() {
        return "w";
    }

    @Override
    public String preProcess(String input) {
        input = input.trim();
        attributes = new HashMap<String, String>();

        String[] wordEntry = input.split("[|]", -1);
        term = wordEntry[0];

        if (wordEntry.length > 1) {
            String[] wordAttr = wordEntry[1].split("[ ]", -1);
            for (String attr : wordAttr) {
                Matcher attrMatch = wordAttrPattern.matcher(attr);
                attrMatch.find();
                if (attrMatch.group(2).length() == 0) {
                    attributes.put("lemma", attrMatch.group(1));
                } else {
                    attributes.put(attrMatch.group(1), attrMatch.group(2));
                }
            }
        }

        return term;
    }

    @Override
    public ArrayList<java.lang.Class> getAllowedContents() {
        return new ArrayList<java.lang.Class>(
                Arrays.asList(
                        TextBlock.class
                )
        );
    }
}
