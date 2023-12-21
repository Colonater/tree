//represent node sturture of tree

package com.keyin;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@Entity
public class TreeStructure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "tree", cascade = CascadeType.ALL)
    private List<TreeNode> nodes;
//getters and setters
    public List<TreeNode> getNodes() {
        return nodes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
