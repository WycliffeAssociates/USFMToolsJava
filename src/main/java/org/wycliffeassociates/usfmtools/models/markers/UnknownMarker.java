package org.wycliffeassociates.usfmtools.models.markers;

public class UnknownMarker extends Marker {
    public String parsedIdentifier;
    public String parsedValue;

    @Override
    public String getIdentifier() {
        return "";
    }

    @Override
    public String preProcess(String input) {
        parsedValue = input;
        return "";
    }
}
