package org.wycliffeassociates.usfmtools.models.markers;

/**
 * Quoted Book Title Marker
 */
public class BKMarker extends Marker {
    public String bookTitle;

    @Override
    public String getIdentifier() {
        return "bk";
    }

    @Override
    public String preProcess(String input) {
        bookTitle = input.trim();
        return "";
    }
}
