package org.wycliffeassociates.usfmtools.models.markers;

/**
 * A speaker Marker (Used mostly in Job and Songs of Solomon)
 */
public class SPMarker extends Marker {
    public String speaker;

    @Override
    public String getIdentifier() {
        return "sp";
    }

    @Override
    public String preProcess(String input) {
        speaker = input.trim();
        return "";
    }
}
