package test;
import java.util.ArrayList;
import java.util.List;

public class node {
	private String id;
	private List<String> line;
	private List<edge> edges;
	
	node(){
		line=new ArrayList<>();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getLine() {
		return line;
	}
	public void setLine(List<String> line) {
		this.line = line;
	}
	public List<edge> getEdges() {
		return edges;
	}
	public void setEdges(List<edge> edges) {
		this.edges = edges;
	}
}

