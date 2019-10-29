import java.util.*;
/*
A simple linkedlist that uses a null node instead of null.
 */

public class DList implements Iterable<String> {
    private int count=0;
    @Override
    public Iterator<String> iterator() {
        return new DListIterator();
    }

    private static class DListNode {
        public String data;
        public DListNode next;
        public DListNode previous;
    }

    private DListNode nil;

    public DList() {
        nil = new DListNode();
        nil.previous = nil;
        nil.next = nil;
        nil.data = null;
    }

    public void addFirst(String elem) {
        DListNode Node = new DListNode();
        Node.data = elem;
        if (nil.next.equals(nil) && nil.previous.equals(nil)) {
            nil.previous = Node;
            nil.next=Node;
            Node.next = nil;
            Node.previous=nil;
            count++;
        }
        nil.next.previous = Node;
        Node.next=nil.next;
        nil.next = Node;
        Node.previous = nil;
        count++;
    }

    public void addLast(String elem) {
        if (nil.next.equals(nil) && nil.previous.equals(nil)) {
            addFirst(elem);
        }
        DListNode LastNode = new DListNode();
        LastNode.data = elem;
        LastNode.previous = nil.previous;
        LastNode.previous.next=LastNode;
        LastNode.next=nil;
        nil.previous=LastNode;
        count++;
    }

    public String getFirst() {
        return nil.next.data;
    }

    public String getLast() { return nil.previous.data; }

    public String removeFirst() throws Exception {
        if (nil.next.equals(nil) && nil.previous.equals(nil)) {
            throw new Exception("No Node Available");
        }
        String tempdata=nil.next.data;
        nil.next=nil.next.next;
        nil.next.previous=nil;
        count--;
        return tempdata;
    }
    public String removeLast() throws Exception {
        if(nil.next.equals(nil) || nil.next.next.equals(nil))
            removeFirst();
        String tempdata=nil.previous.data;
        nil.previous=nil.previous.previous;
        nil.previous.next=nil;
        count=count-2;
        return tempdata;
    }
    public String get(int index){
        if(index>count)
            throw new IndexOutOfBoundsException("The index is greater than the capacity");
        String temp="";
        int indexCount=0;
        for(Iterator<String> iter=iterator(); iter.hasNext();){
            indexCount++;
            if(indexCount == index) {
                temp=iter.next();
                return temp;
            }
            iter.next();
            }
        return "Index not found";
        }
    public String set(int index, String value) {
        if (index > count)
            throw new IndexOutOfBoundsException("The index is greater than the capacity");
        int indexCount = 0;
        String temp="";
        DListIterator setValue =new DListIterator();
        for (Iterator<String> iter = iterator(); iter.hasNext(); ) {
            indexCount++;
            if (indexCount == index) {
                temp=iter.next();
                setValue.pointer.data=value;
            }
            iter.next();
        }
        return temp;
    }
    public int indexOf(Object obj){
        if(!(obj instanceof DListNode))
            return -1;
        int count=0;
        for(Iterator<String> iter=iterator();iter.hasNext();){
            count++;
            if(iter.next().equals(obj))
                return count;
        }
        return -1;
    }
    public boolean contains(Object obj){
        return indexOf(obj) != -1;
    }
    public int size(){
        return count;
    }
    class DListIterator implements Iterator<String> {
        private DListNode pointer;

        public DListIterator() {
            if(nil.next ==nil)
                pointer = nil;
            else
                pointer = nil.next;

        }

        @Override
        public boolean hasNext() {
            return !(pointer.equals(nil));
        }
        @Override
        public String next(){
            StringBuilder data= new StringBuilder();
            data.append(pointer.data);
            pointer=pointer.next;
            return data.toString();
        }
    }
}
