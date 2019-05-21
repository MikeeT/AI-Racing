import java.util.ArrayList;

class connectionHistory {
    int fromNode;
    int toNode;
    int innovationNumber;

    ArrayList<Integer> innovationNumbers = new ArrayList<Integer>();//the innovation Numbers from the connections of the genome which first had this mutation 
    //this represents the genome and allows us to test if another genoeme is the same
    //this is before this connection was added

    //---------------------------------------------------------------------------------------------------------------------------------------------------------
    //constructor
    connectionHistory(int from, int to, int inno, ArrayList<Integer> innovationNos) {
        fromNode = from;
        toNode = to;
        innovationNumber = inno;
        innovationNumbers = (ArrayList)innovationNos.clone();
    }
}