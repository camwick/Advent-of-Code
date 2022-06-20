import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        File file;
        Scanner input;

        if (args.length > 0)
            file = new File(args[0]);
        else
            file = new File("input.txt");

        input = new Scanner(file);

        // parse input
        List<String> lines = new ArrayList<>();
        while (input.hasNextLine())
            lines.add(input.nextLine());

        input.close();

        // convert to integer array
        int[][] heightmap = new int[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); ++i) {
            for (int j = 0; j < lines.get(0).length(); ++j) {
                heightmap[i][j] = Character.getNumericValue(lines.get(i).charAt(j));
            }
        }

        // PART 1
        long start = System.currentTimeMillis();

        // acquire risk levels
        List<Integer> riskLevel = new ArrayList<>();
        for (int y = 0; y < heightmap.length; ++y) {
            for (int x = 0; x < heightmap[y].length; ++x) {
                // top
                if (y == 0) {
                    // handle corners first
                    if (x == 0 && heightmap[y][x] < heightmap[y][x + 1] && heightmap[y][x] < heightmap[y + 1][x])
                        riskLevel.add(1 + heightmap[y][x]);
                    else if (x == heightmap[y].length - 1 && heightmap[y][x] < heightmap[y][x - 1]
                            && heightmap[y][x] < heightmap[y + 1][x]) {
                        riskLevel.add(1 + heightmap[y][x]);
                        break; // end of row
                    }
                    // rest of top layer
                    else if (x > 0 && x < heightmap[y].length - 1 && heightmap[y][x] < heightmap[y][x - 1]
                            && heightmap[y][x] < heightmap[y][x + 1]
                            && heightmap[y][x] < heightmap[y + 1][x])
                        riskLevel.add(1 + heightmap[y][x]);
                }
                // bottom
                else if (y == heightmap.length - 1) {
                    // handle corners first
                    if (x == 0 && heightmap[y][x] < heightmap[y][x + 1] && heightmap[y][x] < heightmap[y - 1][x])
                        riskLevel.add(1 + heightmap[y][x]);
                    else if (x == heightmap[y].length - 1 && heightmap[y][x] < heightmap[y][x - 1]
                            && heightmap[y][x] < heightmap[y - 1][x]) {
                        riskLevel.add(1 + heightmap[y][x]);
                        break; // end of row
                    }
                    // rest of bottom layer
                    else if (x > 0 && x < heightmap[y].length - 1 && heightmap[y][x] < heightmap[y][x - 1]
                            && heightmap[y][x] < heightmap[y][x + 1]
                            && heightmap[y][x] < heightmap[y - 1][x])
                        riskLevel.add(1 + heightmap[y][x]);
                }
                // everything in between
                else {
                    if (x == 0 && heightmap[y][x] < heightmap[y][x + 1] && heightmap[y][x] < heightmap[y - 1][x]
                            && heightmap[y][x] < heightmap[y + 1][x])
                        riskLevel.add(1 + heightmap[y][x]);
                    else if (x == heightmap[y].length - 1 && heightmap[y][x] < heightmap[y][x - 1]
                            && heightmap[y][x] < heightmap[y - 1][x] && heightmap[y][x] < heightmap[y + 1][x]) {
                        riskLevel.add(1 + heightmap[y][x]);
                        break; // end of row
                    } else if (x > 0 && x < heightmap[y].length - 1 && heightmap[y][x] < heightmap[y][x - 1]
                            && heightmap[y][x] < heightmap[y][x + 1]
                            && heightmap[y][x] < heightmap[y - 1][x] && heightmap[y][x] < heightmap[y + 1][x])
                        riskLevel.add(1 + heightmap[y][x]);
                }
            }
        }

        // sum of risk levels
        int riskLevelSum = 0;
        for (int x : riskLevel)
            riskLevelSum += x;

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("\nSum of risk levels: " + riskLevelSum);
        System.out.println("Elapsed time: " + elapsed + "ms\n");

        // PART 2
        start = System.currentTimeMillis();

        // creating heightmap object;
        HeightMap[][] map = new HeightMap[heightmap.length][heightmap[0].length];
        for (int y = 0; y < heightmap.length; ++y) {
            for (int x = 0; x < heightmap[y].length; ++x) {
                map[y][x] = new HeightMap(heightmap[y][x]);

                if ((x == 0 && y == 0) ||
                        (x == map[y].length - 1 && y == 0) ||
                        (x == 0 && y == map.length - 1)
                        || (x == map[y].length - 1 && y == map.length - 1))
                    map[y][x].setCorner(true);
            }
        }
        settingNeighbors(map);

        // finding all basin sizes
        List<Integer> basinSize = new ArrayList<>();
        for (int y = 0; y < map.length; ++y) {
            for (int x = 0; x < map[y].length; ++x) {
                if (!map[y][x].getVisited())
                    basinSize.add(calculateBasinSize(map[y][x]));
            }
        }

        // calculating quotient of largest 3 basins
        Collections.sort(basinSize);

        int quotient = basinSize.get(basinSize.size() - 1) * basinSize.get(basinSize.size() - 2)
                * basinSize.get(basinSize.size() - 3);

        elapsed = System.currentTimeMillis() - start;
        System.out.println("Quotent: " + quotient);
        System.out.println("Elapsed time: " + elapsed + "ms");
    }

    private static int calculateBasinSize(HeightMap current) {
        int counter = 0;

        current.setVisited(true);
        counter++;

        HeightMap[] neighbors = current.getNeighbors();
        for (int i = 0; i < neighbors.length; ++i) {
            if (neighbors[i] == null)
                continue;
            else if (!neighbors[i].getVisited()) {
                counter += calculateBasinSize(neighbors[i]);
            }
        }

        return counter;
    }

    private static void settingNeighbors(HeightMap[][] map) {
        for (int y = 0; y < map.length; ++y) {
            for (int x = 0; x < map[y].length; ++x) {
                // corners
                if (map[y][x].getCorner()) {
                    if (y == 0) {
                        if (x == 0)
                            map[y][x].setNeighbors(new HeightMap[] { null, null, map[y][x + 1], map[y + 1][x] });
                        else
                            map[y][x].setNeighbors(new HeightMap[] { null, map[y][x - 1], null, map[y + 1][x] });
                    } else {
                        if (x == 0)
                            map[y][x].setNeighbors(new HeightMap[] { null, map[y - 1][x], map[y][x + 1], null });
                        else
                            map[y][x].setNeighbors(new HeightMap[] { map[y - 1][x], map[y][x - 1], null, null });
                    }
                }
                // top row
                else if (y == 0) {
                    if (x == 0 || x == map[y].length - 1)
                        continue;
                    else
                        map[y][x].setNeighbors(new HeightMap[] { null, map[y][x - 1], map[y][x + 1], map[y + 1][x] });
                }
                // bottom row
                else if (y == map.length - 1) {
                    if (x == 0 || x == map[y].length - 1)
                        continue;
                    else
                        map[y][x].setNeighbors(new HeightMap[] { map[y - 1][x], map[y][x - 1], map[y][x + 1], null });
                }
                // everything else
                else {
                    if (x == 0)
                        map[y][x].setNeighbors(new HeightMap[] { map[y - 1][x], null, map[y][x + 1], map[y + 1][x] });
                    else if (x == map[y].length - 1)
                        map[y][x].setNeighbors(new HeightMap[] { map[y - 1][x], map[y][x - 1], null, map[y + 1][x] });
                    else
                        map[y][x].setNeighbors(
                                new HeightMap[] { map[y - 1][x], map[y][x - 1], map[y][x + 1], map[y + 1][x] });
                }
            }
        }
    }
}