package GeneralInfo;
import java.util.Arrays;
import java.util.List;

import Person.*;
  /*
  // 0= Tree owner
  // 1= partner( wife/husband)
  // 2= mother
  // 3= father
  // 4= grand mother( from mother)
  // 5= grand father( from mother)
  // 6= grand mother( from fahter)
  // 7= grand father( from father)
  // 10 to 19 siblings
  // 20 to 29 children
  */

/**
 * Tree class
 * @author Yusuf Doğan
 * @version 23/04/2021
 */

public class Tree {
    //properties
    Patient[] listOfRelatives;

    public Tree(){
        listOfRelatives = new Patient[1000];
    }

    public Tree( Patient treeOwner){
        listOfRelatives = new Patient[1000];
        listOfRelatives[0] = treeOwner;
    }

    public Patient[] getListOfRelatives() {
        return listOfRelatives;
    }
    public void addPartner(Patient partner){
        listOfRelatives[1] = partner;
    }
    public void addMother(Patient mother){
        listOfRelatives[2] = mother;
     }
    public  void addFather( Patient father){
        listOfRelatives[3] = father;
     }
    public void addMotherOfMother(Patient grandma){
        listOfRelatives[4] =grandma;
    }
    public  void addMotherOfFather( Patient grandpa){
        listOfRelatives[5] = grandpa;
    }
    public void addFatherOfMother(Patient grandma){
        listOfRelatives[6] = grandma;
    }
    public  void addFatherOfFather( Patient grandpa ){
        listOfRelatives[7] = grandpa;
    }

    /**
     *
     * @param sibling
     * @return
     */
    public boolean addSibling( Patient sibling){
        for( int i=10; i <20; i++ ){
            if(listOfRelatives == null){
                listOfRelatives[i]= sibling;
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param child
     * @return
     */
    public boolean addChild( Patient child){
        for( int i=20; i <30; i++ ) {
            if(listOfRelatives == null){
                listOfRelatives[i]= child;
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param personTree
     * @return
     */
    public boolean isRelative(Tree personTree){
        List<Patient> thisList = Arrays.asList(listOfRelatives);
        List<Patient> otherList =Arrays.asList(personTree.getListOfRelatives());

        for ( int i = 0; i< thisList.size(); i++){
          if(otherList.contains(thisList.get(i)) && thisList.get(i) != null)
              return true;
        }
        return false;
    }
}
