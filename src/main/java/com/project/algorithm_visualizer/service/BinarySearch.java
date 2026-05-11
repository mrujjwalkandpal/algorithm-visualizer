package com.project.algorithm_visualizer.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.algorithm_visualizer.dto.AlgorithmRequest;
import com.project.algorithm_visualizer.dto.AlgorithmResponse;
import com.project.algorithm_visualizer.model.SortSteps;
import com.project.algorithm_visualizer.startegy.AlgorithmStrategy;

@Service("binary-search")
public class BinarySearch implements AlgorithmStrategy {

    @Override
    public AlgorithmResponse visualize(AlgorithmRequest request) {
        List<Integer> numbers = new ArrayList<>(request.getNumbers());
        List<SortSteps> steps = new ArrayList<>();
        Integer target = request.getTarget();

        if (target == null) {
            steps.add(new SortSteps(new ArrayList<>(numbers), new int[] {}, "ERROR",
                    "Target value is missing. Please provide a target integer."));
            return new AlgorithmResponse(steps, 0, "O(log n)", 0, numbers.size());
        }

        // Sort the array first for binary search
        Collections.sort(numbers);
        steps.add(new SortSteps(new ArrayList<>(numbers), new int[] {}, "SORT",
                "Array sorted for binary search: " + numbers.toString()));

        steps.add(new SortSteps(new ArrayList<>(numbers), new int[] {}, "START",
                "Binary Search started: searching for target " + target + " in sorted array."));

        int left = 0;
        int right = numbers.size() - 1;
        int comparisons = 0;
        int foundIndex = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            comparisons++;

            steps.add(new SortSteps(new ArrayList<>(numbers), new int[] { mid }, "COMPARISON",
                    "Comparing target " + target + " with middle element " + numbers.get(mid) + " at index " + mid + "."));

            if (numbers.get(mid).equals(target)) {
                foundIndex = mid;
                steps.add(new SortSteps(new ArrayList<>(numbers), new int[] { mid }, "FOUND",
                        "Target " + target + " found at index " + mid + "."));
                break;
            } else if (numbers.get(mid) < target) {
                left = mid + 1;
                steps.add(new SortSteps(new ArrayList<>(numbers), new int[] { mid }, "SEARCH_RIGHT",
                        "Target " + target + " is greater than " + numbers.get(mid) + ", searching right half."));
            } else {
                right = mid - 1;
                steps.add(new SortSteps(new ArrayList<>(numbers), new int[] { mid }, "SEARCH_LEFT",
                        "Target " + target + " is less than " + numbers.get(mid) + ", searching left half."));
            }
        }

        if (foundIndex < 0) {
            steps.add(new SortSteps(new ArrayList<>(numbers), new int[] {}, "FINISH",
                    "Target " + target + " was not found after " + comparisons + " comparisons."));
        } else {
            steps.add(new SortSteps(new ArrayList<>(numbers), new int[] { foundIndex }, "FINISH",
                    "Success! Found target at index " + foundIndex + " after " + comparisons + " comparisons."));
        }

        return new AlgorithmResponse(steps, 0, "O(log n)", comparisons, numbers.size());
    }
}