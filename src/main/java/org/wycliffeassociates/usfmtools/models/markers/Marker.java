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


    private class StackItem {
        Marker marker;
        Boolean isLastInParent;

        StackItem(Marker marker, Boolean isLastInParent) {
            this.marker = marker;
            this.isLastInParent = isLastInParent;
        }
    }

    public final ArrayList<Marker> getHierarchyToMarker(Marker target) {

        var parents = new Stack<StackItem>();
        int childMarkerContentsCount;

        Boolean found = false;
        var stack = new Stack<StackItem>();
        stack.push(new StackItem(this, false));
        while (stack.size() > 0) {
            var temp = stack.pop();
            var marker = temp.marker;
            var isLastInParent = temp.isLastInParent;

            if (marker == target) {
                found = true;
                break;
            }

            if (marker.contents.size() != 0) {
                // We're descending
                parents.push(new StackItem(marker, isLastInParent));

                childMarkerContentsCount = marker.contents.size();
                for (int i = 0; i < childMarkerContentsCount; i++) {
                    stack.push(new StackItem(marker.contents.get(i), i == 0));
                }
            } else if (stack.size() == 0 || isLastInParent) {
                // We're ascending
                var tmp = parents.pop();
                // keep moving up the parent stack until we aren't the last in a parent
                while (tmp.isLastInParent == true) {
                    tmp = parents.pop();
                }
            }
        }
        var output = new ArrayList<Marker>();

        if (!found) {
            return output;
        }

        output.add(target);
        for (StackItem i : parents) {
            output.add(i.marker);
        }
        Collections.reverse(output);
        return output;
    }

    /**
     * Get the paths to multiple markers
     *
     * @param targets A list of markers to find
     * @returns A dictionary of markers and paths
     * <p>
     * In the case that the marker doesn't exist in the tree the dictionary will contain an empty list for that marker
     **/
    public HashMap<Marker, List<Marker>> getHierachyToMultipleMarkers(List<Marker> targets) {
        HashMap<Marker, List<Marker>> output = new HashMap<Marker, List<Marker>>(targets.size());
        var parents = new Stack<StackItem>();
        int childMarkerContentsCount;

        var stack = new Stack<StackItem>();
        stack.push(new StackItem(this, false));
        while (stack.size() > 0) {
            var temp = stack.pop();
            var marker = temp.marker;
            var isLastInParent = temp.isLastInParent;
            if (targets.contains(marker)) {
                var tmp = new ArrayList<Marker>(parents.size() + 1);
                tmp.add(marker);
                for (StackItem i : parents) {
                    tmp.add(i.marker);
                }
                Collections.reverse(tmp);
                output.put(marker, tmp);
                if (output.size() == targets.size()) {
                    break;
                }
            }

            if (marker.contents.size() != 0) {
                // We're descending
                parents.push(new StackItem(marker, isLastInParent));

                childMarkerContentsCount = marker.contents.size();
                for (int i = 0; i < childMarkerContentsCount; i++) {
                    stack.push(new StackItem(marker.contents.get(i), i == 0));
                }
            } else if (stack.size() == 0 || isLastInParent) {
                // We're ascending
                var tmp = parents.pop();
                // keep moving up the parent stack until we aren't the last in a parent
                while (tmp.isLastInParent == true) {
                    tmp = parents.pop();
                }
            }
        }

        for (var i : targets) {
            if (!output.containsKey(i)) {
                output.put(i, new ArrayList<Marker>());
            }
        }

        return output;
    }

    public final <T extends Marker> ArrayList<T> getChildMarkers(Class<T> clazz) {
        return getChildMarkers(clazz, null);
    }

    /**
     * A recursive search for children of a certain type
     *
     * <typeparam name="T"></typeparam>
     *
     * @return
     */
    public final <T extends Marker> ArrayList<T> getChildMarkers(Class<T> clazz, List<T> ignoredParents) {
        ArrayList<T> output = new ArrayList<T>();
        var stack = new Stack<Marker>();
        stack.addAll(contents);

        if (ignoredParents != null && ignoredParents.contains(clazz)) {
            return output;
        }

        while (stack.size() > 0) {
            var marker = stack.pop();
            if (marker.getClass().isAssignableFrom(clazz)) {
                output.add((T) marker);
            }
            for (var child : marker.contents) {
                if (ignoredParents == null || !ignoredParents.contains(child.getClass())) {
                    stack.push(child);
                }
            }
        }

        Collections.reverse(output);
        return output;
    }

    public final Marker getLastDescendent() {
        if (contents.isEmpty()) {
            return this;
        }

        return contents.get(contents.size() - 1).getLastDescendent();
    }
}
