package ca.jrvs.practice.codingChallenge;

import java.util.Map;

public class CompareTwoMaps {

    /*
    Big O: O(n): Loops through and compares all keys and objects
     */
    public <K, V> boolean compareMaps(Map<K, V> m1, Map<K, V> m2) {
        if (m1.equals(m2)) return true;

        return false;
    }

    /*
    Big O: O(n): Loops through and compares all keys and objects
   */
    public <K, V> boolean compareMaps2(Map<K, V> m1, Map<K, V> m2) {
        if (m1 == m2) return true;

        if (m1.isEmpty() || m2.isEmpty() || m1.size() != m2.size()) return false;

        for (K key : m1.keySet()) {
            if (!m1.get(key).equals(m2.get(key))) {
                return false;
            }
        }

        return true;
    }
}
