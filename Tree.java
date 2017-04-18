/**
 * Created by root on 17-4-17.
 */
import java.util.ArrayList;
import java.util.List;

public class Tree {
    Integer ul_count=0;
    Integer menu_count=0;
    private StringBuffer html = new StringBuffer();
    private List<Node> nodes;

    public Tree(List<Node> nodes){
        this.nodes = nodes;
    }

    public String buildTree(){
        //html.append("<ul>");
        ul_count += 1;
        for (Node node : nodes) {
            String id = node.getId();
            if (node.getParentId() == "") {
                //html.append("\r\n<li id='" + id + "'>" + node.getName()+ "</li>");
                StringBuffer space= new StringBuffer();
                for(int i= 0;i<ul_count*4;i++)
                {
                    space.append(" ");//这里是空格
                }
                //html.append(space + "ParentId:"+node.getParentId()+" "+node.getName()+ "\r\n");
                html.append(space + node.getName()+"(" +node.getParentId()+ ")"+"\r\n");
                build(node);
            }
        }
        ul_count -= 1;
        //html.append("\r\n</ul>");
        return html.toString();
    }

    private void build(Node node){
        List<Node> children = getChildren(node);
        if (!children.isEmpty()) {
            //html.append("\r\n<ul>");
            ul_count += 1;
            StringBuffer space= new StringBuffer();
            for(int i= 0;i<ul_count*4;i++)
            {
                space.append(" ");//这里是空格
            }
            for (Node child : children) {
                String id = child.getId();
                //html.append("\r\n<li id='" + id + "'>" + child.getName()+ "</li>");
                html.append(space + child.getName()+"(" +child.getParentId()+ ")"+"\r\n");
                //html.append(space+ child.getName()+ "\r\n");
                build(child);
            }
            ul_count -= 1;
            //html.append("\r\n</ul>");
        }else {
            menu_count += 1;
        }
    }

    private List<Node> getChildren(Node node){
        List<Node> children = new ArrayList<Node>();
        String id = node.getId();
        for (Node child : nodes) {
            if (id.equals(child.getParentId())) {
                children.add(child);
            }
        }
        return children;
    }
}
