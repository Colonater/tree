package com.keyin;


import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
//jap entiry
@Entity
public class TreeNode {

   //feilds keys
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int value;

    @ManyToOne
    @JoinColumn(name = "tree_id")
    private TreeStructure tree;
//geters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }
    //access
    public void setValue(int value) {
        this.value = value;
    }
// each node has an associated value
    public void setTree(TreeStructure tree) {
    }
}
