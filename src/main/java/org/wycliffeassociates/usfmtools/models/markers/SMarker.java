package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/**
 * Section marker
 */
public class SMarker extends Marker {
    public int weight = 1;
    public String text;

    @Override
    public String getIdentifier() {
        return "s";
    }

    @Override
    public String preProcess(String input) {
        text = tangible.StringHelper.trimStart(input);
        return "";
    }

    @Override
    public ArrayList<java.lang.Class> getAllowedContents() {
        return new ArrayList<java.lang.Class>(
                Arrays.asList(
                        RMarker.class,
                        FMarker.class,
                        FEndMarker.class,
                        SCMarker.class,
                        SCEndMarker.class,
                        EMMarker.class,
                        EMEndMarker.class,
                        BDMarker.class,
                        BDEndMarker.class,
                        ITMarker.class,
                        ITEndMarker.class,
                        BDITMarker.class,
                        BDITEndMarker.class,
                        NOMarker.class,
                        NOEndMarker.class,
                        SUPMarker.class,
                        SUPEndMarker.class,
                        TextBlock.class
                )
        );
    }
}
