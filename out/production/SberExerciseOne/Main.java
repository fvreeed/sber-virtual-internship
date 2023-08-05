import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        String filename = "src\\file\\FirstExercise.csv";
        outputOption(createList(filename));
    }

    public static ArrayList<City> createList(String filename) throws IOException {

        File file = new File(filename);
        ArrayList<City> listCity = new ArrayList<>();
        Scanner scanner = new Scanner(file, StandardCharsets.UTF_8);
        System.setProperty("console.encoding", "utf-8");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] words = line.split(";");
            City city;
            if (words.length < 6) {
                city = new City(words[1], words[2], words[3], Integer.parseInt(words[4]), "");
            } else {
                city = new City(words[1], words[2], words[3], Integer.parseInt(words[4]), words[5]);
            }
            listCity.add(city);
        }
        return listCity;
    }

    public static void outputOption(ArrayList<City> list) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Select sort option:");
        System.out.println("""
                Write 1 for sorting by name
                Write 2 for sorting by name and district
                Write 3 for search most populated city
                Write 4 for get cities in regions
                """);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> {
                list.sort(Comparator.comparing(City::getName));
                output(list);
            }
            case "2" -> {
                list.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName));
                output(list);
            }
            case "3" -> findBiggest(list);
            case "4" -> outputreg(countCities(list));
            default -> System.out.println("Incorrect input!");
        }
    }

    public static void findBiggest(ArrayList<City> list) {

        int maxPopulation = 0, index = 0, count = 1;
        City[] cities = new City[list.size()];
        for (int i = 0; i < list.size(); i++) {
            cities[i] = list.get(i);
        }
        for (City city : cities) {
            if (city.getPopulation() > maxPopulation) {
                maxPopulation = city.getPopulation();
                index = count;
            }
            count++;
        }
        System.out.println("[" + index + "] = " + maxPopulation);
    }

    public static ArrayList<Region> countCities(ArrayList<City> list) {

        ArrayList<Region> regionList = new ArrayList<>();
        String regionName = "";
        int quantity = 0;
        for (City city : list) {
            if (!(regionName.equals(city.getRegion()))) {
                regionName = city.getRegion();
                for (City city1 : list) {
                    if (regionName.equals(city1.getRegion())) {
                        quantity++;
                    }
                }
                regionList.add(new Region(regionName, quantity));
                quantity = 0;
            }
        }
        regionList = clearList(regionList);
        return regionList;
    }

    public static ArrayList<Region> clearList(ArrayList<Region> list) {

        ArrayList<Region> clearList = new ArrayList<>();
        for (Region region : list) {
            if (checkList(clearList, region)) {
                clearList.add(region);
            }
        }
        return clearList;
    }

    public static boolean checkList(ArrayList<Region> list, Region region) {

        for (Region region1 : list) {
            if (region.getName().equals(region1.getName())) {
                return false;
            }
        }
        return true;
    }

    public static void output(ArrayList<City> list) {

        for (City city : list) {
            System.out.println(city);
        }
    }

    public static void outputreg(ArrayList<Region> list) {

        for (Region region : list) {
            System.out.println(region);
        }
    }
}