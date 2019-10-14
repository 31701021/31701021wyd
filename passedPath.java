package test;
import java.util.ArrayList;
import java.util.List;

public class passedPath {
	private node     curNode ;    
	private boolean     visited ;   //是否已被处理    
	private Integer     weight ;        //累积的权值    
	private List<String> passedIDList ; //路径

	 passedPath(node id){    	
		 this.curNode = id;    	
		 this.weight = Integer.MAX_VALUE; //初始化时假设每个节点到源节点的距离为无穷大    	
		 this.passedIDList = new ArrayList<String>();    	
		 this.visited = false;    
	 }        
	 
	 passedPath(){    	
		 this.curNode = null;    	
		 this.weight = Integer.MAX_VALUE;    	
		 this.passedIDList = new ArrayList<String>();    	
		 this.visited = false;    
	 }

	public node getCurNode() {
		return curNode;
	}

	public void setCurNode(node curNode) {
		this.curNode = curNode;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public List<String> getPassedIDList() {
		return passedIDList;
	}

	public void setPassedIDList(List<String> passedIDList) {
		this.passedIDList = passedIDList;
	}
}
