package com.netcracker.group5.medkit.model.dto.medicine;

public class MedicineRequestItem {
    private String name;
    private String manufacturer;
    private String productionForm;
    private String contraindications;
    private String interactions;
    private String packageContent;
    private String takingMethod;
    private String description;

    public MedicineRequestItem() {
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

    public String getProductionForm() {
        return productionForm;
    }

    public void setProductionForm(String productionForm) {
        this.productionForm = productionForm;
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

    public String getTakingMethod() {
        return takingMethod;
    }

    public void setTakingMethod(String takingMethod) {
        this.takingMethod = takingMethod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MedicineRequestItem{" +
                "name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", productionForm='" + productionForm + '\'' +
                ", contraindications='" + contraindications + '\'' +
                ", interactions='" + interactions + '\'' +
                ", packageContent='" + packageContent + '\'' +
                ", takingMethod='" + takingMethod + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
