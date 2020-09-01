package org.wycliffeassociates.usfmtools.models.markers;

/**
 * Footnote origin reference
 */
public class FRMarker extends Marker {
    @Override
    public String getIdentifier() {
        return "fr";
    }

    public String verseReference;

    @Override
    public String preProcess(String input) {
        verseReference = input.trim();
        return "";
    }
}
