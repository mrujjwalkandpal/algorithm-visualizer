package com.project.algorithm_visualizer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.algorithm_visualizer.dto.AlgorithmRequest;
import com.project.algorithm_visualizer.dto.AlgorithmResponse;
import com.project.algorithm_visualizer.model.SortSteps;
import com.project.algorithm_visualizer.startegy.AlgorithmStrategy;

@Service("linear-search")
public class LinearSearch implements AlgorithmStrategy {

    @Override
    public AlgorithmResponse visualize(AlgorithmRequest request) {
        List<Integer> numbers = new ArrayList<>(request.getNumbers());
        List<SortSteps> steps = new ArrayList<>();
        Integer target = request.getTarget();

        if (target == null) {
            steps.add(new SortSteps(new ArrayList<>(numbers), new int[] {}, "ERROR",
                    "Target value is missing. Please provide a target integer."));
            return new AlgorithmResponse(steps, 0, "O(n)", 0, numbers.size());
        }

        steps.add(new SortSteps(new ArrayList<>(numbers), new int[] {}, "START",
                "Linear Search started: scanning array for target " + target + "."));

        int comparisons = 0;
        int foundIndex = -1;

        for (int i = 0; i < numbers.size(); i++) {
            comparisons++;
            steps.add(new SortSteps(new ArrayList<>(numbers), new int[] { i }, "COMPARISON",
                    "Comparing target " + target + " with element " + numbers.get(i) + " at index " + i + "."));

            if (numbers.get(i).equals(target)) {
                foundIndex = i;
                steps.add(new SortSteps(new ArrayList<>(numbers), new int[] { i }, "FOUND",
                        "Target " + target + " found at index " + i + "."));
                break;
            }
        }

        if (foundIndex < 0) {
            steps.add(new SortSteps(new ArrayList<>(numbers), new int[] {}, "FINISH",
                    "Target " + target + " was not found after " + comparisons + " comparisons."));
        } else {
            steps.add(new SortSteps(new ArrayList<>(numbers), new int[] { foundIndex }, "FINISH",
                    "Success! Found target at index " + foundIndex + " after " + comparisons + " comparisons."));
        }

        return new AlgorithmResponse(steps, 0, "O(n)", comparisons, numbers.size());
    }
}
