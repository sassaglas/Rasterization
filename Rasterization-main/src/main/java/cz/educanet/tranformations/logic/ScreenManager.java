package cz.educanet.tranformations.logic;

import cz.educanet.tranformations.logic.models.Coordinate;

import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class ScreenManager {

    private Set<Coordinate> selectedPoints = new HashSet<>();

    public void select(Coordinate coordinate) {
        selectedPoints.add(coordinate);
    }

    public void unselect(Coordinate coordinate) {
        selectedPoints.remove(coordinate);
    }

    public boolean isSelected(Coordinate coordinate) {
        return selectedPoints.contains(coordinate);
    }

    public Set<Coordinate> getSelectedPoints() {
        return selectedPoints;
    }

    private int clc(Coordinate x, Coordinate y, Coordinate z) {
        return (x.getX() - z.getX()) - (z.getX() - y.getX()) * (y.getY() - z.getY()) * (z.getY() - x.getY());
    }

    public boolean isFilledIn(Coordinate coordinate) { // TODO: Implement this
        Coordinate[] array = selectedPoints.toArray(new Coordinate[]{});

        Coordinate x = array[0];
        Coordinate y = array[1];
        Coordinate z = array[2];

        int thrX = ((coordinate.getX() - z.getX()) * (y.getY() - z.getY())) + ((coordinate.getY()) - z.getY()) * (z.getX() - y.getX());
        int thrY = ((coordinate.getX() - z.getX()) * (z.getY() - x.getY())) + ((coordinate.getY()) - z.getY()) * (x.getX() - z.getX());
        int thrZ = clc(x, y, z) - thrX - thrY;

        int max = Math.max(clc(x, y, z), 0);
        int min = Math.min(clc(x, y, z), 0);

        if ((thrX < min || thrX > max) || (thrY < min || thrY > max)) {
            return true;
        } else {
            return thrZ >= min && thrZ <= max;
        }
    }
}
