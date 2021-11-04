import java.util.ArrayList;

public class Tree {

    private Node root;

    public boolean isEmpty(){
        return getRoot() == null ? true : false;
    }

    public void insert(int value){
        Node node = new Node(value);
        Node lastNode = root;

        if(isEmpty()){
            setRoot(node);
        }else{
            while (true){
                if(value <= lastNode.getValue()){
                    if (lastNode.getLeftNode() == null){
                        lastNode.setLeftNode(node);
                        return;
                    }else{
                        lastNode = lastNode.getLeftNode();
                    }
                }else{
                    if (lastNode.getRightNode() == null){
                        lastNode.setRightNode(node);
                        return;
                    }else{
                        lastNode = lastNode.getRightNode();
                    }
                }
            }
        }
    }

    //FORMATAR
    public ArrayList<Node> search(Integer value){
        ArrayList<Node> nodeArrayList = new ArrayList<>();
        Node currentNode = this.root;
        Node lastNode = null;

        while (currentNode.getValue() != value){
            if (value < currentNode.getValue() && currentNode.getLeftNode() != null){
                lastNode = currentNode;
                currentNode = currentNode.getLeftNode();
            }else if(currentNode.getRightNode() != null){
                lastNode = currentNode;
                currentNode = currentNode.getRightNode();
            }else{
                return null;
            }
        }
        nodeArrayList.add(currentNode);
        nodeArrayList.add(lastNode);
        return nodeArrayList;
    }

    public String deepSearch(Integer value){
        Node node = this.root;
        ArrayList<Node> branchList = new ArrayList();
        StringBuilder sb = new StringBuilder();

        if(node == null){
            return null;
        }
        else{
            while (node.getValue() != value){
                sb.append(node.getValue()+ " ");
                if (node.getLeftNode() != null){

                    if(node.getRightNode() != null){
                        branchList.add(node.getRightNode());
                    }
                    node = node.getLeftNode();
                }
                else if(node.getRightNode() != null){
                    node = node.getRightNode();
                }
                else if(branchList.size()>0){
                    node = branchList.get(branchList.size()-1);
                    branchList.remove(node);
                }
                else  {
                    return  sb.toString();
                }
            }
        }
        sb.append(node.getValue() + " ");
        return sb.toString();
    }

    public String breadthSearch(Integer value){
        ArrayList<Node> branchList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        branchList.add(getRoot());

        if(this.root == null){
            return null;
        }
        else {
            while (!branchList.isEmpty()){
                Node currentNode = branchList.get(0);
                sb.append(currentNode.getValue() + " ");
                if (currentNode.getValue() == value){
                    return sb.toString();
                }
                else{
                    if(currentNode.getLeftNode() != null){
                        branchList.add(currentNode.getLeftNode());
                    }
                    if(currentNode.getRightNode() != null){
                        branchList.add(currentNode.getRightNode());
                    }
                }
                branchList.remove(currentNode);
            }
            return sb.toString();
        }
    }

    public void remove(int value){
        ArrayList<Node> searchNode = search(value);
        Node lastNode, currentNode;

        if(searchNode != null){
            lastNode = searchNode.get(1);
            currentNode = searchNode.get(0);

            if(currentNode.getLeftNode() == null && currentNode.getRightNode() == null ){
                if(currentNode == this.root){
                    setRoot(null);
                }
                if (lastNode.getLeftNode() == (currentNode)){
                    lastNode.setLeftNode(null);
                }
                if (lastNode.getRightNode() == (currentNode)){
                    lastNode.setRightNode(null);
                }
            }


            if (currentNode.getLeftNode() != null && currentNode.getRightNode() == null ){
                if(currentNode == this.root){
                    setRoot(currentNode.getLeftNode());
                }
                else if(lastNode.getLeftNode() == currentNode){
                    lastNode.setLeftNode(currentNode.getLeftNode());
                }else{
                    lastNode.setRightNode(currentNode.getLeftNode());
                }
            }

            if (currentNode.getLeftNode() == null && currentNode.getRightNode() != null){
                if(currentNode == this.root){
                    setRoot(currentNode.getRightNode());
                }
                else if(lastNode.getLeftNode() == currentNode){
                    lastNode.setLeftNode(currentNode.getRightNode());
                }else{
                    lastNode.setRightNode(currentNode.getRightNode());
                }
            }

            if(currentNode.getLeftNode() != null && currentNode.getRightNode() != null ){

                Node tmp = currentNode.getRightNode();

                while (tmp.getLeftNode() != null){
                    tmp = tmp.getLeftNode();
                }

                remove(tmp.getValue());
                if (currentNode != this.root){
                    if(lastNode.getLeftNode() == currentNode){
                        lastNode.setLeftNode(tmp);
                    }else{
                        lastNode.setRightNode(tmp);
                    }
                }else{
                    setRoot(tmp);
                }
                tmp.setLeftNode(currentNode.getLeftNode());
                tmp.setRightNode(currentNode.getRightNode());
            }
        }
    }


    public String printTree(Node node, StringBuilder returnString){
        if(node != null){
            printTree(node.getLeftNode(), returnString);
            returnString.append(node.getValue() + " ");
            printTree(node.getRightNode(), returnString);
        }
        return returnString.toString();
    }

    public String printTree(){
        return printTree(getRoot(), new StringBuilder());
    }


    public String printPreOrder(Node node, StringBuilder returnString ){
        if (node != null){
            returnString.append(node.getValue() + " ");
            printPreOrder( node.getLeftNode(), returnString );
            printPreOrder( node.getRightNode(), returnString);
        }
        return returnString.toString();
    }


    public String printPreOrder(){
        return printPreOrder(getRoot(), new StringBuilder());
    }


    public String printPosOrder(Node node, StringBuilder returnString){
        if (node != null){
            printPosOrder( node.getLeftNode(), returnString);
            printPosOrder( node.getRightNode(), returnString);
            returnString.append(node.getValue() + " ");
        }
        return returnString.toString();
    }


    public String printPosOrder(){
        return printPosOrder(getRoot(), new StringBuilder());
    }

    public int getHeight(){
        return getHeight(getRoot());
    }

    public int getHeight(Node node){
        if(node == null || node.getLeftNode() == null && node.getRightNode() == null){
            return 0;
        }else{
            int heightLeft = getHeight(node.getLeftNode());
            int heightRight = getHeight(node.getRightNode());
            return heightLeft > heightRight ? (1+heightLeft) : (1+heightRight);
        }
    }

    public int nodeNumber(){
        return nodeNumber(getRoot());
    }

    public int nodeNumber(Node node){
        if(node == null || node.getLeftNode() == null && node.getRightNode() == null){
            return 0;
        }else{
            int nodesLeft = 1+nodeNumber(node.getLeftNode());
            int nodesRight = 1+nodeNumber(node.getRightNode());
            return nodesLeft+nodesRight;
        }
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Tree(){
        setRoot(null);
    }
}
