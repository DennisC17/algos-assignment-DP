/**
 * Glass Falling
 */
public class GlassFalling {

  public int[][] glassTable;

  // Do not change the parameters!
  public int glassFallingRecur(int floors, int sheets) {
    // Fill in here and change the return

    // if there is only 1 floor left, one test is necessary
    // if there are no floors left, no tests are necessary
    if( floors == 1 || floors == 0 ){
      return floors;
    }

    // if there is only 1 sheet of glass, we test bottom up assuming worst case
    if( sheets == 1 ){
      return floors;
    }

    // minimum number of drops should not exceed number of floors
    // this is just to have a maxiumum comparison
    int minimumDrops = floors;
    int numberOfDrops;

    // let i represent the floor we start dropping glass from
    for( int i=1 ; i <= floors ; i++){
      // if the glass shatters on a given floor:
      //    we have to check the floors below (i-1)
      //    and we have one less sheet of glass (sheets-1)
      // if the glass does not shatter:
      //    we have all the floors above it (floors-i)
      //    and no glass was loss (sheets)
      // We are also looking for worst case scenario, thus we return MAX
      // We count the initial drop +1
      numberOfDrops = 1 + Math.max( glassFallingRecur(i-1, sheets-1), glassFallingRecur(floors-i, sheets) );
      // At any given floor, we care about the worse case scenario or the maximum number of drops
      // If that number is less than the minimumDrops of the entire building so far, we save it
      // After the for loop iterates all possible starting points,
      // we should have the minimum number of drops
      if( numberOfDrops < minimumDrops) minimumDrops = numberOfDrops;
    }
    return minimumDrops;
  }

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int glassFallingMemoized(int floors, int sheets) {
    // Fill in here and change the return

    return 0;
  }

  // Do not change the parameters!
  public int glassFallingBottomUp(int floors, int sheets) {
    // Fill in here and change the return
    glassTable = new int [floors+1][sheets+1];

    // for any given number of sheets of glass,
    // if there is only 1 floor, one test is needed
    // if there are no floors, no tests are needed
    for( int i=1; i<= sheets; i++){
      glassTable[1][i] = 1;
      glassTable[0][i] = 0;
    }
    // for any given number of floors
    // if there is only 1 sheet of glass, the number of tests = number of floors
    for( int j=1; j<=floors; j++){
      glassTable[j][1] = j;
    }

    int numberOfDrops;

    for( int f=2 ; f <= floors; f++){
      for( int s=2 ; s <= sheets; s++){
        // glassTable[f][s] is maxed
        glassTable[f][s] = floors;
        for( int drop=1; drop <= f; drop++){
          // iterate through different floors to drop from
          // glassTable[drop-1][s-1]
          //    glass shatters:
          //      we iterate from below drop floor (drop-1)
          //      remaining sheets of glass (s-1)
          //    glass does not shatter:
          //      the number of floors - dropped floor (f-drop)
          numberOfDrops = 1 + Math.max( glassTable[drop-1][s-1], glassTable[f-drop][s]);
          if( numberOfDrops < glassTable[f][s] ) glassTable[f][s] = numberOfDrops;
        }
      }
    }
    return glassTable[floors][sheets];
  }


  public static void main(String args[]){
      GlassFalling gf = new GlassFalling();

      // Do not touch the below lines of code, and make sure
      // in your final turned-in copy, these are the only things printed
      int minTrials1Recur = gf.glassFallingRecur(27, 2);
      int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
      int minTrials2Recur = gf.glassFallingRecur(100, 3);
      int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
      System.out.println(minTrials1Recur + " " + minTrials1Bottom);
      System.out.println(minTrials2Recur + " " + minTrials2Bottom);
  }
}
