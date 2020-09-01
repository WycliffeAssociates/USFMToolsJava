package org.wycliffeassociates.usfmtools.models.markers;

/**
 * Footnote keyword Marker
 */
public class FKMarker extends Marker {
    @Override
    public String getIdentifier() {
        return "fk";
    }

    public String footNoteKeyword;

    @Override
    public String preProcess(String input) {
        footNoteKeyword = input.trim();
        return "";
    }
}
