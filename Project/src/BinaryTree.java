// Hussein's Binary Tree
// 26 March 2017
// Hussein Suleman

import java.util.ArrayList;
import java.util.List;

public class BinaryTree<dataType>
{
   BinaryTreeNode<dataType> root;
   
   public BinaryTree ()
   {
      root = null;
   }
   
   
   /** 
    * @return int
    */
   public int getHeight ()
   {
      return getHeight (root);
   }   
   
   /** 
    * @param node
    * @return int
    */
   public int getHeight ( BinaryTreeNode<dataType> node )
   {
      if (node == null)
         return -1;
      else
         return 1 + Math.max (getHeight (node.getLeft ()), getHeight (node.getRight ()));
   }
   
   
   /** 
    * @return int
    */
   public int getSize ()
   {
      return getSize (root);
   }   
   
   /** 
    * @param node
    * @return int
    */
   public int getSize ( BinaryTreeNode<dataType> node )
   {
      if (node == null)
         return 0;
      else
         return 1 + getSize (node.getLeft ()) + getSize (node.getRight ());
   }
   
   
   /** 
    * @param node
    */
   public void visit ( BinaryTreeNode<dataType> node )
   {
      System.out.println (node.data);
   }
   
   public void preOrder ()
   {
      preOrder (root);
   }
   
   /** 
    * @param node
    */
   public void preOrder ( BinaryTreeNode<dataType> node )
   {
      if (node != null)
      {
         visit (node);
         preOrder (node.getLeft ());
         preOrder (node.getRight ());
      }   
   }

   public void postOrder ()
   {
      postOrder (root);
   }
   
   /** 
    * @param node
    */
   public void postOrder ( BinaryTreeNode<dataType> node )
   {
      if (node != null)
      {
         postOrder (node.getLeft ());
         postOrder (node.getRight ());
         visit (node);
      }   
   }

   public void inOrder ()
   {
      inOrder (root);
   }
   
   /** 
    * @param node
    */
   public void inOrder ( BinaryTreeNode<dataType> node )
   {
      if (node != null)
      {
         inOrder (node.getLeft ());
         visit (node);
         inOrder (node.getRight ());
      }   
   }

   
   /** 
    * @param nodeList
    */
   //Custom levelOrder
   public void levelOrder (List<BinaryTreeNode<dataType>> nodeList)
   {
      if (root == null)
         return;
      BTQueue<dataType> q = new BTQueue<dataType> ();
      q.enQueue (root);
      BinaryTreeNode<dataType> node;
      while ((node = q.getNext ()) != null)
      {
         nodeList.add(node);
         if (node.getLeft () != null)
            q.enQueue (node.getLeft ());
         if (node.getRight () != null)
            q.enQueue (node.getRight ());
      }
   }

   
   /** 
    * @return List<BinaryTreeNode<dataType>>
    */
   public List<BinaryTreeNode<dataType>> levelOrder(){
      List<BinaryTreeNode<dataType>> nodeList = new ArrayList<>();
      levelOrder(nodeList);
      return nodeList;
   }

}