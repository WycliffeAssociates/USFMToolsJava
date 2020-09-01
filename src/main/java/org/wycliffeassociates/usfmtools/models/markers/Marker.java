package org.wycliffeassociates.usfmtools.models.markers;

import java.util.*;

public abstract class Marker {
    public Marker() {
        contents = new ArrayList<Marker>();
    }

    public ArrayList<Marker> contents;

    public abstract String getIdentifier();

    private int position;

    public final int getPosition() {
        return position;
    }

    public final void setPosition(int value) {
        position = value;
    }

    public ArrayList<java.lang.Class> getAllowedContents() {
        return new ArrayList<java.lang.Class>();
    }

    /**
     * Pre-process the text contents before creating text elements inside of it
     *
     * @param input
     * @return
     */
    public String preProcess(String input) {
        return input;
    }

    public boolean tryInsert(Marker input) {
        if (!contents.isEmpty() && contents.get(contents.size() - 1).tryInsert(input)) {
            return true;
        }
        if (getAllowedContents().contains(input.getClass())) {
            contents.add(input);
            return true;
        }
        return false;
    }

    public final ArrayList<java.lang.Class> getTypesPathToLastMarker() {
        ArrayList<java.lang.Class> types = new ArrayList<java.lang.Class>();
        types.add(this.getClass());
        if (!contents.isEmpty()) {
            types.addAll(contents.get(contents.size() - 1).getTypesPathToLastMarker());
        }
        return types;
    }

    public final ArrayList<Marker> getHierarchyToMarker(Marker target) {
        ArrayList<Marker> output = new ArrayList<Marker>(Arrays.asList(this));

        if (target == this) {
            return output;
        }

        ArrayList<Marker> tmp;
        for (Marker marker : this.contents) {
            tmp = marker.getHierarchyToMarker(target);
            if (!tmp.isEmpty()) {
                output.addAll(tmp);
                return output;
            }
        }
        return new ArrayList<Marker>();
    }

    /**
     * A recursive search for children of a certain type
     *
     * <typeparam name="T"></typeparam>
     *
     * @return
     */
    public final <T extends Marker> ArrayList<T> getChildMarkers(Class<T> clazz) {
        ArrayList<T> output = new ArrayList<T>();

        for (Marker i : contents) {
            if (i.getClass().isAssignableFrom(clazz)) {
                output.add((T) i);
            }
            output.addAll(i.<T>getChildMarkers(clazz));
        }

        return output;
    }

    public final Marker getLastDescendent() {
        if (contents.isEmpty()) {
            return this;
        }

        return contents.get(contents.size() - 1).getLastDescendent();
    }
}
