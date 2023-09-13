import java.util.Arrays;

public class MapCondition {

    private String[] originalMap;
    private boolean isFinishReached = false;

    public MapCondition(String[] map, boolean finish) {
        originalMap = Arrays.copyOf(map, map.length);
        isFinishReached = finish;
    }

    public void setOriginalMap(String[] map) {
        originalMap = Arrays.copyOf(map, map.length);
    }

    public void setFinishReached(boolean finish) {
        isFinishReached = finish;
    }
    public String[] getMap() {
        return originalMap;
    }
    public boolean isFinishReached(){
        return  this.isFinishReached;
    }
}
