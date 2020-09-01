package org.wycliffeassociates.usfmtools.models.markers;

/**
 * Marker to indicate the acrostic letter within a poetic line
 */
public class QACMarker extends Marker {
    public String acrosticLetter;

    @Override
    public String getIdentifier() {
        return "qac";
    }

    @Override
    public String preProcess(String input) {
        acrosticLetter = input.trim();
        return "";
    }
}
