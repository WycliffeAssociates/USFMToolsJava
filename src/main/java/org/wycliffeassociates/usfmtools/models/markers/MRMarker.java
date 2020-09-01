package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/**
 * Major section reference marker
 */
public class MRMarker extends Marker {
    public int Weight = 1;
    public String sectionReference;

    @Override
    public String getIdentifier() {
        return "mr";
    }

    @Override
    public String preProcess(String input) {
        sectionReference = tangible.StringHelper.trimStart(input);
        return "";
    }

    @Override
    public ArrayList<java.lang.Class> getAllowedContents() {
        return new ArrayList<java.lang.Class>(Arrays.asList(FMarker.class, FEndMarker.class));
    }
}
