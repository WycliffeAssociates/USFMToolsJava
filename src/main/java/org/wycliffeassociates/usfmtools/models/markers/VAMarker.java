package org.wycliffeassociates.usfmtools.models.markers;

/**
 * Alternate verse number
 */
public class VAMarker extends Marker {
    public String altVerseNumber;

    @Override
    public String getIdentifier() {
        return "va";
    }

    @Override
    public String preProcess(String input) {
        altVerseNumber = input.trim();
        return "";
    }
}
