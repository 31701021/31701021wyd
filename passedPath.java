package test;
import java.util.ArrayList;
import java.util.List;

public class passedPath {
	private node     curNode ;    
	private boolean     visited ;   //�Ƿ��ѱ�����    
	private Integer     weight ;        //�ۻ���Ȩֵ    
	private List<String> passedIDList ; //·��

	 passedPath(node id){    	
		 this.curNode = id;    	
		 this.weight = Integer.MAX_VALUE; //��ʼ��ʱ����ÿ���ڵ㵽Դ�ڵ�ľ���Ϊ�����    	
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
