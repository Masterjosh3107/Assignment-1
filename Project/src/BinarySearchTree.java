// Hussein's Binary Search Tree
// 27 March 2017
// Hussein Suleman

import java.util.*;

public class BinarySearchTree<dataType extends Comparable<? super dataType>> extends BinaryTree<dataType>
{
   
   /** 
    * @param d
    */
   public void insert ( dataType d )
   {
      if (root == null)
         root = new BinaryTreeNode<dataType> (d, null, null);
      else
         insert (d, root);
   }
   
   /** 
    * @param d
    * @param node
    */
   public void insert ( dataType d, BinaryTreeNode<dataType> node )
   {
      if (d.compareTo (node.data) <= 0)
      {
         if (node.left == null)
            node.left = new BinaryTreeNode<dataType> (d, null, null);
         else
            insert (d, node.left);
      }
      else
      {
         if (node.right == null)
            node.right = new BinaryTreeNode<dataType> (d, null, null);
         else
            insert (d, node.right);
      }
   }
   
   
   /** 
    * @param d
    * @return BinaryTreeNode<dataType>
    */
   public BinaryTreeNode<dataType> find ( dataType d )
   {
      if (root == null)
         return null;
      else
         return find (d, root);
   }
   
   /** 
    * @param d
    * @param node
    * @return BinaryTreeNode<dataType>
    */
   public BinaryTreeNode<dataType> find ( dataType d, BinaryTreeNode<dataType> node )
   {
      if (d.compareTo (node.data) == 0) 
         return node;
      else if (d.compareTo (node.data) < 0)
         return (node.left == null) ? null : find (d, node.left);
      else
         return (node.right == null) ? null : find (d, node.right);
   }
   
   
   /** 
    * @param d
    */
   public void delete ( dataType d )
   {
      root = delete (d, root);
   }   
   
   /** 
    * @param d
    * @param node
    * @return BinaryTreeNode<dataType>
    */
   public BinaryTreeNode<dataType> delete ( dataType d, BinaryTreeNode<dataType> node )
   {
      if (node == null) return null;
      if (d.compareTo (node.data) < 0)
         node.left = delete (d, node.left);
      else if (d.compareTo (node.data) > 0)
         node.right = delete (d, node.right);
      else if (node.left != null && node.right != null )
      {
         node.data = findMin (node.right).data;
         node.right = removeMin (node.right);
      }
      else
         if (node.left != null)
            node = node.left;
         else
            node = node.right;
      return node;
   }
   
   
   /** 
    * @param node
    * @return BinaryTreeNode<dataType>
    */
   public BinaryTreeNode<dataType> findMin ( BinaryTreeNode<dataType> node )
   {
      if (node != null)
         while (node.left != null)
            node = node.left;
      return node;
   }

   
   /** 
    * @param node
    * @return BinaryTreeNode<dataType>
    */
   public BinaryTreeNode<dataType> removeMin ( BinaryTreeNode<dataType> node )
   {
      if (node == null)
         return null;
      else if (node.left != null)
      {
         node.left = removeMin (node.left);
         return node;
      }
      else
         return node.right;
   }
   
   
   /** 
    * @return List<BinaryTreeNode<dataType>>
    */
   // Custom made function
   public List<BinaryTreeNode<dataType>> inOrderTraversal() {
      List<BinaryTreeNode<dataType>> nodeList = new ArrayList<>();
      inOrderTraversal(root, nodeList);
      return nodeList;
  }

   
   /** 
    * @param nodeList
    */
   // Recursive helper function for in-order traversal
   private void inOrderTraversal(BinaryTreeNode<dataType> node, List<BinaryTreeNode<dataType>> nodeList) {
         if (node != null) {
            inOrderTraversal(node.left, nodeList);
            nodeList.add(node);
            inOrderTraversal(node.right, nodeList);
         }
   }
}