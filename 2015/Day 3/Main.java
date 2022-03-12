import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static class House {
        private int presents = 0;
        private ArrayList<Character> directions;
        private ArrayList<House> neighbors;
        private boolean visited = false;

        public House() {
            this.presents++;
            directions = new ArrayList<Character>();
            neighbors = new ArrayList<House>();
        }

        public void visitHouse() {
            presents++;
        }

        public void DFSvisit() {
            this.visited = true;
        }

        public void newNeighbord(char direction, House house) {
            directions.add(direction);
            neighbors.add(house);
        }

        public boolean doesNeighborExist(char direction) {
            return this.directions.contains(direction);
        }

        public House getNeighbor(char direction) {
            return neighbors.get(directions.indexOf(direction));
        }

        public boolean isVisited() {
            return this.visited;
        }

        public int numOfNeighbors() {
            return this.neighbors.size();
        }

        public Iterator<House> getNeighborIterator() {
            return this.neighbors.listIterator();
        }
    }

    // public static int countHouses(House house, int count) {
    // count++;

    // house.DFSvisit();

    // Iterator<House> ite = house.getNeighborIterator();
    // while (ite.hasNext()) {
    // Main.House next = ite.next();
    // if (!next.isVisited())
    // count += countHouses(next, count);
    // // else
    // // count--;
    // }

    // return count;
    // }

    public static void main(String[] args) {
        try {
            File file = new File("inputTest1.txt");
            Scanner scan = new Scanner(file);
            String line = scan.nextLine();
            scan.close();

            Main.House start = new Main.House();

            int numOfHouses = 1;
            Main.House cursor = start;
            for (int i = 0; i < line.length(); ++i) {
                if (cursor.doesNeighborExist(line.charAt(i))) {
                    cursor = cursor.getNeighbor(line.charAt(i));
                    cursor.visitHouse();
                } else {
                    Main.House newHouse = new Main.House();
                    cursor.newNeighbord(line.charAt(i), newHouse);
                    cursor = newHouse;
                    numOfHouses++;
                }
            }

            System.out.println("Num of houses: " + numOfHouses);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}