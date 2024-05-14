package tech.vault.server.infra.util;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@UtilityClass
public class ArraysForOneArray {
    public static String[] combineArrays(String[] firstArray, String[] secondArray, String[] thirdArray) {
        List<String> combinedList = new ArrayList<>();

        // NOTE: Adiciona a lista todos os valores dos outros arrays
        Collections.addAll(combinedList, firstArray);
        Collections.addAll(combinedList, secondArray);
        Collections.addAll(combinedList, thirdArray);

        // NOTE: Transforma a lista em array
        return combinedList.toArray(new String[0]);
    }
}
