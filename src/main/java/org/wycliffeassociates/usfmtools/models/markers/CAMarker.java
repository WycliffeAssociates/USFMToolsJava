package org.wycliffeassociates.usfmtools.models.markers;

/**
 * Alternate chapter number
 */
public class CAMarker extends Marker {
    public String altChapterNumber;

    @Override
    public String getIdentifier() {
        return "ca";
    }

    @Override
    public String preProcess(String input) {
        altChapterNumber = input.trim();
        return "";
    }
}
