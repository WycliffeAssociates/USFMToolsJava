package org.wycliffeassociates.usfmtools.models.markers;

/**
 * A "marker" for a text block. This exists so that we can handle
 */
public class TextBlock extends Marker {
    public TextBlock(String text) {
        this.text = text;
    }

    public String text;

    @Override
    public String getIdentifier() {
        return "";
    }
}
