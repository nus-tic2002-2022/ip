package duke.expandD;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Manager implements Serializable {
    private List<Contact>list;
    public void exit(){
        try {
            FileOutputStream fileOut = new FileOutputStream("data/D_data.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Manager() {
        try{
            FileInputStream fileIn = new FileInputStream("data/D_data.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.list =(List<Contact>) in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (list==null){
            this.list=new ArrayList<>();
        }
    }

    public void addContact(Contact contact){
        list.add(contact);
    }
    public void changeContact(int i,Contact contact){
        list.set(i,contact);
    }

    public void removeContact(int i){
        list.remove(i);
    }
    public Contact getContact(int i){
        return list.get(i);
    }
    public void addUserNote(Contact contact,String note){
        for (Contact user :
                list) {
            if (user.equals(contact)){
                user.addNote(note);
            }
        }
    }
    public Contact getUserByName(String name){
        for (Contact user :
                list) {
            if (user.getName().equals(name)){
                return user;
            }
        }
        return null;
    }
    public void addUserCost(Contact user,String item,Float money){
        for (Contact contact :
                list) {
            if (contact.equals(user)){
                contact.addCost(item, money);
            }
        }
    }
    public void addUserLoan(Contact user,Contact debtor,Float money){
        for(Contact contact:list){
            if (contact.equals(user)){
                contact.changeDebt(debtor,money);
            }
        }
    }
    public void addUserLocation(Contact user,String location,String info){
        for(Contact contact:list){
            if (contact.equals(user)){
                contact.addLocationInfo(location,info);
            }
        }
    }
    public void addUserTrivia(Contact user,String thing){
        for(Contact contact:list){
            if (contact.equals(user)){
                contact.addTrivia(thing);
            }
        }
    }
    public void addCustomerInfo(Contact user,Contact customer,String info){
        for(Contact contact:list){
            if (contact.equals(user)){
                contact.addCustomerInfo(customer,info);
            }
        }
    }
    public void addMerchandiseInfo(Contact user,String good,String info){
        for(Contact contact:list){
            if (contact.equals(user)){
                contact.addMerchandise(good, info);
            }
        }
    }
    public void addUserFriend(Contact user,Contact friend){
        for(Contact contact:list){
            if (contact.equals(user)){
                contact.addFriend(friend);
            }
        }
    }
    public List<Contact> getUserFriend(Contact user){
        for(Contact contact:list){
            if (contact.equals(user)){
                return contact.getFriends();
            }
        }
        return null;
    }
}
