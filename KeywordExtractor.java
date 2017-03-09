import java.util.*;
import java.io.*;

public class KeywordExtractor {
  public static void main(String[] args) {
    String dir = args[0]; // name of directory with input files
    HashMap<String, Integer> dfs;
    dfs = readDocumentFrequencies("freqs.txt");
    
    for(int i = 1; i <= 40; i++){
     String file = dir + "/" + i + ".txt"; 
      HashMap<String,Integer> tfs = computeTermFrequencies(file);
      HashMap<String,Double> tfidf = computeTFIDF(tfs,dfs,40);
      
      System.out.println(i + ".txt");
      
      printTopKeywords(tfidf,5);
      
      System.out.println();
      
    }
    
  }
  
  public static HashMap<String, Integer> computeTermFrequencies(String filename) {
       HashMap<String, Integer> termFrequency= new HashMap<String, Integer>();
  //reading from the file and putting the content into termFrequency     
       try{
       FileReader fr=new FileReader(filename);
       BufferedReader br=new BufferedReader(fr);
 //reading line by line from the input file      
       String line=br.readLine();
       while(line!=null){
         String[] a = line.split(" ");
         
         for(int i = 0; i < a.length; i++){
           String d = DocumentFrequency.normalize(a[i]);
  //computing and putting it into the hashMap         
           if(termFrequency.containsKey(d)){
            int f = termFrequency.get(d);
            f++;
            termFrequency.remove(d);
            termFrequency.put(d,f);
           }else{
             termFrequency.put(d,1); 
           }
           
         }
        line = br.readLine(); 
         
         
       }
       br.close();
       fr.close();
       }
       catch(IOException e){}
       return termFrequency;
  }
  
  public static HashMap<String, Integer> readDocumentFrequencies(String filename) {
    HashMap<String, Integer> read= new HashMap<String, Integer>();
    try{
    FileReader fr=new FileReader(filename);
    BufferedReader br=new BufferedReader(fr);
    String line=br.readLine();
    while(line!=null){
//using the substring method from the string class to get the word and it's corresponding value      
      String word = line.substring(0, line.indexOf(' '));
      int number = Integer.parseInt(line.substring(line.indexOf(' ')+1));
    
      read.put(word,number);
      line=br.readLine(); 
    }
    br.close();
    fr.close();
    }
    catch(IOException e){
    }
    return read;
  }
  
  public static HashMap<String, Double> computeTFIDF(HashMap<String, Integer> tfs, HashMap<String, Integer> dfs, 
                                                     double nDocs) {
       HashMap<String, Double> compute= new HashMap<String, Double>();
       Iterator<String> s = tfs.keySet().iterator();
       while(s.hasNext()){
          String k = s.next();
      //computing the idf and tf    
          
          double idf = Math.log(nDocs/dfs.get(k));
          double tf = tfs.get(k);
          
          compute.put(k,tf*idf);
         
       }
       
       return compute;
  }
  
  /**
   * This method prints the top K keywords by TF-IDF in descending order.
   */
  public static void printTopKeywords(HashMap<String, Double> tfidfs, int k) {
    ValueComparator vc =  new ValueComparator(tfidfs);
    TreeMap<String, Double> sortedMap = new TreeMap<String, Double>(vc);
    sortedMap.putAll(tfidfs);
    
    int i = 0;
    for(Map.Entry<String, Double> entry: sortedMap.entrySet()) {
      String key = entry.getKey();
      Double value = entry.getValue();
      
      System.out.println(key + " " + value);
      i++;
      if (i >= k) {
        break;
      }
    }
  }  
}

/*
 * This class makes printTopKeywords work. Do not modify.
 */
class ValueComparator implements Comparator<String> {
    
    Map<String, Double> map;
    
    public ValueComparator(Map<String, Double> base) {
      this.map = base;
    }
    
    public int compare(String a, String b) {
      if (map.get(a) >= map.get(b)) {
        return -1;
      } else {
        return 1;
      } // returning 0 would merge keys 
    }
}