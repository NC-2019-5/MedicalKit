package com.netcracker.group5.medkit.model.domain.medicine;

import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.domain.enumeration.TakingMethod;

public class Medicine implements Requestable {

    private long id;
    private String name;
    private String manufacturer;
    private String formOfProduction;
    private String contraindications;
    private String interactions;
    private String packageContent;
    private TakingMethod takingMethod;
    private String description;

    public Medicine(long id, String name, String manufacturer, String formOfProduction, String contraindications, String interactions, String packageContent, TakingMethod takingMethod, String description) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.formOfProduction = formOfProduction;
        this.contraindications = contraindications;
        this.interactions = interactions;
        this.packageContent = packageContent;
        this.takingMethod = takingMethod;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getFormOfProduction() {
        return formOfProduction;
    }

    public void setFormOfProduction(String formOfProduction) {
        this.formOfProduction = formOfProduction;
    }

    public String getContraindications() {
        return contraindications;
    }

    public void setContraindications(String contraindications) {
        this.contraindications = contraindications;
    }

    public String getInteractions() {
        return interactions;
    }

    public void setInteractions(String interactions) {
        this.interactions = interactions;
    }

    public String getPackageContent() {
        return packageContent;
    }

    public void setPackageContent(String packageContent) {
        this.packageContent = packageContent;
    }

    public TakingMethod getTakingMethod() {
        return takingMethod;
    }

    public void setTakingMethod(TakingMethod takingMethod) {
        this.takingMethod = takingMethod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
