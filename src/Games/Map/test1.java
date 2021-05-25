package Games.Map;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class test1 {
    public static void main(String[] args) {
        Map<Double, String> paiming = new TreeMap<>();
        paiming.put(10.0, "a");
        paiming.put(20.0, "b");
        paiming.put(8.0, "c");
        Set<Double> keySet = paiming.keySet();
        Iterator<Double> iter = keySet.iterator();
        while (iter.hasNext()) {
            Double key = iter.next();
            System.out.println(key + ":" + paiming.get(key));
        }
    }
    }

