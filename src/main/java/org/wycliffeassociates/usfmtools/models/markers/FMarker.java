package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

/**
 * Footnote marker
 */
public class FMarker extends Marker {
    @Override
    public String getIdentifier() {
        return "f";
    }

    public String footNoteCaller;

    @Override
    public String preProcess(String input) {
        footNoteCaller = input.trim();
        return "";
    }

    @Override
    public ArrayList<java.lang.Class> getAllowedContents() {
        return new ArrayList<java.lang.Class>(
                Arrays.asList(
                        FRMarker.class,
                        FREndMarker.class,
                        FKMarker.class,
                        FTMarker.class,
                        FVMarker.class,
                        FVEndMarker.class,
                        FPMarker.class,
                        FQAMarker.class,
                        FQAEndMarker.class,
                        FQMarker.class,
                        FQEndMarker.class,
                        TLMarker.class,
                        TLEndMarker.class,
                        WMarker.class,
                        WEndMarker.class,
                        TextBlock.class,
                        ITMarker.class,
                        ITEndMarker.class,
                        SCMarker.class,
                        SCEndMarker.class,
                        SUPMarker.class,
                        SUPEndMarker.class,
                        BKMarker.class,
                        BKEndMarker.class,
                        BDMarker.class,
                        BDEndMarker.class
                )
        );
    }
}
