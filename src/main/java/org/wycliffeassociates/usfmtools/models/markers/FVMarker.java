package org.wycliffeassociates.usfmtools.models.markers;

/**
 * Footnote verse number
 */
public class FVMarker extends Marker {
    public String verseCharacter;

    @Override
    public String getIdentifier() {
        return "fv";
    }

    @Override
    public String preProcess(String input) {
        verseCharacter = input.trim();
        return "";
    }
}
