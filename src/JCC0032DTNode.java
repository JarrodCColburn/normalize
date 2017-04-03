// JCC0032DTNode.java
// Jarrod Colburn
// Due: 3 Apr 17
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jarrodcolburn
 */
public class JCC0032DTNode {
    private Object contents;
    private List<JCC0032DTNode> children;
    
    public JCC0032DTNode(){
        this.children = new ArrayList<JCC0032DTNode>();
    }
    public JCC0032DTNode getThis(){
        return this;
    }
    public Object getContents(){
        return this.contents;
    }
    public void setContents(Object object){
        this.contents = object;
    }
    public void addChildren(List<JCC0032DTNode> children){
        this.children.addAll(children);
    }
    public void addChildren(JCC0032DTNode... child){
        for(JCC0032DTNode c: child){
            this.children.add(c);
        }
    }
    public List<JCC0032DTNode> getChildren(){
        return this.children;
    }
    @Override
    public String toString(){
        return this.contents.toString();
    }
}
