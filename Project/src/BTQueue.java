// Hussein's Binary Tree
// 26 March 2017
// Hussein Suleman

public class BTQueue<dataType>
{   
   BTQueueNode<dataType> head;
   BTQueueNode<dataType> tail;
      
   public BTQueue ()
   {
      head = null;
      tail = null;
   }
   
   
   /** 
    * @return BinaryTreeNode<dataType>
    */
   public BinaryTreeNode<dataType> getNext ()
   {
      if (head == null)
         return null;
      BTQueueNode<dataType> qnode = head;
      head = head.next;
      if ( head == null )
         tail = null;
      return qnode.node;
   }
   
   
   /** 
    * @param node
    */
   public void enQueue ( BinaryTreeNode<dataType> node )
   {
      if (tail == null)
      {   
         tail = new BTQueueNode<dataType> (node, null);
         head = tail;
      }   
      else
      {
         tail.next = new BTQueueNode<dataType> (node, null);
         tail = tail.next;
      }   
   }   
}
