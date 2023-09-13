import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        String[] map = {
                "#s###",
                "#...#",
                "..#.#",
                "f#..#",
                "###.."};

        MapCondition mapCondition = new MapCondition(map, false);
        waySearcher(mapCondition, map);
        int i = 5;
    }

    public static String[] waySearcher(MapCondition condition, String[] map) {

        Point currentPos = startSearcher(map);
        Boolean isFinishReached = false;
        ArrayList<String> steps = possibleSteps(map, currentPos, "");
        recursionSearcher(condition, currentPos, steps, map);


        return map;
    }

    public static Point startSearcher(String[] map) {

        Point point = new Point();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length(); j++) {

                char symbol = map[i].charAt(j);
                if (symbol == 's') {
                    point.x = j;
                    point.y = i;
                    return point;
                }
            }
        }
        return point;
    }

    public static ArrayList<String> possibleSteps(String[] map, Point currentPos, String prevStep) {

        ArrayList<String> res = new ArrayList<>();


        if (currentPos.x != 0 && map[currentPos.y].charAt(currentPos.x - 1) != '#'
                && !prevStep.equals("right"))
            res.add("left");
        if (currentPos.x != map[0].length() - 1 && map[currentPos.y].charAt(currentPos.x + 1) != '#'
                && !prevStep.equals("left"))
            res.add("right");
        if (currentPos.y != 0 && map[currentPos.y - 1].charAt(currentPos.x) != '#'
                && !prevStep.equals("down"))
            res.add("up");
        if (currentPos.y != map.length - 1 && map[currentPos.y + 1].charAt(currentPos.x) != '#'
                && !prevStep.equals("up"))
            res.add("down");

        return res;
    }

    public static Point stringToPoint(String str, Point currentPos) {

        if (str.equals("down"))
            currentPos.y++;
        else if (str.equals("up"))
            currentPos.y--;
        else if (str.equals("left"))
            currentPos.x--;
        else
            currentPos.x++;

        return currentPos;
    }

    public static Point stringToPointReverse(String str, Point currentPos) {

        if (str.equals("down"))
            currentPos.y--;
        else if (str.equals("up"))
            currentPos.y++;
        else if (str.equals("left"))
            currentPos.x++;
        else
            currentPos.x--;

        return currentPos;
    }

    public static void writeWay(String[] map, Point current) {

        StringBuilder stringBuilder = new StringBuilder(map[current.y]);
        stringBuilder.setCharAt(current.x, '*');
        map[current.y] = stringBuilder.toString();
    }

    public static void recursionSearcher(MapCondition condition, Point currentPos, ArrayList<String> possibleSteps, String[] map) {


        for (String step : possibleSteps) {
            if (condition.isFinishReached()) break;
            String[] copy = Arrays.copyOf(map, map.length);
            currentPos = stringToPoint(step, currentPos);
            if (map[currentPos.y].charAt(currentPos.x) == 'f') {
                condition.setOriginalMap(Arrays.copyOf(map, map.length));
                condition.setFinishReached(true);
                break;
            }
            writeWay(map, currentPos);
            ArrayList<String> newSteps = possibleSteps(map, currentPos, step);
            recursionSearcher(condition, currentPos, newSteps, map);
            if (!condition.isFinishReached()) {
                currentPos = stringToPointReverse(step, currentPos);
                map = Arrays.copyOf(copy, copy.length);
            }


        }
    }
}
