import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    Person[] listOfRelatives;

    public Tree(){
        listOfRelatives = new Person[1000];
    }

    public Tree( Person treeOwner){
        listOfRelatives = new Person[1000];
        listOfRelatives[0] = treeOwner;
    }

    public Person[] getListOfRelatives() {
        return listOfRelatives;
    }
    public void addPartner(Person partner){
        listOfRelatives[1] = partner;
    }
    public void addMother(Person mother){
        listOfRelatives[2] = mother;
     }
    public  void addFather( Person father){
        listOfRelatives[3] = father;
     }
    public void addMotherOfMother(Person grandma){
        listOfRelatives[4] =grandma;
    }
    public  void addMotherOfFather( Person grandpa){
        listOfRelatives[5] = grandpa;
    }
    public void addFatherOfMother(Person grandma){
        listOfRelatives[6] = grandma;
    }
    public  void addFatherOfFather( Person grandpa ){
        listOfRelatives[7] = grandpa;
    }

    /**
     *
     * @param sibling
     * @return
     */
    public boolean addSibling( Person sibling){
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
    public boolean addChild( Person child){
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
        List<Person> thisList = Arrays.asList(listOfRelatives);
        List<Person> otherList =Arrays.asList(personTree.getListOfRelatives());

        for ( int i = 0; i< thisList.size(); i++){
          if(otherList.contains(thisList.get(i)) && thisList.get(i) != null)
              return true;
        }
        return false;
    }
}
