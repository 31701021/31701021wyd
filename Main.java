package test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
	
	static List<passedPath> paths = null;
	static List<String> nears=null;	
	static List<node> nodes=null;
	static List<node> nodes1=null;
	static passedPath to=null;
	
	public static void main(String[] args) {
		nears=new ArrayList<String>();	
		nodes=new ArrayList<node>();
		nodes1=new ArrayList<node>();
		
		for(int i=0;i<args.length;i++) {
			if(args[i].equals("-map")) {
				try {
					initnodes(args[i+1]);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if(args[i].equals("-b")) {
				search(args[i+1],args[i+2]);
			}
			if(args[i].equals("-o")) {
				try {
					write(args[i+1]);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(args[i].equals("-a")){
				searchline(args[i+1]);
			}
		}
	}
	
	public static void initnodes(String path) throws IOException {		
		InputStreamReader reader = new InputStreamReader (new FileInputStream(path),"UTF-8");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line =bufferedReader.readLine();//读入线路
        while (line!=null){
            int num =Integer.parseInt(bufferedReader.readLine());//读入线路站数
            String prenode=null;
            String curnode=bufferedReader.readLine();
            for(int i=1;i<=num;i++) {
            	node node=new node();
                node.setId(curnode);
                node.getLine().add(line);
                nodes1.add(node);
            	if(prenode!=null)
            		nears.add(prenode);
            	prenode=curnode;
            	if(i!=num) {
	            	curnode=bufferedReader.readLine();
	            	nears.add(curnode);
            	}
            	init(prenode,nears,line);
            	nears.clear();
            }
            line=bufferedReader.readLine();
        }
        bufferedReader.close();
        reader.close();
        System.out.println("读取成功");
	}
	
	public static void init(String id,List<String> nears,String line){
		int flag=0;
		int index=0;
		for(node node:nodes) {
			if(node.getId().equals(id)) {
				flag=1;
				index=nodes.indexOf(node);
			}
		}
		if(flag==0) {
			node tmp = new node();		
			tmp.setId(id);
			tmp.getLine().add(line);
			edge tmp_ = null;		
			List<edge> edges = new ArrayList<edge>();		
			for(String near:nears){			
				tmp_ = new edge();			
				tmp_.setStartNodeId(id);			
				tmp_.setEndNodeId(near);			
				edges.add(tmp_);		
			}		
			tmp.setEdges(edges);		
			nodes.add(tmp);
		}
		else {
			node node =nodes.get(index);
			node.getLine().add(line);
			edge tmp_ = null;
			for(String near:nears){			
				tmp_ = new edge();			
				tmp_.setStartNodeId(id);			
				tmp_.setEndNodeId(near);			
				node.getEdges().add(tmp_);		
			}		
		}
	}

	public static void initPath(List<node> V,String V0){		
		paths = new ArrayList<passedPath>();		
		passedPath path = null;		
		for(node c:V){			
			if(c.getId().equals(V0)){				
				path = new passedPath(c);				
				path.setWeight(0);				
				List<String> tmp = new ArrayList<String>();				
				tmp.add(V0);				
				path.setPassedIDList(tmp);		
			}else{				
				path = new passedPath(c);			
			}			
			paths.add(path);		
		}	
	}
	
	public static node searchNodeById(String id) {
		for(node node:nodes) {
			if(node.getId().equals(id))
				return node;
		}
		return null;
	}
	
	public static void search(String start,String end) {
		initPath(nodes,start);
		run(nodes);		
		node tmp=null;		
		for(node d:nodes){			
			if(d.getId().equals(end)){				
				tmp = d;
			}		
		}		
		for(passedPath e:paths){
			if(e.getCurNode().equals(tmp)){
				to = e;
			}		
		}
		String line=null;
		for(String line1:searchNodeById(to.getPassedIDList().get(0)).getLine()) {
			for(String line2:searchNodeById(to.getPassedIDList().get(1)).getLine()) {
				if(line1.equals(line2)) line=line1;
			}
		}
		System.out.println("乘坐"+line);
		for(String f:to.getPassedIDList()){
			int fg=0;
			for(String line1:searchNodeById(f).getLine()) {
				if(line1.equals(line)) fg=1;
			}
			if(fg==0) {
				for(String line1:searchNodeById(f).getLine()) {
					for(String line2:nodes.get(nodes.indexOf(searchNodeById(f))-1).getLine())
						if(line1.equals(line2)) line=line1;
				}
				System.out.println();
				System.out.println("换乘"+line);
			}
			if(to.getPassedIDList().indexOf(f)==0)
				System.out.print(f);
			else {
				System.out.print("-->"+f);
			}
		}	
		System.out.println();
		System.out.println("总共"+to.getWeight()+"站");
	}
	
	public static void write(String path) throws IOException {
		File file =new File(path);
        if(!file.exists()) {
        	file.getParentFile();        
    	}
    	file.createNewFile();
		Writer out =new FileWriter(file);
		String line=null;
		for(String line1:searchNodeById(to.getPassedIDList().get(0)).getLine()) {
			for(String line2:searchNodeById(to.getPassedIDList().get(1)).getLine()) {
				if(line1.equals(line2)) line=line1;
			}
		}
		out.write("乘坐"+line+"\r\n");
		for(String f:to.getPassedIDList()){
			int fg=0;
			for(String line1:searchNodeById(f).getLine()) {
				if(line1.equals(line)) fg=1;
			}
			if(fg==0) {
				line=searchNodeById(f).getLine().get(0);
				out.write("\r\n");
				out.write("换乘"+line);
				out.write("\r\n");
			}
			if(to.getPassedIDList().indexOf(f)==0)
				out.write(f);
			else {
				out.write("-->"+f);
			}
		}	
		out.write("\r\n");
		out.write("总共"+to.getWeight()+"站");
		out.close();
	}
	
	public static void searchline(String line) {
		System.out.println(line+": ");
		for(node node:nodes1) {
			if(node.getLine().get(0).equals(line))
				System.out.print(node.getId()+" ");
		}
	}
	
	public static void run(List<node> V){		
		passedPath min = new passedPath();//使用无参数的		
		int flag=0;		
		for(passedPath c:paths){
			if(!c.isVisited()){
				if(c.getWeight()<min.getWeight()){
					min = c;
				}				
				flag++;
			}		
		}		
		if(flag==0) return;		
		//用min去更新可达节点的path		
		for(edge c:min.getCurNode().getEdges()){
			//根据目标节点名找到目标节点的passedPath:to		
			node tmp=null;				
			for(node d:V){
				if(d.getId().equals(c.getEndNodeId())){
					tmp = d;
				}				
			}				
			passedPath to=null;				
			for(passedPath e:paths){
				if(e.getCurNode().equals(tmp)){
					to = e;
				}				
			}
			if(to.getWeight()>c.weight+min.getWeight()){
				List<String> tmpList = new ArrayList<String>(min.getPassedIDList());
				tmpList.add(to.getCurNode().getId());
				to.setPassedIDList(tmpList);               //更新路径列表					
				to.setWeight(c.weight+min.getWeight());    //更新累积权值				
				}		
			}		
		min.setVisited(true);		
		run(V);	
	}
	
}
