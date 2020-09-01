package org.wycliffeassociates.usfmtools.models.markers;

/**
 * Tag for book abbreviation
 */
public class TOC3Marker extends Marker {
    public String bookAbbreviation;

    @Override
    public String getIdentifier() {
        return "toc3";
    }

    @Override
    public String preProcess(String input) {
        bookAbbreviation = input.trim();
        return "";
    }
}
