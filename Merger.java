/**
  Represent a merge operation for sorted lists,
  as described in README.md
 */
import java.util.ArrayList;

public class Merger {

    ArrayList<String> usersData;

    /**
      Construct an instance from a list of data
      part of which is to be merged. See README
     */
    public Merger( ArrayList<String> list) {
        usersData = list;
    }


    /**
      Merge the sorted sub-lists.
     */
    public void merge(
      // indexes of sub-list boundaries; see README
        int start0  // index of first item in list0
      , int start1  // index of first item in list1
                    // = just past end of list0
      , int end1    // index past end of list1
      ) {
        ArrayList<String> workData = new ArrayList<>();
        //add padding to front of merged
        int startTracker = 0;
        while(startTracker < start0) {
          workData.add(usersData.get(startTracker));
          startTracker++;
        }
        int listZeroIter = start0;
        int listOneIter = start1;
        for(;;) {
          if(listZeroIter == start1) {
            mergeAll(listOneIter, end1, workData);
            break;
          }
          if(listOneIter == end1) {
            mergeAll(listOneIter, start1, workData);
            
            break;
          }
          if(usersData.get(listZeroIter).compareTo(usersData.get(listOneIter)) < 0) {
            workData.add(usersData.get(listZeroIter));
            listZeroIter++;
          }
          else {
            workData.add(usersData.get(listOneIter));
            listOneIter++;
          }

        }
        for(int endTracker = end1; endTracker < usersData.size(); endTracker++) {
          workData.add(usersData.get(endTracker));
        }
        usersData = workData;
        
    }

    private void mergeAll(int start, int end, ArrayList<String> list) {
      for(int i = start; i < end; i++) {
        list.add(usersData.get(i));
      }
    }


    /**
      @return a string representation of the user's data
     */
    public String toString() {
        return "" + usersData; 
    }

    
    /** 
      @return the boolean value of the statement
         "the data in the range are in ascending order"
     */
    public boolean isSorted( int startAt, int endBefore) {
        for( int i = startAt
           ; i < endBefore -1 // stop early, because comparing to next
           ; i++
           )
            if( usersData.get(i).compareTo( usersData.get(i+1)) > 0) {
                System.out.println( "trouble between position " + i 
                                  + ", which holds " + usersData.get(i)
                                  + ", and position " + (i +1)
                                  + ", which holds " + usersData.get(i +1)
                                  );
                return false;
            }
        return true;
    }
}