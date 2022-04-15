package duke.expandD;

import java.io.Serializable;
import java.util.*;

public class Contact implements Serializable {
    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
        friends = new ArrayList<>();
        notes = new ArrayList<>();
        debts = new HashMap<>();
        costs = new HashMap<>();
        locationInfo = new HashMap<>();
        trivias = new ArrayList<>();
        customerInfo = new HashMap<>();
        merchandiseInfo = new HashMap<>();
    }
    private String name;
    private String phone;
    private List<Contact>friends;
    private List<String> notes;
    private Map<Contact,Float>debts;//user lend money to someone
    private Map<String,Float>costs;
    private Map<String,String>locationInfo;
    private List<String>trivias;
    private Map<Contact,List<String>>customerInfo;
    private Map<String,String>merchandiseInfo;
    public void addTrivia(String thing){
        trivias.add(thing);
    }

    public List<Contact> getFriends() {
        return friends;
    }
    public void addFriend(Contact contact){
        friends.add(contact);
    }
    public void setFriends(List<Contact> friends) {
        this.friends = friends;
    }

    public List<String> getTrivias() {
        return trivias;
    }

    public Map<Contact, List<String>> getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(Map<Contact, List<String>> customerInfo) {
        this.customerInfo = customerInfo;
    }

    public Map<String, String> getMerchandiseInfo() {
        return merchandiseInfo;
    }

    public void setMerchandiseInfo(Map<String, String> merchandiseInfo) {
        this.merchandiseInfo = merchandiseInfo;
    }

    public Map<String, String> getLocationInfo() {
        return locationInfo;
    }

    public void setLocationInfo(Map<String, String> locationInfo) {
        this.locationInfo = locationInfo;
    }


    public void addNote(String note){
        notes.add(note);
    }

    public void changeDebt(Contact debtor,Float money){
        if (debts.containsKey(debtor)){
            Float current = debts.get(debtor);
            current+=money;
            debts.put(debtor,current);
        }else {
            debts.put(debtor,money);
        }
    }
    public void addCost(String item,Float money){
        costs.put(item,money);
    }
    public void addLocationInfo(String location,String info){
        locationInfo.put(location,info);
    }
    public void addCustomerInfo(Contact contact,String info){
        if (customerInfo.containsKey(contact)){
            customerInfo.get(contact).add(info);
        }else {
            List<String>infos = new ArrayList<>();
            infos.add(info);
            customerInfo.put(contact,infos);
        }
    }
    public void addMerchandise(String good,String info){
        merchandiseInfo.put(good,info);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public Map<Contact, Float> getDebts() {
        return debts;
    }

    public void setDebts(Map<Contact, Float> debts) {
        this.debts = debts;
    }

    public Map<String, Float> getCosts() {
        return costs;
    }

    public void setCosts(Map<String, Float> costs) {
        this.costs = costs;
    }


    public void setTrivias(List<String> trivias) {
        this.trivias = trivias;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", friends=" + friends +
                ", notes=" + notes +
                ", debts=" + debts +
                ", costs=" + costs +
                ", locationInfo=" + locationInfo +
                ", trivias=" + trivias +
                ", customerInfo=" + customerInfo +
                ", merchandiseInfo=" + merchandiseInfo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(name, contact.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
