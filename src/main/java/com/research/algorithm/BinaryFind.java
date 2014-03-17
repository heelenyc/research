package com.research.algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BinaryFind {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(0, 1, 2, 3,
				4, 5, 6, 7, 8, 9);
		System.out.println(binaryFind(list, 0, 0, list.size()));
//		Arrays.binarySearch(a, key)
	}

	public static <T extends Comparable<? super T>> int binaryFind(
			List<T> list, T value, int start, int end) {
		if (end - start == 0)
			return -1;
		int i = (start + end) / 2;
		T t = list.get(i);
		if (value.equals(t)) {
			return i;
		} else if (value.compareTo(t) > 0) {
			return binaryFind(list, value, i + 1, end);
		} else {
			return binaryFind(list, value, start, i);
		}
	}

}
