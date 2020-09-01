package org.wycliffeassociates.usfmtools.models.markers;

/**
 * Major title marker
 */
public class MTMarker extends Marker {
    public int weight = 1;
    public String title;

    @Override
    public String getIdentifier() {
        return "mt";
    }

    @Override
    public String preProcess(String input) {
        title = input.trim();
        return "";
    }
}
