package duke.expandD;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class ExParser {
    private Manager manager;
    public void exit(){
        manager.exit();
    }
    public ExParser() {
        this.manager = new Manager();
    }
    private String mergeList(List<String>list,int i){
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < list.size(); j++) {
            if (j<i)continue;
            sb.append(list.get(j));
            sb.append(" ");
        }
        return sb.toString();
    }
    private boolean checkList(List<String>list){
        return list.size()>0 ? true:false;
    }
    public void parse(List<String>list){
        assert list != null;
        if (!checkList(list))return;
        boolean result = false;
        result |= parseCreate(list);
        result |=  parseAdd(list);
        result |=  parseShow(list);
        if (!result){
            printError();
        }
    }
    private boolean parseCreate(List<String>list){
        if (!list.get(0).equalsIgnoreCase("create"))return false;
        manager.addContact(new Contact(list.get(1),list.get(2)));
        printSuccess();
        return true;
    }

    private boolean parseAdd(List<String>list){
        assert list.size()<= 1;
        if (!list.get(0).equalsIgnoreCase("add"))return false;
        if (list.get(1).equalsIgnoreCase("friend")){
            Contact user =  manager.getUserByName(list.get(2));
            Contact friend = manager.getUserByName(list.get(3));
            manager.addUserFriend(user,friend);
        }else if (list.get(1).equalsIgnoreCase("note")){
            Contact user =  manager.getUserByName(list.get(2));
            manager.addUserNote(user,mergeList(list,3));
        }else if (list.get(1).equalsIgnoreCase("cost")){
            Contact user =  manager.getUserByName(list.get(2));
            manager.addUserCost(user,list.get(3),Float.parseFloat(list.get(4)));
        }else if (list.get(1).equalsIgnoreCase("loan")){
            Contact user =  manager.getUserByName(list.get(2));
            Contact friend = manager.getUserByName(list.get(3));
            manager.addUserLoan(user,friend,Float.parseFloat(list.get(4)));
        }else if (list.get(1).equalsIgnoreCase("location")){
            Contact user =  manager.getUserByName(list.get(2));
            String loc = list.get(3);
            manager.addUserLocation(user,loc,mergeList(list,4));
        }else if (list.get(1).equalsIgnoreCase("trivia")){
            Contact user =  manager.getUserByName(list.get(2));
            manager.addUserTrivia(user,mergeList(list,3));
        }else if (list.get(1).equalsIgnoreCase("customer")){
            Contact user =  manager.getUserByName(list.get(2));
            Contact friend = manager.getUserByName(list.get(3));
            manager.addCustomerInfo(user,friend,mergeList(list,4));
        }else if (list.get(1).equalsIgnoreCase("merchandise")){
            Contact user =  manager.getUserByName(list.get(2));
            String goods = list.get(3);
            manager.addMerchandiseInfo(user,goods,mergeList(list,4));
        }else {
            return false;
        }
        printSuccess();
        return true;
    }
    private boolean parseShow(List<String>list){
        assert list.size()<=1;
        if (!list.get(0).equalsIgnoreCase("show"))return false;
        if (list.get(1).equalsIgnoreCase("friend")){
            Contact user =  manager.getUserByName(list.get(2));
            System.out.println(manager.getUserFriend(user));
        }else if (list.get(1).equalsIgnoreCase("note")){
            Contact user =  manager.getUserByName(list.get(2));
            System.out.println(user.getNotes());
        }else if (list.get(1).equalsIgnoreCase("cost")){
            Contact user =  manager.getUserByName(list.get(2));
            System.out.println(user.getCosts());
        }else if (list.get(1).equalsIgnoreCase("loan")){
            Contact user =  manager.getUserByName(list.get(2));
            System.out.println(user.getDebts());
        }else if (list.get(1).equalsIgnoreCase("location")){
            Contact user =  manager.getUserByName(list.get(2));
            System.out.println(user.getLocationInfo());
        }else if (list.get(1).equalsIgnoreCase("trivia")){
            Contact user =  manager.getUserByName(list.get(2));
            System.out.println(user.getTrivias());
        }else if (list.get(1).equalsIgnoreCase("customer")){
            Contact user =  manager.getUserByName(list.get(2));
            System.out.println(user.getCustomerInfo());
        }else if (list.get(1).equalsIgnoreCase("merchandise")){
            Contact user =  manager.getUserByName(list.get(2));
            System.out.println(user.getMerchandiseInfo());
        }else {
            return false;
        }
        printSuccess();
        return true;
    }
    private void printError(){
        System.out.println("sorry ,I don't know what you mean");
    }
    private void printSuccess(){
        System.out.println("your command is executed !");
    }
}
