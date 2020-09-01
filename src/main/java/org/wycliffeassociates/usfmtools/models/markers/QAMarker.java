package org.wycliffeassociates.usfmtools.models.markers;

/**
 * Acrostic heading for poetry
 */
public class QAMarker extends Marker {
    /**
     * Heading for the poetry
     */
    public String heading;

    @Override
    public String getIdentifier() {
        return "qa";
    }

    @Override
    public String preProcess(String input) {
        heading = input.trim();
        return "";
    }
}
