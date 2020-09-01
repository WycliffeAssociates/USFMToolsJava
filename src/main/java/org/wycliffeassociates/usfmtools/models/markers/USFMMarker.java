package org.wycliffeassociates.usfmtools.models.markers;

/**
 * Marker for USFM version
 */
public class USFMMarker extends Marker {
    @Override
    public String getIdentifier() {
        return "usfm";
    }

    /**
     * USFM Version
     */
    private String version;

    public final String getVersion() {
        return version;
    }

    public final void setVersion(String value) {
        version = value;
    }

    @Override
    public String preProcess(String input) {
        setVersion(input.trim());
        return "";
    }
}
