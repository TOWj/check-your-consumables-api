package com.nov.checkyourconsumablesapi.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "consumables")
public class Consumables {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "person_id")
    private int personId;

    @Column(name = "name")
    @NotEmpty(message = "Field shouldn't be empty!")
    @Size(max = 100, message = "Name shouldn't be greater than 100 chars!")
    private String name;

    @Column(name = "mileage_now")
    @NotEmpty(message = "Field shouldn't be empty!")
    @Min(value = 1, message = "Value should be greater than 0!")
    private int mileageNow;

    @Column(name = "replacement_after")
    @NotEmpty(message = "Field shouldn't be empty!")
    @Min(value = 1, message = "Value should be greater than 0!")
    private int replacementAfter;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private UserInfo ownerUser;

    public Consumables() {
    }

    public Consumables(String name, int mileageNow, int replacementAfter) {
        this.name = name;
        this.mileageNow = mileageNow;
        this.replacementAfter = replacementAfter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMileageNow() {
        return mileageNow;
    }

    public void setMileageNow(int mileageNow) {
        this.mileageNow = mileageNow;
    }

    public int getReplacementAfter() {
        return replacementAfter;
    }

    public void setReplacementAfter(int replacementAfter) {
        this.replacementAfter = replacementAfter;
    }

    public UserInfo getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(UserInfo ownerUser) {
        this.ownerUser = ownerUser;
    }

    @Override
    public String toString() {
        return "Consumables{" +
                "id=" + id +
                ", personId=" + personId +
                ", name='" + name + '\'' +
                ", mileageNow=" + mileageNow +
                ", replacementAfter=" + replacementAfter +
                '}';
    }
}
