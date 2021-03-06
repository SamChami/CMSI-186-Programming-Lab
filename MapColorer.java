import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MapColorer {

    public void colorMap() {
        Region region[] = Region.getAllRegionsAsArray();
        int currentRegion = 0;
        int backtrackColor = -1;
        while (currentRegion < region.length && currentRegion != -1) {
            for (int i = 0; i < 4; i++) {
                if (region[currentRegion].canColorWith(i) && i > backtrackColor) {
                    region[currentRegion].setColor(i); 
                    currentRegion = currentRegion + 1;
                    backtrackColor = -1;
                    break;
                } else if (i == 3) {
                    currentRegion = currentRegion - 1;
                    if (currentRegion == -1) {
                        System.err.println("IMPOSSIBLE MAP");
                        break;
                    }
                    backtrackColor = region[currentRegion].getColor();
                    region[currentRegion].removeColor();
                } 
            }
        }

        String repeatChecker = "";
        if (currentRegion != -1) {
            for (Region r : region) {
                System.out.println(r.getName() + ": " + r.getColor());   
                for(Region n : r.getNeighbors()) {
                    repeatChecker = repeatChecker + (r.getName() + "," + n.getName());
                    if (repeatChecker.indexOf(n.getName() + "," + r.getName()) < 0) {
                        System.out.println(r.getName() + "," + n.getName());
                    }
                }
            }
        }
    }
    
    private static void printMap() {
        System.out.println("The countries are: ");
        for (Region r : Region.getAllRegionsAsArray()) {
            System.out.println(r + " with Neigbors " + r.getNeighbors());
        }
    }

    public void readMapFromStandardInput() {
        new BufferedReader(new InputStreamReader(System.in))
            .lines()
            .forEach(line -> {
                String[] pair = line.trim().split(",");
                Region region0 = Region.forName(pair[0]);
                Region region1 = Region.forName(pair[1]);
                region0.addNeighbor(region1);
            });
    }
    public static void main(String[] args) {
        MapColorer mapColorer = new MapColorer();
        mapColorer.readMapFromStandardInput();
        mapColorer.colorMap();
    }
}

/**
* This class is full of a lot of stuff you don't need to know.
*
* But don't hesitate to ask about such things if you like.
*/
class Region {

    private static Map<String, Region> allRegions = new TreeMap<>();

    public static Region forName(String name) {
        allRegions.putIfAbsent(name, new Region(name));
        return allRegions.get(name);
    }

    public static Region[] getAllRegionsAsArray() {
       return allRegions.values().toArray(new Region[allRegions.size()]);
    }

    private String name;
    private Integer color;
    private Set<Region> neighbors = new HashSet<>();

    private Region(String name) {
       this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return this.name;
    }

    public Integer getColor() {
        return color;
    }

    public boolean canColorWith(int color) {
        return !neighbors.stream().anyMatch(n -> Objects.equals(n.color,color));
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void removeColor() {
        this.color = null;
    }

    public Set<Region> getNeighbors() {
       return neighbors;
    }

    public void addNeighbor(Region region) {
       Objects.requireNonNull(region);
       neighbors.add(region);
       region.neighbors.add(this);
    }
}