package com.keyin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
//controller clas for mapping and tree operations
@Controller

public class TreeController {
    //controller class for managing tree operations
    @Autowired
    private TreeRepo nodeRepository;

    @Autowired
    private TreeStructureRepo treeRepository;
//display the page to enter numbers
    @GetMapping("/numbers")
    public String showEnterNumbersPage() {
        return "enter-numbers";
    }
//process numbers
    @PostMapping("/process-numbers")
    public String processNumbers(@RequestParam("numbers") String numbers, Model model) {
        try {
            // parse input string to get list of numbers
            List<Integer> numberList = parseNumbers(numbers);

            // validate that the list is not empty
            if (numberList.isEmpty()) {
                // You might want to add an error message here
                model.addAttribute("errorMessage", "Please enter valid numbers");
                return "numbers";
            }

            // construct binary search tree and save to database
            TreeStructure tree = constructBinarySearchTree(numberList);
            treeRepository.save(tree);

            // add the tree to the model for display on the enter numbers page
            model.addAttribute("tree", tree);

            // return enter numbers page
            return "numbers";
        } catch (Exception e) {
            // add error message
            model.addAttribute("errorMessage", "An error occurred");
            return "numbers";
        }
    }



//display the page with the old trees
    @GetMapping("/lasttrees")
    public String showPreviousTrees(Model model) {
        List<TreeStructure> trees = treeRepository.findAll();
        model.addAttribute("trees", trees);
        return "lasttrees";
    }

    // helper method to parse the input string into a list of integers
    private List<Integer> parseNumbers(String numbers) {
        return Arrays.stream(numbers.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }

    // helper method to construct a binary search tree from a list of numbers
    private TreeStructure constructBinarySearchTree(List<Integer> numbers) {
        TreeStructure tree = new TreeStructure();
        for (Integer number : numbers) {
            TreeNode node = new TreeNode();
            node.setValue(number);
            node.setTree(tree);
            nodeRepository.save(node);
        }
        return tree;
    }
}
