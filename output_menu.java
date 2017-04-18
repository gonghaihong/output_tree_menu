/**
 * Created by root on 17-4-17.
 */

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class output_menu {

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
        List<Node> nodes = new ArrayList<Node>();

        //Node node1 = new Node();
        String line;
        Stream<String> lines = null;
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> menu_txt = new HashMap<>();
        Object id=null;
        Object pid=null;
        Object text=null;


        try (Scanner sc = new Scanner(new File("//home//java_demo//menu_new_one.txt"),"GBK")) {
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                try {
                    menu_txt = mapper.readValue(line, Map.class);
                } catch (JsonParseException e) {
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                id = menu_txt.getOrDefault("id", "Missing value");
                pid= menu_txt.getOrDefault("pId", "Missing value");
                text = menu_txt.getOrDefault("text", "Missing value");
                HashMap dataRecord1 = new HashMap();
                //System.out.printf("id=%s",id.toString());
                //System.out.printf(" pid=%s",pid.toString());
                //System.out.printf(" text=%s%n",text.toString());
                Node node1 = new Node();
                node1.setId(id.toString());
                node1.setName(text.toString());
                node1.setParentId(pid.toString());
                node1.setLink(null);
                nodes.add(node1);
                menu_txt.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Tree tree = new Tree(nodes);
        //System.out.println(tree.buildTree());
        File writename = new File("//home//java_demo//new_ccb_menu.txt");
        if(!writename.exists()){
            writename.createNewFile();
        }
        BufferedWriter out = new BufferedWriter(new FileWriter(writename));
        out.write(tree.buildTree());
        out.write("\n"+"        功能总数： "+tree.menu_count.toString());
        out.flush();
        out.close();
    }
}
