package org.wycliffeassociates.usfmtools.models.markers;

/**
 * Marker for Custom Verse Number
 */
public class VPMarker extends Marker {
    public String verseCharacter;

    @Override
    public String getIdentifier() {
        return "vp";
    }

    @Override
    public String preProcess(String input) {
        verseCharacter = input.trim();
        return "";
    }
}
