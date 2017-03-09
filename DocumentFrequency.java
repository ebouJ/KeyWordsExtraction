import java.util.*;
import java.io.*;

public class DocumentFrequency {
  
  public static void main(String[] args) {
    String dir = args[0]; // name of directory with input files
    HashMap<String, Integer> dfs;
    dfs = extractDocumentFrequencies(dir, 40);
    writeDocumentFrequencies(dfs, "freqs.txt");
  }
  
  public static HashMap<String, Integer> extractDocumentFrequencies(String directory, int nDocs) {
     HashMap<String, Integer> frequency= new HashMap<String, Integer>();
     //looping through all the 40 docs
        for(int i=1; i<=nDocs; i++){
        String filename = directory + "/" + i + ".txt";   
     //calling the extractWordsFromDocument which returns a hashset
        HashSet<String> set = extractWordsFromDocument(filename);
       //using an iterator to iterate through the hastset called set 
        Iterator<String> q = set.iterator();
        while(q.hasNext()){
          String key = q.next();
          if(frequency.containsKey(key)){
          int temp = frequency.get(key);
           temp++;
          frequency.put(key,temp);
        
        }
         else{
           frequency.put(key,1);
        }  
        }
        
     }
     return frequency;
  }
  
  
  public static HashSet<String> extractWordsFromDocument(String filename) {
      HashSet<String> extract= new HashSet<String>();
      try{
         FileReader fr=new FileReader(filename);
         BufferedReader bw=new BufferedReader(fr);
         String line=bw.readLine();
           while(line!=null){
             
              String[] s=line.split(" ");//calling the split method

              for(int i=0; i<s.length; i++){
               //calling the normalize method 
                   s[i]=normalize(s[i]);
                //adding it to my HashSet  
                   extract.add(s[i]);
              }
              line=bw.readLine();
           }
           bw.close();
           fr.close();
      }
      catch(IOException e){
      }
      
      return extract;
  }
  
  
  public static void writeDocumentFrequencies(HashMap<String, Integer> dfs, String filename) {
          ArrayList<String> keys=new ArrayList<String>();
          for(String key: dfs.keySet() ){
              keys.add(key);
         }
            Collections.sort(keys);
    try{ 
   //another way of getting the keys   
       /*Iterator<String> q = dfs.keySet().iterator();
        while(q.hasNext()){
        keys.add(q.next()); 
      } */
      Collections.sort(keys);
       FileWriter fr=new FileWriter(filename);
       BufferedWriter bw=new BufferedWriter(fr);
       /*
       for(int i = 0; i < keys.size();i++){
         bw.write(keys.get(i) + " " + dfs.get(keys.get(i))); 
         bw.newLine();
       }*/
  //using an iterator to iterate through the arrayList     
       Iterator<String> iter = keys.iterator();
       while(iter.hasNext()){
        String k = iter.next();
        bw.write(k + " " + dfs.get(k));
        bw.newLine();
         
       }
       
       
       
       bw.close();
       fr.close();
       
    }
    catch(IOException e){}
  }
  
  /*
   * This method "normalizes" a word, stripping extra whitespace and punctuation.
   * Do not modify.
   */
  public static String normalize(String word) {
    return word.replaceAll("[^a-zA-Z ']", "").toLowerCase();
  }
  
}