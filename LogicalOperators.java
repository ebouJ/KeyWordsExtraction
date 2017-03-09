import java.util.*;
public class LogicalOperators{

  public static void main(String[] args){
    boolean [] a = { false,false, true}; 
    System.out.println(conjunctionIter(a));
    System.out.println(conjunctionRec(a));
    System.out.println(disjunctionIter(a));
    System.out.println(disjunctionRec(a));
  
  }
  
  
  public static boolean conjunctionIter(boolean[] a){
    boolean result = a[0];
    for(int i = 1; i < a.length; i++){
      result = result && a[i];
    }
    return result;
    
  }
  public static boolean conjunctionRec(boolean[]a){
  
    if(a.length == 1){
      
     return a[0]; 
    }else{
      int split = a.length/2;
 //calling a method in the array class to split the given boolean array into two
     boolean[] beg = Arrays.copyOfRange(a,0,split);
     boolean[] end = Arrays.copyOfRange(a,split,a.length);
      //calling a recursive method 
      return conjunctionRec(beg) && conjunctionRec(end);
    }
    
    
  }
  
   
  public static boolean disjunctionIter(boolean[] a){
    //given the first position in the boolean array to result
    boolean result = a[0];
    //looping through
    for(int i = 1; i < a.length; i++){
      result = result || a[i];
    }
    return result;
    
    
  }
  public static boolean disjunctionRec(boolean[] a){
     if(a.length == 1){
      
     return a[0]; 
    }else{
      int split = a.length/2;
//calling a method in the array class to split the given boolean array into two      
     boolean[] beg = Arrays.copyOfRange(a,0,split);
     boolean[] end = Arrays.copyOfRange(a,split,a.length);
  //calling a recursive method    
      return disjunctionRec(beg) || disjunctionRec(end);
    }
  }

  
  
}