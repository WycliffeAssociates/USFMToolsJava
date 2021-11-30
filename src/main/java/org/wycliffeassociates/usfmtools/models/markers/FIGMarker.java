package org.wycliffeassociates.usfmtools.models.markers;

public class FIGMarker extends Marker {

    public String caption;
    public String description;
    public String width;
    public String location;
    public String copyright;
    public String reference;
    public String filePath;

    @Override
    public String getIdentifier() {
        return "fig";
    }

    @Override
    public String preProcess(String input) {
        input = input.trim();

        String[] wordEntry = input.split("\\|");
        if (wordEntry.length > 0) {
            description = wordEntry[0].trim();
        }
        if (wordEntry.length > 1) {
            filePath = wordEntry[1].trim();
        }
        if (wordEntry.length > 2) {
            width = wordEntry[2].trim();
        }
        if (wordEntry.length > 3) {
            location = wordEntry[3].trim();
        }
        if (wordEntry.length > 4) {
            copyright = wordEntry[4].trim();
        }
        if (wordEntry.length > 5) {
            caption = wordEntry[5].trim();
        }
        if (wordEntry.length > 6) {
            reference = wordEntry[6].trim();
        }

        String[] contentArr = input.split("\\|");
        if (contentArr.length > 0 && contentArr.length <= 2) {
            caption = contentArr[0].trim();

            String[] attributes = contentArr[1].split("\"");
            for (int i = 0; i < attributes.length; i++) {
                if (attributes[i].replace(" ", "").contains("alt=")) {
                    description = attributes[i + 1].trim();
                }
                if (attributes[i].replace(" ", "").contains("src=")) {
                    filePath = attributes[i + 1].trim();
                }
                if (attributes[i].replace(" ", "").contains("size=")) {
                    width = attributes[i + 1].trim();
                }
                if (attributes[i].replace(" ", "").contains("loc=")) {
                    location = attributes[i + 1].trim();
                }
                if (attributes[i].replace(" ", "").contains("copy=")) {
                    copyright = attributes[i + 1].trim();
                }
                if (attributes[i].replace(" ", "").contains("ref=")) {
                    reference = attributes[i + 1].trim();
                }
            }
        }
        return "";
    }
}

